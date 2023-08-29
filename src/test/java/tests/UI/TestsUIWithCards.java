package tests.UI;

import entities.Board;

import org.testng.Assert;

import org.testng.annotations.*;
import resources.ConfProperties;
import resources.RestApiMethods;
import trello.BoardPage;

import static resources.ConfProperties.driver;


public class TestsUIWithCards {

    @Test
    public void createCardUI() throws InterruptedException {
        //precondition
        Board expectedBoard = RestApiMethods.preConditionBoard("myTestCreateCardUI");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("myTestCreateCardUI");

        //test
        boardPage.createCard("CardNameTestUI");
        Assert.assertEquals("CardNameTestUI", boardPage.getActualCardNameInActiveBoard());

    }

    @Test
    public void updateCardUI() throws InterruptedException {
        //precondition
        Board newBoard = RestApiMethods
                .createFullBoard("updateCardUI", "Backlog", "FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("updateCardUI");

        //test
        String actNameFirstCard = boardPage.getActualCardNameInActiveBoard();
        boardPage.updateCardName("NewCardName");
        Assert.assertNotEquals("FirstTask", actNameFirstCard);

    }

    @Test
    public void copyCardUI() throws InterruptedException {
        String newNameCopiedCard = "Copied card";
        //precondition
        Board newBoard = RestApiMethods
                .createFullBoard("copyCardUI", "Backlog", "FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("copyCardUI");

        //test
        boardPage.copyCard(newNameCopiedCard);
        Assert.assertNotEquals("FirstTask", newNameCopiedCard);

    }

    @Test
    public void deleteCardUI() throws InterruptedException {
        String cardWillBeDeleted = "TaskWillBeDeleted";

        //precondition
        Board newBoard = RestApiMethods
                .createFullBoard("deleteCardUI", "Backlog", cardWillBeDeleted, "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("deleteCardUI");

        //test
        boardPage.deleteCard(cardWillBeDeleted);
        Assert.assertTrue(boardPage.cardByNameIsNotExist(cardWillBeDeleted));

    }


    @Test
    public void dragCardUI() throws InterruptedException {
        //precondition
        Board newBoard = RestApiMethods
                .createFullBoard("dragCardUI", "Backlog", "FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("dragCardUI");

        //test
        //TODO assert doesn't work =( need check card in list2
        boardPage.moveCardByDrag("FirstTask");
        Assert.assertTrue(boardPage.cardInSecondList("FirstTask"));

    }


    @Test
    public void addLabelToCardUI() throws InterruptedException {
        //precondition
        Board newBoard = RestApiMethods
                .createFullBoard("addLabelToCardUI", "Backlog", "FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("addLabelToCardUI");

        //test
        boardPage.addLabelToCard("FirstTask");
        Assert.assertTrue(boardPage.checkLabelOnCardExists("green"));
        Assert.assertTrue(boardPage.checkLabelOnCardExists("blue"));

    }

    @Test
    public void filteredCardOnBoardUI() throws InterruptedException {
        //precondition
        Board newBoard = RestApiMethods
                .createFullBoard("filteredCardOnBoardUI", "Backlog", "FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("filteredCardOnBoardUI");

        //test
        boardPage.filteredCardOnBoard("First");
        Assert.assertFalse(boardPage.cardIsDisplayed("SecondTask"));

    }

    @AfterMethod
    public void clearAndCloseAfterTest() throws InterruptedException {
        RestApiMethods.closedAllBoards();
        Thread.sleep(1000);
        driver.close();
    }

}
