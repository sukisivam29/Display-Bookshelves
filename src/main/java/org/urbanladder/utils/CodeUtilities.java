package main.java.org.urbanladder.utils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import test.java.basetest.BaseTest;

public class CodeUtilities {

    private static final Logger logger =
            LoggerManager.getLogger(CodeUtilities.class);

    public void logInfo(String message) {
        logger.info(message);

        if (BaseTest.test != null) {
            BaseTest.test.info(message);
        }
    }

    public void captureScreenshot(String screenshotName) {
        try {
            String path = ScreenshotUtil.takeScreenShot(
                    BaseTest.driver,
                    screenshotName
            );

            BaseTest.test.info(
                    "Screenshot captured : " + screenshotName
            );

            BaseTest.test.addScreenCaptureFromPath(path);
        } catch (Exception e) {

            logger.error(
                    "Unable to capture screenshot",
                    e
            );
        }
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) BaseTest.driver)
                .executeScript(
                        "arguments[0].scrollIntoView(false);",
                        element
                );
    }

    public void scrollToEnd() {
        ((JavascriptExecutor) BaseTest.driver)
                .executeScript(
                        "window.scrollTo(0,document.body.scrollHeight);"
                );
    }

    public void clickElement(WebElement element) {
        ((JavascriptExecutor) BaseTest.driver)
                .executeScript(
                        "arguments[0].click();",
                        element
                );
    }
}