package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.GiftCardsPage;
import main.java.org.urbanladder.pages.SearchResultsPage;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.java.basetest.BaseTest;

import java.util.Set;

public class TC17_VerifyGiftCardTheme extends BaseTest {
    @Test
    public void verifyGiftTheme() {

        GiftCardsPage giftCardsPage = new GiftCardsPage(driver);
        Actions actions = new Actions(driver);
        actions.scrollByAmount(0, 600).perform();
        giftCardsPage.selectDesignTheme(properties.getProperty("cardtheme1"));
    }
}