package org.qa.alanb.selenium.assignment2;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.alanb.BaseTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthTest extends BaseTest {

    @Test
    void loginAndLogoutTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username"))
                .sendKeys("tomsmith");

        driver.findElement(By.id("password"))
                .sendKeys("SuperSecretPassword!");

        driver.findElement(By.className("radius")).click();

        WebElement flash = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("flash")
                )
        );


        assertTrue(flash.getText().contains("You logged into a secure area"));

        driver.findElement(By.xpath("//a[@href='/logout']")).click();

        assertTrue(driver.getCurrentUrl().contains("/login"));
        Thread.sleep(5 * 1000);
    }
}
