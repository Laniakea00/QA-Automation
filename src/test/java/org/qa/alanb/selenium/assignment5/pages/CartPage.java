package org.qa.alanb.selenium.assignment5.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private static final Logger log = LogManager.getLogger(CartPage.class);

    private final By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver, 10);
    }

    public void goToCheckout() {
        log.info("Click Checkout (go to checkout)");
        click(checkoutBtn);
    }
}
