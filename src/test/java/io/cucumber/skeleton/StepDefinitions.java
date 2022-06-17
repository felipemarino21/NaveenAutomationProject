package io.cucumber.skeleton;

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

    @Before
    public void setup()  {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Documents\\asd\\skeleton\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,Duration.of(5,SECONDS));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");

    }
    @When("User logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String user, String pass) {

        driver.findElement(By.id("input-email")).sendKeys(user);
        driver.findElement(By.id("input-password")).sendKeys(pass);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @Then("User sees error message")
    public void user_sees_error_message() {
        String error;
        error = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        Assert.assertEquals(error,"Warning: No match for E-Mail Address and/or Password.");
        driver.quit();
    }

    @Given("User clicks continue in the registration menu")
    public void user_clicks_continue_in_the_registration_menu() {
        driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();

    }
    @When("^User signs up with (.+) and (.+) (.+) (.+) (.+)$")
    public void user_signs_up_with_and(String firstname, String lastname, String email, String phone, String password) {
        driver.findElement(By.id("input-firstname")).sendKeys(firstname);
        driver.findElement(By.id("input-lastname")).sendKeys(lastname);
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-telephone")).sendKeys(phone);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.id("input-confirm")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//input[@type='submit']")).click();

    }
    @When("^Signs up with new (.+) and (.+) random (.+)  (.+) (.+)$")
    public void Signs_up_with_new_and_random(String firstname, String lastname, String email, String phone, String password) {
        String aux = String.valueOf(Math.random());
        driver.findElement(By.id("input-firstname")).sendKeys(firstname);
        driver.findElement(By.id("input-lastname")).sendKeys(lastname);
        String[] parts = email.split("@");
        email = parts[0] +aux + "@" + parts[1];
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-telephone")).sendKeys(phone);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.id("input-confirm")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//input[@type='submit']")).click();

    }
    @Then("User sees registration confirmation")
    public void user_sees_registration_confirmation() {
        String confirmation;
        confirmation = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        Assert.assertEquals(confirmation,"Your Account Has Been Created!");
        driver.quit();
    }
    @Then("^User sees registration error$")
    public void user_sees_registration_error()  {
        String confirmation;
        confirmation = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        Assert.assertEquals(confirmation,"Warning: E-Mail Address is already registered!");
        driver.quit();
    }
    @Given("User is on the homepage")
    public void user_is_on_the_homepage() {
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");
    }
    @When("User types in the searchbar the word {string}")
    public void user_types_in_the_searchbar_the_word(String keyword) {
        driver.findElement(By.name("search")).sendKeys(keyword);
    }
    @When("Clicks the search button")
    public void clicks_the_search_button() {
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
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

    @After
    public void teardown() {
    // closes all the browser windows opened by web driver
        driver.quit();
    }

    @Then("User sees account page")
    public void user_sees_account_page() {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url,"https://naveenautomationlabs.com/opencart/index.php?route=account/account");
    }



}
