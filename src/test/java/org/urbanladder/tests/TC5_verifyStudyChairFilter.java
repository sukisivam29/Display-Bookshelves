package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.SearchResultsPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import org.testng.annotations.Test;
import test.java.basetest.BaseTest;

public class TC5_verifyStudyChairFilter extends BaseTest {
    @Test
    public void verifyAllFilterForStudyChair(){
        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        urbanLadderHomePage.enterSearch(properties.getProperty("search.query2"));
        searchResultsPage.clickAllFilters();
        searchResultsPage.clickPrice();
        searchResultsPage.enterMinPrice(properties.getProperty("min.value"));
        searchResultsPage.enterMaxPrice(properties.getProperty("max.value"));
        searchResultsPage.clickStorage();
        searchResultsPage.clickOpenStorageType();
        searchResultsPage.clickAvailablity();
        searchResultsPage.clickOutOfStock();
        searchResultsPage.clickApply();
    }
}
