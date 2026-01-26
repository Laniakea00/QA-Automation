package org.qa.alanb.selenium.assignment5.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {
    private static final Logger log = LogManager.getLogger(CheckoutOverviewPage.class);

    private final By finishBtn = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver, 10);
    }

    public void finish() {
        log.info("Click Finish on Overview");
        click(finishBtn);
    }
}
