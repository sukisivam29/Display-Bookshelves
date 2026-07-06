package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

public class TC11_VerifyNewArrivals extends BaseTest {
    @Test
    public void verifyNewArrivalsMenuVisible() {
        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        SoftAssert softAssert = new SoftAssert();

        try {
            urbanLadderHomePage.handlePopUp();
        }
        catch (Exception e){
            logger.info("No pop found");
        }

        softAssert.assertTrue(urbanLadderHomePage.newArrivalsButtonIsVisible());

        urbanLadderHomePage.hoverOnNewArrivals();
        softAssert.assertTrue(urbanLadderHomePage.newArrivalsMenuIsDisplayed());

        softAssert.assertAll();
        logger.info("TC_11 Execution Completed");
    }
}
