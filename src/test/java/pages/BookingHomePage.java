package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BookingHomePage extends BasePage{
    WebDriver driver;
    WebDriverWait wdWait;
    public BookingHomePage(WebDriver driver, WebDriverWait wdWait) {
        super(driver, wdWait);
        this.driver = driver;
        this.wdWait = wdWait;
        PageFactory.initElements(driver, this);
    }
    String desiredLanguage = "(//div[contains(text(), '$')])[2]";
    String desiredDatePath = "(//td/span[contains(@aria-label, '$')])[1]";

    @FindBy(xpath = "//button[contains(@data-modal-id, 'language-selection')]")
    WebElement languageIcon;

    public void selectLanguage(String language){
        click(languageIcon);
        click(driver.findElement(By.xpath(desiredLanguage.replace("$",language))));
    }

    @FindBy(css = "#ss")
    WebElement searchDestinationInput;

    public void searchDestinationByEnteringText(String destinationName){
        typeText(searchDestinationInput, destinationName);
    }

    @FindBy(xpath = "(//span[contains(@class, 'search_hl_name')])[1]")
    WebElement selectDestination;

    public void selectDestinationByClickingOnSearchResult(){
        click(selectDestination);
    }

    public void selectCheckInCheckOutDates(String checkInDate, String checkOutDate) {
        this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        while(true){
            List<WebElement> numberOfElementsForCheckInDate = driver.findElements(By.xpath(desiredDatePath.replace("$",checkInDate)));
            if(numberOfElementsForCheckInDate.size() > 0){
                click(driver.findElement(By.xpath(desiredDatePath.replace("$",checkInDate))));
                break;
            }else{
                click(driver.findElement(By.xpath("//div[@data-bui-ref='calendar-next']")));
            }}
        while(true){
            List<WebElement> numberOfElementsForCheckOutDate = driver.findElements(By.xpath(desiredDatePath.replace("$",checkOutDate)));
            if(numberOfElementsForCheckOutDate.size() > 0){
                click(driver.findElement(By.xpath(desiredDatePath.replace("$",checkOutDate))));
                break;
            }else{
                click(driver.findElement(By.xpath("//div[@data-bui-ref='calendar-next']")));
            }}
    }

    @FindBy(xpath = "//label[@id='xp__guests__toggle']")
    WebElement roomsAndOccupancyInput;

    @FindBy(xpath = "(//button[contains(@class, 'bui-stepper__add-button')])[1]")
    WebElement adultsIncrementStepper;
    @FindBy(xpath = "(//button[contains(@class, 'bui-stepper__subtract-button')])[1]")
    WebElement adultsDecrementStepper;
    public void changeNumberOfAdults(String numberOfAdults) {
//      Default number of adults is two
        click(roomsAndOccupancyInput);
        if(Integer.parseInt(numberOfAdults) < 2){
            click(adultsDecrementStepper);
        }else if(Integer.parseInt(numberOfAdults) > 2){
            for(int i = 3; i <= Integer.parseInt(numberOfAdults); i++){
                click(adultsIncrementStepper);
            }
        }
    }

    @FindBy(xpath = "(//button[contains(@class, 'bui-stepper__add-button')])[2]")
    WebElement childrenIncrementStepper;
    @FindBy(xpath = "(//button[contains(@class, 'bui-stepper__subtract-button')])[2]")
    WebElement childrenDecrementStepper;
    String ageOfChildXpath = "//select[@name='age'][%]";
    public void changeNumberOfChildren(String numberOfChildren, String firstChildrenAge, String secondChildrenAge, String thirdChildrenAge, String fourthChildrenAge){
//      Default number of children is zero
        LinkedList<String> childrenAge = new LinkedList<String>();
        childrenAge.add(firstChildrenAge);
        childrenAge.add(secondChildrenAge);
        childrenAge.add(thirdChildrenAge);
        childrenAge.add(fourthChildrenAge);
        for(int i = 1; i <= Integer.parseInt(numberOfChildren); i++){
            click(childrenIncrementStepper);
            String removedItem = childrenAge.removeFirst();
            selectByValue(driver.findElement(By.xpath(ageOfChildXpath.replace("%", String.valueOf(i)))), removedItem);
        }
    }

    @FindBy(xpath = "(//button[contains(@class, 'bui-stepper__add-button')])[3]")
    WebElement roomsIncrementStepper;
    @FindBy(xpath = "(//button[contains(@class, 'bui-stepper__subtract-button')])[3]")
    WebElement roomsDecrementStepper;
    public void changeNumberOfRooms(String numberOfRooms) {
//      Default number of rooms is one
        for(int i = 2; i <= Integer.parseInt(numberOfRooms); i++){
            click(roomsIncrementStepper);
        }
    }

    @FindBy(xpath = "//button[@class='sb-searchbox__button ']")
    WebElement searchSubmitButton;
    public void clickOnSearchSubmitButton(){
        click(searchSubmitButton);
        driver.switchTo().activeElement();
    }
}
