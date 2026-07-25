package tests;

import org.testng.annotations.Test;

import javax.swing.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test(description = "Проверка валидной авторизации", priority = 1)
    public void validLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(productsPage.pageIsOpen());
        assertEquals(productsPage.getNamePage(), "Products",
                "Name of the page doesn't correspond to the expected");
    }

    @Test(description = "Проверка невалидной авторизации", priority = 2, invocationCount = 3)
    public void invalidLogin() {
        loginPage.open();
        loginPage.login("Standard_user", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(description = "Проверка заблокированного пользователя", priority = 5)
    public void lockedUserLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(description = "Проверка пустого логина", priority = 3)
    public void emptyLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test(description = "Проверка пустого пароля", priority = 4)
    public void emptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }
}
