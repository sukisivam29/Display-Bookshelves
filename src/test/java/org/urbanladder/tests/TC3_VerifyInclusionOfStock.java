package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.BookshelvesPage;
import org.testng.annotations.Test;
import test.java.basetest.BaseTest;

public class TC3_VerifyInclusionOfStock extends BaseTest {
    @Test
    public void verifyInclusionOfOutOfStock(){
        BookshelvesPage bookshelvesPage = new BookshelvesPage(driver);
        bookshelvesPage.clickAvailablity();
        bookshelvesPage.clickOutOfStock();
        bookshelvesPage.clickApply();
    }
}
