package page.objects;

import java.util.List;
import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.Reporter;

public class FeedPage extends BasePage {
    private By dropDownArrow = new By.ByCssSelector("span.MuiIconButton-label>svg.MuiSvgIcon-root>path[d*='M7']");
    private By options = new By.ByCssSelector("#size-small-standard-multi");

    private By option1 = new By.ByCssSelector("form>div:nth-child(9)");
    private By option2 = new By.ByCssSelector("ul>li:nth-child(3)");
    private By option3 = new By.ByCssSelector("span>input[value*='ProductTypes']");

    public void selectRandomOptionFromDropDown(){

    }

    public void activateDropDown(){
            Reporter.log("Checking if suc login is shown ");
            clickWebElement(dropDownArrow);
            driver.findElement(options).sendKeys(Keys.ARROW_DOWN);
            driver.findElement(options).sendKeys(Keys.ENTER);
      }

    public void getWindow() throws InterruptedException {
        driver.get("https://splunk.internal.shutterfly.com/");
        Thread.sleep(4500);

    }


    //Select select = new Select(driver.findElement(By.id("oldSelectMenu")));

    // Get all the options of the dropdown
   // List<WebElement> options = select.getOptions();

}

