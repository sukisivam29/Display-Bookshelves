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

public class TC4_VerifySamePriceBookshelves extends BaseTest {

    @Test
    public void verifySamePriceBookshelvesDisplayed() {

        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        SoftAssert softAssert = new SoftAssert();

        try {
            urbanLadderHomePage.handlePopUp();
        } catch (Exception e) {
            code.logInfo("No popup found");
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

        softAssert.assertTrue(!products.isEmpty(), "No products found");
        softAssert.assertTrue(!prices.isEmpty(), "No prices found");

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
            code.logInfo("No two products have the same price");
        } else {
            code.logInfo("Two or more products have the same price : " + duplicatePrice);
            int count = 0;
            for (int i = 0; i < Math.min(products.size(), prices.size()); i++) {
                String currentPrice = prices.get(i).getText().trim();
                if (duplicatePrice.equals(currentPrice)) {
                    String productName = products.get(i)
                            .findElement(By.xpath(".//h2"))
                            .getText();
                    count++;
                    code.logInfo("Product " + count + " : " + productName);
                    code.logInfo("Price : " + currentPrice);
                    if (count == 2) {
                        break;
                    }
                }
            }
        }

        softAssert.assertAll();
        code.logInfo("TC_4 Execution Completed");
    }
}