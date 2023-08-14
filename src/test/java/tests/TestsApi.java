package tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import resources.RestApiMethods;

import static resources.RestApiMethods.*;

public class TestsApi {

    @Test
    public void getInfoAboutMe() throws InterruptedException {
        //This test only for check valid token
        RestApiMethods.getInfoUser();
    }

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

    @Test
    public void checkGet() {
        //test for fail
        getCard("64da38d28fe02c8d11c6c0e5");
    }

    @Test
    public void createCards() throws InterruptedException {
        String resBoardId = RestApiMethods.createBoard("myTestDesk13");
        String resColumnId = RestApiMethods.createColumns("MyTestListFirst", resBoardId);
        String resultFirstTask = RestApiMethods.createCard(resColumnId, "MyFirstTask");
        String resultSecondTask = RestApiMethods.createCard(resColumnId, "MySecondTask");
        String resultThirdTask = RestApiMethods.createCard(resColumnId, "MyThirdTask");
        Assert.assertEquals("MyFirstTask", getCard(resultFirstTask).getName());
        Assert.assertEquals("MySecondTask", getCard(resultSecondTask).getName());
        Assert.assertEquals("MyThirdTask", getCard(resultThirdTask).getName());
        deleteBoard(resBoardId);
    }

    @Test
    public void updateCard() throws InterruptedException {
        String resBoardId = RestApiMethods.createBoard("myTestDesk15");
        String resColumnId = RestApiMethods.getColumn(resBoardId).get(0).id;
        String resultFirstTask = RestApiMethods.createCard(resColumnId, "MyFirstTaskOriginalName");
        String resultSecondTask = RestApiMethods.createCard(resColumnId, "MySecondTaskOriginalName");
        String resultThirdTask = RestApiMethods.createCard(resColumnId, "MyThirdTaskOriginalName");

        RestApiMethods.updateCard("newCardName_first", resultFirstTask);
        RestApiMethods.updateCard("newCardName_Second", resultSecondTask);

        Assert.assertEquals("newCardName_first", getCard(resultFirstTask).getName());
        Assert.assertEquals("newCardName_Second", getCard(resultSecondTask).getName());
        Assert.assertEquals("MyThirdTaskOriginalName", getCard(resultThirdTask).getName());
        deleteBoard(resBoardId);
    }


    @AfterTest
    public void clearSpaceInTrello() {
        System.out.println("don't worry, I threw out the trash");
    }
}

