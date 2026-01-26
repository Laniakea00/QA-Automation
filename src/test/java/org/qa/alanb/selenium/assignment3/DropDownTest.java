package org.qa.alanb.selenium.assignment3;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.qa.alanb.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DropDownTest extends BaseTest {

    @Test
    public void dropDownTest() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/dropdown");

        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(dropdownElement);

        dropdown.selectByVisibleText("Option 1");
        assertEquals("Option 1", dropdown.getFirstSelectedOption().getText());

        dropdown.selectByValue("2");
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

        dropdown.selectByIndex(1);
        assertEquals("Option 1", dropdown.getFirstSelectedOption().getText());

        Thread.sleep(5000);
    }
}
