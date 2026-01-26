package org.qa.alanb.selenium.assignment4.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmationPage {

    private static final Logger LOGGER = LogManager.getLogger(ConfirmationPage.class);
    private WebDriver driver;
    private WebDriverWait wait;

    private By title = By.tagName("h1");
    private By cardNumber = By.xpath("//table/tbody/tr[4]/td[2]");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitleText() {
        LOGGER.info("Waiting for title to be visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(title));
        String text = driver.findElement(title).getText();
        LOGGER.info("Title text retrieved: {}", text);
        return text;
    }

    public String getCardNumber() {
        LOGGER.info("Getting card number");
        String text = driver.findElement(cardNumber).getText();
        LOGGER.info("Card number retrieved: {}", text);
        return text;
    }
}
