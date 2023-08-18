package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static resources.ConfProperties.driver;

public class BoardPage {
    public static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

    String buttonSubmitAddColumn = "//*[@id=\"board\"]/div/form/div/input";
    String firstCardInActiveBoard = "//*[@class=\"list-card-title js-card-name\"]";

    //block edit Card
    String elCardNameEditCard = ".js-card-detail-title-input";
    String elClosedEditCard = ".js-close-window";

    String buttonCopyInEditCard = ".js-copy-card";

    //block pop-over
    String buttonSubmit = ".js-submit";
    String textAreaCopyCard = "//textarea[@name=\"name\"]";

    public void createColumn(String nameColumn) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".list-name-input")))
                .sendKeys(nameColumn);
        driver.findElement(By.xpath(buttonSubmitAddColumn)).click();
    }

    public void openBoard(String nameBoard) {
        String locatorBoard = "//*[@title=\"" + nameBoard + "\"]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorBoard)));
        driver.findElement(By.xpath(locatorBoard))
                .click();

    }

    public void createCard(String nameCard) throws InterruptedException {

        Thread.sleep(1000); //BAD!
        WebElement buttonAddCard = driver.findElement(By.cssSelector(".js-add-a-card"));
        if (buttonAddCard.isDisplayed()) {
            buttonAddCard.click();
            Thread.sleep(1000); //BAD!
            WebElement inputCardTitle = driver.findElement(By.cssSelector(".js-card-title"));
            inputCardTitle.click();
            inputCardTitle.sendKeys(nameCard);
            Thread.sleep(1000); //BAD!
            WebElement submitAddCard = driver.findElement(By.cssSelector(".js-add-card"));
            submitAddCard.click();
        } else {
            Thread.sleep(1000); //BAD!
            WebElement inputCardTitle = driver.findElement(By.cssSelector(".js-card-title"));
            inputCardTitle.click();
            inputCardTitle.sendKeys(nameCard);
            Thread.sleep(1000); //BAD!
            WebElement submitAddCard = driver.findElement(By.cssSelector(".js-add-card"));
            submitAddCard.click();
        }

    }

    public String getActualCardNameInActiveBoard() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstCardInActiveBoard)));
        String name = driver.findElement(By.xpath(firstCardInActiveBoard))
                .getAttribute("textContent");
        System.out.println(name);
        return name;
    }

    public void updateCardName(String newName) {

        driver.findElement(By.xpath("//span[text()='FirstTask']")).click();
        driver.findElement(By.cssSelector(elCardNameEditCard)).click();
        driver.findElement(By.cssSelector(elCardNameEditCard)).clear();
        driver.findElement(By.cssSelector(elCardNameEditCard)).sendKeys(newName);
        driver.findElement(By.cssSelector(elCardNameEditCard)).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector(elClosedEditCard)).click();

    }

    public void copyCard(String copiedCardName) {

        driver.findElement(By.xpath("//span[text()='FirstTask']")).click();
        driver.findElement(By.cssSelector(buttonCopyInEditCard)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textAreaCopyCard))).clear();
        driver.findElement(By.xpath(textAreaCopyCard)).sendKeys(copiedCardName);

        driver.findElement(By.cssSelector(buttonSubmit)).click();
        driver.findElement(By.cssSelector(elClosedEditCard)).click();

    }

    public void moveCard() {
    }

    public void editColumn() {
    }
}
