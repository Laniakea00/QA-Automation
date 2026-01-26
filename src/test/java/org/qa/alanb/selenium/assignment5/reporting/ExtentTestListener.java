package org.qa.alanb.selenium.assignment5.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.qa.alanb.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(ExtentTestListener.class);

    private static final ExtentReports extent = ExtentManager.getExtent();
    private static final ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        tlTest.set(test);
        test.info("Test started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        tlTest.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        tlTest.get().fail(result.getThrowable());
        log.error("Extent: FAIL {}. Cause: {}", testName, result.getThrowable().toString(), result.getThrowable());

        String path = ScreenshotUtil.takeScreenshot(BaseTest.getDriver(), testName);
        if (path != null) {
            try {
                tlTest.get().addScreenCaptureFromPath(path);
                log.info("Screenshot saved: {}", path);
            } catch (Exception e) {
                log.error("Failed to attach screenshot to Extent for {}", testName, e);
            }
        } else {
            log.error("Screenshot path is null for {}", testName);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        tlTest.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        log.info("Extent: FLUSH report -> test-output/ExtentReport.html");
    }
}
