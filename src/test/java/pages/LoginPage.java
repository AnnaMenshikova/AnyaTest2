package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final By loginInput = By.cssSelector("#user-name");
    private final By passwordInput = By.cssSelector("#password");
    private final By loginBtn = By.cssSelector("#login-button");
    private final By error = By.cssSelector("[data-test='error']");
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public void login(final String userName, final String password) {
        driver.findElement(loginInput).sendKeys(userName);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginBtn).click();
    }

    public boolean isErrorDisplayed() {
        return driver.findElement(error).isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(error).getText();
    }
}
