package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import trello.LoginPage;

import java.time.Duration;
import java.util.logging.Logger;

public class ConfProperties {
    public static final Logger LOGGER = Logger.getLogger(RestApiMethods.class.getName());

    public static WebDriver driver;

    public static WebDriver chrome() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    public static WebDriver preconditionWithLogin() throws InterruptedException {
        driver = chrome();
        LoginPage.login();
        return driver;
    }

}