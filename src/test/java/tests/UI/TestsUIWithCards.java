package tests.UI;

import entities.Board;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.ConfProperties;
import resources.RestApiMethods;
import trello.BoardPage;

import static resources.ConfProperties.driver;

public class TestsUIWithCards {

    @Test
    public void createCardUI() throws InterruptedException {
        //-------Precondition
        Board expectedBoard = RestApiMethods.preConditionBoard("myTestCreateCardUI");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("myTestCreateCardUI");
        //-------

        boardPage.createCard("CardNameTestUI");
        Assert.assertEquals("CardNameTestUI", boardPage.getActualCardNameInActiveBoard());

        //-------delete
        RestApiMethods.deleteBoard(expectedBoard.getId());
        driver.close();
        //-------
    }

    @Test
    public void updateCardUI() throws InterruptedException {
        //-------Precondition
        Board newBoard = RestApiMethods
                .createFullBoard("updateCardUI", "Backlog", "FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("updateCardUI");
        //-------

        String actNameFirstCard = boardPage.getActualCardNameInActiveBoard();
        boardPage.updateCardName("NewCardName");
        Assert.assertNotEquals("FirstTask", actNameFirstCard);

        //-------delete
        RestApiMethods.deleteBoard(newBoard.getId());
        driver.close();
        //-------
    }


    @Test
    public void copyCardUI() throws InterruptedException {
        String newNameCopiedCard = "Copied card";
        //-------Precondition
        Board newBoard = RestApiMethods
                .createFullBoard("copyCardUI", "Backlog", "FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("copyCardUI");
        //-------

        boardPage.copyCard(newNameCopiedCard);
        Assert.assertNotEquals("FirstTask", newNameCopiedCard);

        //-------delete
        RestApiMethods.deleteBoard(newBoard.getId());
        driver.close();
        //-------

    }

    @Test
    public void deleteCardUI() throws InterruptedException {
        String cardWillBeDeleted = "TaskWillBeDeleted";
        //-------Precondition
        Board newBoard = RestApiMethods
                .createFullBoard("deleteCardUI", "Backlog", cardWillBeDeleted, "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("deleteCardUI");
        //-------

        boardPage.deleteCard(cardWillBeDeleted);
        Assert.assertTrue(boardPage.cardByNameIsNotExist(cardWillBeDeleted));
        //-------delete
        RestApiMethods.deleteBoard(newBoard.getId());
        driver.close();
        //-------

    }


    @Test
    public void dragCardUI() throws InterruptedException {
        //-------Precondition
        Board newBoard = RestApiMethods
                .createFullBoard("dragCardUI", "Backlog", "FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("dragCardUI");
        //-------

        //TODO assert doesn't work =( need check card in list2
        boardPage.moveCardByDrag("FirstTask");
        Assert.assertTrue(boardPage.cardInSecondList("FirstTask"));

        //-------delete
        RestApiMethods.deleteBoard(newBoard.getId());
        driver.close();
        //-------

    }


    @Test
    public void addLabelToCardUI() throws InterruptedException {
        //-------Precondition
        Board newBoard = RestApiMethods
                .createFullBoard("addLabelToCardUI", "Backlog", "FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("addLabelToCardUI");
        //-------

        boardPage.addLabelToCard("FirstTask");
        Assert.assertTrue(boardPage.checkLabelOnCardExists("green"));
        Assert.assertTrue(boardPage.checkLabelOnCardExists("blue"));

        //-------delete
        RestApiMethods.deleteBoard(newBoard.getId());
        driver.close();
        //-------
    }

    @Test
    public void filteredCardOnBoardUI() throws InterruptedException {
        //-------Precondition
        Board newBoard = RestApiMethods
                .createFullBoard("filteredCardOnBoardUI", "Backlog", "FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("filteredCardOnBoardUI");
        //-------

        boardPage.filteredCardOnBoard("First");
        Assert.assertFalse(boardPage.cardIsDisplayed("SecondTask"));

        //-------delete
        RestApiMethods.deleteBoard(newBoard.getId());
        driver.close();
        //-------
    }


}
