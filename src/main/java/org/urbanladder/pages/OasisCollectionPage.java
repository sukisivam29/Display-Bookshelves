package main.java.org.urbanladder.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OasisCollectionPage {
    private WebDriver driver;

    public OasisCollectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // This path is Temporary for testing purpose only, I will fix it later
    @FindBy(xpath = "//*[@id=\"custom-page-container\"]/div/table/tbody/tr/td/table[2]/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr/td/div/div/a/img")
    WebElement livingRoomButton;

    public boolean isLivingRoomVisible() {
        return livingRoomButton.isDisplayed();
    }

    public void clickLivingRoomButton() {
        livingRoomButton.click();
    }
}
