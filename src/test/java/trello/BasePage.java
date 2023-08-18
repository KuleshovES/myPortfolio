package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static resources.ConfProperties.driver;

public class BasePage {
    public static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    public static final WebElement buttonCreateNewBoard = driver.findElement(By.xpath("//*[@data-testid=\"create-board-tile\"]/div"));
    public static final WebElement buttonViewClosedBoards = driver.findElement(By.cssSelector(".view-all-closed-boards-button"));
    //public static final WebElement dropdownWorkSpace = driver.findElement(By.cssSelector(".nch-select"));

    String InputNameBoard = "//*[@data-testid=\"create-board-title-input\"]";
    String DisplayNameBoard = "//*[@data-testid=\"board-name-display\"]";
    String ButtonApplyCreateNewBoard = "//*[@data-testid=\"create-board-submit-button\"]";
    String ButtonCreateFromTemplate = "//*[@data-testid=\"create-from-template-button\"]";
    String FirstElementTemplate = "div.o2d0gKBosLRXMb > div > ul > li:nth-child(1)";//может переделать?

    String actionMenu = "//*[@aria-label=\"Меню\"]";
    String actionMenuButtonClosedBoard = ".js-close-board";
    String actionMenuConfirmClosedBoard = "//*[@value=\"Закрыть\"]";
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

    public static void showClosedBoards() {
        buttonViewClosedBoards.click();

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


