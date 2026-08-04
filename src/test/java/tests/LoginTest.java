package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import javax.swing.*;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;
import static user.UserFactory.withLockedAdminPermission;

public class LoginTest extends BaseTest {

    @Test(description = "Проверка валидной авторизации", priority = 1)
    public void validLogin() {
        System.out.println("LoginTest.validLogin running in thread: "
                + Thread.currentThread().getName());
        loginPage.open();
        loginPage.login(withAdminPermission());

        assertTrue(productsPage.pageIsOpen());
        assertEquals(productsPage.getNamePage(), PRODUCTS.getDisplayName(),
                "Name of the page doesn't correspond to the expected");
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {new User("Standard_user", "secret_sauce"),
                        "Epic sadface: Username and password do not match any user in this service"},
                {withLockedAdminPermission(),
                        "Epic sadface: Sorry, this user has been locked out."},
                {new User("", "secret_sauce"), "Epic sadface: Username is required"},
                {new User("standard_user", ""), "Epic sadface: Password is required"}
        };
    }

    @Test(priority = 2, dataProvider = "loginData")
    public void invalidLogin(User user, String errorMsg) {
        System.out.println("LoginTest.invalidLogin running in thread: "
                + Thread.currentThread().getName());
            loginPage.open();
            loginPage.login(user);
            assertTrue(loginPage.isErrorDisplayed());
            assertEquals(loginPage.getErrorMessage(), errorMsg);
        }
    }
