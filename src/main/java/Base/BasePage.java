package Base;

import Utils.AppConfig;
import Utils.Reporter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    private WebDriverWait webDriverWaitLonger;
    private WebDriverWait webDriverWaitShorter;
    private FluentWait<WebDriver> fluentWait;


    public BasePage(){
        this.driver = BaseTest.getWebDriver();
        webDriverWaitLonger = new WebDriverWait(driver, Duration.ofSeconds(12));
        webDriverWaitShorter = new WebDriverWait(driver, Duration.ofSeconds(2));
        fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(7)).pollingEvery(Duration.ofMillis(700))
                .ignoring(NoSuchElementException.class);
    }

    public WebElement findwebElement(By element){
        Reporter.log("Getting web element " + element);
        return driver.findElement(element);
    }

    public List<WebElement> findWebElements(By element){
        Reporter.log("Getting the list of web elements " + element);
        return driver.findElements(element);
    }

    public void clickWebElement(By element){
        Reporter.log("Clicking on the web element " + element);
        findwebElement(element).click();
    }

    public void clickWebElementByIndex(By elements, int webElementIndex){
        Reporter.log("Clicking webElement by index " + webElementIndex + " from the list of webElements");
        driver.findElements(elements).get(webElementIndex -1).click();
    }

    public String getWebElementText(By element){
        Reporter.log("Extracting webElement text ");
        String text = findwebElement(element).getText();
        Reporter.log("Element text is "+text);
        return text;
    }

    public String getWebElementAttributeValue(By element, String attribute){
        Reporter.log("Extracting the value from the webElement");
        String givenValue = findwebElement(element).getAttribute(attribute);
        Reporter.log("The "+attribute+" from "+element+" is "+givenValue);
        return givenValue;
    }

    public void validLog() {
        Reporter.log("Clicking on the login button after the page is loaded ");
        driver.findElement(By.cssSelector("div.enter-block.enter-block-last>a")).click();
        Reporter.log("Sending valid userName to the input field ");
        driver.findElement(By.id("usernameLogin")).sendKeys(AppConfig.login);
        Reporter.log("Sending valid password to the input field ");
        driver.findElement(By.id("loginPasswordLabel")).sendKeys(AppConfig.password);
        Reporter.log("Clicking the login button ");
        driver.findElement(By.cssSelector("form.loginLoginForm>div:nth-child(7)>button")).click();
    }

    public List<WebElement> findElements(By element){
        Reporter.log("Getting list of web elements " + element);
        return driver.findElements(element);}

    public boolean isDysplayed(By element){
        Reporter.log("Checking if element is shown ");
        if (findElements(element).size() >0) {
            return true;
        } else
            return false;
    }

    public void scrollWithJSToElement(WebElement element){
        Reporter.log("Scrolling to element with JS");
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public List<String> getTextFromListOfElements(By element){
        Reporter.log("Getting text from elements");
        List<String> stringList = new ArrayList<>();
        List<WebElement> elements = findElements(element);
        for (WebElement elemt : elements){
            stringList.add(elemt.getText());
        }
        return stringList;
    }

  /*  public String getElementAttrValue(By element, String attribute){
        Reporter.log("Extracting set value from element");
       // List<String> givenValue = clickWebElement(element).getAttribute(attribute);
        Reporter.log("The " + attribute + " from " + element + " is " + givenValue);
      //  return givenValue;
    }*/


    public boolean containsUrl (String str){
        Reporter.log("Checking that url contains " + str);
        boolean currentUrl = driver.getCurrentUrl().contains(str);
        return currentUrl;
    }
}
