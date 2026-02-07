package org.qa.alanb.cucumber.steps;

import io.cucumber.java.en.*;
import org.qa.alanb.selenium.assignment5.pages.*;
import org.testng.Assert;

import static org.qa.alanb.cucumber.hooks.Hooks.driver;

public class BookingPomSteps {

    private LoginPage login;
    private ProductsPage products;
    private CartPage cart;
    private CheckoutInfoPage info;
    private CheckoutOverviewPage overview;
    private CheckoutCompletePage complete;

    @Given("I am logged in as a standard user")
    public void iAmLoggedInAsStandardUser() {
        login = new LoginPage(driver);
        products = new ProductsPage(driver);
        cart = new CartPage(driver);
        info = new CheckoutInfoPage(driver);
        overview = new CheckoutOverviewPage(driver);
        complete = new CheckoutCompletePage(driver);

        login.openLogin();
        login.login("standard_user", "secret_sauce");

        Assert.assertEquals(products.getTitle(), "Products", "Products page title mismatch");
    }

    @When("I add product {string} to the cart")
    public void iAddProductToTheCart(String productName) {
        products.addToCartByName(productName);
        products.openCart();
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        cart.goToCheckout();
    }

    @When("I enter customer info {string} {string} {string}")
    public void iEnterCustomerInfo(String firstName, String lastName, String postalCode) {
        info.fillCustomerInfo(firstName, lastName, postalCode);
    }

    @When("I continue checkout")
    public void iContinueCheckout() {
        info.continueCheckout();
    }

    @When("I finish checkout")
    public void iFinishCheckout() {
        info.continueCheckout();
        overview.finish();
    }

    @Then("I should see order confirmation message {string}")
    public void iShouldSeeOrderConfirmationMessage(String expectedMessage) {
        Assert.assertEquals(complete.getCompleteHeader(), expectedMessage);
    }

    @Then("I should see checkout error containing {string}")
    public void iShouldSeeCheckoutErrorContaining(String expectedText) {
        Assert.assertTrue(info.getErrorText().contains(expectedText));
    }
}
