package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.BookshelvesPage;
import main.java.org.urbanladder.pages.UrbanLadderHomePage;
import org.testng.annotations.Test;
import test.java.basetest.BaseTest;

public class TC1_VerifyPrice extends BaseTest {
    @Test
    public void verifyBookshelvesPriceBelow15000() {
        UrbanLadderHomePage urbanLadderHomePage = new UrbanLadderHomePage(driver);
        BookshelvesPage bookshelvesPage = new BookshelvesPage(driver);

        String book = properties.getProperty("search.value");
        urbanLadderHomePage.enterSearch(book);

        bookshelvesPage.clickAllFilters();
        bookshelvesPage.clickPrice();

        String minPrice=properties.getProperty("min.value");
        bookshelvesPage.enterMinPrice(minPrice);

        String maxPrice=properties.getProperty("max.value");
        bookshelvesPage.enterMaxPrice(maxPrice);
    }
}
