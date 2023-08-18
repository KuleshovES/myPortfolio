package tests.UI;

import entities.Board;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import resources.ConfProperties;
import resources.RestApiMethods;
import trello.BoardPage;

import static resources.ConfProperties.driver;

public class TestsUIWithCards {

    @Test
    public void createCardUI() throws InterruptedException {
        Board expectedBoard = RestApiMethods.preConditionBoard("myTestCreateCardUI");
        driver = ConfProperties.preconditionForTest();

        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("myTestCreateCardUI");
        boardPage.createCard("CardNameTestUI");

        Assert.assertEquals("CardNameTestUI", boardPage.getActualCardNameInActiveBoard());

        RestApiMethods.deleteBoard(expectedBoard.getId());
        driver.close();
    }

    @Test
    public void updateCardUI() throws InterruptedException {

        Board newBoard = RestApiMethods
                .createFullBoard("updateCardUI", "Backlog", "FirstTask", "SecondTask");
        driver = ConfProperties.preconditionForTest();

        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("updateCardUI");

        String actNameFirstCard = boardPage.getActualCardNameInActiveBoard();
        boardPage.updateCardName("NewCardName");

        Assert.assertNotEquals("FirstTask", actNameFirstCard);

        RestApiMethods.deleteBoard(newBoard.getId());
        driver.close();

    }


    @Test
    public void copyCardUI() throws InterruptedException {
        String newNameCopiedCard = "Copied card";
        Board newBoard = RestApiMethods
                .createFullBoard("copyCardUI", "Backlog", "FirstTask", "SecondTask");
        driver = ConfProperties.preconditionForTest();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("copyCardUI");
        boardPage.copyCard(newNameCopiedCard);

        Assert.assertNotEquals("FirstTask", newNameCopiedCard);

        RestApiMethods.deleteBoard(newBoard.getId());
        driver.close();


        /*Precondition: sigInUI + board exists + open board + card Exists
        Steps:
        1) choose card and click on edit
        2) choose and click "copy"
        3) check new card exists and equals old card */

    }

    @Ignore
    @Test
    public void deleteCardUI() {
        RestApiMethods.preConditionBoard("myTestDeleteCardUI");
        /*Precondition: sigInUI + board exists + open board + card Exists
        Steps:
        1) choose card and click on edit
        2) click on "delete"
        3) check card not exists */

    }

    @Ignore
    @Test
    public void dragCardUI() {
        RestApiMethods.preConditionBoard("myTestDragCardUI");
        /*Precondition: sigInUI + board exists + open board + card Exists
        Steps:
        1) choose card and check column = first
        2) drag card from column first to second
        3) check cards column = second */

    }

    @Ignore
    @Test
    public void addLabelToCardUI() {
        /*Precondition: sigInUI + board exists + open board + card Exists
        Steps:
        1) choose card and click on edit
        2) click on add label
        3) choose and add two labels
        4) click on save
        5) check label exists */

    }

    @Ignore
    @Test
    public void filteredCardOnBoardUI() {
        /*Precondition: sigInUI + board exists + open board + card exist
        Steps:
        1) click on "filters"
        2) input text (cardName)
        3) check result = 1 card display */
    }



}
