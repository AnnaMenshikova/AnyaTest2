package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test
    public void validLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        boolean isDisplayed = driver.findElement(By.cssSelector("[data-test='title']")).isDisplayed();
        String Title = driver.findElement(By.cssSelector("[data-test='title']")).getText();

        assertTrue(isDisplayed);
        assertEquals(Title, "Products");
    }

    @Test
    public void invalidLogin() {
        loginPage.open();
        loginPage.login("Standard_user", "secret_sauce");

        boolean isErrorDisplayed = driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
        String errorMessage = driver.findElement(By.cssSelector("[data-test='error']")).getText();

        assertTrue(isErrorDisplayed);
        assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void lockedUserLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        boolean isErrorDisplayed = driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
        String errorMessage = driver.findElement(By.cssSelector("[data-test='error']")).getText();

        assertTrue(isErrorDisplayed);
        assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void emptyLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        boolean isErrorDisplayed = driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
        String errorMessage = driver.findElement(By.cssSelector("[data-test='error']")).getText();

        assertTrue(isErrorDisplayed);
        assertEquals(errorMessage, "Epic sadface: Username is required");
    }

    @Test
    public void emptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");

        boolean isErrorDisplayed = driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
        String errorMessage = driver.findElement(By.cssSelector("[data-test='error']")).getText();

        assertTrue(isErrorDisplayed);
        assertEquals(errorMessage, "Epic sadface: Password is required");
    }
}
