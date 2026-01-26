package org.qa.alanb.selenium.assignment2;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.qa.alanb.BaseTest;

public class SearchTest extends BaseTest {
    @Test
    void wikipediaSearchTest() throws InterruptedException {
        driver.get("https://www.wikipedia.org/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        WebElement searchInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input#searchInput")
                )
        );

        searchInput.sendKeys("Sony");

        driver.findElement(By.xpath("//button[@type='submit']")).click();


        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("firstHeading")
                )
        );

        assertTrue(heading.getText().contains("Sony"));
        Thread.sleep(5 * 1000);

    }
}
