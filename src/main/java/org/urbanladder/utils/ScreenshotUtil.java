package main.java.org.urbanladder.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    public static String takeScreenShot(WebDriver driver, String fileName) throws IOException {
        File screenshotsDir = new File(System.getProperty("user.dir") + "/reports/screenshots");
        screenshotsDir.mkdirs();
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String path = screenshotsDir.getAbsolutePath() + "/" + fileName + "_" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        FileHandler.copy(src, destination);
        return "screenshots/" + destination.getName();
    }
}