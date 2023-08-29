package trello;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static resources.ConfProperties.LOGGER;
import static resources.ConfProperties.driver;
//import static resources.ConfProperties.driver;

public class BoardPage {

    public final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

    String firstCardInActiveBoard = "//*[@class=\"list-card-title js-card-name\"]";
    String defaultSecondTask = "//span[text()='SecondTask']";
    String buttonShowedClosedBoards = ".view-all-closed-boards-button";

    //open board
    String locFirstList = "//*[@aria-label=\"Backlog\"]";
    String locSecondList = "//*[@aria-label=\"To Do\"]";
    String buttonFiltered = "//*[@data-testid=\"filter-popover-button\"]";
    String inputFilter = "//input[@aria-placeholder]";
    String buttonAddNewColumn = ".js-add-list";
    String buttonSubmitAddColumn = "//*[@id=\"board\"]/div/form/div/input";

    //block edit Card
    String locCardNameEditCard = ".js-card-detail-title-input";
    String locClosedEditCard = ".js-close-window";

    String buttonCopyCardInEdit = ".js-copy-card";
    String buttonArchiveCardInEdit = ".js-archive-card";
    String buttonDeleteCardInEdit = ".js-delete-card";
    String buttonAddLabelToCardInEdit = ".js-edit-labels";

    //block pop-over
    String buttonSubmitPopover = ".js-submit";
    String buttonConfirmPopover = ".js-confirm";
    String buttonClosePopover = "//*[@data-testid=\"popover-close\"]";
    String textAreaCopyCard = "//textarea[@name=\"name\"]";
    String addLabelGreen = "//div[@data-color=\"green\"]";
    String addLabelBlue = "//div[@data-color=\"blue\"]";




