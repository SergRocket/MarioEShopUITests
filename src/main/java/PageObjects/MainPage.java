package PageObjects;

import Base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    @FindBy(css = "div.logo>img")
    private WebElement logo;
    @FindBy(css = "#switch-lang")
    private WebElement langDropDown;
    @FindBy(css = "a>span.icon-icons_muzi-07")
    private WebElement favButton;
    @FindBy(css = "div.enter-block.enter-block-last>a")
    private WebElement enterToAccount;
    @FindBy(css = "div.shop-cart-head>a")
    private WebElement shoppingCart;
    @FindBy(css = "catalog main-novelty")
    private WebElement newModels;
    @FindBy(css = "search-input")
    private WebElement searchField;
    @FindBy(css = "div.navbar.navigation-collapse>ul>li")
    private WebElement navMenu;
    @FindBy(css = "div.navbar.navigation-collapse>ul>li:nth-child(2)")
    private WebElement mansShoesPage;

}
