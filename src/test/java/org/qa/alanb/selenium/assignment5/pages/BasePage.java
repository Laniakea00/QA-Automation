package org.qa.alanb.selenium.assignment5.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver, int timeoutSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    public void open(String url) {
        log.info("Navigate to URL: {}", url);
        driver.get(url);
    }

    protected void click(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
        } catch (TimeoutException e) {
            log.error("Timeout: element not clickable {}", locator, e);
            throw e;
        }
    }

    protected void type(By locator, String text, boolean clearFirst) {
        try {
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (clearFirst) el.clear();
            el.sendKeys(text);
        } catch (TimeoutException e) {
            log.error("Timeout: element not visible for typing {}", locator, e);
            throw e;
        }
    }

    protected String textOf(By locator) {
        try {
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return el.getText().trim();
        } catch (TimeoutException e) {
            log.error("Timeout: element not visible for reading text {}", locator, e);
            throw e;
        }
    }

    protected boolean isVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
