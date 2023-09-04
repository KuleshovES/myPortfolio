package tests.UI;

import entities.Board;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import resources.ConfProperties;
import resources.RestApiMethods;
import trello.BasePage;
import trello.BoardPage;

import static resources.ConfProperties.driver;

public class TestsUIWithBoards {

    @Epic(value = "UI")
    @Feature(value = "Tests with Boards")
    @Description(value = "Test check create Board without template By UI")
    @Test
    public void createBoardSimpleUI() throws InterruptedException {
        //precondition
        driver = ConfProperties.preconditionWithLogin();
        BasePage expectedBoard = new BasePage();

        //test
        expectedBoard.createNewBoard("createBoardSimpleUI");
        Assert.assertEquals("createBoardSimpleUI", expectedBoard.getActualNameActiveBoard());

    }

    @Epic(value = "UI")
    @Feature(value = "Tests with Boards")
    @Description(value = "Test check create Board with template By UI")
    @Flaky
    @Test
    public void createBoardWithTemplateUI() throws InterruptedException {
        //precondition
        driver = ConfProperties.preconditionWithLogin();
        BasePage basePage = new BasePage();

        //test
        basePage.createNewBoardFromTemplate("createBoardWithTemplateUI");
        Assert.assertEquals("createBoardWithTemplateUI", basePage.getActualNameActiveBoard());

    }

    @Epic(value = "UI")
    @Feature(value = "Tests with Boards")
    @Description(value = "Test check update Board By UI")
    @Test
    public void updateBoardUI() throws InterruptedException {
        //precondition
        Board newBoard = RestApiMethods.createFullBoard();
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard(newBoard.getName());

        //test
        String originalNameBoard = newBoard.getName();
        boardPage.updateBoardName("new board name");
        Assert.assertNotEquals(originalNameBoard, boardPage.getBoardNameUI());

    }

    @Epic(value = "UI")
    @Feature(value = "Tests with Boards")
    @Description(value = "Test check button shows Closed Boards By UI")
    @Test
    public void showClosedBoards() throws InterruptedException {
        //precondition unusual
        Board newBoard = RestApiMethods.createFullBoard();
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();

        //test
        RestApiMethods.closedBoard(newBoard.getId());
        Assert.assertTrue(boardPage.showClosedBoards());

    }

    @AfterMethod
    public void clearAndCloseAfterTest() throws InterruptedException {
        RestApiMethods.closedAllBoards();
        Thread.sleep(1000);
        driver.close();
    }

}
