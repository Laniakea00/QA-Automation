package org.qa.alanb.selenium.assignment5.tests;

import org.qa.alanb.BaseTest;
import org.qa.alanb.selenium.assignment5.reporting.ExtentTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.qa.alanb.selenium.assignment5.pages.*;

@Listeners(ExtentTestListener.class)
public class BookingPomTest extends BaseTest {

    @Test
    public void testBookingSuccessPom() {
        log.info("Scenario: booking success");
        LoginPage login = new LoginPage(driver);
        ProductsPage products = new ProductsPage(driver);
        CartPage cart = new CartPage(driver);
        CheckoutInfoPage info = new CheckoutInfoPage(driver);
        CheckoutOverviewPage overview = new CheckoutOverviewPage(driver);
        CheckoutCompletePage complete = new CheckoutCompletePage(driver);

        login.openLogin();
        login.login("standard_user", "secret_sauce");

        Assert.assertEquals(products.getTitle(), "Products");
        log.info("Assert page title is Products");

        products.addToCartByName("Sauce Labs Backpack");
        products.openCart();

        cart.goToCheckout();

        info.fillCustomerInfo("Alan", "Berikzhan", "010000");
        info.continueCheckout();

        overview.finish();

        Assert.assertEquals(complete.getCompleteHeader(), "Thank you for your order!");
        log.info("Assert order confirmation message");
    }

    @Test
    public void testBookingNegativeMissingFirstName() {
        log.info("Scenario: booking negative - missing first name");
        LoginPage login = new LoginPage(driver);
        ProductsPage products = new ProductsPage(driver);
        CartPage cart = new CartPage(driver);
        CheckoutInfoPage info = new CheckoutInfoPage(driver);

        login.openLogin();
        login.login("standard_user", "secret_sauce");

        products.addToCartByName("Sauce Labs Backpack");
        products.openCart();

        cart.goToCheckout();

        info.fillCustomerInfo("", "Test", "010000");
        info.continueCheckout();

        Assert.assertTrue(info.getErrorText().contains("First Name is required"));
        log.info("Assert error message about required First Name");
    }
}
