package test.java.org.urbanladder.tests;

import main.java.org.urbanladder.pages.BookshelvesPage;
import org.testng.annotations.Test;
import test.java.basetest.BaseTest;

public class TC2_VerifyStorageType extends BaseTest {
    @Test
    public void verifyBookshelvesStorageType(){
        BookshelvesPage bookshelvesPage = new BookshelvesPage(driver);
//        bookshelvesPage.clickAllFilters();
        bookshelvesPage.clickStorage();
        bookshelvesPage.clickOpenStorageType();
//        bookshelvesPage.clickApply();
    }
}
