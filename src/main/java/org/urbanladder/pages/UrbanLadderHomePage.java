package main.java.org.urbanladder.pages;

import java.time.Duration;
import java.util.List;

import main.java.org.urbanladder.utils.CodeUtilities;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UrbanLadderHomePage extends CodeUtilities {
    WebDriver driver;
    WebDriverWait wait;

    public UrbanLadderHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Gift Cards']")
    private WebElement giftCards;

    @FindBy(id = "searchInput")
    private WebElement searchBox;

    @FindBy(xpath = "//div[contains(@role, 'button')]/span[text()='New Arrivals']")
    private WebElement newArrivalsButton;

    @FindBy(xpath = "//span[normalize-space()='New Arrivals']/ancestor::div[@role='button']/following::div[contains(@id,'category-menu')][1]")
    WebElement newArrivalsMenu;

    @FindBy(xpath = "//a[normalize-space()='Oasis Collection']")
    private WebElement oasisCollectionButton;

    @FindBy(xpath = "//a[normalize-space()='Oasis Collection']/ancestor::h2/following-sibling::ul/li")
    private List<WebElement> oasisCollectionList;

    @FindBy(css = "ct-web-popup-imageonly")
    private WebElement popUp;

    public void clickOnGiftCardsPage() {
        giftCards.click();
    }

    public void enterSearch(String searchInput) {
        searchBox.sendKeys(searchInput + Keys.ENTER);
    }

    public boolean newArrivalsButtonIsVisible() {
        return newArrivalsButton.isDisplayed();
    }

    public void hoverOnNewArrivals() {
        Actions actions = new Actions(driver);
        actions.moveToElement(newArrivalsButton).perform();
    }

    public boolean oasisCollectionButtonIsDisplayed() {
        return oasisCollectionButton.isDisplayed();
    }

    public String oasisCollectionURL() {
        return oasisCollectionButton.getAttribute("href");
    }

    public void clickOasisCollectionButton() {
        oasisCollectionButton.click();
    }

    public List<WebElement> retrieveOasisCollectionList() {
        return oasisCollectionList;
    }

    public boolean newArrivalsMenuIsDisplayed() {
        return newArrivalsMenu.isDisplayed();
    }

    public void handlePopUp() throws Exception {
        SearchContext shadowRoot = popUp.getShadowRoot();
        WebElement popUpClose = shadowRoot.findElement(By.id("close"));
        clickElement(popUpClose);
    }
}
