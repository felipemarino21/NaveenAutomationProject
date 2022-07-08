package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UrlString {

    public static String propertiesas(String url) throws IOException {
        String path = System.getProperty("user.dir");
        FileInputStream fis = new FileInputStream(path + "\\src\\test\\resources\\global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        return prop.getProperty(url);
    }

}
