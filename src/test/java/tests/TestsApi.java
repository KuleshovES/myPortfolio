package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import resources.RestApiMethods;

public class TestsApi {

    @Test
    public void getInfoAboutMe() throws InterruptedException {
        //This test only for check valid token
        RestApiMethods.getInfoUser();
    }

    @Test
    public void createBoard() throws InterruptedException {
        RestApiMethods.createBoard("myTestDesk1");
        //TODO add check create board (getBoard)
    }

    @Test
    public void createColumns() throws InterruptedException {
        String resBoardId = RestApiMethods.createBoard("myTestDesk12");
        RestApiMethods.createColumns("MyTestListFirst", resBoardId);
        RestApiMethods.createColumns("MyTestListSecond", resBoardId);
        //TODO add check create column (getColumn)
    }

    @Test
    public void createCards() throws InterruptedException {
        String resBoardId = RestApiMethods.createBoard("myTestDesk13");
        String resColumnId = RestApiMethods.createColumns("MyTestListFirst", resBoardId);
        RestApiMethods.createCard(resColumnId, "MyFirstTask");
        RestApiMethods.createCard(resColumnId, "MySecondTask");
        RestApiMethods.createCard(resColumnId, "MyThirdTask");
        //TODO add check create cards (getCards)
    }

    @Test
    public void updateCard() throws InterruptedException {
        String resBoardId = RestApiMethods.createBoard("myTestDesk15");
        String resColumnId = RestApiMethods.getColumn(resBoardId).get(0).id;
        String myFirstTask = RestApiMethods.createCard(resColumnId, "MyFirstTaskOriginalName");
        String mySecondTask = RestApiMethods.createCard(resColumnId, "MySecondTaskOriginalName");
        RestApiMethods.createCard(resColumnId, "MyThirdTaskOriginalName");
        RestApiMethods.updateCard("newCardName_first", myFirstTask);
        RestApiMethods.updateCard("newCardName_Second", mySecondTask);
        //TODO
        // add check create change Name (getCards)
    }


    @AfterTest
    public void clearSpaceInTrello() {
        //TODO
        //clear all data after test
        System.out.println("don't worry, I threw out the trash");
    }
}

