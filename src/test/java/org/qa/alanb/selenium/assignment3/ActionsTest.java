package org.qa.alanb.selenium.assignment3;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.qa.alanb.BaseTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActionsTest extends BaseTest {

    @Test
    public void testActions() {
        driver.get("https://demoqa.com/buttons");

        Actions actions = new Actions(driver);

        WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
        WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));

        actions.doubleClick(doubleClickBtn).perform();

        WebElement doubleClickMsg = driver.findElement(By.id("doubleClickMessage"));

        assertTrue(doubleClickMsg.isDisplayed());
        System.out.println(LocalDateTime.now() + " double click done");

        actions.contextClick(rightClickBtn).perform();

        WebElement rightClickMsg = driver.findElement(By.id("rightClickMessage"));
        System.out.println(LocalDateTime.now() + " right click done");


        assertTrue(rightClickMsg.isDisplayed());

    }
}
