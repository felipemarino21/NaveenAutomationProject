package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    By email = By.id("input-email");
    By password = By.id("input-password");

    By loginButton = By.xpath("//input[@type='submit']");

    By errorMessage = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

    By registrationButton = By.xpath("//a[@class='btn btn-primary']");
    public void inputEmail(String user){
        driver.findElement(email).sendKeys(user);
    }
    public void inputPassword(String pass){
        driver.findElement(password).sendKeys(pass);
    }
    public void clickLogin(){
        driver.findElement(loginButton).click();
    }
    public void checkError(String expected){
        String error = driver.findElement(errorMessage).getText();
        Assert.assertEquals(error,expected);
    }
    public void clickContinue(){
        driver.findElement(registrationButton).click();
    }


}
