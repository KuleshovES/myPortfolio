package tests.UI;

import entities.Board;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import resources.ConfProperties;
import resources.RestApiMethods;
import trello.BasePage;
import trello.BoardPage;

import static resources.ConfProperties.driver;

public class TestsUIWithBoards {

    @Test
    public void createBoardSimpleUI() throws InterruptedException {
        //precondition
        driver = ConfProperties.preconditionWithLogin();
        BasePage expectedBoard = new BasePage();

        //test
        expectedBoard.createNewBoard("createBoardSimpleUI");
        Assert.assertEquals("createBoardSimpleUI", expectedBoard.getActualNameActiveBoard());

    }

    @Test
    public void createBoardWithTemplateUI() throws InterruptedException {
        //precondition
        driver = ConfProperties.preconditionWithLogin();
        BasePage basePage = new BasePage();

        //test
        basePage.createNewBoardFromTemplate("createBoardWithTemplateUI");
        Assert.assertEquals("createBoardWithTemplateUI", basePage.getActualNameActiveBoard());

    }

    @Test
    public void updateBoardUI() throws InterruptedException {
        //precondition
        Board newBoard = RestApiMethods
                .createFullBoard("updateCardUI", "Backlog", "FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("updateCardUI");

        //test

    }

    @Test
    public void showClosedBoards() throws InterruptedException {
        //precondition unusual
        Board newBoard = RestApiMethods
                .createFullBoard("showClosedBoards", "Backlog", "FirstTask", "SecondTask");
        driver = ConfProperties.preconditionWithLogin();
        RestApiMethods.closedBoard(newBoard.getId());
        BoardPage boardPage = new BoardPage();

        //test
        Assert.assertTrue(boardPage.showClosedBoards());

    }

    @AfterMethod
    public void clearAndCloseAfterTest() throws InterruptedException {
        RestApiMethods.closedAllBoards();
        Thread.sleep(1000);
        driver.close();
    }

}
