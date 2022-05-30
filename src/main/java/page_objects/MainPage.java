package page_objects;

import base.BasePage;
import utils.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainPage extends BasePage {

    public By logomainPage = new By.ByCssSelector("div.logo>img");
    public By logo = new By.ByCssSelector("div.logo>a");
    public By favButton = new By.ByCssSelector("a>span.icon-icons_muzi-07");
    public By langDropDown = new By.ByCssSelector("#switch-lang");
    public By enterToAccount = new By.ByCssSelector("div.enter-block.enter-block-last>a");
    public By shoppingCart = new By.ByCssSelector("div.shop-cart-head>strong");
    public By newModels = new By.ByCssSelector("a[href*='novinki']>span");
    public By searchField = new By.ByCssSelector("search-input");
    public By navMenu = new By.ByCssSelector("div.navbar.navigation-collapse>ul>li");
    public By mansShoesPage = new By.ByCssSelector("div.navbar.navigation-collapse>ul>li:nth-child(2)");
    public By congraImage = new By.ByCssSelector("div.row-profile-img");
    public By logoBig = new By.ByCssSelector("div.logo");




    public boolean isCompanyLogoVisible() {
        Reporter.log("Checking if logo is shown ");
        if (isDysplayed(logo)) {
            return true;
        } else
            return false;
    }

    public boolean isCompanyLogoVisib(){
        Reporter.log("Checking if logo is shown ");
        if (isDysplayed(logomainPage)) {
            return true;
        } else
            return false;
    }

    public boolean isCongraIsVisible(){
        Reporter.log("Checking if welcome msg is shown ");
        if(isDysplayed(congraImage)){
            return true;
        } else
            return false;
    }

    public void openMainPageAfterLogin(){
        Reporter.log("Checking if suc login is shown ");
        clickWebElement(logoBig);
        //clickWebElement(logo);
    }

    public List<String> isAllWebElementsAreVisible(){
        Reporter.log("Checking if all main elements are shown ");
        List<String> elemtsTest = new ArrayList<>();
        elemtsTest.add(driver.findElement(langDropDown).getText().toLowerCase());
        elemtsTest.add(driver.findElement(shoppingCart).getText().toLowerCase());
        elemtsTest.add(driver.findElement(newModels).getText().toLowerCase());
        return elemtsTest;
    }

    public RandomCategoryPage selectRandomCategory(){
        Reporter.log("Selecting random int to select random menu section ");
        Random random = new Random();
        int randomCat = random.nextInt(2 - 1) + 1;
        findwebElement(By.cssSelector(" ul.nav-navigation>li:nth-child("+randomCat+")")).click();
        return new RandomCategoryPage();
    }

    public List<WebElement> findElements(By element){
        Reporter.log("Getting list of web elements " + element);
        return driver.findElements(element);}
}
