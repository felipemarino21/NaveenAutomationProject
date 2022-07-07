package StepDefinitions;

import Pages.*;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.TestContextSetup;

import java.io.File;
import java.io.IOException;


public class Hooks {
    public LoginPage loginPage;
    public RegistrationPage registrationPage;

    public HomePage homePage;
    public SearchPage searchPage;
    public CartPage cartPage;
    public AccountPage accountPage;
    TestContextSetup testContextSetup;

    public Hooks(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
    }
    @Before
    public void setup() {

        loginPage = new LoginPage(testContextSetup.driver);
        registrationPage = new RegistrationPage(testContextSetup.driver);
        homePage = new HomePage(testContextSetup.driver);
        searchPage= new SearchPage(testContextSetup.driver);
        cartPage = new CartPage(testContextSetup.driver);
        accountPage = new AccountPage(testContextSetup.driver);
    }
    @After
    public void AfterScenario() throws IOException {
        testContextSetup.driver.quit();
    }

    @AfterStep
    public void AddScreenshot(Scenario scenario) throws IOException {
        WebDriver driver = testContextSetup.driver;
        if (scenario.isFailed()) {
            // screenshot
            File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
            scenario.attach(fileContent, "image/png", "image");
        }

    }
}