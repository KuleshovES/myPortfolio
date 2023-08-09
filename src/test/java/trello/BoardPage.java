package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static resources.ConfProperties.driver;

public class BoardPage {
    public static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    WebElement buttonAddColumn = driver.findElement(By.cssSelector(".open-add-list.js-open-add-list"));
    //*[@id="board"]/div/form/a
    //WebElement buttonAddCard = driver.findElement(By.cssSelector(".js-add-a-card"));
    //WebElement submitAddCard = driver.findElement(By.cssSelector(".js-add-card"));
    String buttonSubmitAddColumn = "//*[@id=\"board\"]/div/form/div/input";

    public void createColumn(String nameColumn) {
        //buttonAddColumn.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".list-name-input")))
                .sendKeys(nameColumn);
        driver.findElement(By.xpath(buttonSubmitAddColumn)).click();
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
        }
        else {
            Thread.sleep(1000); //BAD!
            WebElement inputCardTitle = driver.findElement(By.cssSelector(".js-card-title"));
            inputCardTitle.click();
            inputCardTitle.sendKeys(nameCard);
            Thread.sleep(1000); //BAD!
            WebElement submitAddCard = driver.findElement(By.cssSelector(".js-add-card"));
            submitAddCard.click();
        }

    }
    public void editCard() {
    }
    public void moveCard() {
    }
    public void editColumn() {
    }
}
