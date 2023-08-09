package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static java.lang.Math.random;
import static resources.ConfProperties.driver;

public class BasePage {
    public static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    public static final WebElement buttonCreateNewBoard = driver.findElement(By.xpath("//*[@data-testid=\"create-board-tile\"]/div"));
    public static final WebElement buttonViewClosedBoards = driver.findElement(By.cssSelector(".view-all-closed-boards-button"));
    //public static final WebElement dropdownWorkSpace = driver.findElement(By.cssSelector(".nch-select"));

    String locatorInputNameBoard = "//*[@data-testid=\"create-board-title-input\"]";
    String locatorButtonApplyCreateNewBoard = "//*[@data-testid=\"create-board-submit-button\"]";
    String locatorButtonCreateFromTemplate = "//*[@data-testid=\"create-from-template-button\"]";
    String locatorFirstElementTemplate = "div.o2d0gKBosLRXMb > div > ul > li:nth-child(1)";//может переделать?

    public void createNewBoard() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(buttonCreateNewBoard));
        buttonCreateNewBoard.click();
        driver.findElement(By.xpath(locatorInputNameBoard))
                .sendKeys("NewDesk" + random());
        driver.findElement(By.xpath(locatorButtonApplyCreateNewBoard)).click();
    }

    public void createNewBoardFromTemplate() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(buttonCreateNewBoard));
        buttonCreateNewBoard.click();
        driver.findElement(By.xpath(locatorButtonCreateFromTemplate)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorFirstElementTemplate))).click();
        driver.findElement(By.xpath(locatorInputNameBoard))
                .sendKeys(" " + random());
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorButtonApplyCreateNewBoard))).click();
    }

    public static void showClosedBoards() {
        buttonViewClosedBoards.click();

    }


}


