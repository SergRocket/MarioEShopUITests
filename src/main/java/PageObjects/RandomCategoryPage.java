package PageObjects;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RandomCategoryPage extends BasePage {
    public By logomainPage = new By.ByCssSelector("div.logo>img");
    public By pageTilte = new By.ByCssSelector("h1.title");
    public By productsBlock = new By.ByCssSelector("div[id='products']");
    public By productsCards = new By.ByCssSelector("div.short-description-values");
    public By productsOldPrices = new By.ByCssSelector("div.price-old");
    public By productsCurrentPrices = new By.ByCssSelector("div.price.shk-price");
    public By productsDescrArticle = new By.ByCssSelector("div.h2>a[href*='catalog']");
    public By productsSizes = new By.ByCssSelector("div.size-row");
    public By pagination = new By.ByCssSelector("div.pagination-row");

    public int getQntProductCarts(){
        int elemt = driver.findElements(productsCards).size();
        return elemt;
    }

    public List< String > getValues() {
        List<String> allProductData = new ArrayList<>();
        driver.findElements(productsBlock)
                .stream()
                .forEach(product -> allProductData.add(product.getText()));
        System.out.println("dfdf");
        return allProductData;
    }

    public void getValuesPrices() {
        List<String> oldPrices = new ArrayList<>();
        driver.findElements(productsOldPrices)
                .stream()
                .forEach(product -> oldPrices.add(product.getText()));
        System.out.println("dfdf");
        List<String> newPrices = new ArrayList<>();
        driver.findElements(productsCurrentPrices)
                .stream()
                .forEach(product -> newPrices.add(product.getText()));
        System.out.println("dfdf");

    }
}
