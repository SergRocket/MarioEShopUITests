package PageObjects;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    public List<String> getAttrFromCard(){
        driver.findElements(productsDescrArticle)
    }

   // WebElement correct = emp.stream().filter((element) -> element.getText().contains(correctName)).findFirst().orElse(null);
   private List< String > attrValues(By loc, String name) {
       return getValues(loc, e -> e.getAttribute(name));
   }
    private List< String > getValues(By loc, Function<WebElement,String > pred) {

        List< WebElement > elements = driver.findElements(loc);

        List< String > values = elements.stream().map(pred)
                .collect(Collectors.toList());

        return values;


    }
    List<String> allProductNames = new ArrayList<>();

    // Locating all product names at home page
    /*
     * We do not need to store list of web elements as well. We can get the stream of found web elements
     * and apply aggregate function forEach(). Logic behind forEach is to get the text of each web element
     * and add to list. We are using lambda expression inside forEach.
     */
		driver.findElements(By.xpath("//ul[contains(@class,'active')]//a[@class='product-name']"))
                .stream()
		.forEach(product -> allProductNames.add(product.getText()));
}
