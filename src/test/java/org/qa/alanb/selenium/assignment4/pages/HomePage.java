package org.qa.alanb.selenium.assignment4.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);
    private WebDriver driver;

    private By fromPort = By.name("fromPort");
    private By toPort = By.name("toPort");
    private By findFlightsButton = By.cssSelector("input.btn-primary");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        LOGGER.info("Opening home page");
        driver.get("https://blazedemo.com/");
    }

    public void searchFlight(String from, String to) {
        LOGGER.info("Searching flight from {} to {}", from, to);
        driver.findElement(fromPort).sendKeys(from);
        driver.findElement(toPort).sendKeys(to);
        driver.findElement(findFlightsButton).click();
        LOGGER.info("Flight search completed");
    }
}
