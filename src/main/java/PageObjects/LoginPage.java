package PageObjects;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(css = "#usernameLogin")
    private WebElement usernameField;
    @FindBy(css = "#loginPasswordLabel")
    private WebElement passwordField;
    @FindBy(css = "form.loginLoginForm>div:nth-child(7)>button")
    private WebElement loginButton;
    @FindBy(css = "div.text-center>h1>small")
    private WebElement loginFormName;
    @FindBy(css = "div.footer-menu-title>a")
    private WebElement footerMenuLinks;
    @FindBy(css = "div.logo>a")
    private WebElement logoAndLink;
    @FindBy(css = "div.text-error")
    private WebElement errorMsgAtLogin;

    public By brandImage = new By.ByCssSelector("img[src*='media/logo']");

}
