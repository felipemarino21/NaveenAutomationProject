package StepDefinitions;

import Pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestContextSetup;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;


public class StepDefinitions {
    public WebDriverWait wait;
    public Actions a;
    public LoginPage loginPage;
    public RegistrationPage registrationPage;

    public HomePage homePage;
    public SearchPage searchPage;
    public CartPage cartPage;
    public AccountPage accountPage;

    TestContextSetup testContextSetup;

    public StepDefinitions(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.loginPage = new LoginPage(this.testContextSetup.driver);
        this.registrationPage = new RegistrationPage(this.testContextSetup.driver);
        this.homePage = new HomePage(this.testContextSetup.driver);
        this.searchPage = new SearchPage(this.testContextSetup.driver);
        this.cartPage = new CartPage(this.testContextSetup.driver);
        this.accountPage = new AccountPage(this.testContextSetup.driver);
        this.a = new Actions(testContextSetup.driver);
        this.wait = new WebDriverWait(testContextSetup.driver, Duration.of(5,SECONDS));
    }

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        testContextSetup.driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");

    }
    @When("User logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String user, String pass) {
        loginPage.inputEmail(user);
        loginPage.inputPassword(pass);
        loginPage.clickLogin();
    }

    @Then("User sees error message")
    public void user_sees_error_message() {

        loginPage.checkError();
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
    @When("Signs up with new {string} and {string} random {string}  {string} {string}")
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
        registrationPage.getError();
    }
    @Given("User is on the homepage")
    public void user_is_on_the_homepage() {
        testContextSetup.driver.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");
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

        accountPage.compareUrl();
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
