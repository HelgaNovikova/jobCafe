package pages;

import model.Job;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class FindJobPage extends BasePage {

    @FindBy(xpath = "//input[@name='position']")
    private WebElement positionFilter;

    @FindBy(xpath = "//input[@name='location']")
    private WebElement locationFilter;

    @FindBy(xpath = "//input[@name='company']")
    private WebElement companyFilter;

    @FindBy(xpath = "//button[text()='search']")
    private WebElement searchButton;

    @FindBy(xpath = "//button[text()='reset']")
    private WebElement resetButton;

    @FindBy(xpath = "//ul[@class='entry-meta']/li")
    private List<WebElement> onePageResultField;

    @FindBy(xpath = "//div[@class='error-indicator']")
    private WebElement errorMessageNoResult;

    public FindJobPage() {
        PageFactory.initElements(wd, this);
    }

    public String getErrorMessageNoResult() {
        String firstString = errorMessageNoResult.findElement(By.xpath("./span[1]")).getText();
        String secondString = errorMessageNoResult.findElement(By.xpath("./span[2]")).getText();
        return firstString + secondString;
    }

    public void fillCompanyFilter(String companies) {
        companyFilter.sendKeys(companies);
    }

    public void fillLocationFilter(String locations) {
        locationFilter.sendKeys(locations);
    }

    public void fillPositionFilter(String positions) {
        positionFilter.sendKeys(positions);
    }

    public String getPositionFilterText() {
        return positionFilter.getText();
    }

    public String getLocationFilterText() {
        return locationFilter.getText();
    }

    public String getCompanyFilterText() {
        return companyFilter.getText();
    }

    public void resetFields() {
        resetButton.click();
    }

    public List<Job> findJobsByCompany(String companies) throws InterruptedException {
        fillCompanyFilter(companies);
        searchButton.click();
        Thread.sleep(2000); //todo try to get rid of it
        return getJobsFromResultField(onePageResultField);
    }

    public List<Job> findJobsByLocation(String locations) throws InterruptedException {
        fillLocationFilter(locations);
        searchButton.click();
        Thread.sleep(2000); //todo try to get rid of it
        return getJobsFromResultField(onePageResultField);
    }

    public List<Job> findJobsByPosition(String positions) throws InterruptedException {
        fillPositionFilter(positions);
        searchButton.click();
        Thread.sleep(2000); //todo try to get rid of it
        return getJobsFromResultField(onePageResultField);
    }

    private List<Job> getJobsFromResultField(List<WebElement> resultField) {
        List<Job> jobs = new ArrayList<>();
        for (WebElement item : onePageResultField) {
            String position = item.findElement(By.xpath(".//h2")).getText();
            String company = item.findElement(By.xpath(".//p/b")).getText();
            String location = item.findElement(By.xpath("./span[2]")).getText();
            jobs.add(new Job(position, company, location));
        }
        return jobs;
    }

    public List<Job> findJobsByPositionLocationCompany(String companies, String locations, String positions) throws InterruptedException {
        positionFilter.sendKeys(positions);
        locationFilter.sendKeys(locations);
        companyFilter.sendKeys(companies);
        searchButton.click();
        Thread.sleep(2000); //todo try to get rid of it
        return getJobsFromResultField(onePageResultField);
    }
}
