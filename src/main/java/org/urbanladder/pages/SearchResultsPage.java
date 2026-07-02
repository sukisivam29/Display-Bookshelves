package main.java.org.urbanladder.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage {
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

    @FindBy(xpath = "//div[@aria-label='Storage Type']")
    WebElement storageType;

    @FindBy(xpath = "//div[text()='Open Storage']")
    WebElement openStorageType;

    @FindBy(xpath = "//div[@aria-label='Price']")
    WebElement price;

    @FindBy(xpath="(//div[@id='inputfields']//input[@type='text'])[1]")
    WebElement input1;

    @FindBy(xpath="(//div[@id='inputfields']//input[@type='text'])[2]")
    WebElement input2;

    @FindBy(xpath = "//div[@aria-label='Availability']")
    WebElement availability;

    @FindBy(id = "accordion-panel-availability")
    WebElement outOfStock;

    @FindBy(xpath = "//button[@data-testid='plp-filter-apply-button']")
    WebElement apply;

    public void clickOnGiftCardsPage(){
        giftCards.click();
    }

    public void clickAllFilters(){
        allFilters.click();
    }

    public void clickStorage(){
        storageType.click();
    }

    public void clickOpenStorageType(){
        openStorageType.click();
    }

    public void clickPrice(){
        price.click();
    }

    public void enterMinPrice(String minPrice){
        input1.clear();
        input1.sendKeys(minPrice+ Keys.ENTER);
    }

    public void enterMaxPrice(String maxPrice){
        input2.clear();
        input2.sendKeys(maxPrice+Keys.ENTER);
    }

    public void clickAvailablity(){
        availability.click();
    }

    public void clickOutOfStock(){
        outOfStock.click();
    }

    public void clickApply(){
//        apply.click();
//        wait.until(ExpectedConditions.elementToBeClickable(apply));
        apply.click();
    }
}
