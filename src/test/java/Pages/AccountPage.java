package Pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AccountPage {
    public WebDriver driver;


    public AccountPage(WebDriver driver){
        this.driver = driver;
    }
    public void compareUrl(){
        String expected = "https://naveenautomationlabs.com/opencart/index.php?route=account/account";
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url,expected);
    }

}
