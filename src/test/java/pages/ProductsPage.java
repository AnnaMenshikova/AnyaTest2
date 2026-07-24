package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    private final By pageName = By.cssSelector("[data-test='title']");
    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean pageIsOpen() {
        return driver.findElement(pageName).isDisplayed();
    }

    public String getNamePage() {
        return driver.findElement(pageName).getText();
    }

}
