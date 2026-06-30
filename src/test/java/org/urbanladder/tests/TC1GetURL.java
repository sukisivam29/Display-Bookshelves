package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.java.basetest.DriverInit;

public class TC1GetURL extends DriverInit {
    @Test
    public void verify_url() throws InterruptedException {
        String url = p.getProperty("geturl");
        String baseUrl = driver.getCurrentUrl();
        Assert.assertEquals(baseUrl, url,"This is not the Urban Ladder Home Page");
        UrbanLadderHomePage homePage = new UrbanLadderHomePage(driver);
        String baseUrl1 = driver.getCurrentUrl();
        Assert.assertNotEquals(baseUrl1, url);
        System.out.println("URL verification completed");
    }
}
