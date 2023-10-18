import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.AboutUsPage;
import pages.HomePage;
import utils.UseCaseBase;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AboutUsPageTests extends UseCaseBase {

    private static AboutUsPage aboutUsPage;

    @BeforeAll
    public static void classSetup() {
        HomePage homePage = new HomePage();
        homePage.navigateToHomePage();
        aboutUsPage = homePage.navigateToAboutUsPage();
    }

    @Test
    public void aboutUsPageIsLoaded() {
        assertTrue(aboutUsPage.isLogoVisible());
    }
}
