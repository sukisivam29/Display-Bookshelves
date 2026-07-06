package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.GiftCardsPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

import java.util.Set;

public class TC19_VerifySenderAndReceiverDetails extends BaseTest {

    @Test
    public void verifyEnteringSenderAndReceiverDetails(){
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
        String amount = properties.getProperty("quantity");
        String deliveryOptions = properties.getProperty("delivery.option");
        String modeOfDelivery = properties.getProperty("delivery.mode");
        String senderFirstName = properties.getProperty("sender.firstname");
        String senderLastName = properties.getProperty("sender.lastname");
        String senderEmail = properties.getProperty("sender.email");
        String senderMobile = properties.getProperty("sender.mobile");
        String receiverFirstName = properties.getProperty("receiver.firstname");
        String receiverLastName = properties.getProperty("receiver.lastname");
        String receiverEmail = properties.getProperty("receiver.email");
        String receiverMobile = properties.getProperty("receiver.mobile");
        String giftMessage = properties.getProperty("message");

        giftCardsPage.enterDenominationAmount(denominationAmount);
        giftCardsPage.enterQuantity(amount);
        giftCardsPage.selectDeliveryOptions(deliveryOptions);
        giftCardsPage.selectModeOfDelivery(modeOfDelivery);
        giftCardsPage.enterSenderFirstname(senderFirstName);
        giftCardsPage.enterSenderLastname(senderLastName);
        giftCardsPage.enterSenderEmail(senderEmail);
        giftCardsPage.enterSenderMobile(senderMobile);
        if(deliveryOptions.equals("gift")){
            giftCardsPage.enterReceiverFirstname(receiverFirstName);
            giftCardsPage.enterReceiverLastname(receiverLastName);
            if(modeOfDelivery.equals("email")){
                giftCardsPage.enterReceiverEmail(receiverEmail);
            }
            else if(modeOfDelivery.equals("sms")){
                giftCardsPage.enterReceiverMobile(receiverMobile);
            }
            else{
                giftCardsPage.enterReceiverEmail(receiverEmail);
                giftCardsPage.enterReceiverMobile(receiverMobile);
            }
        }
        giftCardsPage.enterGiftMessage(giftMessage);
//        giftCardsPage.clickPreviewGiftCard();

        //Need to Add Assertion
    }
}
