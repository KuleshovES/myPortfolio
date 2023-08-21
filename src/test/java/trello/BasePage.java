package trello;

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
    public static final WebElement buttonCreateNewBoard = driver.findElement(By.xpath("//*[@data-testid=\"create-board-tile\"]/div"));

    String InputNameBoard = "//*[@data-testid=\"create-board-title-input\"]";
    String DisplayNameBoard = "//*[@data-testid=\"board-name-display\"]";
    String ButtonApplyCreateNewBoard = "//*[@data-testid=\"create-board-submit-button\"]";
    String ButtonCreateFromTemplate = "//*[@data-testid=\"create-from-template-button\"]";
    String FirstElementTemplate = "div.o2d0gKBosLRXMb > div > ul > li:nth-child(1)";//может переделать?

    String buttonLogInHomePage = "//a[contains(text(),'Log in')]";

    //account-info-menu
    String buttonOpenAccountMenu = "//*[@data-testid=\"header-member-menu-button\"]";
    String buttonLogOutAccountMenu = "//*[@data-testid=\"account-menu-logout\"]";
    String buttonConfirmLogOut = "logout-submit";

    //TODO change language
    String actionMenu = "//*[@aria-label=\"Меню\"]";
    String actionMenuConfirmClosedBoard = "//*[@value=\"Закрыть\"]";

    String actionMenuButtonClosedBoard = ".js-close-board";
    String buttonDeleteClosedBoard = "//*[@data-testid=\"close-board-delete-board-button\"]";
    String buttonConfirmDeleteClosedBoard = "//*[@data-testid=\"close-board-delete-board-confirm-button\"]";

    public void createNewBoard(String boardNameUI) {
        System.out.println("Start create new Board: " + boardNameUI);
        wait.until(ExpectedConditions.elementToBeClickable(buttonCreateNewBoard));
        buttonCreateNewBoard.click();
        driver.findElement(By.xpath(InputNameBoard))
                .sendKeys(boardNameUI);
        driver.findElement(By.xpath(ButtonApplyCreateNewBoard)).click();
    }

    public void createNewBoardFromTemplate(String boardNameUI) {
        System.out.println("Start create new Board From Template: " + boardNameUI);
        wait.until(ExpectedConditions.elementToBeClickable(buttonCreateNewBoard));
        buttonCreateNewBoard.click();
        driver.findElement(By.xpath(ButtonCreateFromTemplate)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(FirstElementTemplate))).click();
        driver.findElement(By.xpath(InputNameBoard))
                .clear();
        driver.findElement(By.xpath(InputNameBoard))
                .sendKeys(boardNameUI);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ButtonApplyCreateNewBoard))).click();
    }

    public void openAccountMenu () {
        driver.findElement(By.xpath(buttonOpenAccountMenu)).click();

    }

    public String getCurrentUserName() {
        openAccountMenu();
        String locCurrentUserName = "//div[contains(@class, \"tS3UagTaVXEivA\")]";
        return driver.findElement(By.xpath(locCurrentUserName)).getAttribute("textContent");
    }

    public void logOut () {
        openAccountMenu();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonLogOutAccountMenu))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(buttonConfirmLogOut))).click();
    }

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


    public String getActualNameActiveBoard() {
        String name = driver.findElement(By.xpath(DisplayNameBoard))
                .getAttribute("textContent");
        System.out.println(name);
        return name;
    }

    public void closedBoard() {
        driver.findElement(By.xpath(actionMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(actionMenuButtonClosedBoard))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(actionMenuConfirmClosedBoard))).click();

    }

    public void deleteBoard() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonDeleteClosedBoard))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonConfirmDeleteClosedBoard))).click();

    }
}


