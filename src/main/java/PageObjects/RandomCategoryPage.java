package PageObjects;

import Base.BasePage;
import org.apache.commons.collections4.ListUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public boolean getValuesPrices() {
        List<String> oldPrices = new ArrayList<>();
        driver.findElements(productsOldPrices)
                .stream()
                .forEach(product -> oldPrices.add(product.getText().replaceAll(" грн.","")));
        List<Integer> oldPricesInteger = oldPrices.stream().map(Integer::parseInt).collect(Collectors.toList());
        List<String> newPrices = new ArrayList<>();
        driver.findElements(productsCurrentPrices)
                .stream()
                .forEach(product -> newPrices.add(product.getText().replaceAll(" грн.","")));
        List<Integer> newPricesInteger = newPrices.stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Boolean> equalityResult = IntStream.range(0, oldPricesInteger.size()).mapToObj(i -> oldPricesInteger.get(i) > newPricesInteger.get(i))
                .collect(Collectors.toList());
        return equalityResult.stream().allMatch(x->x.equals(true));
    }

   public boolean compareSizes(){
        List<String> sizes = new ArrayList<>();
        driver.findElements(productsSizes)
                .stream()
                .forEach(product -> sizes.add(product.getText().replaceAll("Розміри: ","")));
        List<String[]> separateSizesAsStrings = sizes.stream().map(x->x.split(", ")).collect(Collectors.toList());
        List<Integer> listOfSizesConvertedToIntegers = separateSizesAsStrings.stream().flatMap(x -> Arrays.stream(x))
                .map(Integer::parseInt).collect(Collectors.toList());
        List<Boolean> equalityResult = IntStream.range(0, listOfSizesConvertedToIntegers.size()).mapToObj(i -> listOfSizesConvertedToIntegers.get(i) <= listOfSizesConvertedToIntegers.get(i))
                .collect(Collectors.toList());
        return equalityResult.stream().allMatch(x->x.equals(true));
    }



}
