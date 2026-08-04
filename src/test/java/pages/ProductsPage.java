package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
    private static final String ADD_TO_CART = "//*[text()='%s']//ancestor::div" +
            "[@class='inventory_item']//child::*[text()='Add to cart']";

    private final By counter = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));
    private final By cartLink = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-link"));
    private final By addToCartBnt = By.xpath(TEXT_LOCATOR_PATTERN.formatted("Add to cart"));

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart(final String goodsName) {
        By goods = By.xpath(ADD_TO_CART.formatted(goodsName));
        driver.findElement(goods).click();
    }

    public void addToCart(int goodsIndex) {
        driver.findElements(addToCartBnt).get(goodsIndex).click();
    }

    public int checkCounterValue() {
        return Integer.parseInt(driver.findElement(counter).getText());
    }

    public String checkCounterColor() {
        return driver.findElement(counter).getCssValue("background-color");
    }

    public void switchToCart() {
        driver.findElement(cartLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageName));
    }
}
