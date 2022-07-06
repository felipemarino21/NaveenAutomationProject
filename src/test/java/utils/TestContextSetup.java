package utils;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class TestContextSetup {
    public WebDriver driver;
    public TestBase testBase;

    public TestContextSetup() throws IOException {
        this.testBase = new TestBase();
        this.driver = this.testBase.WebDriverManager();
    }
}
