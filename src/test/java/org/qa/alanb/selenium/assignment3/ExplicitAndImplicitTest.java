package org.qa.alanb.selenium.assignment3;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.alanb.BaseTest;

import java.time.Duration;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ExplicitAndImplicitTest extends BaseTest {

    @Test
    public void testImplicitPass(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");

        driver.findElement(By.cssSelector("#start button")).click();
        System.out.println(LocalDateTime.now() + " click");

        WebElement text = driver.findElement(By.id("finish"));
        System.out.println(LocalDateTime.now() + " finish");

        System.out.println(text.getText());
    }

    @Test
    public void testImplicitFail() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");

        driver.findElement(By.cssSelector("#start button")).click();
        System.out.println(LocalDateTime.now() + " click");


        assertThrows(NoSuchElementException.class, () -> {
            WebElement text = driver.findElement(By.id("finish"));
        });

        System.out.println(LocalDateTime.now() + "Element not found");
    }

    @Test
    public void testExplicitPass(){
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        driver.findElement(By.cssSelector("#start button")).click();
        System.out.println(LocalDateTime.now() + " click");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement text = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("finish"))
        );

        System.out.println(LocalDateTime.now() + " visible");
        System.out.println(text.getText());
    }
}
