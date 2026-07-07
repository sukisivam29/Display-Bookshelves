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

    @FindBy(xpath = "//a[contains(@href,'living-room-oasis-collection')]/img")
    WebElement livingRoomButton;

    public boolean isLivingRoomVisible() {
        return livingRoomButton.isDisplayed();
    }

    public void clickLivingRoomButton() {
        livingRoomButton.click();
    }
}
