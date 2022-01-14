package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium_core.DriverManager;
import selenium_core.GetDriverManager;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wdWait;
    DriverManager driverManager;

    public void init(String browserVersion, int implicitlyWait){
        driverManager = GetDriverManager.getChromeDriverManager();
        this.driver = driverManager.getWebDriver(browserVersion);
        this.driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
    }
    public void quitDriver(){
        driverManager.quiteWebDriver();
    }
}
