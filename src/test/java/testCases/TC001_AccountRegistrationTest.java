package testCases;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	
	
	@Test(groups={"Regression","Master"})
	public void verify_Account_Registration() throws InterruptedException 
	{
		logger.info("**** Starting TC001_AccountRegistrationTest ****");
		
		try {
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info(" Clicked on MyAccount Link ");
		hp.clickRegister();
		logger.info(" Clicked on Register Link ");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info(" Providing Customer Details.... ");
		
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");//randomly generated Email
		regpage.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		
		Thread.sleep(5000);
		regpage.clickContinue();
		
		logger.info(" Validating Expected Message... ");
		
		String confmsg=regpage.getConfirmationmsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		
		catch(Exception e) 
		{
			logger.error("Test Failed...");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		
		logger.info("**** Finished TC001_AccountRegistrationTest ****");
	}
	
	

}
