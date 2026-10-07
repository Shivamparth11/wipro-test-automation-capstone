package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Page Object for the SauceDemo product/inventory page shown after login.
 */
public class InventoryPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    public InventoryPage() {
        super();
    }

    public boolean isLoaded() {
        return wait.until(ExpectedConditions.visibilityOf(pageTitle))
                .getText().equalsIgnoreCase("Products");
    }

    public int getProductCount() {
        return productNames.size();
    }

    public void logout() {
        menuButton.click();
        wait.until(ExpectedConditions.visibilityOf(logoutLink)).click();
    }
}
