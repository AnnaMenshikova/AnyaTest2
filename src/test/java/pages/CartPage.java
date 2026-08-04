package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public ArrayList<String> getProductsName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_item_name")));
        List<WebElement> allProductsNames = driver.findElements(By.cssSelector(".inventory_item_name"));
        ArrayList<String> names = new ArrayList<>();

        for (WebElement productBlock : allProductsNames) {
            names.add(productBlock.getText());
        }
        return names;
    }
}
