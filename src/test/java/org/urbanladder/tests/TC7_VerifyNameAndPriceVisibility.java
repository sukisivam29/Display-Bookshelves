package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.SearchResultsPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

import java.util.List;

public class TC7_VerifyNameAndPriceVisibility extends BaseTest {

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

        urbanLadderHomePage.enterSearch(properties.getProperty("search.query2"));
        searchResultsPage.clickAllFilters();
        searchResultsPage.clickStorage();
        searchResultsPage.clickOpenStorageType();
        searchResultsPage.clickAvailablity();

        searchResultsPage.clickPrice();
        searchResultsPage.enterMinPrice(properties.getProperty("min.value"));
        searchResultsPage.enterMaxPrice(properties.getProperty("max.value"));
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

        logger.info("TC_7 Execution Completed");
        test.info("TC_7 Execution Completed");
        softAssert.assertAll();
    }
}