package base;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import PageObjects.MyAddNewCustomerPage;
import PageObjects.MyLoginPage;
import PageObjects.MySearchCustomerPage;
import Utitlities.ReadConfig;

public class Base {
	public static WebDriver driver;
	public static MyLoginPage loginPage;
	public static MyAddNewCustomerPage addNewCustPg;
	public static MySearchCustomerPage searchCustPg;
	public static Logger log;
	public ReadConfig readConfig;
	//generate random email
	public String generateEmailId()
	{
		return(RandomStringUtils.randomAlphabetic(5));
	}
}
