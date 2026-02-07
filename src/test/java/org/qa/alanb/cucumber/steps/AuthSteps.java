package org.qa.alanb.cucumber.steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static org.qa.alanb.cucumber.hooks.Hooks.driver;

public class AuthSteps {

    private WebDriverWait wait;

    @Given("I open the login page")
    public void openLoginPage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @When("I login with username {string} and password {string}")
    public void login(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("radius")).click();
    }

    @Then("I should see success message containing {string}")
    public void successMessageContains(String expectedText) {
        WebElement flash = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("flash"))
        );
        Assert.assertTrue(flash.getText().contains(expectedText));
    }

    @When("I logout")
    public void logout() {
        driver.findElement(By.xpath("//a[@href='/logout']")).click();
    }

    @Then("I should be redirected to the login page")
    public void redirectedToLogin() {
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}
