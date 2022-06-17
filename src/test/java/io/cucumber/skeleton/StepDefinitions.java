package io.cucumber.skeleton;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.RegistrationPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;


public class StepDefinitions {
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions a;
    public LoginPage loginPage;
    public RegistrationPage registrationPage;

    public HomePage homePage;

    @Before
    public void setup()  {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Documents\\asd\\skeleton\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,Duration.of(5,SECONDS));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        homePage = new HomePage(driver);
    }

    @After
    public void teardown() {
        // closes all the browser windows opened by web driver
        driver.quit();
    }
    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");

    }
    @When("User logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String user, String pass) {
        loginPage.inputEmail(user);
        loginPage.inputPassword(pass);
        loginPage.clickLogin();
    }

    @Then("User sees error message")
    public void user_sees_error_message() {
        String expected = "Warning: No match for E-Mail Address and/or Password.";
        loginPage.checkError(expected);
    }

    @Given("User clicks continue in the registration menu")
    public void user_clicks_continue_in_the_registration_menu() {
        loginPage.clickContinue();
    }

    @When("^User signs up with (.+) and (.+) (.+) (.+) (.+)$")
    public void user_signs_up_with_and(String firstname, String lastname, String email, String phone, String password) {
        registrationPage.inputFirstName(firstname);
        registrationPage.inputLastName(lastname);
        registrationPage.inputEmail(email);
        registrationPage.inputTelephone(phone);
        registrationPage.inputPassword(password);
        registrationPage.clickCheck();
        registrationPage.clickSubmit();
    }
    @When("^Signs up with new (.+) and (.+) random (.+)  (.+) (.+)$")
    public void Signs_up_with_new_and_random(String firstname, String lastname, String email, String phone, String password) {
        registrationPage.inputFirstName(firstname);
        registrationPage.inputLastName(lastname);
        registrationPage.inputEmail(registrationPage.createRandomEmail(email));
        registrationPage.inputTelephone(phone);
        registrationPage.inputPassword(password);
        registrationPage.clickCheck();
        registrationPage.clickSubmit();

    }
    @Then("User sees registration confirmation")
    public void user_sees_registration_confirmation() {
        String expected = "Your Account Has Been Created!";
        registrationPage.getConfirmation(expected);
    }
    @Then("^User sees registration error$")
    public void user_sees_registration_error()  {
        String expected = "Warning: E-Mail Address is already registered!";
        registrationPage.getError(expected);

    }
    @Given("User is on the homepage")
    public void user_is_on_the_homepage() {
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");
    }
    @When("User types in the searchbar the word {string}")
    public void user_types_in_the_searchbar_the_word(String keyword) {
        homePage.searchItem(keyword);
    }
    @When("Clicks the search button")
    public void clicks_the_search_button() {
        homePage.clickSearch();
    }
    @Then("User sees all the results containing {string} in the name")
    public void user_sees_all_the_results_containing_in_the_name(String name) {

        List<WebElement> list = driver.findElements(By.xpath("//div[@class='caption']/h4/a"));
        for (WebElement webElement : list) {
            String confirmation = webElement.getText();
            Assert.assertTrue(confirmation.contains(name));
        }
    }

    @When("User hovers with the mouse over the MP3 players category")
    public void user_hovers_with_the_mouse_over_the_mp3_players_category() {
        a= new Actions(driver);
        WebElement ele = driver.findElement(By.xpath("//*[contains(text(),'MP3')]"));
        a.moveToElement(ele).build().perform();
    }

    @Then("User sees all the elements in that category")
    public void user_sees_all_the_elements_in_that_category() {
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'MP3')]/following-sibling::div/div/ul/li"));
        for (WebElement webElement : list) {
            boolean confirmation = webElement.isDisplayed();
            Assert.assertTrue(confirmation);
        }
    }

    @When("User adds a Macbook to cart")
    public void user_adds_a_macbook_to_cart() {
        driver.findElement(By.xpath("((//*[.='MacBook']/parent::div/parent::div)/div[@class='button-group']/button)[1]")).click();
    }
    @When("User clicks on the cart")
    public void user_clicks_on_the_cart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")));
        driver.findElement(By.xpath("//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")).click();
    }
    @Then("User sees the Macbook in the cart")
    public void user_sees_the_macbook_in_the_cart() {
        boolean condition = driver.findElement(By.xpath("//ul[@class='dropdown-menu pull-right']//a[contains(text(),'MacBook')]")).isDisplayed();
        Assert.assertTrue(condition);
    }

    @Then("User sees the add to cart notification")
    public void user_sees_the_add_to_cart_notification() {
        boolean condition = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']/i")).isDisplayed();
        Assert.assertTrue(condition);
    }

    @When("User adds {string} Macbook to cart")
    public void user_adds_macbook_to_cart(String string) {
        int n= Integer.parseInt(string);
        for(int i=0; i<n; i++){
            driver.findElement(By.xpath("((//*[.='MacBook']/parent::div/parent::div)/div[@class='button-group']/button)[1]")).click();
        }
    }
    @When("User clicks on view cart")
    public void user_clicks_on_view_cart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//p/a/strong)[1]")));
        driver.findElement(By.xpath("(//p/a/strong)[1]")).click();
    }
    @Then("User sees {string} MacBook  in the cart")
    public void user_sees_mac_book_in_the_cart(String string){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@class='form-control'])[1]")));
        int n= Integer.parseInt(string);
        int value = Integer.parseInt(driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).getAttribute("value"));
        Assert.assertEquals(value,n);
    }



    @Then("User sees account page")
    public void user_sees_account_page() {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url,"https://naveenautomationlabs.com/opencart/index.php?route=account/account");
    }

    @When("User changes currency from dollars to euros")
    public void user_changes_currency_from_dollars_to_euros() {
        driver.findElement(By.id("form-currency")).click();
        driver.findElement(By.xpath("//button[@name='EUR']")).click();

    }
    @Then("User sees all the prices in euros")
    public void user_sees_all_the_prices_in_euros() {
         List<WebElement> list = driver.findElements(By.xpath("//p[@class='price']"));
        for (WebElement webElement : list) {
            String currency = webElement.getText();
            Assert.assertTrue(currency.contains("€"));
        }

    }

}
