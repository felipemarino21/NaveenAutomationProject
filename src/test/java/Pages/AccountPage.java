package Pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.TextConstants;

public class AccountPage {
    public WebDriver driver;


    public AccountPage(WebDriver driver){
        this.driver = driver;
    }
    public void compareUrl(){
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url,TextConstants.AccountUrl);
    }

}
