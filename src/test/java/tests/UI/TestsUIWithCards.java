package tests.UI;

import entities.Board;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;

import org.testng.annotations.*;
import resources.ConfProperties;
import resources.RestApiMethods;
import trello.BoardPage;

import static resources.ConfProperties.driver;


public class TestsUIWithCards {

    @Epic(value = "UI")
    @Feature(value = "Tests with Cards")
    @Description(value = "Test check create card By UI")
    @Test
    public void createCardUI() throws InterruptedException {
        //precondition
        Board expectedBoard = RestApiMethods.preConditionBoard("myTestCreateCardUI");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard(expectedBoard.getName());

        //test
        boardPage.createCard("CardNameTestUI");
        Assert.assertEquals("CardNameTestUI", boardPage.getActualCardNameInActiveBoard());

    }

    @Epic(value = "UI")
    @Feature(value = "Tests with Cards")
    @Description(value = "Test check update card By UI")
    @Test
    public void updateCardUI() throws InterruptedException {
        //precondition
        Board newBoard = RestApiMethods.createFullBoard();
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard(newBoard.getName());

        //test
        String oldNameFirstCard = boardPage.getActualCardNameInActiveBoard();
        boardPage.updateCardName("NewCardName");
        String actualNameFirstCard = boardPage.getActualCardNameInActiveBoard();
        Assert.assertNotEquals(actualNameFirstCard, oldNameFirstCard);

    }

    @Epic(value = "UI")
    @Feature(value = "Tests with Cards")
    @Description(value = "Test check copy card By UI")
    @Test
    public void copyCardUI() throws InterruptedException {
        String newNameCopiedCard = "Copied card";
        //precondition
        Board newBoard = RestApiMethods.createFullBoard("FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard(newBoard.getName());

        //test
        boardPage.copyCard(newNameCopiedCard);
        Assert.assertNotEquals("FirstTask", newNameCopiedCard);

    }

    @Epic(value = "UI")
    @Feature(value = "Tests with Cards")
    @Description(value = "Test check delete card By UI")
    @Test
    public void deleteCardUI() throws InterruptedException {
        //precondition
        Board newBoard = RestApiMethods.createFullBoard("FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard(newBoard.getName());
        String cardNameWillBeDeleted = boardPage.getActualCardNameInActiveBoard();

        //test
        boardPage.deleteCard(cardNameWillBeDeleted);
        Assert.assertTrue(boardPage.cardByNameIsNotExist(cardNameWillBeDeleted));

    }

    @Epic(value = "UI")
    @Feature(value = "Tests with Cards")
    @Description(value = "Test check drag card By UI")
    @Test
    public void dragCardUI() throws InterruptedException {
        //precondition
        Board newBoard = RestApiMethods.createFullBoard("FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard(newBoard.getName());

        //test
        //TODO assert doesn't work =( need check card in list2
        boardPage.moveCardByDrag("FirstTask");
        Assert.assertTrue(boardPage.cardInSecondList("FirstTask"));

    }

    @Epic(value = "UI")
    @Feature(value = "Tests with Cards")
    @Description(value = "Test check added label to card By UI")
    @Test
    public void addLabelToCardUI() throws InterruptedException {
        //precondition
        Board newBoard = RestApiMethods.createFullBoard("FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard(newBoard.getName());

        //test
        boardPage.addLabelToCard("FirstTask");
        Assert.assertTrue(boardPage.checkLabelOnCardExists("green"));
        Assert.assertTrue(boardPage.checkLabelOnCardExists("blue"));

    }

    @Epic(value = "UI")
    @Feature(value = "Tests with Cards")
    @Description(value = "Test check filtered card on board By UI")
    @Test
    public void filteredCardOnBoardUI() throws InterruptedException {
        //precondition
        Board newBoard = RestApiMethods.createFullBoard("FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard(newBoard.getName());

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
