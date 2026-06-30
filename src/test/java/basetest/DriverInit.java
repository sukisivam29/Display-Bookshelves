package test.java.basetest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class DriverInit {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    public Properties p;

    @BeforeClass
    public void setDriver() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        FileReader file=new FileReader(".//src//test//resources//config.properties");
        p=new Properties();
        p.load(file);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.urbanladder.com/");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public static void takeScreenShot(WebDriver driver, String fileName) throws IOException {
        File screenshotsDir = new File(System.getProperty("user.dir") + "/screenshots");
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(screenshotsDir, fileName + ".png");
        FileHandler.copy(src, destination);
    }
}