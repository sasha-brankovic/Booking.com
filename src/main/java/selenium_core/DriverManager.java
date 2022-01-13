package selenium_core;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    WebDriver driver;
    public abstract void createWebDriver(String browserVersion);

    public WebDriver getWebDriver(String browserVersion){
        if(driver == null){
            createWebDriver(browserVersion);
        }
        return driver;
    }
    public void quiteWebDriver(){
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
