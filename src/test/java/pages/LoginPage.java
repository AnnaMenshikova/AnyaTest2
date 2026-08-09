package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {
    private final By loginInput = By.cssSelector("#user-name");
    private final By passwordInput = By.cssSelector("#password");
    private final By loginBtn = By.cssSelector("#login-button");
    private final By error = By.cssSelector(DATA_TEST_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие браузера")
    public LoginPage open() {
        driver.get(BASE_URL);

        return this;
    }

    @Step("Логинимся под кредами: {user.login}, пароль {user.password}")
    public LoginPage login(User user) {
        driver.findElement(loginInput).sendKeys(user.getLogin());
        driver.findElement(passwordInput).sendKeys(user.getPassword());
        driver.findElement(loginBtn).click();

        return this;
    }

    @Step("Проверяем сообщается ли сообщение об ошибке")
    public boolean isErrorDisplayed() {
        return driver.findElement(error).isDisplayed();
    }

    @Step("Проверяем текст сообщения об ошибке")
    public String getErrorMessage() {
        return driver.findElement(error).getText();
    }
}
