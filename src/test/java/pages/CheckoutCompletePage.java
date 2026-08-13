package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage{
    private final By icon = By.cssSelector(DATA_TEST_PATTERN.formatted("pony-express"));
    private final By completeHeader = By.cssSelector(DATA_TEST_PATTERN.formatted("complete-header"));
    private final By completeTxt = By.cssSelector(DATA_TEST_PATTERN.formatted("complete-text"));
    private final By backHomeBtn = By.cssSelector(DATA_TEST_PATTERN.formatted("back-to-products"));
    private final By generatePdfOrderBtn = By.cssSelector(DATA_TEST_PATTERN.formatted("generate-pdf-order"));

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем наличие значка успешного оформления заказа")
    public boolean isIconPresent() {
        return !driver.findElements(icon).isEmpty();
    }

    @Step("Получаем заголовок страницы завершения заказа")
    public String getCompleteHeader() {
        return driver.findElement(completeHeader).getText();
    }

    @Step("Получаем текст сообщения о завершении заказа")
    public String getCompleteText() {
        return driver.findElement(completeTxt).getText();
    }

    @Step("Возврат на страницу товаров")
    public ProductsPage backHome() {
        driver.findElement(backHomeBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageName));

        return new ProductsPage(driver);
    }

    @Step("Генерируем PDF с информацией о заказе")
    public void generatePdfOrder() {
        driver.findElement(generatePdfOrderBtn).click();
    }
}
