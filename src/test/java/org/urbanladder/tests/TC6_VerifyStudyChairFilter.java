package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.SearchResultsPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import main.java.org.urbanladder.utils.ExcelReaderUtil;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

public class TC6_VerifyStudyChairFilter extends BaseTest {
    @Test
    public void verifyAllFilterForStudyChair(){
        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        SoftAssert softAssert = new SoftAssert();

        try {
            urbanLadderHomePage.handlePopUp();
        }
        catch (Exception e){
            logger.info("No pop up found");
            test.info("No pop up found");
        }

        String searchText = ExcelReaderUtil.getCellValue(
                properties.getProperty("excelPath"),
                properties.getProperty("chair.sheetName"),
                "SearchText");

        String minPrice = ExcelReaderUtil.getCellValue(
                properties.getProperty("excelPath"),
                properties.getProperty("chair.sheetName"),
                "MinPrice");

        String maxPrice = ExcelReaderUtil.getCellValue(
                properties.getProperty("excelPath"),
                properties.getProperty("chair.sheetName"),
                "MaxPrice");

        urbanLadderHomePage.enterSearch(searchText);
        searchResultsPage.clickAllFilters();
        searchResultsPage.clickPrice();
        searchResultsPage.enterMinPrice(minPrice);
        searchResultsPage.enterMaxPrice(maxPrice);
        searchResultsPage.clickStorage();
        searchResultsPage.clickOpenStorageType();
        searchResultsPage.clickAvailablity();
        searchResultsPage.clickOutOfStock();
        searchResultsPage.clickApply();

        int firstPrice = searchResultsPage.getFirstProductPrice();
        int max = Integer.parseInt(maxPrice);

        softAssert.assertTrue(
                firstPrice <= max,
                "Highest priced study chair displayed is outside the filter range. Actual Price: "
                        + firstPrice
        );
        softAssert.assertAll();
        logger.info("TC_6 Execution Completed");
        test.info("TC_6 Execution Completed");
    }
}