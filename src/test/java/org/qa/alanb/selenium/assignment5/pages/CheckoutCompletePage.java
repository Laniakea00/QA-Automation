package org.qa.alanb.selenium.assignment5.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {
    private static final Logger log = LogManager.getLogger(CheckoutCompletePage.class);

    private final By completeHeader = By.cssSelector("[data-test='complete-header']");
    private final By completeText = By.cssSelector("[data-test='complete-text']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver, 10);
    }

    public String getCompleteHeader() {
        log.info("Read completion header");
        return textOf(completeHeader);
    }

    public String getCompleteText() {
        log.info("Read completion text");
        return textOf(completeText);
    }
}
