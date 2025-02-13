package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
		
	}


	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;//Nida@123#
	
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtConformPassword;
	
	@FindBy(xpath="//input[@name='agree']") WebElement chkPolicy;
	
	@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;
	
	
	public void setFirstName(String firstName) 
	{
		txtFirstName.sendKeys(firstName);
	}
	
	public void setLastName(String lastName) 
	{
		txtLastName.sendKeys(lastName);
	}
	
	public void setEmail(String email) 
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String tel) 
	{
		txtTelephone.sendKeys(tel);
	}
	
	public void setPassword(String pass) 
	{
		txtPassword.sendKeys(pass);
	}
	
	public void setConfirmPassword(String pass) 
	{
		txtConformPassword.sendKeys(pass);
	}
	
	public void setPrivacyPolicy() 
	{
		chkPolicy.click();
	}
	
	public void clickContinue() 
	{
		//Sol 1
		btnContinue.click();
		
		//Sol 2
		//btnContinue.submit();
		
		//Sol 3
		//btnContinue.sendKeys(Keys.RETURN);
		
		//Sol 4
		//Actions act=new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
		
		//Sol 5
		//JavascriptExecutor js=(JavascriptExecutor) driver;
		//js.executeScript("arguments[0].click()",btnContinue );
		
		//Sol 6
		//WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}
	
	
	public String getConfirmationmsg() 
	{
		
		try{
			return (msgConfirmation.getText());
		}
		
		catch(Exception e) 
		{
			return (e.getMessage());
		}
	}
	
	
	
	

}
