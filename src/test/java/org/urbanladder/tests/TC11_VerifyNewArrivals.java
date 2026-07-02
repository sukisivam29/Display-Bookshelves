package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

public class TC11_VerifyNewArrivals extends BaseTest {
    @Test
    public void verifyNewArrivalsMenuVisible() {
        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(urbanLadderHomePage.newArrivalsButtonIsVisible());

        urbanLadderHomePage.hoverOnNewArrivals();
        softAssert.assertTrue(urbanLadderHomePage.newArrivalsMenuIsDisplayed());

        softAssert.assertAll();
    }
}
