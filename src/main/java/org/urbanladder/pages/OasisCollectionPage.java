package main.java.org.urbanladder.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class OasisCollectionPage {
    private WebDriver driver;

    public OasisCollectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
