package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class BasePage {
    protected static WebDriver wd;
    protected static WebDriverWait wait;
    protected Properties properties = new Properties();

    public Properties getProperties() {
        return properties;
    }

    public BasePage() {
        InputStream inputStream;
        try {
            String propFileName = "application.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setWebDriver(WebDriver webDriver) {
        wd = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    }

    public String getCurrentPageURL() {
        return wd.getCurrentUrl();
    }

    public void takeScreenshot(String name) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(properties.getProperty("screenshotsFolder") + name + ".jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
