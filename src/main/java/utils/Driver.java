package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    private static WebDriver webDriver;

    public static WebDriver getWebDriver(String browser) {

        switch (browser) {
            case "FIREFOX" -> webDriver = new FirefoxDriver();
            case "IE" -> webDriver = new InternetExplorerDriver();
            default -> webDriver = new ChromeDriver();
        }

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        return webDriver;
    }

    public static void closeDriver() {
        if (webDriver != null) {
            webDriver.close();
        }
    }
}
