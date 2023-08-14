package tests.Api;

import org.testng.Assert;
import org.testng.annotations.Test;
import resources.RestApiMethods;

import static resources.RestApiMethods.deleteBoard;
import static resources.RestApiMethods.getCard;

public class TestCards {

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
}
