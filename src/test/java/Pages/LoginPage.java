package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.TextConstants;
import utils.UrlString;

import java.io.IOException;


public class LoginPage {
    public WebDriver driver;
    public UrlString urlString = new UrlString();

    public LoginPage(WebDriver driver){

        this.driver = driver;
    }

    private By email = By.id("input-email");
    private By password = By.id("input-password");

    private By loginButton = By.xpath("//input[@type='submit']");

    private By errorMessage = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

    private By registrationButton = By.xpath("//a[@class='btn btn-primary']");
    public void inputEmail(String user){
        driver.findElement(email).sendKeys(user);
    }
    public void inputPassword(String pass){
        driver.findElement(password).sendKeys(pass);
    }
    public void clickLogin(){
        driver.findElement(loginButton).click();
    }
    public void checkError(){
        String error = driver.findElement(errorMessage).getText();
        Assert.assertEquals(error,TextConstants.LoginWarning);
    }
    public void clickContinue(){
        driver.findElement(registrationButton).click();
    }

    public void getLoginPage() throws IOException {
        driver.get(UrlString.propertiesas("urlLogin"));
    }


}
