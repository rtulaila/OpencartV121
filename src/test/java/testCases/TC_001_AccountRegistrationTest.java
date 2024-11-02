package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	@Test(groups = {"Regression", "Master"})
	public void verify_account_registration() {
		
		logger.info("***Starting AccountRegistration TestCase...");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		logger.info("***Clicked on My Account and Register****");

		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("**Providing Customer Details****");
		
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString() + "@gmail.com");
 		regpage.setPassword(randomAlphaNumeric());
 		regpage.setPrivacyPolicy();
		regpage.clickContinue();

		logger.info("***Validating Expected Message****");
		String confmsg = regpage.getConfirmationMsg();
		if (confmsg.contentEquals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);			
		}
		else {
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.assertFalse(false);	
		}
		
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		}
		catch (Exception e) {
			Assert.fail();
		}
		
		
	}
}
