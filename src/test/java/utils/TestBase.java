package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
    public WebDriver driver;
    public String path = System.getProperty("user.dir");

    public WebDriver WebDriverManager() throws IOException {

        Properties prop = getProperties();
        String url = prop.getProperty("url");
        if (driver == null) {
            if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
                driver.manage().deleteAllCookies();
                driver.get(url);
            } else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
                // Mozilla Firefox Code
            } else if (prop.getProperty("browser").equalsIgnoreCase("edge")) {
                // Microsoft Edge Code
            }
        }

        return driver;
    }

    public Properties getProperties() throws IOException {
        FileInputStream fis = new FileInputStream(path + "\\src\\test\\resources\\global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        return prop;
    }

    public void setProperties(String propertie, String value) {
        Properties prop = new Properties();
        try {
            final FileInputStream fis = new FileInputStream(path + "\\src\\test\\resources\\global.properties");
            prop.load(fis);
            fis.close();
            prop.setProperty(propertie, value);
            final FileOutputStream output = new FileOutputStream(path + "\\src\\test\\resources\\global.properties");
            prop.store(output, "");
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}