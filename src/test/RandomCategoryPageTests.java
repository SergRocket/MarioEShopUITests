import Base.BasePage;
import Base.BaseTest;
import PageObjects.MainPage;
import PageObjects.RandomCategoryPage;
import TestData.TestRailConfigAnnotation;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RandomCategoryPageTests  extends BaseTest {

    @Test(testName = "checkAllItemElements",priority=4)
    public void checkAllItemElements() {
        SoftAssert softAssert = new SoftAssert();
        MainPage mainPage = new MainPage();
        RandomCategoryPage randomCategoryPage = new RandomCategoryPage();
        mainPage.selectRandomCategory();
        randomCategoryPage.getValues();
        softAssert.assertTrue(randomCategoryPage.getValues().stream().anyMatch(x->x.contains("Додати у кошик")));
        softAssert.assertTrue(randomCategoryPage.getValues().stream().anyMatch(x->x.contains("Додати в список бажань")));
        softAssert.assertTrue(randomCategoryPage.getValues().stream().anyMatch(x->x.contains("Жіночі")));
        softAssert.assertTrue(randomCategoryPage.getValues().stream().anyMatch(x->x.contains("Арт:")));
        softAssert.assertTrue(randomCategoryPage.getValues().stream().anyMatch(x->x.contains("грн")));
        softAssert.assertAll();
    }


    @Test(testName = "checkOldPriceBiggerToNew",priority=5)
    public void checkOldPriceBiggerToNew() {
        SoftAssert softAssert = new SoftAssert();
        RandomCategoryPage randomCategoryPage = new RandomCategoryPage();
        randomCategoryPage.getValues();
        softAssert.assertTrue(randomCategoryPage.getValues().stream().anyMatch(x->x.contains("Додати у кошик")));
        softAssert.assertTrue(randomCategoryPage.getValues().stream().anyMatch(x->x.contains("Додати в список бажань")));
        softAssert.assertTrue(randomCategoryPage.getValues().stream().anyMatch(x->x.contains("Жіночі")));
        softAssert.assertTrue(randomCategoryPage.getValues().stream().anyMatch(x->x.contains("Арт:")));
        softAssert.assertTrue(randomCategoryPage.getValues().stream().anyMatch(x->x.contains("грн")));
        softAssert.assertTrue(randomCategoryPage.getValuesPrices());
        softAssert.assertAll();
    }

}
