package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.BasePage;
import pages.LoginPage;

/**
 * TestNG test class for login functionality.
 * Demonstrates: annotations, groups, dependency, data provider (parameterization).
 */
public class LoginTest extends BasePage {

    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        initDriver();
        loginPage = new LoginPage();
    }

    @Test(groups = "smoke", priority = 1)
    public void validLoginShouldShowInventoryPage() {
        loginPage.open();
        var inventoryPage = loginPage.loginAs("standard_user", "secret_sauce");
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page did not load");
        Assert.assertTrue(inventoryPage.getProductCount() > 0, "No products displayed");
    }

    @Test(groups = "regression", priority = 2,
          dependsOnMethods = "validLoginShouldShowInventoryPage",
          alwaysRun = true)
    public void logoutShouldReturnToLoginPage() {
        loginPage.open();
        var inventoryPage = loginPage.loginAs("standard_user", "secret_sauce");
        inventoryPage.logout();
        Assert.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/"));
    }

    @Test(groups = "regression", dataProvider = "invalidCredentials")
    public void invalidLoginShouldShowError(String username, String password) {
        loginPage.open();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected error message not shown");
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][] {
            {"locked_out_user", "secret_sauce"},
            {"standard_user", "wrong_password"},
            {"", ""}
        };
    }

    @AfterClass
    public void tearDown() {
        quitDriver();
    }
}
