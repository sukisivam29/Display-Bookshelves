package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

import java.util.Set;

public class TC16_VerifyGiftPage extends BaseTest {
    @Test
    public void navigateToGiftCardPage(){
        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        String currentWindowId = driver.getWindowHandle();
        SoftAssert softAssert = new SoftAssert();
        try {
            urbanLadderHomePage.handlePopUp();
        }
        catch (Exception e){
            logger.info("No pop up found");
        }
        urbanLadderHomePage.clickOnGiftCardsPage();
        Set<String> windows = driver.getWindowHandles();
        for(String window : windows){
            if(!currentWindowId.equals(window)){
                driver.switchTo().window(window);
            }
        }
        System.out.println(driver.getTitle());
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = properties.getProperty("giftPage.url");

        softAssert.assertEquals(currentUrl, expectedUrl);
        logger.info("TC_16  Execution Completed");

    }
}
