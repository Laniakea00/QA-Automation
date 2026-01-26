package org.qa.alanb.selenium.assignment5.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final Logger log = LogManager.getLogger(LoginPage.class);

    public static final String URL = "https://www.saucedemo.com/";

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By error = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver, 10);
    }

    public void openLogin() {
        log.info("Open Login page");
        open(URL);
    }

    public void login(String user, String pass) {
        log.info("Login attempt with username={}", user);
        type(username, user, true);
        type(password, pass, true);
        click(loginBtn);
    }

    public String getErrorText() {
        log.info("Read login error message");
        return textOf(error);
    }
}
