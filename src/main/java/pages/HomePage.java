package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(xpath = "//img[@alt='coming soon']")
    private WebElement image;

    @FindBy(xpath = "//nav[@id='navbar']//a[@name='About Us']")
    private WebElement aboutUsMenuItem;

    @FindBy(xpath = "//nav[@id='navbar']//a[@name='Find Job']")
    private WebElement findJobMenuItem;

    public HomePage() {
        PageFactory.initElements(wd, this);
    }

    public boolean isImageVisible() {
        return image.isDisplayed();
    }

    public void navigateToHomePage() {
        wd.get(properties.getProperty("url"));
    }

    public AboutUsPage navigateToAboutUsPage() {
        aboutUsMenuItem.click();
        return new AboutUsPage();
    }

    public FindJobPage navigateToFindJobPage() {
        findJobMenuItem.click();
        return new FindJobPage();
    }

}
