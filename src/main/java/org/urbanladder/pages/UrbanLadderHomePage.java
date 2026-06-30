package main.java.org.urbanladder.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class UrbanLadderHomePage {
    private WebDriver driver;

    public UrbanLadderHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
