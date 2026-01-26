package org.qa.alanb.selenium.assignment4.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FlightsPage {

    private static final Logger LOGGER = LogManager.getLogger(FlightsPage.class);
    private WebDriver driver;
    private WebDriverWait wait;


    private By firstFlightButton = By.xpath("//table/tbody/tr[1]/td[1]/input");

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void chooseFirstFlight() {
        LOGGER.info("Waiting for first flight to be clickable and clicking it");
        wait.until(ExpectedConditions.elementToBeClickable(firstFlightButton));
        driver.findElement(firstFlightButton).click();
        LOGGER.info("First flight chosen");
    }
}
