package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.GiftCardsPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_CustomizeGiftCard extends BaseClass {
	@Test(dataProvider="GiftCardData",dataProviderClass=DataProviders.class)
	public void test_customizeGiftCard(String name,String email,String phNo,String name1,String email1,String phNo1,String address,String pincode) throws IOException {
		HomePage hp=new HomePage(driver);
		hp.clickGiftCards();
		GiftCardsPage g =new GiftCardsPage(driver);
		g.hoverBirthday();
		g.clickChooseButton();
		g.enterAmount("10000");
		g.clickNextButton();
		g.viewForm();
		g.enterRecipientName(name);
		g.enterRecipientEmail(email);
		g.enterRecipientMobile(phNo);
		g.enterSenderName(name1);
		g.enterSenderEmail(email1);
		g.enterSenderMobile(phNo1);
		g.enterSenderAddress(address);
		g.enterSenderPincode(pincode);
		g.ClickConfirmButton();  
		if(g.isSuccessMsgDisplayed()) {
			 System.out.println("Success");
		}else {
			
			System.out.println(g.error_Msg());
			String v=g.error_Msg();
			Assert.assertFalse(v.isEmpty(),"Test Failed No error message displayed");
			
		}
		new BaseClass().captureScreen("TC003_CustomizeGiftCard");
	}

}
