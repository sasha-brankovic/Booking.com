package pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class BasePage {
    WebDriver driver;
    WebDriverWait wdWait;
    String waitTimeExplicit = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WAIT_TIME");
    int waitExplicit = Integer.parseInt(waitTimeExplicit);

    public BasePage(WebDriver driver, WebDriverWait wdWait){
        this.driver = driver;
        this.wdWait = wdWait;
        PageFactory.initElements(driver, this);
    }
    public void typeText(WebElement element, String text){
        wdWait = new WebDriverWait(driver, waitExplicit);
        wdWait.until(ExpectedConditions.visibilityOf(element));
        wdWait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            if(text != null) {
                element.clear();
                element.sendKeys(text);
            } else {
                System.out.println("Parameter was null!");
            }
        }catch (StaleElementReferenceException e){
            if(text != null) {
                element.clear();
                element.sendKeys(text);
            } else {
                System.out.println("Parameter was null!");
            }
        }
    }
    public void click(WebElement element){
        wdWait = new WebDriverWait(driver, waitExplicit);
        wdWait.until(ExpectedConditions.visibilityOf(element));
        wdWait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
            element.click();
        }catch (StaleElementReferenceException e){
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
            element.click();
        }
    }
    public void selectByVisibleText(WebElement element, String value){
        wdWait = new WebDriverWait(driver, waitExplicit);
        wdWait.until(ExpectedConditions.visibilityOf(element));
        wdWait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            if (value!=null) {
                Select subjectHeadingSelect = new Select(element);
                subjectHeadingSelect.selectByVisibleText(value);
            }else{
                System.out.println("Parameter was null!");
            }
        }catch (StaleElementReferenceException e){
            if (value!=null) {
                Select subjectHeadingSelect = new Select(element);
                subjectHeadingSelect.selectByVisibleText(value);
            }else{
                System.out.println("Parameter was null!");
            }
        }
    }
    public void selectByValue(WebElement element, String value){
        wdWait = new WebDriverWait(driver, waitExplicit);
        wdWait.until(ExpectedConditions.visibilityOf(element));
        wdWait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            if (value!=null) {
                Select subjectHeadingSelect = new Select(element);
                subjectHeadingSelect.selectByValue(value);
            }else{
                System.out.println("Parameter was null!");
            }
        }catch (StaleElementReferenceException e){
            if (value!=null) {
                Select subjectHeadingSelect = new Select(element);
                subjectHeadingSelect.selectByValue(value);
            }else{
                System.out.println("Parameter was null!");
            }
        }
    }
    public void assertEquals(WebElement element, String expectedValue){
        wdWait = new WebDriverWait(driver, waitExplicit);
        wdWait.until(ExpectedConditions.visibilityOf(element));
        wdWait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            Assert.assertEquals(element.getText(), expectedValue);
        }catch (StaleElementReferenceException e){
            Assert.assertEquals(element.getText(), expectedValue);
        }
    }
}
