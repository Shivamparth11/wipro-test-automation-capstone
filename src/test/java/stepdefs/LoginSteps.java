package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.BasePage;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginSteps {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Before
    public void setUp() {
        BasePage.initDriver();
        loginPage = new LoginPage();
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        loginPage.open();
    }

    @When("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        inventoryPage = loginPage.clickLogin();
    }

    @Then("the inventory page should be displayed")
    public void the_inventory_page_should_be_displayed() {
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page was not displayed");
    }

    @And("at least {int} product should be listed")
    public void at_least_product_should_be_listed(int minCount) {
        Assert.assertTrue(inventoryPage.getProductCount() >= minCount);
    }

    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed() {
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected error message not shown");
    }

    @After
    public void tearDown() {
        BasePage.quitDriver();
    }
}
