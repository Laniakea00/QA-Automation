package org.qa.alanb.selenium.assignment4.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PurchasePage {

    private static final Logger LOGGER = LogManager.getLogger(PurchasePage.class);
    private WebDriver driver;
    private WebDriverWait wait;

    private By name = By.id("inputName");
    private By address = By.id("address");
    private By city = By.id("city");
    private By creditCard = By.id("creditCardNumber");
    private By purchaseButton = By.cssSelector("input.btn-primary");


    public PurchasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LOGGER.info("PurchasePage initialised");
    }

    public void fillForm(String nameValue, String addressValue, String cityValue, String cardNumber) {
        LOGGER.info("Waiting for form fields to be visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(name));
        LOGGER.info("Fields visible, filling form");
        driver.findElement(name).sendKeys(nameValue);
        driver.findElement(address).sendKeys(addressValue);
        driver.findElement(city).sendKeys(cityValue);
        driver.findElement(creditCard).sendKeys(cardNumber);
        LOGGER.info("Form filled successfully");
    }

    public void submit() {
        LOGGER.info("Clicking purchase button");
        driver.findElement(purchaseButton).click();
    }
}
