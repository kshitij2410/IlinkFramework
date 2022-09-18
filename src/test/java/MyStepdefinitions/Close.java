package MyStepdefinitions;

import base.Base;
import io.cucumber.java.en.Then;

public class Close extends Base{
	@Then("close browser")
	public void close_browser() {
	    driver.close();
	    driver.quit();
	}
}
