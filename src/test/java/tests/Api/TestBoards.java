package tests.Api;

import entities.Board;
import entities.Column;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.RestApiMethods;

import static resources.RestApiMethods.*;

public class TestBoards {
    @Test
    public void createBoard() throws InterruptedException {
        Board expectedBoard = RestApiMethods.preConditionBoard("myTestDesk13");
        Board actualBoard = getBoard(expectedBoard.getId());
        Assert.assertEquals(expectedBoard.getName(), actualBoard.getName());
        deleteBoard(expectedBoard.getId());
    }

    @Test
    public void createColumns() throws InterruptedException {
        Board board = RestApiMethods.preConditionBoard("myTestDesk12");
        Column expectedFirstColumn = RestApiMethods.preConditionColumn("MyTestListFirst", board);
        Column expectedSecondColumn = RestApiMethods.preConditionColumn("MyTestListSecond", board);

        RestApiMethods.createColumns(expectedFirstColumn, board);
        String actualFirstColumn = getFilteredNameColumn(board, expectedFirstColumn);

        RestApiMethods.createColumns(expectedSecondColumn, board);
        String actualSecondColumn = getFilteredNameColumn(board, expectedSecondColumn);

        Assert.assertEquals(expectedFirstColumn.getName(), actualFirstColumn);
        Assert.assertEquals(expectedSecondColumn.getName(), actualSecondColumn);
        deleteBoard(board.getId());

    }


}
