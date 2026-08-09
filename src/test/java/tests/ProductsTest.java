package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.ProductsPage;

import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

@Epic("Интернет-магазин")
@Feature("Товары")
@Owner("Menshikova Anna anna@list.ru")
public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Bolt T-Shirt",
                    "Sauce Labs Bike Light",
                    "Sauce Labs Fleece Jacket");

    @Story("Добавление товаров в корзину")
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("AnyaTest2")
    @Description("Проверка возможности добавить товар в корзину")
    public void checkGoodsAdded() {
        System.out.println("ProductsTest.checkGoodsAdded running in thread: "
                + Thread.currentThread().getName());

        ProductsPage productsPage = loginPage
                .open()
                .login(withAdminPermission());

        assertEquals(productsPage.getNamePage(), PRODUCTS.getDisplayName(),
                "Name of the page doesn't correspond to the expected");
        assertTrue(productsPage.pageIsOpen());

        productsPage.addToCart(5);

        for (String goodName : goodsList) {
            productsPage.addToCart(goodName);
        }

        assertEquals(productsPage.checkCounterValue(), 4);
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
