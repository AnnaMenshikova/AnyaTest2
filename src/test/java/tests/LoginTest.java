package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ProductsPage;
import user.User;
import javax.swing.*;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;
import static user.UserFactory.withLockedAdminPermission;

@Epic("Интернет-магазин")
@Feature("Авторизация")
@Owner("Menshikova Anna anna@list.ru")
public class LoginTest extends BaseTest {

    @Story("Удачная авторизация")
    @Test(priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("AnyaTest2")
    @Issue("AnyaTest")
    @Description("Проверка корректной авторизации")
    public void validLogin() {
        System.out.println("LoginTest.validLogin running in thread: "
                + Thread.currentThread().getName());

        ProductsPage productsPage = loginPage
                .open()
                .login(withAdminPermission());

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

    @Story("Неудачная авторизация")
    @Test(priority = 2, dataProvider = "loginData")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("AnyaTest2")
    @Description("Проверка некорректной авторизации")
    public void invalidLogin(User user, String errorMsg) {
        System.out.println("LoginTest.invalidLogin running in thread: "
                + Thread.currentThread().getName());
        loginPage
                .open()
                .loginInvalid(user);

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorMessage(), errorMsg);
    }
}
