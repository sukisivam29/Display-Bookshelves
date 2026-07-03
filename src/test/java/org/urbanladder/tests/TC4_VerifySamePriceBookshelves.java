package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.SearchResultsPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TC4_VerifySamePriceBookshelves extends BaseTest {

    @Test
    public void verifySamePriceBookshelvesDisplayed() {

        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        SoftAssert softAssert = new SoftAssert();

        try {
            urbanLadderHomePage.handlePopUp();
        } catch (Exception e) {
            logger.info("No popup found");
        }

        urbanLadderHomePage.enterSearch(properties.getProperty("search.query1"));

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

        List<WebElement> products = searchResultsPage.getProductList();
        List<WebElement> prices = searchResultsPage.getProductPrices();

        softAssert.assertTrue(products.size() > 0, "No products found");
        softAssert.assertTrue(prices.size() > 0, "No prices found");

        Map<String, Integer> priceMap = new HashMap<>();

        for (WebElement price : prices) {

            String priceText = price.getText().trim();

            if (!priceText.isEmpty()) {
                priceMap.put(priceText,
                        priceMap.getOrDefault(priceText, 0) + 1);
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

        } else {

            logger.info("Two or more products have the same price : " + duplicatePrice);

            int count = 0;

            for (int i = 0; i < Math.min(products.size(), prices.size()); i++) {

                String currentPrice = prices.get(i).getText().trim();

                if (duplicatePrice.equals(currentPrice)) {

                    String productName = products.get(i)
                            .findElement(By.xpath(".//h2"))
                            .getText();

                    count++;

                    logger.info("Product " + count + " : " + productName);
                    logger.info("Price : " + currentPrice);

                    if (count == 2) {
                        break;
                    }
                }
            }
        }

        logger.info("TC_4 Execution Completed");

        softAssert.assertAll();
    }
}