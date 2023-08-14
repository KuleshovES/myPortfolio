package tests.Api;

import org.testng.Assert;
import org.testng.annotations.Test;
import resources.RestApiMethods;

import static resources.RestApiMethods.*;

public class TestBoards {
    @Test
    public void createBoard() throws InterruptedException {
        String resBoardId = RestApiMethods.createBoard("myTestDesk1");
        Assert.assertEquals("myTestDesk1", getBoard(resBoardId).getName());
        deleteBoard(resBoardId);
    }

    @Test
    public void createColumns() throws InterruptedException {
        String resBoardId = RestApiMethods.createBoard("myTestDesk12");

        String resultFirstColumn = RestApiMethods.createColumns("MyTestListFirst", resBoardId);
        String resultSecondColumn = RestApiMethods.createColumns("MyTestListSecond", resBoardId);

        Assert.assertEquals("MyTestListFirst", getFilteredNameColumn(resBoardId, resultFirstColumn));
        Assert.assertEquals("MyTestListSecond", getFilteredNameColumn(resBoardId, resultSecondColumn));
        deleteBoard(resBoardId);
    }


}
