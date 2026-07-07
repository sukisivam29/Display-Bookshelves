package test.java.basetest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import main.java.org.urbanladder.utils.ExtentReportManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public static WebDriver driver;
    protected WebDriverWait wait;
    public static Properties properties;
    protected Logger logger;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void setupExtentReport() {
        extent = ExtentReportManager.getExtentReports();
    }

    @BeforeMethod
    @Parameters("browser")
    public void setDriver(String browser) throws IOException {

        logger = main.java.org.urbanladder.utils.LoggerManager.getLogger(this.getClass());
        logger.info("Spinning up Resources");

        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }
        FileReader file =
                new FileReader(".//src//test//resources//config.properties");

        properties = new Properties();
        properties.load(file);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("base.url"));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {

        logger.info("Closing resources");

        if (driver != null) {
            driver.quit();
        }
    }
}