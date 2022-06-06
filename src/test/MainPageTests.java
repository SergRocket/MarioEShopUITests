import Base.BaseTest;
import PageObjects.MainPage;
import PageObjects.RandomCategoryPage;
import TestData.TestRailConfigAnnotation;
import Utils.AppConfig;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MainPageTests extends BaseTest {

    @TestRailConfigAnnotation(id="5")
    @Test(testName = "NavMenuTest",priority=3)
    public void checkAllItemElements() {
        SoftAssert softAssert = new SoftAssert();
        MainPage mainPage = new MainPage();
        mainPage.selectRandomCategory();
        softAssert.assertTrue(mainPage.isCompanyLogoVisib());
    }
}
