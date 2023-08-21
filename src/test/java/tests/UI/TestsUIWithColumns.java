package tests.UI;

import entities.Board;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.ConfProperties;
import resources.RestApiMethods;
import trello.BoardPage;

import static resources.ConfProperties.driver;

public class TestsUIWithColumns {

    @Test
    public void createColumnUI() throws InterruptedException {
        //-------Precondition
        Board newBoard = new Board("myTestBoard");
        RestApiMethods.createBoard(newBoard);
        driver = ConfProperties.preconditionWithLogin();
        BoardPage boardPage = new BoardPage();
        boardPage.openBoard("myTestBoard");
        //-------

        boardPage.createColumn("myTestColumn");
        Assert.assertTrue(boardPage.columnByNameIsDisplayed("myTestColumn"));

        //-------delete
        RestApiMethods.deleteBoard(newBoard.getId());
        driver.close();
        //-------
    }
}
