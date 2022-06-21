package base;

import test.data.TestRailAPISetup;
import test.data.TestRailConfigAnnotation;
import utils.*;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

@Listeners({BaseListener.class})
public class BaseTest {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public WebDriver driver;
    private String suiteName;
    private static String env;
    public String testName;
    public String browserName;
    protected APIClient client;
    protected HashMap data;
    public static String testRailUserName = "serg.lishko1988@gmail.com";
    public static String testRailPassword = "a0.4zajeI2r8vcjTX3Of";
    public static String testRailUrl = "https://sergtesterqa.testrail.io/";
    protected String testSuiteName;

    public static WebDriver getWebDriver() {
        return driverThreadLocal.get();
    }

    @Parameters({"PROJECT"})
    @BeforeSuite
    public void startTestRun(ITestContext testContext, @Optional("1")String PROJECT_ID) {
        client = new APIClient(testRailUserName);
        client.setUser(testRailPassword);
        client.setPassword(testRailUrl);
        data = new HashMap();
        data.put("inclide_all", true);
        data.put("name", "Test run " + System.currentTimeMillis());

    }

    @BeforeClass
    public synchronized void beforeClass(ITestContext testContext) {
        suiteName = testContext.getCurrentXmlTest().getSuite().getName();
        ExtentTest extentTest = ExtentReportManager.getiInstanceOfExtentReports(suiteName).createTest(getClass().getName());
        parentTest.set(extentTest);
    }

    @Parameters({"browser"})
    @BeforeTest
    public void setBrowserAndEnv(@Optional("chrome")String browser) {
        browserName = browser;
        driver = createDriver(browser);
        driver.get(AppConfig.startURL);
    }

    @Parameters({"browser"})
    @BeforeMethod
    public void beforeMethosSetup(Method method, ITestContext context, @Optional("opera")String browserName) {
        ExtentTest extentTest = parentTest.get().createNode(method.getName());
        test.set(extentTest);
        testName = method.getName();
        Reporter.log("Method - " + testName + " - has started");
        if(method.isAnnotationPresent(TestRailConfigAnnotation.class)) {
            TestRailConfigAnnotation configAnnotation = method.getAnnotation(TestRailConfigAnnotation.class);
            context.setAttribute("caseId", configAnnotation.id());
        }
    }

    @AfterMethod
    public void afterMethodSetup(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.get().fail(result.getThrowable());
            String exeptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
            test.get().fail("<details><summry><b><font color = red>Exeption Occured, click to see details: "
                    + "</font></b></summary>" + exeptionMessage.replaceAll(",", "<br>") + "</details> \n");
            String pathsSecond = takeScreenShot("Failure ScreenShot", result.getMethod().getMethodName());
            test.get().fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>",
                    MediaEntityBuilder.createScreenCaptureFromPath(pathsSecond).build());
            utils.Reporter.logOnFail("Test has failed");
        } else if(result.getStatus() == ITestResult.SKIP)
            test.get().skip(result.getThrowable());
        else
            test.get().pass("Test has passed");
            ExtentReportManager.getiInstanceOfExtentReports(suiteName).flush();
            Reporter.log("Begin stopping tests");
    }

    @Parameters({"browser"})
    @AfterTest
    public void killDriver(@Optional("chrome")String browserName) {
        if(driver != null) {
            driver.close();
        }
        driverThreadLocal.remove();
    }

    public static ThreadLocal<ExtentTest> getTest() {
        return test;
    }

    public static WebDriver getDriver() {

        return driverThreadLocal.get();
    }

    public static String getENV() {
        return env;
    }

    public WebDriver createDriver(String browserName) {
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driverThreadLocal.set(new ChromeDriver());
                break;
            case "opera":
                WebDriverManager.operadriver().setup();
                driverThreadLocal.set(new OperaDriver());
                break;
            default:
                System.out.println("Failed to start a browser");
        }
        return driverThreadLocal.get();
    }

    public void putResultInTestRail(String testCaseId, ITestResult testResult) {
        if(!testCaseId.equals("0")) {
            Map data = new HashMap();
            if(testResult.getStatus() == ITestResult.FAILURE){
                data.put("status_id", 5);
                data.put("comment", "Fail");
            } else if(testResult.getStatus() == ITestResult.SKIP){
                data.put("status_id", 5);
                data.put("comment", "Skipped");
            } else {
                data.put("status_id", 1);
                data.put("comment", "Passed");
            }
            try {
                TestRailAPISetup.getClientInstance().sendPost("add_result_for_case/" + /*testRailRunId*/  "/"
                        + testCaseId, data);
            } catch (IOException | APIExeption e) {
                e.printStackTrace();
            }
        }
    }

    protected String takeScreenShot(String fileName, String methodName) {
        WebDriver augmentDriver = new Augmenter().augment(getWebDriver());
        File scrFile = ((TakesScreenshot) augmentDriver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + File.separator + "target" + File.separator + "screenshots" +
                File.separator + getTodayDate() + File.separator + methodName + File.separator +
                getSystemTime() + " " + fileName + ".png";
        String paths = "target" + File.separator + "screenshots" +
                File.separator + getTodayDate() + File.separator + methodName + File.separator +
                getSystemTime() + " " + fileName + ".png";

        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paths;
    }

    private static String getTodayDate() {
        return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    public String getSystemTime() {
        return (new SimpleDateFormat("HHmmssSS").format(new Date()));
    }

    protected List<LogEntry> getBrowserLog() {
        LogEntries entries = driver.manage().logs().get("browser");
        List<LogEntry> logEntries = entries.getAll();
        return logEntries;
    }

}
