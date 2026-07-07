package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.SearchResultsPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import main.java.org.urbanladder.utils.ExcelReaderUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

import java.util.List;
import java.util.Set;

public class TC10_VerifyDeliveryLocations extends BaseTest {

    @Test
    public void verifyMaharashtraDeliveryLocations() {

        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        SoftAssert softAssert = new SoftAssert();

        try {
            urbanLadderHomePage.handlePopUp();
        } catch (Exception e) {
            logger.info("No popup found");
        }

        urbanLadderHomePage.enterSearch(properties.getProperty("search.query2"));

        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");

        String parentWindow = driver.getWindowHandle();
        searchResultsPage.clickMoreLink();
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        List<String> expectedCities =
                ExcelReaderUtil.getExpectedMenuItems(
                        properties.getProperty("cities.excelPath"),
                        properties.getProperty("cities.sheetName"));

        List<String> actualCities =
                searchResultsPage.getMaharashtraCities()
                        .stream()
                        .map(element -> element.getText().trim())
                        .filter(city -> !city.isEmpty())
                        .toList();

        logger.info("Actual Maharashtra Cities : " + actualCities);

        softAssert.assertTrue(
                actualCities.containsAll(expectedCities),
                "One or more Maharashtra cities from Excel are missing on UI");


        softAssert.assertTrue(
                actualCities.contains("Pune"),
                "Pune is not present in Maharashtra cities list");


        logger.info("Pune city verified successfully");
        softAssert.assertAll();
        logger.info("TC_10 Execution Completed");
    }
}