package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import main.java.org.urbanladder.utils.ExcelReaderUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

import java.util.List;
import java.util.stream.Collectors;

public class TC13_VerifyOasisCollectionSubMenu extends BaseTest {
    @Test
    public void verifyOasisCollectionIsDisplayed() {
        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        SoftAssert softAssert = new SoftAssert();

        List<String> expectedOasisItems = ExcelReaderUtil.getExpectedMenuItems(properties.getProperty("oasis.excel"), "MenuData");

        urbanLadderHomePage.hoverOnNewArrivals();
        softAssert.assertTrue(urbanLadderHomePage.oasisCollectionButtonIsDisplayed());

        List<WebElement> oasisSubMenu = urbanLadderHomePage.retrieveOasisCollectionList();

        List<String> actualItems = oasisSubMenu.stream()
                        .map(element -> element.getText().trim())
                                .toList();

        softAssert.assertEquals(actualItems, expectedOasisItems, "The Oasis sub-menu items do not match.");

        softAssert.assertAll();
    }
}
