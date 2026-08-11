package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import checkout.CheckoutInformation;

import static enums.TitleNaming.*;
import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

@Epic("Интернет-магазин")
@Feature("Информация о пользователе")
@Owner("Menshikova Anna anna@list.ru")
public class CheckoutTest extends BaseTest {

    @Story("Полный цикл оформления заказа")
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("AnyaTest2")
    @Description("Проверка полного цикла оформления заказа")
    public void checkCheckoutInformation() {
        ProductsPage productsPage = loginPage
                .open()
                .login(withAdminPermission());

        productsPage.addToCart("Sauce Labs Bike Light");

        CartPage cartPage = productsPage.switchToCart();
        CheckoutPage checkoutPage = cartPage.switchToCheckout();

        assertTrue(checkoutPage.pageIsOpen());
        assertEquals(checkoutPage.getNamePage(), CHECKOUT.getDisplayName(),
                "Name of the page doesn't correspond to the expected");

        CheckoutInformation information = new CheckoutInformation
                ("Имя", "Фамилия", "010101");
        CheckoutOverviewPage checkoutOverviewPage = checkoutPage.fillInformation(information);

        assertTrue(checkoutOverviewPage.pageIsOpen());
        assertEquals(checkoutOverviewPage.getNamePage(), CHECKOUT_OVERVIEW.getDisplayName(),
                "Name of the page doesn't correspond to the expected");

        assertEquals(checkoutOverviewPage.getItemTotal() + checkoutOverviewPage.getTax(),
                checkoutOverviewPage.getTotal(), 0.01);

        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.finish();

        assertTrue(checkoutCompletePage.pageIsOpen());
        assertEquals(checkoutCompletePage.getNamePage(), COMPLETE.getDisplayName(),
                "Name of the page doesn't correspond to the expected");

        assertTrue(checkoutCompletePage.isIconPresent());
        assertEquals(checkoutCompletePage.getCompleteHeader(), "Thank you for your order!",
                "Complete header doesn't correspond to the expected");
        assertEquals(checkoutCompletePage.getCompleteText(),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!",
                "Complete text doesn't correspond to the expected");
    }

    @DataProvider
    public Object[][] invalidCheckoutData() {
        return new Object[][]{
                {new CheckoutInformation("", "Фамилия", "010101"),
                        "Error: First Name is required"},
                {new CheckoutInformation("Имя", "", "010101"),
                        "Error: Last Name is required"},
                {new CheckoutInformation("Имя", "Фамилия", ""),
                        "Error: Postal Code is required"}
        };
    }

    @Story("Проверка некорректного заполнения информации для оформления заказа")
    @Test(dataProvider = "invalidCheckoutData")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка обязательности полей на странице Checkout")
    public void invalidCheckoutInformation(CheckoutInformation information, String errorMessage) {

        ProductsPage productsPage = loginPage
                .open()
                .login(withAdminPermission());

        productsPage.addToCart("Sauce Labs Bike Light");
        CartPage cartPage = productsPage.switchToCart();

        CheckoutPage checkoutPage = cartPage
                .switchToCheckout()
                .fillInformationIncorrect(information);

        assertTrue(checkoutPage.isErrorDisplayed());
        assertEquals(checkoutPage.getErrorMessage(), errorMessage);
    }
}
