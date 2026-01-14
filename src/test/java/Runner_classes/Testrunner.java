package Runner_classes;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


    @CucumberOptions(
            features = "src/test/resources/Features/Login.feature",glue={"Stepdefinitions","Hooks"},
            monochrome = true,dryRun = false,
            plugin={"pretty",
                    "html:target/cucumber.html","rerun:target/rerun.txt",
                    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
            }
    )
    public class Testrunner extends AbstractTestNGCucumberTests {
        @Override
        @DataProvider(parallel = true)
        public Object [][] scenarios()
        {
            return super.scenarios();
        }
        //testing the git stash
    }


