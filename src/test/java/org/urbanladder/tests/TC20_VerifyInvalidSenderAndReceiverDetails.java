package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.GiftCardsPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import main.java.org.urbanladder.utils.ExcelReaderUtil;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

import java.util.Set;

public class TC20_VerifyInvalidSenderAndReceiverDetails extends BaseTest {
    @Test
    public void verifyEnteringInvalidSenderAndReceiverDetails() {
        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        GiftCardsPage giftCardsPage = new GiftCardsPage(driver);
        SoftAssert softAssert = new SoftAssert();

        try {
            urbanLadderHomePage.handlePopUp();
        } catch (Exception e) {
            code.logInfo("No pop up found");
        }

        String currentWindowId = driver.getWindowHandle();
        urbanLadderHomePage.clickOnGiftCardsPage();

        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!currentWindowId.equals(window)) {
                driver.switchTo().window(window);
            }
        }

        String excelPath = properties.getProperty("excelPath");
        String giftSheetName = properties.getProperty("gift.sheetName");
        String senderAndReceiverSheetName = properties.getProperty("sender.receiver.sheetName");

        String denominationAmount = ExcelReaderUtil.getCellValue(excelPath, giftSheetName, "Denomination");
        String quantity = ExcelReaderUtil.getCellValue(excelPath, giftSheetName, "Quantity");
        String deliveryOptions = ExcelReaderUtil.getCellValue(excelPath, giftSheetName, "DeliveryOption");
        String modeOfDelivery = ExcelReaderUtil.getCellValue(excelPath, giftSheetName, "DeliveryMode");

        String senderFirstName = ExcelReaderUtil.getCellValue(excelPath, senderAndReceiverSheetName, "SenderFirstName");
        String senderLastName = ExcelReaderUtil.getCellValue(excelPath, senderAndReceiverSheetName, "SenderLastName");
        String senderEmail = ExcelReaderUtil.getCellValue(excelPath, senderAndReceiverSheetName, "InvalidEmail");
        String senderMobile = ExcelReaderUtil.getCellValue(excelPath, senderAndReceiverSheetName, "SenderMobile");

        String receiverFirstName = ExcelReaderUtil.getCellValue(excelPath, senderAndReceiverSheetName, "ReceiverFirstName");
        String receiverLastName = ExcelReaderUtil.getCellValue(excelPath, senderAndReceiverSheetName, "ReceiverLastName");
        String receiverEmail = ExcelReaderUtil.getCellValue(excelPath, senderAndReceiverSheetName, "ReceiverEmail");
        String receiverMobile = ExcelReaderUtil.getCellValue(excelPath, senderAndReceiverSheetName, "ReceiverMobile");
        String giftMessage = ExcelReaderUtil.getCellValue(excelPath, senderAndReceiverSheetName, "Message");

        giftCardsPage.enterDenominationAmount(denominationAmount);
        giftCardsPage.enterQuantity(quantity);
        giftCardsPage.selectDeliveryOptions(deliveryOptions);
        giftCardsPage.selectModeOfDelivery(modeOfDelivery);
        giftCardsPage.enterSenderFirstname(senderFirstName);
        giftCardsPage.enterSenderLastname(senderLastName);
        giftCardsPage.enterSenderEmail(senderEmail);
        giftCardsPage.enterSenderMobile(senderMobile);

        if (deliveryOptions.equals("gift")) {
            giftCardsPage.enterReceiverFirstname(receiverFirstName);
            giftCardsPage.enterReceiverLastname(receiverLastName);

            if (modeOfDelivery.equals("email")) {
                giftCardsPage.enterReceiverEmail(receiverEmail);
            } else if (modeOfDelivery.equals("sms")) {
                giftCardsPage.enterReceiverMobile(receiverMobile);
            } else {
                giftCardsPage.enterReceiverEmail(receiverEmail);
                giftCardsPage.enterReceiverMobile(receiverMobile);
            }
        }

        String errorMessage = giftCardsPage.getInvalidEmailText();
        String expectedErrorMessage = ExcelReaderUtil.getCellValue(excelPath, senderAndReceiverSheetName, "ExpectedErrorMessage");
        logger.error(errorMessage);

        giftCardsPage.enterGiftMessage(giftMessage);

        softAssert.assertEquals(errorMessage, expectedErrorMessage);
        softAssert.assertAll();
        code.logInfo("TC_20 Execution Completed");
    }
}
