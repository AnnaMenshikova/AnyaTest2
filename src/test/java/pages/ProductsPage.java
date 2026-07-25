package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    private final By pageName = By.cssSelector("[data-test='title']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getNamePage() {
        return driver.findElement(pageName).getText();
    }
}
