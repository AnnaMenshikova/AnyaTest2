package pages;

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

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(User user) {
        driver.findElement(loginInput).sendKeys(user.getLogin());
        driver.findElement(passwordInput).sendKeys(user.getPassword());
        driver.findElement(loginBtn).click();
    }

    public boolean isErrorDisplayed() {
        return driver.findElement(error).isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(error).getText();
    }
}
