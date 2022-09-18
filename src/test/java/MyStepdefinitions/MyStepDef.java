package MyStepdefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import PageObjects.MyAddNewCustomerPage;
import PageObjects.MyLoginPage;
import PageObjects.MySearchCustomerPage;
import Utitlities.ReadConfig;
import base.Base;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MyStepDef extends Base{
	
	
	@Before()
	public void setUp1() throws Exception {
		readConfig=new ReadConfig();
		FileInputStream file=new FileInputStream("config.properties");
		//readConfig.load(file);
		log=LogManager.getLogger("MyStepDef");
		System.out.println("Setup-sanity executed...");
		String browser=readConfig.getBrowser();
		switch(browser.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;

		}
		
		    log.info("setup 1 executed");
	}
	/*@Before("@regression")
	public void setUp2() throws Exception {
		System.out.println("Setup-regrssion executed...");
		 WebDriverManager.chromedriver().setup();
		    driver=new ChromeDriver();
		   // Thread.sleep(3000);
		    log.info("setup 2 executed");
	}
	/*@BeforeStep
	public void beforeStepMethodDemo()
	{
		System.out.println("This is before step....");
	}


	@AfterStep
	public void afterStepMethodDemo()
	{
		System.out.println("This is after step....");
	}*/
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
	   
	    loginPage=new MyLoginPage(driver);
	    addNewCustPg=new MyAddNewCustomerPage(driver);
	    searchCustPg=new MySearchCustomerPage(driver);
	    log.info("user launch browser");
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
	   driver.get(url);
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String emailadd, String password) throws Exception {
		loginPage.enterEmail(emailadd);
		loginPage.enterPassword(password);
		log.info("url launch ");
	}

	@When("Click on Login")
	public void click_on_login() {
		loginPage.clickOnLoginButton();
		log.info("login button clicked");
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String expectedTitle) {
	   String actualTitle=driver.getTitle();
	   if(actualTitle.equals(expectedTitle))
		{
			log.warn("Test passed: Login feature :Page title matched.");

			Assert.assertTrue(true);//pass
		}
		else
		{
			Assert.assertTrue(false);//fail
			log.warn("Test Failed: Login feature- page title not matched.");


		}
	}
/////Add new customer
	@When("User click on Log out link")
	public void user_click_on_log_out_link() throws Exception {
		Thread.sleep(3000);
		loginPage.clickOnLogOutButton();
		log.info("logout button clicked");
	}
	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
		String actualTitle=driver.getTitle();
		String expectedTitle ="Dashboard / nopCommerce administration";
		if(actualTitle.equals(expectedTitle))
		{
			log.warn("Test passed: Login feature :Page title matched.");

			Assert.assertTrue(true);//pass
		}
		else
		{
			Assert.assertTrue(false);//fail
			log.warn("Test Failed: Login feature- page title not matched.");


		}
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
	   addNewCustPg.clickOnCustomersMenu();
	   //Thread.sleep(3000);
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() throws Exception {
		addNewCustPg.clickOnCustomersMenuItem();
		 //Thread.sleep(3000);
	}

	@When("click on Add new button")
	public void click_on_add_new_button() {
		addNewCustPg.clickOnAddnew();
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		String actualTitle = addNewCustPg.getPageTitle();
		System.out.println(actualTitle);
		String expectedTitle = "Add a new customer / nopCommerce administration";

		if(actualTitle.equals(expectedTitle))
		{
			log.info("user can view dashboard test passed.");
			Assert.assertTrue(true);

		}
		else
		{
			Assert.assertTrue(false);
			log.warn("user can view dashboard test failed.");

		}
	}

	@When("User enter customer info")
	public void user_enter_customer_info() {
		//addNewCustPg.enterEmail("mast93@gmail.com");
				addNewCustPg.enterEmail(generateEmailId() + "@gmail.com");
				addNewCustPg.enterPassword("test1");
				addNewCustPg.enterFirstName("Prachi");
				addNewCustPg.enterLastName("Gupta");
				addNewCustPg.enterGender("Female");
				addNewCustPg.enterDob("6/13/1988");
				addNewCustPg.enterCompanyName("CodeStudio");
				addNewCustPg.enterAdminContent("Admin content");
				addNewCustPg.enterManagerOfVendor("Vendor 1");

				//log.info("customer information entered");
	}

	@When("click on Save button")
	public void click_on_save_button() {
	   addNewCustPg.clickOnSave();
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String exptectedConfirmationMsg) {
		String bodyTagText = driver.findElement(By.tagName("Body")).getText();
		if(bodyTagText.contains(exptectedConfirmationMsg))
		{
			Assert.assertTrue(true);//pass
			log.info("User can view confirmation message - passed");

		}
		else
		{
			log.warn("User can view confirmation message - failed");

			Assert.assertTrue(false);//fail

		}

	}
	//search customer page
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
		searchCustPg.enterEmailAdd("victoria_victoria@nopCommerce.com");
		log.info("Email address entered");
	}

	@When("Click on search button")
	public void click_on_search_button() {
		searchCustPg.clickOnSearchButton();
		log.info("Clicked on search button.");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() throws Exception {
		String expectedEmail = "victoria_victoria@nopCommerce.com";

		//   Assert.assertTrue(SearchCustPg.searchCustomerByEmail(expectedEmail));

		if( searchCustPg.searchCustomerByEmail(expectedEmail) ==true)
		{
			Assert.assertTrue(true);
			log.info("User should found Email in the Search table - passed");

		}
		else {
			log.info("User should found Email in the Search table - passed");
			Assert.assertTrue(false);

		}
	}
	/////Search customer by name
	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		searchCustPg.enterFirstName("Victoria");
	}
	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		searchCustPg.enterLastName("Terces");
	}
	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() throws Exception {
		String expectedName = "Victoria Terces";


		if( searchCustPg.searchCustomerByName(expectedName) ==true)
		{
			Assert.assertTrue(true);
		}
		else
			Assert.assertTrue(false);
	}
//	@Then("close browser")
//	public void close_browser() {
//	    driver.close();
//	    driver.quit();
//	}
	@After
	public void teardown(Scenario sc)
	{
		System.out.println("Tear Down method executed..");
		if(sc.isFailed()==true)
		{
			//Convert web driver object to TakeScreenshot

			String fileWithPath = "C:\\Users\\Mahesh\\eclipse-workspace\\MyLatestIlinkFramework\\snapshot\\failed.png";
			TakesScreenshot scrShot =((TakesScreenshot)driver);

			//Call getScreenshotAs method to create image file
			File srcFile=scrShot.getScreenshotAs(OutputType.FILE);

			//Move image file to new destination
			File destFile=new File(fileWithPath);

			//Copy file at destination

			try {
				FileUtils.copyFile(srcFile, destFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		driver.quit();
	}
	
	
	@AfterStep
	public void addScreenshot(Scenario scenario) {
		if(scenario.isFailed()) {
		final byte[] screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot,"image/png",scenario.getName());
		}
	}
	


}
