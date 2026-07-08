package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.SearchResultsPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import main.java.org.urbanladder.utils.CodeUtilities;
import main.java.org.urbanladder.utils.ExcelReaderUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.java.basetest.BaseTest;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class TC10_VerifyDeliveryLocations extends BaseTest {

    @Test
    public void verifyMaharashtraDeliveryLocations() {

        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        SoftAssert softAssert = new SoftAssert();
        CodeUtilities code = new CodeUtilities();

        try {
            urbanLadderHomePage.handlePopUp();
        } catch (Exception e) {
            code.logInfo("No pop up found");
        }

        String searchText = ExcelReaderUtil.getCellValue(
                properties.getProperty("excelPath"),
                properties.getProperty("chair.sheetName"),
                "SearchText"
        );

        urbanLadderHomePage.enterSearch(searchText);
        code.scrollToEnd();
        String parentWindow = driver.getWindowHandle();
        searchResultsPage.clickMoreLink();
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        code.captureScreenshot("TC10 - Delivery_Location_Page_Opened");

        logger.info("Current URL : {}", driver.getCurrentUrl());
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(driver ->
                        !searchResultsPage.getMaharashtraCities().isEmpty());

        List<String> expectedCities =
                ExcelReaderUtil.getExpectedMenuItems(
                        properties.getProperty("excelPath"),
                        properties.getProperty("cities.sheetName")
                );

        List<String> actualCities =
                searchResultsPage.getMaharashtraCities()
                        .stream()
                        .map(WebElement::getText)
                        .map(String::trim)
                        .filter(city -> !city.isEmpty())
                        .toList();

        logger.info("Actual Maharashtra Cities : {}", actualCities);

        softAssert.assertTrue(
                actualCities.containsAll(expectedCities),
                "One or more Maharashtra cities from Excel are missing on UI"
        );

        softAssert.assertTrue(
                actualCities.contains("Pune"),
                "Pune is not present in Maharashtra cities list"
        );

        code.logInfo("Pune city verified successfully");
        code.logInfo("TC_10 Execution Completed");
        softAssert.assertAll();
    }
}