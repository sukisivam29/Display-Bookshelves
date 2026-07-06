package main.java.org.urbanladder.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GiftCardsPage {

    WebDriver driver;
    WebDriverWait wait;

    public GiftCardsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    @FindBy(xpath = "(//div[@id='design-theme']//img)[1]")
    WebElement birthdayTheme;

    @FindBy(xpath = "(//div[@id='design-theme']//img)[3]")
    WebElement anniversaryTheme;

    @FindBy(xpath = "(//input[@id='firstname'])[1]")
    WebElement senderFirstname;

    @FindBy(xpath = "(//input[@id='lastname'])[1]")
    WebElement senderLastname;

    @FindBy(xpath = "(//input[@id='email'])[1]")
    WebElement senderEmail;

    @FindBy(xpath = "(//input[@id='telephone'])[1]")
    WebElement senderMobileNumber;

    @FindBy(xpath = "(//input[@id='firstname'])[2]")
    WebElement receiverFirstname;

    @FindBy(xpath = "(//input[@id='lastname'])[2]")
    WebElement receiverLastname;

    @FindBy(xpath = "(//input[@id='email'])[2]")
    WebElement receiverEmail;

    @FindBy(xpath = "(//input[@id='telephone'])[2]")
    WebElement receiverMobileNumber;

    @FindBy(id = "giftMessage")
    WebElement giftMessage;

    @FindBy(xpath = "//button[text()='PREVIEW E-GIFT-CARD']")
    WebElement previewGiftCard;

    @FindBy(xpath = "//div[contains(@class,'payment-details-section')]")
    WebElement paymentDetailsSection;

    public void enterDenominationAmount(String amount){
        try {
            denominationAmount.sendKeys(amount);
        } catch (Exception e) {
            denominationAmount.sendKeys(amount);
        }
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
        scrollToElement(birthdayTheme);
        switch (mode.toLowerCase()){
            case "email" : deliveryModeEmail.click(); break;
            case "sms" : deliveryModeSMS.click(); break;
            case "both" : deliveryModeBoth.click(); break;
            default: throw new IllegalArgumentException("Invalid mode of delivery: "+mode);
        }
    }

    public void selectDesignTheme(String theme){
        switch (theme.toLowerCase()){
            case "birthday" : wait.until(ExpectedConditions.elementToBeClickable(birthdayTheme)).click(); break;
            case "anniversary" : wait.until(ExpectedConditions.elementToBeClickable(anniversaryTheme)).click(); break;
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

    public void enterSenderMobile(String mobile) {
        senderMobileNumber.sendKeys(mobile);
    }

    public void enterReceiverFirstname(String firstname){
        receiverFirstname.sendKeys(firstname);
    }

    public void enterReceiverLastname(String lastname){
        receiverLastname.sendKeys(lastname);
    }

    public void enterReceiverMobile(String mobile){
        receiverMobileNumber.sendKeys(mobile);
    }

    public void enterReceiverEmail(String email){
        receiverEmail.sendKeys(email);
    }

    public void enterGiftMessage(String message){
        giftMessage.sendKeys(message);
    }

    public boolean isBirthdayThemeDisplayed() {
        return birthdayTheme.isDisplayed();
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", element);
    }

    public void clickPreviewGiftCard(){
        scrollToElement(paymentDetailsSection);
        previewGiftCard.click();
    }

}