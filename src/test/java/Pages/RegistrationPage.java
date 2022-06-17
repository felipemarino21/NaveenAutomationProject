package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RegistrationPage {
    public WebDriver driver;

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
    }

    By firstName = By.id("input-firstname");
    By lastName = By.id("input-lastname");
    By emailLoc = By.id("input-email");
    By telephone = By.id("input-telephone");
    By password = By.id("input-password");
    By confirm = By.id("input-confirm");
    By checkTerms = By.xpath("//input[@type='checkbox']");
    By submit = By.xpath("//input[@type='submit']");
    By confirmation = By.xpath("//div[@id='content']/h1");
    By registrationError = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

    public void inputFirstName(String firstname){
        driver.findElement(firstName).sendKeys(firstname);
    }
    public void inputLastName(String lastname){
        driver.findElement(lastName).sendKeys(lastname);
    }
    public void inputEmail(String email){
        driver.findElement(emailLoc).sendKeys(email);
    }
    public void inputTelephone(String phone){
        driver.findElement(telephone).sendKeys(phone);
    }
    public void inputPassword(String pass){
        driver.findElement(password).sendKeys(pass);
        driver.findElement(confirm).sendKeys(pass);
    }
    public void clickCheck(){
        driver.findElement(checkTerms).click();
    }
    public void clickSubmit(){
        driver.findElement(submit).click();
    }
    public String createRandomEmail(String baseEmail){
        String aux = String.valueOf(Math.random());
        String[] parts = baseEmail.split("@");
        return parts[0] + aux + "@" + parts[1];
    }
    public void getConfirmation(String expected){
        String actual = driver.findElement(confirmation).getText();
        Assert.assertEquals(actual,expected);
    }
    public void getError(String expected){
        String actual = driver.findElement(registrationError).getText();
        Assert.assertEquals(actual,expected);
    }

}
