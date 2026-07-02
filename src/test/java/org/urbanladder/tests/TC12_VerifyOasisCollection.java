package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import org.openqa.selenium.devtools.v85.backgroundservice.BackgroundService;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

public class TC12_VerifyOasisCollection extends BaseTest {
    @Test
    public void verifyOasisCollectionIsDisplayed() {
        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(urbanLadderHomePage.newArrivalsButtonIsVisible());
        urbanLadderHomePage.hoverOnNewArrivals();

        softAssert.assertTrue(urbanLadderHomePage.oasisCollectionButtonIsDisplayed());

        String oasisButtonUrl = urbanLadderHomePage.oasisCollectionURL();
        softAssert.assertEquals(oasisButtonUrl, properties.getProperty("oasis.url"));

        softAssert.assertAll();
    }
}
