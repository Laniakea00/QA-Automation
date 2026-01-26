package org.qa.alanb.selenium.assignment5.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Path;

public class ExtentManager {
    private static ExtentReports extent;

    public static synchronized ExtentReports getExtent() {
        if (extent == null) {
            try {
                Files.createDirectories(Path.of("test-output"));
            } catch (Exception ignored) {}

            ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Project", "QA Automation");
        }
        return extent;
    }
}
