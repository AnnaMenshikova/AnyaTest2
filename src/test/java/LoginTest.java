import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest {

    @Test
    public void validLogin() {
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.saucedemo.com/");
        browser.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        browser.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        browser.findElement(By.cssSelector("#login-button")).click();
        String Title = browser.findElement(By.cssSelector("[data-test='title']")).getText();
        boolean isDisplayed = browser.findElement(By.cssSelector("[data-test='title']")).isDisplayed();

        assertTrue(isDisplayed);
        assertEquals(Title, "Products");
        browser.quit();
    }

    @Test
    public void invalidLogin() {
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.saucedemo.com/");
        browser.findElement(By.cssSelector("#user-name")).sendKeys("Standard_user");
        browser.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        browser.findElement(By.cssSelector("#login-button")).click();
        boolean isErrorDisplayed = browser.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
        String errorMessage = browser.findElement(By.cssSelector("[data-test='error']")).getText();

        assertTrue(isErrorDisplayed);
        assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
        browser.quit();
    }
}
