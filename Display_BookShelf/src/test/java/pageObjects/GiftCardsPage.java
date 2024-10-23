package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

    public class GiftCardsPage extends BasePage {
	public GiftCardsPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(linkText="Gift Cards")
	WebElement gift_card;
	
	@FindBy(xpath="//*[@id=\"app-container\"]/div/main/section/section[1]/ul/li[3]/div")
	 WebElement birthday;
	
	@FindBy(xpath="//*[@id=\"app-container\"]/div/main/section/section[1]/ul/li[3]/div/div/button")
    WebElement ChooseButton;
	
	@FindBy(id="ip_2251506436")
	WebElement amount;
	
	@FindBy(xpath="//*[@id=\"app-container\"]/div/main/section/section[1]/ul/li[3]/div/div/button")
	WebElement button_next;
	
	@FindBy(xpath="//*[@id='app-container']/div/main/section/section[3]")
	WebElement form;
	
	@FindBy(id="ip_4036288348")
	WebElement recipientName;
	
	@FindBy(id="ip_137656023")
	WebElement recipientEmail;
	
	@FindBy(id="ip_3177473671")
	WebElement recipientMobile;
	
	@FindBy(id="ip_1082986083")
	WebElement senderName;
	
	@FindBy(id="ip_4081352456")
	WebElement senderMail;
	
	@FindBy(id="ip_2121573464")
	WebElement senderMobile;
	
	@FindBy(id="ip_2194351474")
	WebElement senderAddress;
	
	@FindBy(id="ip_567727260")
	WebElement senderPincode;
	
	@FindBy(xpath="//*[@id=\"app-container\"]/div/main/section/section[3]/form/button")
	WebElement confirmButton;
	
	@FindBy(id="ip_4081352456")
	WebElement errorMsg;
	
	@FindBy(xpath="//*[@id=\"app-container\"]/div/main/section/section[4]/div[2]/button/span[1]")
	//*[@id="app-container"]/div/main/section/section[4]/div[2]/button/span[1]
	//*[@id="app-container"]/div/main/section/section[4]/div[2]/h2/text()
	WebElement successMsg;
	
	public void clickGiftCard() {
//		 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", gift_card);
		 js.executeScript("arguments[0].click();", gift_card);
	}
	
	public void hoverBirthday() {
		actions.moveToElement(birthday).perform();
	}
	
	public void clickChooseButton() {
		 ChooseButton.click();
	}
	
	public void enterAmount(String Amount) {
		amount.sendKeys(Amount);
	}
	public void clickNextButton() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", button_next);
	}
	public void viewForm() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", form);
	}
	
	public void enterRecipientName(String name) {
		 recipientName.sendKeys(name);
	}	
    public void enterRecipientEmail(String email) {
    	recipientEmail.sendKeys(email);
	}
    public void enterRecipientMobile(String mobile) {
    	recipientMobile.sendKeys(mobile);
    }
    public void enterSenderName(String sendername) {
    	senderName.sendKeys(sendername);
    }
    public void enterSenderEmail(String sendermail) {
    	senderMail.sendKeys(sendermail);
    }
    public void enterSenderMobile(String sendermobile) {
    	senderMobile.sendKeys(sendermobile);
    }
    public void enterSenderAddress(String address) {
    	senderAddress.sendKeys(address);
    }
    public void enterSenderPincode(String pincode) {
    	senderPincode.sendKeys(pincode);
    }
    public void ClickConfirmButton() {
    	wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
    	confirmButton.click();
    }
    public String error_Msg() {
    	return errorMsg.getAttribute("validationMessage");
    }
    public boolean isSuccessMsgDisplayed() {
    	return successMsg.isDisplayed();
    }
    
  
}
