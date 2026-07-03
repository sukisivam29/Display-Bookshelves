package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.GiftCardsPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

public class TC17_VerifyDesignTemplate extends BaseTest {

    @Test
    public void verifyDesignTemplate(){
        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        GiftCardsPage giftCardsPage = new GiftCardsPage(driver);
        try {
            urbanLadderHomePage.handlePopUp();
        }
        catch (Exception e){
            logger.info("No pop up found");
        }
        String theme = properties.getProperty("theme");
        giftCardsPage.selectDesignTheme(theme);
        logger.info("TC_19 Execution Completed");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                giftCardsPage.isBirthdayThemeDisplayed(),
                "Birthday theme is not displayed"
        );
    }
}
