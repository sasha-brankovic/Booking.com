package selenium_core;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends DriverManager {

    @Override
    public void createWebDriver(String browserVersion) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver"+browserVersion+".exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        this.driver = new ChromeDriver(chromeOptions);
    }
}
