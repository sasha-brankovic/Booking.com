package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
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
}
