package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object for the SauceDemo login page.
 * https://www.saucedemo.com/
 */
public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public LoginPage() {
        super();
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public InventoryPage clickLogin() {
        loginButton.click();
        return new InventoryPage();
    }

    public boolean isErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
    }

    public String getErrorText() {
        return errorMessage.getText();
    }

    // Convenience method used directly by TestNG tests
    public InventoryPage loginAs(String username, String password) {
        open();
        enterUsername(username);
        enterPassword(password);
        return clickLogin();
    }
}
