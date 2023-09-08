package trello;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static resources.ConfProperties.LOGGER;
import static resources.ConfProperties.driver;

public class BasePage {
    public static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    String buttonCreateNewBoard = "//*[@data-testid=\"create-board-tile\"]/div";

    String inputNameBoard = "//*[@data-testid=\"create-board-title-input\"]";
    String displayNameBoard = "//*[@data-testid=\"board-name-display\"]";
    String buttonApplyCreateNewBoard = "//*[@data-testid=\"create-board-submit-button\"]";
    String buttonCreateFromTemplate = "//*[@data-testid=\"create-from-template-button\"]";
    String firstElementTemplate = "div.o2d0gKBosLRXMb > div > ul > li:nth-child(1)";//может переделать?
    String buttonMainPage = "//*[@id=\"header\"]/a";



    String buttonLogInHomePage = "//a[contains(text(),'Log in')]";

    //--
    private final String firstBoardOnBasePage = "//a[@class=\"board-tile\"]";
    private final String buttonShowedClosedBoards = ".view-all-closed-boards-button";

    //leftMenu
    private final String buttonGoToBoardsWorkSpace = "//*[@data-testid=\"open-boards-link\"]";



    //account-info-menu
    static String buttonOpenAccountMenu = "//*[@data-testid=\"header-member-menu-button\"]";
    static String buttonLogOutAccountMenu = "//*[@data-testid=\"account-menu-logout\"]";
    static String buttonConfirmLogOut = "logout-submit";

    //boards
    @Step("Create board by UI with name: {boardName}")
    public void createNewBoard(String boardNameUI) {
        String boardInWorkSpace = "//a[contains(text(),'" + boardNameUI + "')]";
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(buttonCreateNewBoard))));
        driver.findElement(By.xpath(buttonCreateNewBoard)).click();
        driver.findElement(By.xpath(inputNameBoard))
                .sendKeys(boardNameUI);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonApplyCreateNewBoard))).click();
        //driver.findElement(By.xpath(ButtonApplyCreateNewBoard)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(boardInWorkSpace)));
        driver.findElement(By.xpath(buttonGoToBoardsWorkSpace)).click();
    }

    @Step("Create template board by UI with name: {boardName}")
    public void createNewBoardFromTemplate(String boardNameUI) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(buttonCreateNewBoard))));
        driver.findElement(By.xpath(buttonCreateNewBoard)).click();
        driver.findElement(By.xpath(buttonCreateFromTemplate)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(firstElementTemplate))).click();
        driver.findElement(By.xpath(inputNameBoard))
                .clear();
        driver.findElement(By.xpath(inputNameBoard))
                .sendKeys(boardNameUI);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonApplyCreateNewBoard))).click();
        driver.findElement(By.xpath(buttonGoToBoardsWorkSpace)).click();
    }

    //account
    @Step("Open account menu")
    public static void openAccountMenu() {
        driver.findElement(By.xpath(buttonOpenAccountMenu)).click();

    }

    @Step("Get Current Username")
    public String getCurrentUserName() {
        openAccountMenu();
        String locCurrentUserName = "//div[contains(@class, \"tS3UagTaVXEivA\")]";
        return driver.findElement(By.xpath(locCurrentUserName)).getAttribute("textContent");
    }

    @Step("LogOut")
    public static void logOut() {
        openAccountMenu();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonLogOutAccountMenu))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(buttonConfirmLogOut))).click();
    }

    @Step("Checking the opening of the main page")
    public boolean mainPageIsOpen() {
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

    @Step("Show closed boards on UI")
    public boolean showClosedBoards() {
        boolean result;
        driver.findElement(By.cssSelector(buttonShowedClosedBoards)).click();
        try {
            LOGGER.info("Try find Closed Boards");
            driver.findElement(By.xpath("//button[text()='Reopen']")).isDisplayed();
            result = true;

        } catch (NoSuchElementException ex) {
            LOGGER.info("Closed Boards not found");
            result = false;
        }
        return result;
    }

    @Step("Get actual name active board")
    public String getActualNameActiveBoard() throws InterruptedException {
        wait(1000);
        String name = driver.findElement(By.xpath(displayNameBoard))
                .getAttribute("textContent");

        return name;
    }

    @Step("Get name first board")
    public String getNameFirstBoard() {
        return driver.findElement(By.xpath(firstBoardOnBasePage))
                .getAttribute("outerText");
    }

    @Step("Open main Page")
    public void openMainPage() {
        driver.findElement(By.xpath(buttonMainPage)).click();
    }
}


