import Base.BaseTest;
import PageObjects.MainPage;
import TestData.TestRailConfigAnnotation;
import Utils.AppConfig;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginPageTests extends BaseTest {

    @Test(testName = "LoginTest",priority=1)
    public void validLogin(){
        SoftAssert softAssert = new SoftAssert();
        MainPage mainPage = new MainPage();
        mainPage.validLog();
        softAssert.assertTrue(mainPage.isCompanyLogoVisible());
        softAssert.assertTrue(mainPage.isCongraIsVisible());
        softAssert.assertTrue(mainPage.containsUrl(AppConfig.expectedURLAfterLogin));
        softAssert.assertAll();
    }


    @Test(testName = "NavMenuTest",priority=2)
    public MainPage navMenu() {
        SoftAssert softAssert = new SoftAssert();
        MainPage mainPage = new MainPage();
        softAssert.assertTrue(mainPage.isCompanyLogoVisible());
        mainPage.openMainPageAfterLogin();
        softAssert.assertTrue(mainPage.isAllWebElementsAreVisible().stream().filter
        (str -> str.contains(AppConfig.textInElements)).allMatch(s ->s.length()>3));
        return new MainPage();
    }
}
