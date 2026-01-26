package org.qa.alanb.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    protected static final Logger log = LogManager.getLogger(DriverFactory.class.getName());

    public static WebDriver create(String testName) {
        log.info("create WebDriver");

        String RUN_MODE = "cloud"; // или "local"
        String BS_USERNAME = "alanberikzhan_kD4xCr";
        String BS_ACCESS_KEY = "zPSEz4MznceNg9Epj146";

        if (!"cloud".equalsIgnoreCase(RUN_MODE)) {
            return new ChromeDriver();
        }

        String endpoint = "https://hub-cloud.browserstack.com/wd/hub";

        // --- Chrome options ---
        ChromeOptions chromeOptions = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        chromeOptions.setExperimentalOption("prefs", prefs);

        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding");

        // --- W3C capabilities ---
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("browserName", get("BROWSER", "chrome"));
        caps.setCapability("browserVersion", get("BROWSER_VERSION", "latest"));
        caps.setCapability("platformName", get("PLATFORM", "Windows 11"));
        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        // --- BrowserStack options ---
        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("sessionName", testName);
        bstackOptions.put("buildName", get("BUILD_NAME", "Assignment-6"));
        bstackOptions.put("seleniumVersion", "4.16.1");

        caps.setCapability("bstack:options", bstackOptions);

        try {
            String authUrl = endpoint.replace("https://", "https://" + BS_USERNAME + ":" + BS_ACCESS_KEY + "@");
            log.info("BrowserStack URL: {}", authUrl);
            return new RemoteWebDriver(new URL(authUrl), caps);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать RemoteWebDriver для BrowserStack", e);
        }
    }

    private static String get(String key, String def) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.isBlank()) return sys;
        String env = System.getenv(key);
        if (env != null && !env.isBlank()) return env;
        return def;
    }
}
