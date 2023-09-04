package tests.Api;

import entities.Board;
import entities.Column;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import resources.RestApiMethods;

import static resources.RestApiMethods.*;

public class TestBoards {

    @Epic(value = "Api")
    @Feature(value = "Tests with Boards")
    @Description(value = "Test check create Board")
    @Test
    public void createBoard() {
        Board expectedBoard = RestApiMethods.preConditionBoard("myTestDesk13");
        Board actualBoard = getBoard(expectedBoard.getId());
        Assert.assertEquals(expectedBoard.getName(), actualBoard.getName());
    }

    @Epic(value = "Api")
    @Feature(value = "Tests with Boards")
    @Description(value = "Test check create Columns")
    @Test
    public void createColumns() {
        Board board = RestApiMethods.preConditionBoard("myTestDesk12");
        Column expectedFirstColumn = RestApiMethods.preConditionColumn("MyTestListFirst", board);

        RestApiMethods.createColumns(expectedFirstColumn, board);
        String actualFirstColumn = getFilteredNameColumn(board, expectedFirstColumn);

        Assert.assertEquals(expectedFirstColumn.getName(), actualFirstColumn);

    }


    @AfterMethod
    public void clearData () {
        RestApiMethods.closedAllBoards();
    }


}
