package main.java.org.urbanladder.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import test.java.basetest.BaseTest;

public class CodeUtilities extends BaseTest {
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(false);", element);
    }

    public void clickElement(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }
}