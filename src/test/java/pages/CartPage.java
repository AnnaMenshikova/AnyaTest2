package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private final By productName = By.cssSelector(".inventory_item_name");
    private final By checkoutBtn = By.cssSelector(DATA_TEST_PATTERN.formatted("checkout"));
    private final By continueShoppingBtn = By.cssSelector(DATA_TEST_PATTERN.formatted("continue-shopping"));

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получаем список названий товаров в корзине")
    public ArrayList<String> getProductsName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
        List<WebElement> allProductsNames = driver.findElements(productName);
        ArrayList<String> names = new ArrayList<>();
        for (WebElement productBlock : allProductsNames) {
            names.add(productBlock.getText());
        }
        return names;
    }

    @Step("Переход на страницу информации о пользователе")
    public CheckoutPage switchToCheckout() {
        driver.findElement(checkoutBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageName));

        return new CheckoutPage(driver);
    }

    @Step("Возврат к странице товаров")
    public ProductsPage continueShopping() {
        driver.findElement(continueShoppingBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageName));

        return new ProductsPage(driver);
    }
}
