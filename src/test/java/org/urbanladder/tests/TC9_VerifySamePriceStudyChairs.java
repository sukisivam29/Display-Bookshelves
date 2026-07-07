package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.SearchResultsPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import main.java.org.urbanladder.utils.ExcelReaderUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TC9_VerifySamePriceStudyChairs extends BaseTest {

    @Test
    public void verifySamePriceBookshelvesDisplayed() {

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
        List<WebElement> prices = searchResultsPage.getProductPrices();

        softAssert.assertTrue(products.size() > 0, "No products found");
        softAssert.assertTrue(prices.size() > 0, "No prices found");

        Map<String, Integer> priceMap = new HashMap<>();

        for (WebElement price : prices) {
            String priceText = price.getText().split("\n")[0].trim();

            if (!priceText.isEmpty()) {
                priceMap.put(
                        priceText,
                        priceMap.getOrDefault(priceText, 0) + 1
                );
            }
        }

        String duplicatePrice = null;

        for (Map.Entry<String, Integer> entry : priceMap.entrySet()) {
            if (entry.getValue() >= 2) {
                duplicatePrice = entry.getKey();
                break;
            }
        }

        if (duplicatePrice == null) {
            logger.info("No two products have the same price");
            test.info("No two products have the same price");

        } else {
            logger.info("Two or more products have the same price : " + duplicatePrice);
            test.info("Two or more products have the same price : " + duplicatePrice);
            int count = 0;

            for (int i = 0; i < Math.min(products.size(), prices.size()); i++) {
                String currentPrice = prices.get(i).getText().split("\n")[0].trim();

                if (duplicatePrice.equals(currentPrice)) {
                    String productName = products.get(i)
                            .findElement(By.xpath(".//h2"))
                            .getText();
                    count++;
                    logger.info("Product " + count + " : " + productName);
                    test.info("Product " + count + " : " + productName);
                    logger.info("Price : " + currentPrice);
                    test.info("Price : " + currentPrice);

                    if (count == 2) {
                        break;
                    }
                }
            }
        }

        logger.info("TC_9 Execution Completed");
        test.info("TC_9 Execution Completed");

        softAssert.assertAll();
    }
}