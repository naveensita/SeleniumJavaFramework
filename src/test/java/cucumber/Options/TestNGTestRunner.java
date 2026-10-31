package cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * The Class TestNGTestRunner.
 * @author Naveen.Pathak
 */
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.coforge.sita.iborders.stepdefinition",
    		"com.coforge.sita.iborders.utils"},
    	tags = "@RegressionC",
    plugin = {
            "html:target/cucumber-reports.html"
        },
        monochrome = true
)

public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	
}
