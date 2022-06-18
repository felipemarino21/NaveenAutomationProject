package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class CartPage {
    public WebDriver driver;


    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    By itemQuantity = By.xpath("(//input[@class='form-control'])[1]");

    public void compareQuantity(WebDriverWait wait,String number){
        int n = Integer.parseInt(number);
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemQuantity));
        int value = Integer.parseInt(driver.findElement(itemQuantity).getAttribute("value"));
        Assert.assertEquals(value,n);
    }



}
