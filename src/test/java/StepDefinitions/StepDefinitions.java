package StepDefinitions;

import Pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static java.time.temporal.ChronoUnit.SECONDS;


public class StepDefinitions {
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions a;
    public LoginPage loginPage;
    public RegistrationPage registrationPage;

    public HomePage homePage;
    public SearchPage searchPage;
    public CartPage cartPage;
    public AccountPage accountPage;
    @Before
    public void setup() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (prop.getProperty("browser").equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\msedgedriver.exe");
            driver = new EdgeDriver();
        }
        a= new Actions(driver);
        wait = new WebDriverWait(driver,Duration.of(5,SECONDS));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        homePage = new HomePage(driver);
        searchPage= new SearchPage(driver);
        cartPage = new CartPage(driver);
        accountPage = new AccountPage(driver);
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

    @When("User signs up with {string} {string} {string} {string} {string}")
    public void user_signs_up_with(String firstname, String lastname, String email, String phone, String password) {
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
    public void user_sees_all_the_results_containing_in_the_name(String keyword) {
        searchPage.compareResults(keyword);
    }

    @When("User hovers with the mouse over the MP3 players category")
    public void user_hovers_with_the_mouse_over_the_mp3_players_category() {
        homePage.hoverElement(a);
    }

    @Then("User sees all the elements in that category")
    public void user_sees_all_the_elements_in_that_category() {
        homePage.checkSubCategory();
    }

    @When("User adds a Macbook to cart")
    public void user_adds_a_macbook_to_cart() {
        homePage.addItemToCart();
    }
    @When("User clicks on the cart")
    public void user_clicks_on_the_cart() {
        homePage.clickCart(wait);
    }
    @Then("User sees the Macbook in the cart")
    public void user_sees_the_macbook_in_the_cart() {
        homePage.checkItemInCart(wait);
    }

    @Then("User sees the add to cart notification")
    public void user_sees_the_add_to_cart_notification() {
        homePage.checkCartNotification();
    }

    @When("User adds {string} Macbook to cart")
    public void user_adds_macbook_to_cart(String string) {
        int n= Integer.parseInt(string);
        for(int i=0; i<n; i++){
            homePage.addItemToCart();
        }
    }
    @When("User clicks on view cart")
    public void user_clicks_on_view_cart() {
        homePage.clickViewCart(wait);
    }
    @Then("User sees {string} MacBook  in the cart")
    public void user_sees_mac_book_in_the_cart(String number){

        cartPage.compareQuantity(wait,number);
    }
    @Then("User sees account page")
    public void user_sees_account_page() {
        String expected = "https://naveenautomationlabs.com/opencart/index.php?route=account/account";
        accountPage.compareUrl(expected);
    }

    @When("User changes currency from dollars to euros")
    public void user_changes_currency_from_dollars_to_euros() {
        homePage.changeCurrencyToEuros();
    }
    @Then("User sees all the prices in euros")
    public void user_sees_all_the_prices_in_euros() {
         homePage.CheckIfCurrencyIsEuros();
    }

}
