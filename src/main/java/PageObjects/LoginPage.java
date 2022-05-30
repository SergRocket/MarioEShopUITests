package PageObjects;

import Base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(css = "#usernameLogin")
    private WebElement usernameField;
    @FindBy(css = "#loginPasswordLabel")
    private WebElement passwordField;
    @FindBy(css = "form.loginLoginForm>div:nth-child(7)>button")
    private WebElement creationForm;
    @FindBy(css = "div.text-center>h1>small")
    private WebElement loginFormName;
    @FindBy(css = "div.footer-menu-title>a")
    private WebElement footerMenuLinks;
    @FindBy(css = "div.logo>a")
    private WebElement logoAndLink;

}
