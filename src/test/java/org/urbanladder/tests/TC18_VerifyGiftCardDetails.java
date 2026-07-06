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

        String denominationAmount = properties.getProperty("denomination.amount");
        String quantity = properties.getProperty("quantity");
        String deliveryOptions = properties.getProperty("delivery.option");
        String modeOfDelivery = properties.getProperty("delivery.mode");

        giftCardsPage.enterDenominationAmount(denominationAmount);
        giftCardsPage.enterQuantity(quantity);
        giftCardsPage.selectDeliveryOptions(deliveryOptions);
        giftCardsPage.selectModeOfDelivery(modeOfDelivery);

        //Need to Add assertion
    }
}
