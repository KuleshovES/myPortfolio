package resources;

import entities.Board;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import trello.LoginPage;

import java.time.Duration;
import java.util.logging.Logger;

public class ConfProperties {
    public static final Logger LOGGER = Logger.getLogger(RestApiMethods.class.getName());

    public static WebDriver driver;

    @Step("Prepare WebDriver")
    public static WebDriver chrome() {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    @Step("Precondition login")
    public static WebDriver preconditionWithLogin() throws InterruptedException {
        driver = chrome();
        LoginPage.login();
        return driver;
    }


    public static void clearDataAndCloseDriver () {
        RestApiMethods.closedAllBoards();
    }

    public static void clearDataUI (Board board) {
        RestApiMethods.deleteBoard(board.getId());
    }
}