    public void createColumn(String nameColumn) {
        driver.findElement(By.cssSelector(buttonAddNewColumn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".list-name-input")))
                .sendKeys(nameColumn);
        driver.findElement(By.xpath(buttonSubmitAddColumn)).click();
    }

    public boolean columnByNameIsDisplayed (String name) {
        String locFindColumn = "//*[@aria-label=\"" + name + "\"]";
        boolean res;
        LOGGER.info("Start searching column = " + name);
        try {
            driver.findElement(By.xpath(locFindColumn)).isDisplayed();
            LOGGER.info("Column find!");
            res = true;
        } catch (NoSuchElementException ex) {
            LOGGER.info("Column not find!");
            res = false;
        }
        return res;
    }

    public void openBoard(String nameBoard) {
        String locatorBoard = "//*[@title=\"" + nameBoard + "\"]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorBoard)));
        driver.findElement(By.xpath(locatorBoard)).click();

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
        return name;
    }

    public void updateCardName(String newName) {

        driver.findElement(By.xpath("//span[text()='FirstTask']")).click();
        driver.findElement(By.cssSelector(locCardNameEditCard)).click();
        driver.findElement(By.cssSelector(locCardNameEditCard)).clear();
        driver.findElement(By.cssSelector(locCardNameEditCard)).sendKeys(newName);
        driver.findElement(By.cssSelector(locCardNameEditCard)).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector(locClosedEditCard)).click();

    }

    public void copyCard(String copiedCardName) {

        driver.findElement(By.xpath("//span[text()='FirstTask']")).click();
        driver.findElement(By.cssSelector(buttonCopyCardInEdit)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textAreaCopyCard))).clear();
        driver.findElement(By.xpath(textAreaCopyCard)).sendKeys(copiedCardName);

        driver.findElement(By.cssSelector(buttonSubmitPopover)).click();
        driver.findElement(By.cssSelector(locClosedEditCard)).click();

    }

    public void deleteCard(String cardName) {
        String elCardByName = "//span[text()='" + cardName + "']";
        driver.findElement(By.xpath(elCardByName)).click();
        driver.findElement(By.cssSelector(buttonArchiveCardInEdit)).click();
        driver.findElement(By.cssSelector(buttonDeleteCardInEdit)).click();
        driver.findElement(By.cssSelector(buttonConfirmPopover)).click();

    }

    public boolean cardByNameIsNotExist(String cardName) {
        LOGGER.info("Start check exist card: " + cardName);
        String elCardByName = "//span[text()='" + cardName + "']";
        boolean result;

        try {
            result = !(driver.findElement(By.xpath(elCardByName)).isDisplayed());
            LOGGER.info("Find card by name :" + cardName);
        } catch (NoSuchElementException ex) {
            result = true;
            LOGGER.info("Card not found by name :" + cardName);
        }
        return result;
    }

    public void moveCardByDrag(String cardName) {

        String locCardByName = "//span[text()='" + cardName + "']";

        WebElement elCardByName = driver.findElement(By.xpath(locCardByName));
        WebElement elSecondList = driver.findElement(By.xpath(locSecondList));

        Actions action = new Actions(driver);
        action
                .dragAndDrop(elCardByName, elSecondList)
                .build()
                .perform();

    }

    public boolean cardInSecondList(String cardName) {
        String locCardByName = "//span[text()='" + cardName + "']";
        WebElement elSecondList = driver.findElement(By.xpath(locSecondList));
        WebElement elCardByName = driver.findElement(By.xpath(locCardByName));
        if (elCardByName.getLocation() == elSecondList.getLocation()) {
            LOGGER.info("Место первого: " + elCardByName.getLocation());
            LOGGER.info("Место второго: " + elSecondList.getLocation());
            LOGGER.info(cardName + " In second List");
            return true;
        } else {
            LOGGER.info(cardName + " In first List");
            LOGGER.info("Иначе Место первого: " + elCardByName.getLocation());
            LOGGER.info("Иначе Место второго: " + elSecondList.getLocation());
            return false;
        }
    }

    public void addLabelToCard(String cardName) {
        String elCardByName = "//span[text()='" + cardName + "']";
        driver.findElement(By.xpath(elCardByName)).click();
        driver.findElement(By.cssSelector(buttonAddLabelToCardInEdit)).click();

        driver.findElement(By.xpath(addLabelGreen)).click();
        driver.findElement(By.xpath(addLabelBlue)).click();

        driver.findElement(By.xpath(buttonClosePopover)).click();
        driver.findElement(By.cssSelector(locClosedEditCard)).click();
    }

    public boolean checkLabelOnCardExists(String color) {
        String locLabelColor = "//button[@data-color=\"" + color + "\"]";
        boolean result;

        try {
            result = (driver.findElement(By.xpath(locLabelColor)).isDisplayed());
            LOGGER.info("Find label: " + color);
        } catch (NoSuchElementException ex) {
            result = false;
            LOGGER.info("Label: " + color + " not found!");
        }
        return result;

    }

    public void filteredCardOnBoard(String filterText) throws InterruptedException {
        driver.findElement(By.xpath(buttonFiltered)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(inputFilter))).sendKeys(filterText);
        wait.until(ExpectedConditions.attributeContains(By.xpath(inputFilter), "defaultValue", filterText));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(defaultSecondTask)));

    }

    public boolean cardIsDisplayed(String cardName) {

        LOGGER.info("Start check card is displayed: " + cardName);
        String elCardByName = "//span[text()='" + cardName + "']";
        boolean result;

        try {
            result = (driver.findElement(By.xpath(elCardByName)).isDisplayed());
            LOGGER.info("Card isn't Displayed: " + cardName);
        } catch (NoSuchElementException ex) {
            result = true;
            LOGGER.info("Card is Displayed :" + cardName);
        }
        return result;
    }

    public boolean showClosedBoards() {
        boolean result;
        driver.findElement(By.cssSelector(buttonShowedClosedBoards)).click();
        try {
            LOGGER.info("Try find Closed Boards");
            driver.findElement(By.xpath("//a[text()='showClosedBoards']")).isDisplayed();
            result = true;

        } catch (NoSuchElementException ex) {
            LOGGER.info("Closed Boards not found");
            result = false;
        }
        return result;
    }

}
