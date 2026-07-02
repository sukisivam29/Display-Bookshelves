package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.SearchResultsPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

import java.util.NoSuchElementException;

public class TC1_VerifyBookshelvesFilter extends BaseTest {
    @Test
    public void verifyAllFiltersForBookshelves(){
        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        SoftAssert softAssert = new SoftAssert();
        try {
            urbanLadderHomePage.handlePopUp();
        }
        catch (Exception e){
            logger.info("No pop up found");
        }
        urbanLadderHomePage.enterSearch(properties.getProperty("search.query1"));
        logger.info("Searching for Bookshelves");
        searchResultsPage.clickAllFilters();
        searchResultsPage.clickStorage();
        searchResultsPage.clickOpenStorageType();
        searchResultsPage.clickAvailablity();
        searchResultsPage.clickOutOfStock();
        searchResultsPage.clickPrice();
        searchResultsPage.enterMinPrice(properties.getProperty("min.value"));
        searchResultsPage.enterMaxPrice(properties.getProperty("max.value"));
        searchResultsPage.clickApply();
        searchResultsPage.clickSortBy();
        searchResultsPage.clickHighToLow();
        int firstPrice = searchResultsPage.getFirstProductPrice();
        int maxPrice = Integer.parseInt(properties.getProperty("max.value"));
        logger.info("TC_1 Execution Completed");
        softAssert.assertTrue(
                firstPrice <= maxPrice,
                "Highest priced bookshelf displayed is outside the filter range. Actual Price: "
                        + firstPrice
        );
        softAssert.assertAll();
    }
}
