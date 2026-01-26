package org.qa.alanb.selenium.assignment5.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage extends BasePage {
    private static final Logger log = LogManager.getLogger(CheckoutInfoPage.class);

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By error = By.cssSelector("[data-test='error']");

    public CheckoutInfoPage(WebDriver driver) {
        super(driver, 10);
    }

    public void fillCustomerInfo(String first, String last, String zip) {
        log.info("Fill customer info: firstName={}, lastName={}, zip={}", first, last, zip);
        type(firstName, first, true);
        type(lastName, last, true);
        type(postalCode, zip, true);
    }

    public void continueCheckout() {
        log.info("Click Continue on Checkout Info");
        click(continueBtn);
    }

    public String getErrorText() {
        log.info("Read checkout info error message");
        return textOf(error);
    }
}
