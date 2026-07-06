package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.GiftCardsPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

import java.util.Set;

public class TC18_VerifyGiftCardDetails extends BaseTest {

    @Test
    public void verifyValidGiftCardDetails(){
        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        GiftCardsPage giftCardsPage = new GiftCardsPage(driver);
        SoftAssert softAssert = new SoftAssert();
        try {
            urbanLadderHomePage.handlePopUp();
        }
        catch (Exception e){
            logger.info("No pop up found");
        }
        String currentWindowId = driver.getWindowHandle();
        urbanLadderHomePage.clickOnGiftCardsPage();
        Set<String> windows = driver.getWindowHandles();
        for(String window : windows){
            if(!currentWindowId.equals(window)){
                driver.switchTo().window(window);
            }
        }
        giftCardsPage.enterDenominationAmount(properties.getProperty("denomination.amount"));
        giftCardsPage.enterQuantity(properties.getProperty("quantity"));
        giftCardsPage.selectDeliveryOptions(properties.getProperty("delivery.option"));
        giftCardsPage.selectModeOfDelivery(properties.getProperty("delivery.mode"));

        //Need to Add assertion
    }
}
