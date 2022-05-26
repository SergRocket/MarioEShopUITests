package Base;

import Utils.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.security.mscapi.CPublicKey;

import java.time.Duration;
import java.util.List;

public class BasePage {

    private WebDriver driver;
    private WebDriverWait webDriverWaitLonger;
    private WebDriverWait webDriverWaitShorter;
    private FluentWait<WebDriver> fluentWait;


    public BasePage(){
        this.driver = BaseTest.getWebDriver();
        webDriverWaitLonger = new WebDriverWait(driver, Duration.ofSeconds(12));
        webDriverWaitShorter = new WebDriverWait(driver, Duration.ofSeconds(2));
        fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(7)).pollingEvery(Duration.ofMillis(700))
                .ignoring(NoSuchElementException.class);
    }

    public WebElement findwebElement(By element){
        Reporter.log("Getting web element " + element);
        return driver.findElement(element);
    }

    public List<WebElement> findWebElements(By element){
        Reporter.log("Getting the list of web elements " + element);
        return driver.findElements(element);
    }

    public void clickWebElement(By element){
        Reporter.log("Clicking on the web element " + element);
        findwebElement(element).click();
    }

    public void clickWebElementByIndex(By elements, int webElementIndex){
        Reporter.log("Clicking webElement by index " + webElementIndex + " from the list of webElements");
        driver.findElements(elements).get(webElementIndex -1).click();
    }

    public String getWebElementText(By element){
        Reporter.log("Extracting webElement text ");
        String text = findwebElement(element).getText();
        Reporter.log("Element text is "+text);
        return text;
    }

    public String getWebElementAttributeValue(By element, String attribute){
        Reporter.log("Extracting the value from the webElement");
        String givenValue = findwebElement(element).getAttribute(attribute);
        Reporter.log("The "+attribute+" from "+element+" is "+givenValue);
        return givenValue;
    }
}
