package main.java.org.urbanladder.pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UrbanLadderHomePage {
    WebDriver driver;
    WebDriverWait wait;

    public UrbanLadderHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "searchInput")
    private WebElement searchBox;

    @FindBy(xpath = "//a[text()='Gift Cards']")
    private WebElement giftCardsButton;

    @FindBy(xpath = "//div[contains(@role, 'button')]/span[text()='New Arrivals']")
    private WebElement newArrivalsButton;

    @FindBy(id = "category-menu-0")
    WebElement newArrivalsMenu;

    @FindBy(xpath = "//div[@id='category-menu-0']//a[text()='Oasis Collection']")
    private WebElement oasisCollectionButton;

    @FindBy(xpath = "//div[@id='category-menu-0']//a[text()='Oasis Collection']/parent::*/following-sibling::ul/li")
    private List<WebElement> oasisCollectionList;

    @FindBy(css = "ct-web-popup-imageonly")
    private WebElement popUp;

//    @FindBy(id = "close")
//    private WebElement popUpClose;

    public void enterSearch(String searchInput) {
        searchBox.sendKeys(searchInput + Keys.ENTER);
    }

    public void clickGiftCards() {
        giftCardsButton.click();
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
//        wait.until(ExpectedConditions.visibilityOf(popUp));
        SearchContext shadowRoot = popUp.getShadowRoot();
        WebElement popUpClose = shadowRoot.findElement(By.id("close"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", popUpClose);
    }
}
