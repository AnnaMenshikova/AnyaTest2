package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutOverviewPage extends BasePage {
    private final By itemTotal = By.cssSelector(DATA_TEST_PATTERN.formatted("subtotal-label"));
    private final By tax = By.cssSelector(DATA_TEST_PATTERN.formatted("tax-label"));
    private final By total = By.cssSelector(DATA_TEST_PATTERN.formatted("total-label"));
    private final By finishBtn = By.cssSelector(DATA_TEST_PATTERN.formatted("finish"));
    private final By cancelBtn = By.cssSelector(DATA_TEST_PATTERN.formatted("cancel"));

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получаем сумму товаров")
    public double getItemTotal() {
        String text = driver.findElement(itemTotal).getText();
        return Double.parseDouble(text.split("\\$")[1]);
    }

    @Step("Получаем сумму налога")
    public double getTax() {
        String text = driver.findElement(tax).getText();
        return Double.parseDouble(text.split("\\$")[1]);
    }

    @Step("Получаем итоговую сумму заказа")
    public double getTotal() {
        String text = driver.findElement(total).getText();
        return Double.parseDouble(text.split("\\$")[1]);
    }

    @Step("Завершаем оформление заказа")
    public CheckoutCompletePage finish() {
        driver.findElement(finishBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageName));

        return new CheckoutCompletePage(driver);
    }

    @Step("Отмена оформления заказа")
    public ProductsPage cancel() {
        driver.findElement(cancelBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageName));

        return new ProductsPage(driver);
    }
}
