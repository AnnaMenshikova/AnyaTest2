package pages;

import io.qameta.allure.Step;
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

    @Step("Добавляем товар '{goodsName}' в корзину")
    public ProductsPage addToCart(final String goodsName) {
        By goods = By.xpath(ADD_TO_CART.formatted(goodsName));
        driver.findElement(goods).click();

        return this;
    }

    @Step("Добавляем товар с индексом '{goodsIndex}' в корзину")
    public ProductsPage addToCart(int goodsIndex) {
        driver.findElements(addToCartBnt).get(goodsIndex).click();

        return this;
    }

    @Step("Получаем значение счетчика в корзине")
    public int checkCounterValue() {
        return Integer.parseInt(driver.findElement(counter).getText());
    }

    @Step("Получаем цвет фона счетчика в корзине")
    public String checkCounterColor() {
        return driver.findElement(counter).getCssValue("background-color");
    }

    @Step("Переход на страницу корзины")
    public CartPage switchToCart() {
        driver.findElement(cartLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageName));

        return new CartPage(driver);
    }
}
