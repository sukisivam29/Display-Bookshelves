package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.java.basetest.BaseTest;

public class TC1_GetURL extends BaseTest {
    @Test
    public void verify_url() throws InterruptedException {
        String url = properties.getProperty("url");
        String baseUrl = driver.getCurrentUrl();
        Assert.assertEquals(baseUrl, url,"This is not the Urban Ladder Home Page");
        UrbanLadderHomePage homePage = new UrbanLadderHomePage(driver);
        String baseUrl1 = driver.getCurrentUrl();
        Assert.assertNotEquals(baseUrl1, url);
        System.out.println("URL verification completed");
    }
}
