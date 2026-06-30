package main.java.org.urbanladder.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GiftCardsPage {
    private WebDriver driver;

    public GiftCardsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstname")
    WebElement firstName;

    @FindBy(id = "lastname")
    WebElement lastName;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "telephone")
    WebElement mobileNumber;

    public void enterFirstName(String name) {
        firstName.sendKeys(name);
    }

    public void enterLastName(String name) {
        lastName.sendKeys(name);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterTelephone(String mobile) {
        mobileNumber.sendKeys(mobile);
    }
}