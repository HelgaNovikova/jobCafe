package utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class UseCaseBase {

    private static WebDriver webDriver;
    private static BasePage page;

    @BeforeAll
    public static void setupMain() {
        page = new BasePage();
        webDriver = Driver.getWebDriver(page.getProperties().getProperty("browser"));
        page.setWebDriver(webDriver);
    }


    @AfterAll
    public static void tearDownMain() {
        Driver.closeDriver();
        webDriver = null;
    }
}
