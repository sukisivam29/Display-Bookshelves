package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.GiftCardsPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import main.java.org.urbanladder.utils.ExcelReaderUtil;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

import java.util.Set;

public class TC17_VerifyDesignTemplate extends BaseTest {

    @Test
    public void verifyDesignTemplate() {
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

        String theme = ExcelReaderUtil.getCellValue(
                properties.getProperty("excelPath"),
                properties.getProperty("gift.sheetName"),
                "Theme");
        giftCardsPage.selectDesignTheme(theme);

        softAssert.assertTrue(
                giftCardsPage.isBirthdayThemeDisplayed(),
                "Birthday theme is not displayed"
        );

        code.logInfo("TC_17 Execution Completed");
    }
}