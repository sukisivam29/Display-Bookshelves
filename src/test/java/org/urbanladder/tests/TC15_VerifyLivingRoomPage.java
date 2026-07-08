package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.OasisCollectionPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

public class TC15_VerifyLivingRoomPage extends BaseTest {
    @Test
    public void verifyLivingRoomPage() {
        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        OasisCollectionPage oasisCollectionPage = new OasisCollectionPage(driver);
        SoftAssert softAssert = new SoftAssert();

        try {
            urbanLadderHomePage.handlePopUp();
        } catch (Exception e) {
            code.logInfo("No pop up found");
        }

        urbanLadderHomePage.hoverOnNewArrivals();
        urbanLadderHomePage.clickOasisCollectionButton();
        softAssert.assertTrue(oasisCollectionPage.isLivingRoomVisible());
        oasisCollectionPage.clickLivingRoomButton();

        String livingRoomPageUrl = driver.getCurrentUrl();
        softAssert.assertEquals(livingRoomPageUrl, properties.getProperty("livingRoom.url"));
        String livingRoomTitle = driver.getTitle();
        softAssert.assertEquals(livingRoomTitle, properties.getProperty("livingRoom.title"));
        code.logInfo("TC_15 Execution Completed");
    }
}