package MyTestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(
		features = {".//Features/"},
		glue="MyStepdefinitions",
		dryRun = false,
		monochrome = true,
		//tags="@Sanity or @regression",
		//tags = "@Sanity",//scenarios under @sanity tag will be executed
		plugin = {"pretty","html:target/cucumber-reports/reports_html.html",
				"json:target/cucumber-reports/reports_json.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				}	
		)
public class MyRun extends AbstractTestNGCucumberTests{

}
