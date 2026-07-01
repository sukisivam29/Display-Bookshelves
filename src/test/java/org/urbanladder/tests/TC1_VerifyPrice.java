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
        urbanLadderHomePage.enterSearch("Bookshelves");
        bookshelvesPage.clickAllFilters();
        bookshelvesPage.clickPrice();
        bookshelvesPage.enterMinPrice("0");
        bookshelvesPage.enterMaxPrice("15000");
    }
}
