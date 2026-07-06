package test.java.basetest;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import com.aventstack.extentreports.ExtentReports;
import main.java.org.urbanladder.utils.ExtentReportManager;

public class BaseTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    public static Properties properties;
    protected Logger logger;
    public static ExtentReports extent;

    @BeforeMethod
    public void setDriver() throws IOException {
        logger = main.java.org.urbanladder.utils.LoggerManager.getLogger(this.getClass());
        driver = new ChromeDriver();
        FileReader file = new FileReader(".//src//test//resources//config.properties");
        properties = new Properties();
        properties.load(file);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("base.url"));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        logger.info("Spinning up Resources");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        logger.info("Closing resources");
    }

    @BeforeSuite
    public void setupExtentReport() {
        extent = ExtentReportManager.getExtentReports();
    }
}