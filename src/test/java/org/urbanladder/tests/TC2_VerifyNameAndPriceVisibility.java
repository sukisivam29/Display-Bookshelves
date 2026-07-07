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

public class TC2_VerifyNameAndPriceVisibility extends BaseTest {

    @Test
    public void verifyNameAndPriceVisibility() {

        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        SoftAssert softAssert = new SoftAssert();

        try {
            urbanLadderHomePage.handlePopUp();
        } catch (Exception e) {
            logger.info("No popup found");
            test.info("No popup found");
        }

        String searchText = ExcelReaderUtil.getCellValue(
                properties.getProperty("excelPath"),
                properties.getProperty("book.sheetName"),
                "SearchText");

        String minPrice = ExcelReaderUtil.getCellValue(
                properties.getProperty("excelPath"),
                properties.getProperty("book.sheetName"),
                "MinPrice");

        String maxPrice = ExcelReaderUtil.getCellValue(
                properties.getProperty("excelPath"),
                properties.getProperty("book.sheetName"),
                "MaxPrice");

        urbanLadderHomePage.enterSearch(searchText);
        searchResultsPage.clickAllFilters();
        searchResultsPage.clickStorage();
        searchResultsPage.clickOpenStorageType();
        searchResultsPage.clickAvailablity();
        searchResultsPage.clickOutOfStock();
        searchResultsPage.clickPrice();
        searchResultsPage.enterMinPrice(minPrice);
        searchResultsPage.enterMaxPrice(maxPrice);
        searchResultsPage.clickApply();
        searchResultsPage.clickSortBy();
        searchResultsPage.clickHighToLow();

        List<WebElement> products = searchResultsPage.getProductList();
        List<WebElement> prices = searchResultsPage.getProductPrices();

        softAssert.assertTrue(products.size() > 0, "No products displayed");
        softAssert.assertTrue(prices.size() > 0, "No prices displayed");

        try {
            WebElement firstProduct = products.get(0);
            WebElement firstProductName = firstProduct.findElement(By.xpath(".//h2"));
            softAssert.assertTrue(
                    firstProductName.isDisplayed(),
                    "First product name is not visible"
            );
            softAssert.assertFalse(
                    firstProductName.getText().trim().isEmpty(),
                    "First product name is empty"
            );
            logger.info("First Product Name : " + firstProductName.getText());
            test.info("First Product Name : " + firstProductName.getText());
        } catch (Exception e) {
            softAssert.fail("Unable to fetch first product name");
        }

        try {
            WebElement firstPrice = prices.get(0);
            softAssert.assertTrue(
                    firstPrice.isDisplayed(),
                    "First product price is not visible"
            );
            softAssert.assertFalse(
                    firstPrice.getText().trim().isEmpty(),
                    "First product price is empty"
            );
            logger.info("First Product Price : " + firstPrice.getText());
            test.info("First Product Price : " + firstPrice.getText());
        } catch (Exception e) {
            softAssert.fail("Unable to fetch first product price");
        }

        logger.info("TC_2 Execution Completed");
        test.info("TC_2 Execution Completed");
        softAssert.assertAll();
    }
}