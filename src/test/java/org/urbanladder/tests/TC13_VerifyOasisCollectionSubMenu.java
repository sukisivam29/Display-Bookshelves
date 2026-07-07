package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import main.java.org.urbanladder.utils.ExcelReaderUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

import java.util.List;

public class TC13_VerifyOasisCollectionSubMenu extends BaseTest {
    @Test
    public void verifyOasisCollectionIsDisplayed() {
        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        SoftAssert softAssert = new SoftAssert();

        try {
            urbanLadderHomePage.handlePopUp();
        }
        catch (Exception e){
            logger.info("No pop up found");
            test.info("No pop up found");
        }

        List<String> expectedOasisItems = ExcelReaderUtil.getExpectedMenuItems(properties.getProperty("oasis.excelPath"), properties.getProperty("oasis.sheetName"));
        urbanLadderHomePage.hoverOnNewArrivals();
        softAssert.assertTrue(urbanLadderHomePage.oasisCollectionButtonIsDisplayed());

        List<WebElement> oasisSubMenu = urbanLadderHomePage.retrieveOasisCollectionList();

        List<String> actualItems = oasisSubMenu.stream()
                        .map(element -> element.getText().trim())
                                .toList();

        softAssert.assertEquals(actualItems, expectedOasisItems, "The Oasis sub-menu items do not match.");

        softAssert.assertAll();
        logger.info("TC_13 Execution Completed");
        test.info("TC_13 Execution Completed");
    }
}