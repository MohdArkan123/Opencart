package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;



public class TC003_LoginDDT extends BaseClass
{

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="Datadriven")//getting data provider from different class
	public void verify_LoginDDT(String email,String password,String expected) 
	{
		logger.info("*****Starting TC003_LoginDDT*********");
		
		try {
		//HomePage
				HomePage hp=new HomePage(driver);
				hp.clickMyAccount();
				hp.clickLogin();
				
		//LoginPage
				LoginPage lp=new LoginPage(driver);
				lp.setEmail(email);
				lp.setPassword(password);
				lp.clickLogin();
				
		//MyAccountPage
				MyAccountPage macc=new MyAccountPage(driver);
				boolean targetPage=macc.isMyAccountPageExists();
				
				
				
				/*
				 Data is valid--->login success--->test pass -->logout
				 			      login failed--->test fail
				 
				 Data is invalid--->login success--->test fail -->logout
				 					login failed--->test pass
				 */
				
				
				if(expected.equalsIgnoreCase("Valid")) {
					if(targetPage==true) {
						macc.clickLogout();
						Assert.assertTrue(true); //Data is valid--->login success--->test pass -->logout
					}
					else {
						Assert.assertTrue(false);//Data is valid--->login failed--->test fail
					}
				}
				
				
				
				if(expected.equalsIgnoreCase("Invalid")) {
					if(targetPage==true) {
						macc.clickLogout();
						Assert.assertTrue(false); //Data is invalid--->login success--->test fail -->logout
					}
					else {
						Assert.assertTrue(true);//Data is invalid--->login failed--->test pass
					}
				}
		}
		catch(Exception e) {
			Assert.fail();
		}
				
			logger.info("*****Finished TC003_LoginDDT*********");
				


				
	}
}
