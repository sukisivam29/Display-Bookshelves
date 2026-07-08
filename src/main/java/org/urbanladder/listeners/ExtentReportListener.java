package main.java.org.urbanladder.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import main.java.org.urbanladder.utils.ExtentReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test.java.basetest.BaseTest;
import main.java.org.urbanladder.utils.ScreenshotUtil;
import org.apache.logging.log4j.Logger;
import main.java.org.urbanladder.utils.LoggerManager;


public class ExtentReportListener implements ITestListener {

    private static final Logger logger = LoggerManager.getLogger(ExtentReportListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        BaseTest.test = BaseTest.extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        BaseTest.test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest.test.log(Status.FAIL, "Test Failed");
        BaseTest.test.fail(result.getThrowable());
        try {
            String screenshotPath = ScreenshotUtil.takeScreenShot(
                    BaseTest.driver,
                    result.getMethod().getMethodName()
            );
            BaseTest.test.addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            logger.error("Failed to capture screenshot", e);
            BaseTest.test.warning("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        BaseTest.test.log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        BaseTest.extent.flush();
    }
}