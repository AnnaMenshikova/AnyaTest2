package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import checkout.CheckoutInformation;

public class CheckoutPage extends BasePage {
    private final By firstNameInput = By.cssSelector("#first-name");
    private final By lastNameInput = By.cssSelector("#last-name");
    private final By zipPostalCodeInput = By.cssSelector("#postal-code");
    private final By continueBtn = By.cssSelector("#continue");
    private final By error = By.cssSelector(DATA_TEST_PATTERN.formatted("error"));

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Заполняем данные пользователя для оформления заказа")
    public CheckoutOverviewPage fillInformation(CheckoutInformation information) {
        driver.findElement(firstNameInput).sendKeys(information.getFirstName());
        driver.findElement(lastNameInput).sendKeys(information.getLastName());
        driver.findElement(zipPostalCodeInput).sendKeys(information.getZipPostalCode());
        driver.findElement(continueBtn).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(pageName));

        return new CheckoutOverviewPage(driver);
    }

    @Step("Заполняем некорректные данные для оформления заказа")
    public CheckoutPage fillInformationIncorrect(CheckoutInformation information) {
        driver.findElement(firstNameInput).sendKeys(information.getFirstName());
        driver.findElement(lastNameInput).sendKeys(information.getLastName());
        driver.findElement(zipPostalCodeInput).sendKeys(information.getZipPostalCode());
        driver.findElement(continueBtn).click();

        return this;
    }

    @Step("Проверяем сообщается ли сообщение об ошибке")
    public boolean isErrorDisplayed() {
        return driver.findElement(error).isDisplayed();
    }

    @Step("Проверяем текст сообщения об ошибке")
    public String getErrorMessage() {
        return driver.findElement(error).getText();
    }
}
