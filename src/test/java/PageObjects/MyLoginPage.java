package PageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyLoginPage {
	public WebDriver ldriver;
   
	public MyLoginPage(WebDriver rDriver)
	{
		ldriver=rDriver;

		PageFactory.initElements(rDriver, this);
	}
	WebDriverWait wait;
	static JavascriptExecutor exe;
	
	
	@FindBy(id = "Email")
	WebElement email;
	
	@FindBy(id="Password")
	WebElement password;

	@FindBy(xpath = "//button[@class='button-1 login-button']")
	WebElement LoginBtn;
	
	
	@FindBy(linkText = "Logout")
	WebElement logout;
	
	public void enterEmail(String emailAdd) throws Exception
	{
		Thread.sleep(3000);
		email.clear();
		email.sendKeys(emailAdd);
	}
	
	public void enterPassword(String pwd)
	{
		password.clear();
		password.sendKeys(pwd);
	}
	
	public void clickOnLoginButton()
	{
		LoginBtn.click();
	}
	
	public void clickOnLogOutButton()
	{
//		wait = new WebDriverWait(ldriver, Duration.ofSeconds(30));
//		wait.until(ExpectedConditions.elementToBeClickable(logout));
		//logout.click();
	exe=(JavascriptExecutor)ldriver;
		exe.executeScript("arguments[0].click()", logout);
		
	}
}
