package page.objects;

import base.BasePage;
import utils.Reporter;
import org.openqa.selenium.By;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomCategoryPage extends BasePage {
    private By logomainPage = new By.ByCssSelector("div.logo>img");
    private By pageTilte = new By.ByCssSelector("h1.title");
    private By productsBlock = new By.ByCssSelector("div[id='products']");
    private By productsCards = new By.ByCssSelector("div.short-description-values");
    private By productsOldPrices = new By.ByCssSelector("div.price-old");
    private By productsCurrentPrices = new By.ByCssSelector("div.price.shk-price");
    private By productsDescrArticle = new By.ByCssSelector("div.h2>a[href*='catalog']");
    private By productsSizes = new By.ByCssSelector("div.size-row");
    private By pagination = new By.ByCssSelector("div.pagination-row");

    public int getQntProductCarts() {
        Reporter.log("Get qnt of the added goods to the cart ");
        return driver.findElements(productsCards).size();
    }

    public List<String> getValues() {
        Reporter.log("Checking if text in each element are shown correctly ");
        List<String> allProductData = new ArrayList<>();
        driver.findElements(productsBlock).forEach(product -> allProductData.add(product.getText()));
        return allProductData;
    }

    public boolean getValuesPrices() {
        Reporter.log("Checking if prices are shown correctly ");
        List<String> oldPrices = new ArrayList<>();
        driver.findElements(productsOldPrices).forEach(product -> oldPrices.add(product.getText().
                replaceAll(" грн.","")));
        Reporter.log("Deleting unused chars from the str ");
        List<Integer> oldPricesInteger = oldPrices.stream().map(Integer::parseInt).collect(Collectors.toList());
        Reporter.log("Converting chars to the list of ints ");
        List<String> newPrices = new ArrayList<>();
        driver.findElements(productsCurrentPrices).forEach(product -> newPrices.add(product.getText().
                replaceAll(" грн.","")));
        Reporter.log("Deleting unused chars from the str ");
        List<Integer> newPricesInteger = newPrices.stream().map(Integer::parseInt).collect(Collectors.toList());
        Reporter.log("Converting chars to the list of ints ");
        List<Boolean> equalityResult = IntStream.range(0, oldPricesInteger.size()).mapToObj(i -> oldPricesInteger.
                 get(i) > newPricesInteger.get(i)).collect(Collectors.toList());
        Reporter.log("Comparing int values old should be bigger then new ");

        Reporter.log("Checking that aech comparation returned true ");
        return equalityResult.stream().allMatch(x -> x.equals(true));
    }

   public boolean compareSizes() {
        List<String> sizes = new ArrayList<>();
        driver.findElements(productsSizes).forEach(product -> sizes.add(product.getText().
                replaceAll("Розміри: ","")));
        Reporter.log("Deleting unused chars from the str ");
        List<String[]> separateSizesAsStrings = sizes.stream().map(x -> x.split(", ")).collect(Collectors.toList());
        Reporter.log("Splitting the list of sizes by coma into separate two digit values ");
        List<Integer> listOfSizesConvertedToIntegers = separateSizesAsStrings.stream().flatMap(Arrays::stream).
                map(Integer::parseInt).collect(Collectors.toList());
        Reporter.log("Converting splitted values into list of ins ");
        List<Boolean> equalityResult = IntStream.range(0, listOfSizesConvertedToIntegers.size()).
                mapToObj(i -> listOfSizesConvertedToIntegers.get(i) <= listOfSizesConvertedToIntegers.get(i)).
                collect(Collectors.toList());
        Reporter.log("Comparing int values the next one should be bigger that previous ");

        Reporter.log("Checking that aech comparation returned true ");
        return equalityResult.stream().allMatch(x -> x.equals(true));
    }

}
