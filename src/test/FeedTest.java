import base.BaseTest;
import org.apache.poi.ss.formula.functions.T;
import org.testng.annotations.Test;
import page.objects.FeedPage;

public class FeedTest extends BaseTest {
    @Test
    public void clickDropDown() throws InterruptedException {
        FeedPage feedPage = new FeedPage();
        feedPage.getWindow();
    }
}
