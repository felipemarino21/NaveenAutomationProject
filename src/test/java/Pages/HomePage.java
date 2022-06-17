package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    public WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    By searchBar = By.name("search");
    By searchButton = By.xpath("//button[@class='btn btn-default btn-lg']");


    public void searchItem(String keyword){
        driver.findElement(searchBar).sendKeys(keyword);
    }
    public void clickSearch(){
        driver.findElement(searchButton).click();
    }

}
