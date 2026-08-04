package tests;

import org.testng.annotations.Test;
import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Bolt T-Shirt",
                    "Sauce Labs Bike Light",
                    "Sauce Labs Fleece Jacket");

    @Test
    public void checkGoodsAdded() {
        System.out.println("ProductsTest.checkGoodsAdded running in thread: "
                + Thread.currentThread().getName());
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertEquals(productsPage.getNamePage(), PRODUCTS.getDisplayName(),
                "Name of the page doesn't correspond to the expected");
        productsPage.pageIsOpen();
        productsPage.addToCart(5);
        for (String goodName : goodsList) {
            productsPage.addToCart(goodName);
        }
        assertEquals(productsPage.checkCounterValue(), 4);
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
