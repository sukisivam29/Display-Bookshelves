package main.java.org.urbanladder.pages;

import main.java.org.urbanladder.utils.CodeUtilities;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage extends CodeUtilities {
    WebDriver driver;
    WebDriverWait wait;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Gift Cards']")
    private WebElement giftCards;

    @FindBy(xpath = "//div[text()='ALL FILTERS']")
    WebElement allFilters;

    @FindBy(xpath = "//div[@aria-controls='dropdown-menu-sort-by']")
    WebElement sortBy;

    @FindBy(xpath = "//div[@data-testid='plp-sort-option-1']")
    WebElement highToLow;

    @FindBy(xpath = "//div[@aria-label='Storage Type']")
    WebElement storageType;

    @FindBy(xpath = "//div[text()='Open Storage']")
    WebElement openStorageType;

    @FindBy(xpath = "//div[@aria-label='Price']")
    WebElement price;

    @FindBy(xpath = "(//div[@id='inputfields']//input[@type='text'])[1]")
    WebElement input1;

    @FindBy(xpath = "(//div[@id='inputfields']//input[@type='text'])[2]")
    WebElement input2;

    @FindBy(xpath = "//div[@aria-label='Availability']")
    WebElement availability;

    @FindBy(xpath = "//div[@aria-label='Out Of Stock'][.//p/div/div[contains(text(),'Out Of Stock')]]")
    WebElement outOfStock;

    @FindBy(xpath = "//button[@data-testid='plp-filter-apply-button']")
    WebElement apply;

    @FindBy(xpath = "(//div[@class='MniCX']//div[@class='HOVM7']//div/div[@data-testid='plp-product-card'])")
    List<WebElement> productList;

    @FindBy(xpath = "(//div[@class='MniCX']//div[@class='HOVM7']//div/div[@data-testid='plp-product-card']/div[@class='o0mbO']//span[@class='sr-only'])[1]")
    WebElement firstProductPrice;

    @FindBy(xpath = "//div[@class='ug1_C']")
    List<WebElement> productPrices;

    @FindBy(linkText = "Contact Us")
    WebElement contactUsLink;

    @FindBy(xpath = "//a[contains(@href,'bulk@urbanladder.com')]")
    WebElement bulkOrderEmail;

    @FindBy(xpath = "//a[@href='/page/cities-we-deliver-in']")
    WebElement moreLink;

    @FindBy(xpath = "//*[text()='Maharashtra']")
    WebElement maharashtraHeader;

    @FindBy(xpath = "//strong[text()='Maharashtra']/ancestor::td/following-sibling::td//a")
    List<WebElement> maharashtraCities;

    @FindBy(xpath = "//a[contains(text(),'Pune')]")
    WebElement puneCity;

    public void clickOnGiftCardsPage(){
        giftCards.click();
    }

    public void clickAllFilters() {
        clickElement(allFilters);
    }

    public void clickSortBy() {
        sortBy.click();
    }

    public void clickHighToLow() {
        highToLow.click();
    }

    public void clickStorage() {
        storageType.click();
    }

    public void clickOpenStorageType() {
        openStorageType.click();
    }

    public void clickPrice() {
        price.click();
    }

    public void enterMinPrice(String minPrice) {
        input1.clear();
        input1.sendKeys(minPrice + Keys.ENTER);
    }

    public void enterMaxPrice(String maxPrice) {
        input2.clear();
        input2.sendKeys(maxPrice);
        Actions action = new Actions(driver);
        action.click(input1).perform();
    }

    public void clickAvailablity() {
        availability.click();
    }

    public void clickOutOfStock() {
        outOfStock.click();
    }

    public void clickApply() {
        apply.click();
    }

    public int getFirstProductPrice() {
        String priceText = firstProductPrice.getAttribute("textContent")
                .replaceAll("[^0-9]", "")
                .trim();
        return Integer.parseInt(priceText);
    }

    public List<WebElement> getProductList() {
        return productList;
    }

    public List<WebElement> getProductPrices() {
        return productPrices;
    }

    public void clickContactUs() {
        contactUsLink.click();
    }

    public String getBulkOrderEmail() {
        return bulkOrderEmail.getText();
    }

    public void clickMoreLink() {
        moreLink.click();
    }

    public boolean isMaharashtraDisplayed() {
        return maharashtraHeader.isDisplayed();
    }

    public boolean isPuneDisplayed() {
        return puneCity.isDisplayed();
    }

    public List<WebElement> getMaharashtraCities() {
        return maharashtraCities;
    }


}
