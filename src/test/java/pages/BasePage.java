package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;
import java.time.Duration;

public class BasePage {
    public static final String DATA_TEST_PATTERN = "[data-test='%s']";
    public static final String TEXT_LOCATOR_PATTERN = "//*[text()='%s']";
    public static final String BASE_URL = PropertyReader.getProperty("saucedemo.url");
    protected final By pageName = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));

    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Проверяем отображение страницы")
    public boolean pageIsOpen() {
        return driver.findElement(pageName).isDisplayed();
    }

    @Step("Получаем название страницы")
    public String getNamePage() {
        return driver.findElement(pageName).getText();
    }
}
