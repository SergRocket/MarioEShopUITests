package PageObjects;

import Base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RandomCategoryPage extends BasePage {
    @FindBy(css = "ul.nav-navigation>li.active")
    private WebElement selectedMenuSection;
    @FindBy(css = "div.logo>a")
    private WebElement shopLogo;
    @FindBy(css = "div.shop-cart-head")
    private WebElement shopCart;
    @FindBy(css = "div.col-md-9 > h1")
    private WebElement sectionTitle;
    @FindBy(css = "button.buy_btn_rework")
    private WebElement addToCartButton;
    @FindBy(css = "div.catalog-block.catalog-block--new>form")
    private WebElement itemsQntShownInCategory;
    @FindBy(css = "div.img-block")
    private WebElement productImage;
    @FindBy(css = "div.h2>a")
    private WebElement productTitle;
    @FindBy(css = "div.price-old")
    private WebElement productOldPrice;
    @FindBy(css = "div.div-table.price-row>div")
    private WebElement priceBlock;
    @FindBy(css = "div.price.shk-price")
    private WebElement productNewPrice;
    @FindBy(css = "small.small.js-articul.articul-desctop")
    private WebElement productArticle;
    @FindBy(css = "div.size-row")
    private WebElement sizeRow;
    @FindBy(css = "span.span.wish-text")
    private WebElement addToFavButton;


}
