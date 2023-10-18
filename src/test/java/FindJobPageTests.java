import model.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.FindJobPage;
import pages.HomePage;
import utils.UseCaseBase;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindJobPageTests extends UseCaseBase {

    private static FindJobPage jobPage;
    private static final Logger logger = LogManager.getLogger(FindJobPageTests.class);

    @BeforeEach
    public void classSetup() {
        HomePage hp = new HomePage();
        hp.navigateToHomePage();
        jobPage = hp.navigateToFindJobPage();
    }

    @ValueSource(strings = {"Toronto, Tel Aviv, Chicago, New York"})
    @ParameterizedTest
    public void searchByLocation(String locations) throws InterruptedException {
        List<String> locationList = Arrays.asList(locations.split("\\s*,\\s*"));
        List<Job> jobs = jobPage.findJobsByLocation(locations);
        jobPage.takeScreenshot("locationSearch");
        for (Job item : jobs) {
            long number = locationList.stream().filter(s -> item.getLocation().contains(s)).count();
            assertEquals(1, number);
        }
    }

    @ValueSource(strings = {"QA, Developer, Project Manager"})
    @ParameterizedTest
    public void searchByPosition(String positions) throws InterruptedException {
        List<String> positionList = Arrays.asList(positions.split("\\s*,\\s*"));
        List<Job> jobs = jobPage.findJobsByPosition(positions);
        jobPage.takeScreenshot("positionSearch");
        for (Job item : jobs) {
            long number = positionList.stream().filter(s -> item.getPosition().toLowerCase().contains(s.toLowerCase())).count();
            assertEquals(1, number);
        }
    }

    @ValueSource(strings = {"Apple, Facebook, Google"})
    @ParameterizedTest
    public void searchByCompany(String companies) throws InterruptedException {
        List<String> companiesList = Arrays.asList(companies.split("\\s*,\\s*"));
        List<Job> jobs = jobPage.findJobsByCompany(companies);
        jobPage.takeScreenshot("companiesSearch");
        for (Job item : jobs) {
            long number = companiesList.stream().filter(s -> item.getCompany().contains(s)).count();
            assertEquals(1, number);
        }
    }

    @MethodSource("combinedSearchData")
    @ParameterizedTest
    public void combinedSearch(String companies, String locations, String positions) throws InterruptedException {
        List<String> positionList = Arrays.asList(positions.split("\\s*,\\s*"));
        List<String> companiesList = Arrays.asList(companies.split("\\s*,\\s*"));
        List<String> locationList = Arrays.asList(locations.split("\\s*,\\s*"));
        List<Job> jobs = jobPage.findJobsByPositionLocationCompany(companies, locations, positions);
        jobPage.takeScreenshot("combinedSearch");
        for (Job item : jobs) {
            boolean companyFound = companiesList.stream().anyMatch(s -> item.getCompany().contains(s));
            boolean positionFound = positionList.stream().anyMatch(s -> item.getPosition().contains(s));
            boolean locationFound = locationList.stream().anyMatch(s -> item.getLocation().contains(s));
            assertTrue(companyFound && locationFound && positionFound);
        }
    }

    private static Stream<Arguments> combinedSearchData() {
        return Stream.of(
                Arguments.of("Google", "USA", "Manager")
        );
    }


    @Test
    public void noResultsSearch() throws InterruptedException {
        List<Job> jobs = jobPage.findJobsByPosition("abracadabra");
        assertTrue(jobs.isEmpty());
        String actualErrorMessage = jobPage.getErrorMessageNoResult();
        String expectedErrorMessage = "No results found!Please try different search criteria";
        assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void reset() {
        jobPage.fillCompanyFilter("sdfsdf");
        jobPage.fillLocationFilter("sdfsdf");
        jobPage.fillPositionFilter("gertergd");
        jobPage.resetFields();
        assertTrue(jobPage.getCompanyFilterText().isEmpty());
        assertTrue(jobPage.getLocationFilterText().isEmpty());
        assertTrue(jobPage.getPositionFilterText().isEmpty());
    }
}
