package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.GiftCardsPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

public class TC19_VerifyDesignTemplate extends BaseTest {

    @Test
    public void verifyDesignTemplate(){
        GiftCardsPage giftCardsPage = new GiftCardsPage(driver);
        String theme = properties.getProperty("theme");
        giftCardsPage.selectDesignTheme(theme);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                giftCardsPage.isBirthdayThemeDisplayed(),
                "Birthday theme is not displayed"
        );
    }
}
