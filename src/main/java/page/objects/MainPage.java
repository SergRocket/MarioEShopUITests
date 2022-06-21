package page.objects;

import base.BasePage;
import utils.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainPage extends BasePage {

    private By logomainPage = new By.ByCssSelector("div.logo>img");
    private By logo = new By.ByCssSelector("div.logo>a");
    private By favButton = new By.ByCssSelector("a>span.icon-icons_muzi-07");
    private By langDropDown = new By.ByCssSelector("#switch-lang");
    private By enterToAccount = new By.ByCssSelector("div.enter-block.enter-block-last>a");
    private By shoppingCart = new By.ByCssSelector("div.shop-cart-head>strong");
    private By newModels = new By.ByCssSelector("a[href*='novinki']>span");
    private By searchField = new By.ByCssSelector("search-input");
    private By navMenu = new By.ByCssSelector("div.navbar.navigation-collapse>ul>li");
    private By mansShoesPage = new By.ByCssSelector("div.navbar.navigation-collapse>ul>li:nth-child(2)");
    private By congraImage = new By.ByCssSelector("div.row-profile-img");
    private By logoBig = new By.ByCssSelector("div.logo");




    public boolean isCompanyLogoVisible() {
        Reporter.log("Checking if logo is shown ");
        return isDysplayed(logo);
    }

    public boolean isCompanyLogoVisib() {
        Reporter.log("Checking if logo is shown ");
        return isDysplayed(logomainPage);
    }

    public boolean isCongraIsVisible() {
        Reporter.log("Checking if welcome msg is shown ");
        return isDysplayed(congraImage);
    }

    public void openMainPageAfterLogin() {
        Reporter.log("Checking if suc login is shown ");
        clickWebElement(logoBig);
    }

    public List<String> isAllWebElementsAreVisible() {
        Reporter.log("Checking if all main elements are shown ");
        List<String> elemtsTest = new ArrayList<>();
        elemtsTest.add(driver.findElement(langDropDown).getText().toLowerCase());
        elemtsTest.add(driver.findElement(shoppingCart).getText().toLowerCase());
        elemtsTest.add(driver.findElement(newModels).getText().toLowerCase());
        return elemtsTest;
    }

    public RandomCategoryPage selectRandomCategory() {
        Reporter.log("Selecting random int to select random menu section ");
        Random random = new Random();
        int randomCat = random.nextInt(2 - 1) + 1;
        findwebElement(By.cssSelector(" ul.nav-navigation>li:nth-child(" + randomCat + ")")).click();
        return new RandomCategoryPage();
    }

    public List<WebElement> findElements(By element) {
        Reporter.log("Getting list of web elements " + element);
        return driver.findElements(element);}
}
