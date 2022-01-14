package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.BookingHomePage;
import tests.BaseTest;

public class BookingSteps extends BaseTest {
    @Before
    public void initCucumber() {
        init("97", 30);
    }
    @After
    public void tearDown(){
        quitDriver();
    }
    @Given("Open Booking.com home page")
    public void openBookingComHomePage() {
        driver.get("https://www.booking.com");
    }

    @When("Change language to {string}")
    public void changeLanguageTo(String desiredLanguage) {
        BookingHomePage bookingHomePage = new BookingHomePage(driver, wdWait);
        bookingHomePage.selectLanguage(desiredLanguage);
    }

    @And("Search for destination place {string}")
    public void searchForDestinationPlace(String desiredDestination) {
        BookingHomePage bookingHomePage = new BookingHomePage(driver, wdWait);
        bookingHomePage.searchDestinationByEnteringText(desiredDestination);
        bookingHomePage.selectDestinationByClickingOnSearchResult();
    }

    @And("Select CheckIn and CheckOut dates {string} {string}")
    public void selectCheckInAndCheckOutDates(String checkInDate, String checkOutDate) {
        BookingHomePage bookingHomePage = new BookingHomePage(driver, wdWait);
        bookingHomePage.selectCheckInCheckOutDates(checkInDate, checkOutDate);
    }

    @And("Enter number of Adults, Children, Children age and Rooms {string} {string} {string} {string} {string} {string} {string}")
    public void enterNumberOfAdultsChildrenChildrenAgeAndRooms(String numberOfAdults, String numberOfChildren, String firstChildrenAge, String secondChildrenAge, String thirdChildrenAge, String fourthChildrenAge, String numberOfRooms) throws InterruptedException {
        BookingHomePage bookingHomePage = new BookingHomePage(driver, wdWait);
        bookingHomePage.changeNumberOfAdults(numberOfAdults);
        bookingHomePage.changeNumberOfChildren(numberOfChildren, firstChildrenAge, secondChildrenAge, thirdChildrenAge, fourthChildrenAge);
        bookingHomePage.changeNumberOfRooms(numberOfRooms);
    }

    @And("Click on Search")
    public void clickOnSearch() {
        BookingHomePage bookingHomePage = new BookingHomePage(driver, wdWait);
        bookingHomePage.clickOnSearchSubmitButton();
    }

    @Then("Compare search result parameters with entered parameters {string} {string} {string} {string} {string} {string}")
    public void compareSearchResultParametersWithEnteredParameters(String destination, String checkInDate, String checkOutDate,
                                                                   String numberOfAdults, String numberOfChildren, String numberOfRooms){
        Assert.assertEquals(driver.findElement(By.xpath("//div/input[contains(@value, "+destination+") and contains(@placeholder, "+destination+")]"))
                .getAttribute("value"), destination);
        Assert.assertEquals(driver.findElement(By.xpath("//div/input[contains(@value, "+destination+") and contains(@placeholder, "+destination+" )]"))
                .getAttribute("placeholder"), destination);
        String checkInDateText = driver.findElement(By.xpath("(//div[contains(@data-placeholder, 'Datum prijavljivanja')])[1]")).getText();
        String checkOutDateText = driver.findElement(By.xpath("(//div[contains(@data-placeholder, 'Datum odjavljivanja')])[1]")).getText();
        String numberOfAdultsText = driver.findElement(By.xpath("//span[@class='adults-count']")).getText();
        String numberOfChildrenText = driver.findElement(By.xpath("//span[@class='children-count']")).getText();
        String numberOfRoomsText = driver.findElement(By.xpath("//span[@data-room-count]")).getText();

        Assert.assertTrue((checkInDateText.replaceAll("[.,]", "")).contains(checkInDate));
        Assert.assertTrue((checkOutDateText.replaceAll("[.,]", "")).contains(checkOutDate));
        Assert.assertTrue(numberOfAdultsText.contains(numberOfAdults));
        Assert.assertTrue(numberOfChildrenText.contains(numberOfChildren));
//        The test will fail on the next assertion - the number of rooms (broj jedinica) isn't the same as we entered on the previous page
        Assert.assertTrue(numberOfRoomsText.contains(numberOfRooms));
    }
}
