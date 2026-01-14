package Stepdefinitions;

import Pages.Accountpage;
import Pages.Loginpage;
import Utilities.Driverfactory;
import Utilities.Excelutil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Map;

public class Login {

    WebDriver driver;
    Map<String, String> data = null;
    public Loginpage loginpage;
    public Accountpage accnt_page;
    @Given("user is on login page")
    public void user_is_on_login_page() {
        driver= Driverfactory.getDriver();
        loginpage=new Loginpage(driver);
    }
    @When("When user login using excel {int}")
    public void when_user_login_using_excel(Integer rownum) {
        data =
                Excelutil.getTestData("Login", rownum);
        loginpage.enteremail(data.get("Username"));
        loginpage.enterpwd(data.get("Pwd"));
        accnt_page=loginpage.login_btn();

    }
    @Then("User should login successfully")
    public void user_should_login_successfully() {
        String expected_result=data.get("ExpectedResult");
        if(expected_result.equalsIgnoreCase("My account"))
        {
            Assert.assertTrue(accnt_page.successmessageverification(),"Login should be successful but failed");
        } else if (expected_result.equalsIgnoreCase("Warning: No match for E-Mail Address and/or Password.")) {
            Assert.assertEquals(loginpage.warningmessage_vrfctn(),"Warning: No match for E-Mail Address and/or Pwd","warning message is incoorect");
        }
        else
        {
            Assert.fail("Invalid ExpectedResult value in Excel");
        }
    }

}
