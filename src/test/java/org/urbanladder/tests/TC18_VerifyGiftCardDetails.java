package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.GiftCardsPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import main.java.org.urbanladder.utils.ExcelReaderUtil;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

import java.util.Set;

public class TC18_VerifyGiftCardDetails extends BaseTest {

    @Test
    public void verifyValidGiftCardDetails() {
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

        String denominationAmount = ExcelReaderUtil.getCellValue(excelPath, giftSheetName, "Denomination");
        String quantity = ExcelReaderUtil.getCellValue(excelPath, giftSheetName, "Quantity");
        String deliveryOptions = ExcelReaderUtil.getCellValue(excelPath, giftSheetName, "DeliveryOption");
        String modeOfDelivery = ExcelReaderUtil.getCellValue(excelPath, giftSheetName, "DeliveryMode");

        giftCardsPage.enterDenominationAmount(denominationAmount);
        giftCardsPage.enterQuantity(quantity);
        giftCardsPage.selectDeliveryOptions(deliveryOptions);
        giftCardsPage.selectModeOfDelivery(modeOfDelivery);

        softAssert.assertEquals(
                giftCardsPage.getDenominationAmount(),
                denominationAmount,
                "Denomination amount mismatch");

        softAssert.assertEquals(
                giftCardsPage.getQuantity(),
                quantity,
                "Quantity mismatch");

        code.captureScreenshot("TC18 - Gift_Card_Filled");

        softAssert.assertAll();
        code.logInfo("TC_18 Execution Completed");
    }
}
