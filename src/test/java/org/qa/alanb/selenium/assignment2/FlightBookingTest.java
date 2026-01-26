package org.qa.alanb.selenium.assignment2;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.alanb.BaseTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightBookingTest extends BaseTest {
    @Test
    void bookFlightTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://blazedemo.com/");

        driver.findElement(By.name("fromPort"))
                .sendKeys("Boston");

        driver.findElement(By.name("toPort"))
                .sendKeys("London");

        driver.findElement(By.cssSelector("input.btn-primary")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[1]/td[1]/input")));
        driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]/input")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputName")));
        driver.findElement(By.id("inputName")).sendKeys("Rostislav");
        driver.findElement(By.id("address")).sendKeys("Petrov st.");
        driver.findElement(By.id("city")).sendKeys("Astana");
        driver.findElement(By.id("creditCardNumber")).sendKeys("1111111111111111");

        driver.findElement(By.cssSelector("input.btn-primary")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        WebElement title = driver.findElement(By.tagName("h1"));
        assertEquals("Thank you for your purchase today!", title.getText());
        Thread.sleep(5 * 1000);
    }
}
