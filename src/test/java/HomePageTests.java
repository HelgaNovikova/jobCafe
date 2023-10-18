import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import utils.UseCaseBase;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTests extends UseCaseBase {

    private static HomePage homePage;

    @BeforeAll
    public static void classSetup() {
        homePage = new HomePage();
    }

    @BeforeEach
    public void beforeTest() {
        homePage.navigateToHomePage();
    }

    @Test
    public void homePageIsLoadedCorrectly() {
        assertTrue(homePage.isImageVisible());
    }
}
