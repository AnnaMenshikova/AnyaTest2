package tests;

import checkout.CheckoutInformation;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.*;

import static enums.TitleNaming.COMPLETE;
import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

@Epic("Интернет-магазин")
@Feature("Завершение заказа")
@Owner("Menshikova Anna [anna@list.ru](mailto:anna@list.ru)")
public class CheckoutCompleteTest extends BaseTest {

    private CheckoutCompletePage openCheckoutCompletePage() {

        ProductsPage productsPage = loginPage
                .open()
                .login(withAdminPermission());

        productsPage.addToCart("Sauce Labs Bike Light");
        CartPage cartPage = productsPage.switchToCart();
        CheckoutPage checkoutPage = cartPage.switchToCheckout();
        CheckoutInformation information = new CheckoutInformation
                ("Имя", "Фамилия", "010101");
        CheckoutOverviewPage checkoutOverviewPage = checkoutPage.fillInformation(information);

        return checkoutOverviewPage.finish();
    }

    @Story("Проверка страницы успешного оформления заказа")
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("AnyaTest2")
    @Description("Проверка отображения элементов страницы Checkout Complete")
    public void checkCheckoutCompletePage() {

        CheckoutCompletePage checkoutCompletePage = openCheckoutCompletePage();

        assertTrue(checkoutCompletePage.pageIsOpen());
        assertEquals(checkoutCompletePage.getNamePage(), COMPLETE.getDisplayName(),
                "Name of the page doesn't correspond to the expected");
        assertTrue(checkoutCompletePage.isIconDisplayed());
        assertEquals(checkoutCompletePage.getCompleteHeader(), "Thank you for your order!",
                "Complete header doesn't correspond to the expected");
        assertEquals(checkoutCompletePage.getCompleteText(),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!",
                "Complete text doesn't correspond to the expected");
    }

    @Story("Возврат на страницу товаров после оформления заказа")
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка работы кнопки Back Home")
    public void checkBackHomeButton() {

        CheckoutCompletePage checkoutCompletePage = openCheckoutCompletePage();
        ProductsPage productsPage = checkoutCompletePage.backHome();

        assertTrue(productsPage.pageIsOpen());
        assertEquals(productsPage.getNamePage(), PRODUCTS.getDisplayName(),
                "Name of the page doesn't correspond to the expected");
    }

    @Story("Генерация PDF заказа")
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка возможности сформировать PDF заказа")
    public void checkGeneratePdfOrderButton() {

        CheckoutCompletePage checkoutCompletePage = openCheckoutCompletePage();
        checkoutCompletePage.generatePdfOrder();
    }
}
