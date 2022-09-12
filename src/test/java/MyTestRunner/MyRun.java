package MyTestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;



@RunWith(Cucumber.class)
@CucumberOptions(
		features = {".//Features/Customers.feature"},
		glue="MyStepdefinitions",
		dryRun = false,
		monochrome = true,
		//tags = "@Sanity",//scenarios under @sanity tag will be executed
		plugin = {"pretty","html:target/cucumber-reports/reports_html.html",
				"json:target/cucumber-reports/reports_json.json"
				}	
		)
public class MyRun {

}
