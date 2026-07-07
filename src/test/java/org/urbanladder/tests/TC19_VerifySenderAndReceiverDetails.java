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
            test.info("No pop up found");
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

        if(deliveryOptions.equalsIgnoreCase("gift")) {
            softAssert.assertTrue(
                    giftCardsPage.isSendAsGiftSelected(),
                    "Send As Gift option is not selected");
        } else {
            softAssert.assertTrue(
                    giftCardsPage.isBuyForSelfSelected(),
                    "Buy For Self option is not selected");
        }

        if(modeOfDelivery.equalsIgnoreCase("email")) {
            softAssert.assertTrue(
                    giftCardsPage.isEmailModeSelected(),
                    "Email mode is not selected");
        }
        else if(modeOfDelivery.equalsIgnoreCase("sms")) {
            softAssert.assertTrue(
                    giftCardsPage.isSmsModeSelected(),
                    "SMS mode is not selected");
        }
        else {
            softAssert.assertTrue(
                    giftCardsPage.isBothModeSelected(),
                    "Both mode is not selected");
        }

        softAssert.assertEquals(
                giftCardsPage.getSenderFirstname(),
                senderFirstName,
                "Sender First Name mismatch");

        softAssert.assertEquals(
                giftCardsPage.getSenderLastname(),
                senderLastName,
                "Sender Last Name mismatch");

        softAssert.assertEquals(
                giftCardsPage.getSenderEmail(),
                senderEmail,
                "Sender Email mismatch");

        softAssert.assertEquals(
                giftCardsPage.getSenderMobile(),
                senderMobile,
                "Sender Mobile mismatch");

        if(deliveryOptions.equals("gift")) {
            softAssert.assertEquals(
                    giftCardsPage.getReceiverFirstname(),
                    receiverFirstName,
                    "Receiver First Name mismatch");

            softAssert.assertEquals(
                    giftCardsPage.getReceiverLastname(),
                    receiverLastName,
                    "Receiver Last Name mismatch");

            if(modeOfDelivery.equals("email")) {
                softAssert.assertEquals(
                        giftCardsPage.getReceiverEmail(),
                        receiverEmail,
                        "Receiver Email mismatch");
            }

            if(modeOfDelivery.equals("sms")) {
                softAssert.assertEquals(
                        giftCardsPage.getReceiverMobile(),
                        receiverMobile,
                        "Receiver Mobile mismatch");
            }

            if(modeOfDelivery.equals("both")) {
                softAssert.assertEquals(
                        giftCardsPage.getReceiverEmail(),
                        receiverEmail);

                softAssert.assertEquals(
                        giftCardsPage.getReceiverMobile(),
                        receiverMobile);
            }
        }

        softAssert.assertEquals(
                giftCardsPage.getGiftMessage(),
                giftMessage,
                "Gift Message mismatch");

        softAssert.assertAll();
        logger.info("TC_19 Execution Completed");
        test.info("TC_19 Execution Completed");
    }
}