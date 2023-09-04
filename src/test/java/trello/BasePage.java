package trello;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static resources.ConfProperties.LOGGER;
import static resources.ConfProperties.driver;

public class BasePage {
    public static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    String buttonCreateNewBoard = "//*[@data-testid=\"create-board-tile\"]/div";

    String InputNameBoard = "//*[@data-testid=\"create-board-title-input\"]";
    String DisplayNameBoard = "//*[@data-testid=\"board-name-display\"]";
    String ButtonApplyCreateNewBoard = "//*[@data-testid=\"create-board-submit-button\"]";
    String ButtonCreateFromTemplate = "//*[@data-testid=\"create-from-template-button\"]";
    String FirstElementTemplate = "div.o2d0gKBosLRXMb > div > ul > li:nth-child(1)";//может переделать?
    String buttonMainPage = "//*[@id=\"header\"]/a";

    String buttonLogInHomePage = "//a[contains(text(),'Log in')]";

    //account-info-menu
    static String buttonOpenAccountMenu = "//*[@data-testid=\"header-member-menu-button\"]";
    static String buttonLogOutAccountMenu = "//*[@data-testid=\"account-menu-logout\"]";
    static String buttonConfirmLogOut = "logout-submit";

    //boards
    @Step ("Create board by UI with name: {boardName}")
    public void createNewBoard(String boardNameUI) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(buttonCreateNewBoard))));
        driver.findElement(By.xpath(buttonCreateNewBoard)).click();
        driver.findElement(By.xpath(InputNameBoard))
                .sendKeys(boardNameUI);
        driver.findElement(By.xpath(ButtonApplyCreateNewBoard)).click();
    }

    @Step ("Create template board by UI with name: {boardName}")
    public void createNewBoardFromTemplate(String boardNameUI) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(buttonCreateNewBoard))));
        driver.findElement(By.xpath(buttonCreateNewBoard)).click();
        driver.findElement(By.xpath(ButtonCreateFromTemplate)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(FirstElementTemplate))).click();
        driver.findElement(By.xpath(InputNameBoard))
                .clear();
        driver.findElement(By.xpath(InputNameBoard))
                .sendKeys(boardNameUI);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ButtonApplyCreateNewBoard))).click();
    }

    //account
    @Step ("Open account menu")
    public static void openAccountMenu() {
        driver.findElement(By.xpath(buttonOpenAccountMenu)).click();

    }

    @Step ("Get Current Username")
    public String getCurrentUserName() {
        openAccountMenu();
        String locCurrentUserName = "//div[contains(@class, \"tS3UagTaVXEivA\")]";
        return driver.findElement(By.xpath(locCurrentUserName)).getAttribute("textContent");
    }

    @Step ("LogOut")
    public static void logOut() {
        openAccountMenu();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonLogOutAccountMenu))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(buttonConfirmLogOut))).click();
    }

    @Step ("Checking the opening of the main page")
    public boolean mainPageIsOpen () {
        boolean result;

        try {
            result = (driver.findElement(By.xpath(buttonLogInHomePage)).isDisplayed());
            LOGGER.info("Your tab: " + driver.getCurrentUrl());
        } catch (NoSuchElementException ex) {
            result = false;
            LOGGER.info("Your tab: " + driver.getCurrentUrl());
        }
        return result;

    }


    @Step ("Get actual name active board")
    public String getActualNameActiveBoard() throws InterruptedException {
        wait(1000);
        String name = driver.findElement(By.xpath(DisplayNameBoard))
                .getAttribute("textContent");

        return name;
    }

    @Step ("Open main Page")
    public void openMainPage() {
        driver.findElement(By.xpath(buttonMainPage)).click();
    }
}


