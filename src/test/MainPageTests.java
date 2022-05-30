import base.BaseTest;
import page_objects.MainPage;
import test_data.TestRailConfigAnnotation;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MainPageTests extends BaseTest {
    @TestRailConfigAnnotation(id="3")
    @Test(testName = "NavMenuTest",priority=3)
    public void checkAllItemElements() {
        SoftAssert softAssert = new SoftAssert();
        MainPage mainPage = new MainPage();
        mainPage.selectRandomCategory();
        softAssert.assertTrue(mainPage.isCompanyLogoVisib());

    }
}
