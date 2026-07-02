package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.java.basetest.BaseTest;

import java.util.Set;

public class TC16_verifyGiftPage extends BaseTest {
    @Test
    public void navigateToGiftCardPage(){
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        String currentWindowId = driver.getWindowHandle();
        searchResultsPage.clickOnGiftCardsPage();
        Set<String> windows = driver.getWindowHandles();
        for(String window : windows){
            if(!currentWindowId.equals(window)){
                driver.switchTo().window(window);
            }
        }
        System.out.println(driver.getTitle());
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = properties.getProperty("giftpage.url");
        Assert.assertEquals(currentUrl, expectedUrl);
    }
}