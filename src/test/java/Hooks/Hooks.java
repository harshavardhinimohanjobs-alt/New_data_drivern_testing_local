package Hooks;

import Utilities.Configreader;
import Utilities.Driverfactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Hooks {
    @Before
    public void setup()
    {
        Properties prop= Configreader.initializeproperties();
        Driverfactory.Initializebrowser(prop.getProperty("browser"));
        Driverfactory.getDriver().get(prop.getProperty("url"));
    }
    @After
    public void teardown(Scenario scenario) {
        String scenario_name = scenario.getName().replaceAll(" ", "_");

        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) Driverfactory.getDriver();
            //This is for merge conflict demo

            // Attach screenshot to report (preferred way)
            byte[] screenshotBytes = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotBytes, "image/png", scenario_name);
            //this is for merge conflict demo

            // Save screenshot as file
            File src = ts.getScreenshotAs(OutputType.FILE);
            String path = "target/screenshots/screenshot_" + scenario_name + ".png";

            try {
                FileUtils.copyFile(src, new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Driverfactory.quitDriver();
    }
}
