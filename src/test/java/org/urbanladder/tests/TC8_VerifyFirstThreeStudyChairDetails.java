package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.SearchResultsPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import main.java.org.urbanladder.utils.ExcelReaderUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

import java.util.List;

public class TC8_VerifyFirstThreeStudyChairDetails extends BaseTest {

    @Test
    public void verifyFirstThreeBookshelfDetails() {

        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        SoftAssert softAssert = new SoftAssert();

        try {
            urbanLadderHomePage.handlePopUp();
        } catch (Exception e) {
            code.logInfo("No pop up found");
        }

        String excel= properties.getProperty("excelPath");
        String sheet = properties.getProperty("book.sheetName");
        String searchText = ExcelReaderUtil.getCellValue(excel,sheet,"SearchText");
        String minPrice = ExcelReaderUtil.getCellValue(excel,sheet,"MinPrice");
        String maxPrice = ExcelReaderUtil.getCellValue(excel,sheet,"MaxPrice");

        urbanLadderHomePage.enterSearch(searchText);
        searchResultsPage.clickAllFilters();
        searchResultsPage.clickStorage();
        searchResultsPage.clickOpenStorageType();
        searchResultsPage.clickAvailablity();
        searchResultsPage.clickPrice();
        searchResultsPage.enterMinPrice(minPrice);
        searchResultsPage.enterMaxPrice(maxPrice);
        searchResultsPage.clickApply();
        searchResultsPage.clickSortBy();
        searchResultsPage.clickHighToLow();

        List<WebElement> products = searchResultsPage.getProductList();

        softAssert.assertTrue(products.size() >= 3,
                "Less than 3 bookshelf products available");

        for (int i = 0; i < 3; i++) {
            WebElement product = products.get(i);
            try {
                WebElement productName = product.findElement(By.xpath(".//h2"));
                softAssert.assertTrue(
                        productName.isDisplayed(),
                        "Product name not visible for product " + (i + 1)
                );
                softAssert.assertFalse(
                        productName.getText().trim().isEmpty(),
                        "Product name missing for product " + (i + 1));
                code.logInfo("Bookshelf " + (i + 1) + " : " + productName.getText());
            } catch (Exception e) {
                softAssert.fail(
                        "Unable to capture details for bookshelf " + (i + 1));
            }
        }

        softAssert.assertAll();
        code.logInfo("TC_8 Execution Completed");
    }
}