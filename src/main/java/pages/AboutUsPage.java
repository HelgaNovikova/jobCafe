package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutUsPage extends BasePage {

    @FindBy(xpath = "//h2[contains(text(), 'CAFE')]")
    private WebElement logo;

    public boolean isLogoVisible() {
        return logo.isDisplayed();
    }

    public AboutUsPage() {
        PageFactory.initElements(wd, this);
    }

}
