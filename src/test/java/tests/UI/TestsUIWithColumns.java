package tests.UI;

import entities.Board;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import resources.ConfProperties;
import resources.RestApiMethods;
import trello.BoardPage;

import static resources.ConfProperties.driver;

public class TestsUIWithColumns {

    @Epic(value = "UI")
    @Feature(value = "Tests with Columns")
    @Description(value = "Test check create Column By UI")
    @Test
    public void createColumnUI() throws InterruptedException {
        //-------Precondition
        Board newBoard = new Board("myTestBoard");
        RestApiMethods.createBoard(newBoard);
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard(newBoard.getName());
        //-------

        boardPage.createColumn("myTestColumn");
        Assert.assertTrue(boardPage.columnByNameIsDisplayed("myTestColumn"));

    }

    @AfterMethod
    public void clearAndCloseAfterTest() throws InterruptedException {
        RestApiMethods.closedAllBoards();
        Thread.sleep(1000);
        driver.close();
    }

}
