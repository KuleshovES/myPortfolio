package tests.Api;

import entities.Board;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.RestApiMethods;

import static resources.RestApiMethods.*;

public class TestBoards {
    @Test
    public void createBoard() throws InterruptedException {
        Board board = new Board("myTestDesk13");
        String resBoardId = RestApiMethods.createBoard(board,"myTestDesk1");
        Assert.assertEquals("myTestDesk1", getBoard(resBoardId).getName());
        deleteBoard(resBoardId);
    }

    @Test
    public void createColumns() throws InterruptedException {
        Board board = new Board("myTestDesk13");
        String resBoardId = RestApiMethods.createBoard(board,"myTestDesk12");

        String resultFirstColumn = RestApiMethods.createColumns("MyTestListFirst", resBoardId);
        String resultSecondColumn = RestApiMethods.createColumns("MyTestListSecond", resBoardId);

        Assert.assertEquals("MyTestListFirst", getFilteredNameColumn(resBoardId, resultFirstColumn));
        Assert.assertEquals("MyTestListSecond", getFilteredNameColumn(resBoardId, resultSecondColumn));
        //deleteBoard(resBoardId);
    }


}
