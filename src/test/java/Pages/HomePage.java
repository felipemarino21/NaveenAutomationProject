package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class HomePage {
    public WebDriver driver;

    public HomePage(WebDriver driver){

        this.driver = driver;
    }

    private By searchBar = By.name("search");
    private By searchButton = By.xpath("//button[@class='btn btn-default btn-lg']");

    private By category = By.xpath("//*[contains(text(),'MP3')]");

    private By subCategory = By.xpath("//*[contains(text(),'MP3')]/following-sibling::div/div/ul/li");
    private By item = By.xpath("((//*[.='MacBook']/parent::div/parent::div)/div[@class='button-group']/button)[1]");
    private By cart = By.xpath("//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']");

    private By itemInCart = By.xpath("//ul[@class='dropdown-menu pull-right']//a[contains(text(),'MacBook')]");

    private By cartNotification = By.xpath("//div[@class='alert alert-success alert-dismissible']/i");

    private By viewCart = By.xpath("(//p/a/strong)[1]");

    private By currencyEuros = By.xpath("//button[@name='EUR']");

    private By currencyButton = By.id("form-currency");
    private By prices = By.xpath("//p[@class='price']");

    public void searchItem(String keyword){
        driver.findElement(searchBar).sendKeys(keyword);
    }
    public void clickSearch(){
        driver.findElement(searchButton).click();
    }
    public void hoverElement(Actions a){

        WebElement ele = driver.findElement(category);
        a.moveToElement(ele).build().perform();
    }
    public void checkSubCategory(){
        List<WebElement> list = driver.findElements(subCategory);
        for (WebElement webElement : list) {
            Assert.assertTrue(webElement.isDisplayed());
        }
    }
    public void addItemToCart(){
        driver.findElement(item).click();
    }
    public void clickCart(WebDriverWait wait){
        wait.until(ExpectedConditions.elementToBeClickable(cart));
        driver.findElement(cart).click();
    }
    public void checkItemInCart(WebDriverWait wait){
        wait.until(ExpectedConditions.elementToBeClickable(itemInCart));
        Assert.assertTrue(driver.findElement(itemInCart).isDisplayed());
    }
    public void checkCartNotification(){
        Assert.assertTrue(driver.findElement(cartNotification).isDisplayed());
    }
    public void clickViewCart(WebDriverWait wait){
        wait.until(ExpectedConditions.elementToBeClickable(viewCart));
        driver.findElement(viewCart).click();
    }
    public void changeCurrencyToEuros(){
        driver.findElement(currencyButton).click();
        driver.findElement(currencyEuros).click();
    }
    public void CheckIfCurrencyIsEuros(){
        List<WebElement> list = driver.findElements(prices);
        for (WebElement webElement : list) {
            String currency = webElement.getText();
            Assert.assertTrue(currency.contains("€"));
        }
    }




}
