package org.qa.alanb;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.qa.alanb.driver.DriverFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseTest {

    protected static WebDriver driver;
    protected final Logger log = LogManager.getLogger(this.getClass());

    protected String baseUrl = "https://www.saucedemo.com/";

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void setUpClass() {
        boolean headless = !"0".equals(System.getenv().getOrDefault("HEADLESS", "0"));
        String RUN_MODE="cloud"; // local or cloud

        if ("cloud".equalsIgnoreCase(RUN_MODE)) {
            driver = DriverFactory.create("Assignment-6");
        } else {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.default_content_setting_values.notifications", 2);
            options.setExperimentalOption("prefs", prefs);

            options.addArguments("--disable-notifications");
            options.addArguments("--incognito");
            options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding");

            if (headless) options.addArguments("--headless=new");
            options.addArguments("--window-size=1400,900");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");

            driver = new ChromeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        log.info("WebDriver setup: browser=Chrome, headless={}, window=1400x900", headless);
    }

    @BeforeMethod
    public void beforeEach(Method method) {
        log.info("TEST START: {}", method.getName());

        driver.manage().deleteAllCookies();
        driver.get(baseUrl);

        // очистка состояния приложения
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.clear(); window.sessionStorage.clear();");
        driver.navigate().refresh();

        slowMo();
    }


    @AfterMethod(alwaysRun = true)
    public void afterEach(ITestResult result) {
        String status = switch (result.getStatus()) {
            case ITestResult.SUCCESS -> "PASS";
            case ITestResult.FAILURE -> "FAIL";
            case ITestResult.SKIP -> "SKIP";
            default -> "UNKNOWN";
        };
        log.info("TEST END: {} => {}", result.getName(), status);
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        try {
            if ("cloud".equalsIgnoreCase(System.getenv("RUN_ENV"))) {
                String status = "passed"; // если упал — Sauce сам пометит failed
                ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + status);
            }
        } finally {
            if (driver != null) driver.quit();
            log.info("WebDriver teardown: quit()");
        }
    }

    protected void slowMo() {
        String s = System.getenv().getOrDefault("SLOWMO", "0");
        try {
            double seconds = Double.parseDouble(s);
            if (seconds > 0) Thread.sleep((long) (seconds * 1000));
        } catch (Exception ignored) {}
    }
}
