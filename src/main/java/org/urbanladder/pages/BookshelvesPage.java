package main.java.org.urbanladder.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookshelvesPage {
    private WebDriver driver;

    public BookshelvesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "inputSearchDrawer")
    WebElement search;

    @FindBy(className="GjPty")
    WebElement filter;

    @FindBy(id="filter-tab-storage-type")
    WebElement storage;

    @FindBy(className = "Dlqfv")
    WebElement storageCheckbox;

    @FindBy(id="filter-tab-min_price_effective")
    WebElement price;

    @FindBy(xpath="//div[@id='inputfields']//input[@type='text'])[1]")
    WebElement input1;

    @FindBy(xpath="//div[@id='inputfields']//input[@type='text'])[2]")
    WebElement input2;

    @FindBy(id="filter-tab-is_available")
    WebElement availability;

    @FindBy(className = "UnrgZ")
    WebElement outof;

    @FindBy(className = "zTzmw")
    WebElement apply;

    public void clickSearch(){
        search.sendKeys("bookselves");
        search.click();
    }

    public void clickFilter(){
        filter.click();
    }

    public void clickStorage(){
        storage.click();
    }

    public void clickOpenStore(){
        storageCheckbox.click();
    }

    public void clickPrice(){
        price.click();
    }

    public void minPrice(){
        input1.sendKeys("0");
    }

    public void maxPrice(){
        input1.sendKeys("15000");
    }

    public void clickAvailable(){
        availability.click();
    }

    public void outOfStock(){
        outof.click();
    }

    public void clickApply(){
        apply.click();
    }
}
