package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductsPage;

import java.util.List;

import static enums.TitleNaming.*;
import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

@Epic("Интернет-магазин")
@Feature("Корзина")
@Owner("Menshikova Anna anna@list.ru")
public class CartTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Bolt T-Shirt",
                    "Sauce Labs Bike Light",
                    "Sauce Labs Fleece Jacket");

    @Story("Проверка товаров в корзине")
    @Test
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("AnyaTest2")
    @Description("Проверка отображения добавленных товаров в корзине")
    public void checkGoodsAdded() {
        System.out.println("CartTest.checkGoodsAdded running in thread: "
                + Thread.currentThread().getName());

        ProductsPage productsPage = loginPage
                .open()
                .login(withAdminPermission());

        assertEquals(productsPage.getNamePage(), PRODUCTS.getDisplayName(),
                "Name of the page doesn't correspond to the expected");

        for (String goodName : goodsList) {
            productsPage
                    .addToCart(goodName);
        }

        CartPage cartPage = productsPage.switchToCart();

        assertEquals(cartPage.getNamePage(), CART.getDisplayName(),
                "Name of the page doesn't correspond to the expected");
        assertFalse(cartPage.getProductsName().isEmpty());
        assertEquals(cartPage.getProductsName().size(), 3);
        assertTrue(cartPage.getProductsName().contains("Sauce Labs Bike Light"));
        assertEquals(cartPage.getProductsName(), goodsList);
    }

    @Story("Проверка кнопки Continue Shopping")
    @Test
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("AnyaTest2")
    @Description("Проверка возврата на страницу товаров после нажатия кнопки Continue Shopping")
    public void checkContinueShopping() {

        System.out.println("CartTest.checkContinueShopping running in thread: "
                + Thread.currentThread().getName());

        ProductsPage productsPage = loginPage
                .open()
                .login(withAdminPermission());

        productsPage.addToCart("Sauce Labs Bike Light");
        CartPage cartPage = productsPage.switchToCart();
        ProductsPage productsAfterContinue = cartPage.continueShopping();

        assertTrue(productsAfterContinue.pageIsOpen());
        assertEquals(productsAfterContinue.getNamePage(), PRODUCTS.getDisplayName(),
                "Name of the page doesn't correspond to the expected");
    }
}
