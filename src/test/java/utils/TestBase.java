package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class TestBase {
    public WebDriver driver;
    public String path = System.getProperty("user.dir");

    public WebDriver WebDriverManager() throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\global.properties");
        Properties prop = new Properties();
        prop.load(fis);

        if (driver == null) {

            if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
            } else if (prop.getProperty("browser").equalsIgnoreCase("edge")) {
                System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\msedgedriver.exe");
                driver = new EdgeDriver();
            }
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();

        }
        return driver;
    }



}