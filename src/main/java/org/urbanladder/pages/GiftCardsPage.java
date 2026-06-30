package main.java.org.urbanladder.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GiftCardsPage {

    WebDriver driver;

    public GiftCardsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "denomination")
    WebElement denominationAmount;

    @FindBy(id = "quantity")
    WebElement quantity;

    @FindBy(xpath = "//input[@label='Send As Gift']")
    WebElement sendAsGiftButton;

    @FindBy(xpath = "//input[@label='Buy For Self']")
    WebElement buyForSelfButton;

    @FindBy(id = "giftNow")
    WebElement giftNow;

    @FindBy(id = "giftLater")
    WebElement giftLater;

    @FindBy(id = "deliveryModeEmail")
    WebElement deliveryModeEmail;

    @FindBy(id = "deliveryModeSMS")
    WebElement deliveryModeSMS;

    @FindBy(id = "deliveryModeANY")
    WebElement deliveryModeBoth;

    @FindBy(xpath = "//div[@data-index='0']")
    WebElement birthdayTheme;

    @FindBy(xpath = "//div[@data-index='2']")
    WebElement anniversaryTheme;

    @FindBy(xpath = "(//input[@id='firstname'])[1]")
    WebElement senderFirstname;

    @FindBy(xpath = "(//input[@id='lastname'])[1]")
    WebElement senderLastname;

    @FindBy(xpath = "(//input[@id='email'])[1]")
    WebElement senderEmail;

    @FindBy(id = "(//input[@id='telephone'])[1]")
    WebElement senderMobileNumber;

    @FindBy(xpath = "(//input[@id='firstname'])[2]")
    WebElement recieverFirstname;

    @FindBy(xpath = "(//input[@id='lastname'])[2]")
    WebElement recieverLastname;

    @FindBy(xpath = "(//input[@id='email'])[1]")
    WebElement recieverEmail;

    @FindBy(xpath = "(//input[@id='telephone'])[2]")
    WebElement recieverMobileNumber;

    @FindBy(id = "giftMessage")
    WebElement giftMessage;

    public void enterDenominationAmount(String amount){
        denominationAmount.sendKeys(amount);
    }

    public void enterQuantity(String quantityOfCards){
        quantity.sendKeys(quantityOfCards);
    }

    public void selectDeliveryOptions(String option){
        switch (option.toLowerCase()){
            case "gift" : sendAsGiftButton.click(); break;
            case "self" : buyForSelfButton.click(); break;
            default: throw new IllegalArgumentException("Invalid delivery option: "+option);
        }
    }

    public void selectDeliveryDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inputDate = LocalDate.parse(date, formatter);
        if(LocalDate.now().equals(inputDate)){
            giftNow.click();
        }
        else{
            giftLater.click();
            /*
            Need to Handle the date Selector
            Refer the notes.txt
            */
        }
    }

    public void selectModeOfDelivery(String mode){
        switch (mode.toLowerCase()){
            case "email" : deliveryModeEmail.click(); break;
            case "sms" : deliveryModeSMS.click(); break;
            case "both" : deliveryModeBoth.click(); break;
            default: throw new IllegalArgumentException("Invalid mode of delivery: "+mode);
        }
    }

    public void selectDesignTheme(String theme){
        switch (theme.toLowerCase()){
            case "birthday" : birthdayTheme.click(); break;
            case "anniversary" : anniversaryTheme.click(); break;
            default: throw new IllegalArgumentException("Invalid design theme: "+theme);
        }
    }

    public void enterSenderFirstname(String firstname) {
        senderFirstname.sendKeys(firstname);
    }

    public void enterSenderLastname(String lastname) {
        senderLastname.sendKeys(lastname);
    }

    public void enterSenderEmail(String email) {
        senderEmail.sendKeys(email);
    }

    public void enterSenderTelephone(String mobile) {
        senderMobileNumber.sendKeys(mobile);
    }

    public void enterRecieverFirstname(String firstname){
        recieverFirstname.sendKeys(firstname);
    }

    public void enterRecieverLastname(String lastname){
        recieverLastname.sendKeys(lastname);
    }

    public void enterRecieverTelephone(String mobile){
        recieverMobileNumber.sendKeys(mobile);
    }

    public void enterRecieverEmail(String email){
        recieverEmail.sendKeys(email);
    }

    public void enterGiftMessage(String message){
        giftMessage.sendKeys(message);
    }

}