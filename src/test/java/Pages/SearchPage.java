package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class SearchPage {
    public WebDriver driver;


    public SearchPage(WebDriver driver){
        this.driver = driver;
    }

    By results = By.xpath("//div[@class='caption']/h4/a");


    public void compareResults(String name){
        List<WebElement> list = driver.findElements(results);
        for (WebElement webElement : list) {
            String confirmation = webElement.getText();
            Assert.assertTrue(confirmation.contains(name));
        }
    }




}
