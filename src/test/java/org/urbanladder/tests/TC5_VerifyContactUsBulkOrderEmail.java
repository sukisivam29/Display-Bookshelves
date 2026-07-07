package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.SearchResultsPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import main.java.org.urbanladder.utils.CodeUtilities;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

import java.util.Set;

public class TC5_VerifyContactUsBulkOrderEmail extends BaseTest {

    @Test
    public void verifyBulkOrderEmail() {

        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        SoftAssert softAssert = new SoftAssert();
        CodeUtilities code=new CodeUtilities();

        try {
            urbanLadderHomePage.handlePopUp();
        }
        catch (Exception e) {
            logger.info("No popup found");
            test.info("No popup found");
        }

        code.scrollToEnd();
        searchResultsPage.clickContactUs();
        String parentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        String actualEmail = searchResultsPage.getBulkOrderEmail();
        logger.info("Bulk Order Email : " + actualEmail);
        test.info("Bulk Order Email : " + actualEmail);

        softAssert.assertEquals(
                actualEmail,
                "bulk@urbanladder.com",
                "Incorrect bulk order email displayed");


        logger.info("TC_5 Execution Completed");
        test.info("TC_5 Execution Completed");
        softAssert.assertAll();
    }
}