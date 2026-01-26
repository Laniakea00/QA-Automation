package org.qa.alanb.selenium.assignment5.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage extends BasePage {

    private static final Logger log = LogManager.getLogger(ProductsPage.class);
    private WebDriverWait wait;

    private final By title = By.cssSelector("[data-test='title']");
    private final By cartLink = By.cssSelector("[data-test='shopping-cart-link']");

    public ProductsPage(WebDriver driver) {
        super(driver, 10);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitle() {
        log.info("Read Products page title");
        return textOf(title);
    }

    public void addToCartByName(String itemName) {
        log.info("Add item to cart: {}", itemName);

        By buttonLocator = By.xpath(
                "//div[@data-test='inventory-item-name' and normalize-space()='" + itemName + "']" +
                        "/ancestor::div[@data-test='inventory-item']//button"
        );

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonLocator));
        button.click();

        // Ждём **новый элемент с текстом Remove**
        wait.until(ExpectedConditions.textToBePresentInElementLocated(buttonLocator, "Remove"));

        log.info("Item added подтверждён (button changed to Remove)");
    }

    public void openCart() {
        log.info("Open cart");
        click(cartLink);
    }
}
