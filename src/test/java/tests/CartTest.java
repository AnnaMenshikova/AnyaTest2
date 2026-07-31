package tests;

import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

public class CartTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Bolt T-Shirt",
                    "Sauce Labs Bike Light",
                    "Sauce Labs Fleece Jacket");

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        for (String goodName : goodsList) {
            productsPage.addToCart(goodName);
        }
        productsPage.switchToCart();
        assertFalse(cartPage.getProductsName().isEmpty());
        assertEquals(cartPage.getProductsName().size(), 3);
        assertTrue(cartPage.getProductsName().contains("Sauce Labs Bike Light"));
        assertEquals(cartPage.getProductsName(), goodsList);
    }
}
