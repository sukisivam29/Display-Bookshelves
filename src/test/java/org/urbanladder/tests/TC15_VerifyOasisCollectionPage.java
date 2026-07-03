package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

public class TC15_VerifyOasisCollectionPage extends BaseTest {
    @Test
    public void verifyOasisCollectionPage() {
        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        SoftAssert softAssert = new SoftAssert();

        try {
            urbanLadderHomePage.handlePopUp();
        }
        catch (Exception e){
            logger.info("No pop up found");
        }

        String currentWindow = driver.getWindowHandle();
        urbanLadderHomePage.hoverOnNewArrivals();
        String oasisButtonUrl = urbanLadderHomePage.oasisCollectionURL();
        softAssert.assertEquals(oasisButtonUrl, properties.getProperty("oasis.url"));
        urbanLadderHomePage.clickOasisCollectionButton();

        String oasisCollectionPageTitle = driver.getTitle();
        softAssert.assertEquals(properties.getProperty("oasis.title"), oasisCollectionPageTitle, "Current Page is not Oasis Collection Page");

        logger.info("TC_15 Execution Completed");
    }
}