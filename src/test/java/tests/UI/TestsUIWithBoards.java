package tests.UI;

import entities.Board;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.ConfProperties;
import resources.RestApiMethods;
import trello.BasePage;
import trello.BoardPage;

import static resources.ConfProperties.driver;

public class TestsUIWithBoards {

    @Test
    public void createBoardSimpleUI() throws InterruptedException {
        driver = ConfProperties.preconditionWithLogin();
        BasePage expectedBoard = new BasePage();
        expectedBoard.createNewBoard("createBoardSimpleUI");
        Assert.assertEquals("createBoardSimpleUI", expectedBoard.getActualNameActiveBoard());
        expectedBoard.closedBoard();
        expectedBoard.deleteBoard();
        driver.close();
    }

    @Test
    public void createBoardWithTemplateUI() throws InterruptedException {
        driver = ConfProperties.preconditionWithLogin();

        BasePage basePage = new BasePage();
        basePage.createNewBoardFromTemplate("createBoardWithTemplateUI");

        Assert.assertEquals("createBoardWithTemplateUI", basePage.getActualNameActiveBoard());

        basePage.closedBoard();
        basePage.deleteBoard();
        driver.close();
    }

    @Test
    public void updateBoardUI() throws InterruptedException {
        //-------Precondition
        Board newBoard = RestApiMethods
                .createFullBoard("updateCardUI", "Backlog", "FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("updateCardUI");
        //-------
    }

    @Test
    public void showClosedBoards() throws InterruptedException {
        //-------Precondition unusual
        Board newBoard = RestApiMethods
                .createFullBoard("showClosedBoards", "Backlog", "FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        RestApiMethods.closedBoard(newBoard.getId());
        BoardPage boardPage = new BoardPage();
        //---
        Assert.assertTrue(boardPage.showClosedBoards());
        //-------
        driver.close();

    }

}
