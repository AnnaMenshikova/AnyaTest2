package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test
    public void validLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(productsPage.pageIsOpen());
        assertEquals(productsPage.getNamePage(), "Products",
                "Name of the page doesn't correspond to the expected");
    }

    @Test
    public void invalidLogin() {
        loginPage.open();
        loginPage.login("Standard_user", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void lockedUserLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void emptyLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test
    public void emptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }
}
