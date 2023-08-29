package tests.Api;

import entities.Board;
import entities.Card;
import entities.Column;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import resources.RestApiMethods;

import static resources.RestApiMethods.*;

public class TestCards {

    @Test
    public void createCards() {
        //precondition
        Board board = RestApiMethods.preConditionBoard("myTestCreateCards");
        Column column = RestApiMethods.preConditionColumn("MyTestListFirst", board);

        //test
        Card expectedCard = new Card("MyFirstTask");
        createCard(column, expectedCard);
        Card actualCard = getCard(expectedCard.getId());
        Assert.assertEquals(expectedCard.getName(), actualCard.getName());

    }


    @Test
    public void updateCard() {
        //precondition
        Board board = RestApiMethods.preConditionBoard("myTestUpdateCard");
        Column column = RestApiMethods.getColumn(board.getId()).get(0);
        Card firstCard = new Card("MyFirstTaskOriginalName");
        Card secondCard = new Card("MySecondTaskOriginalName");
        RestApiMethods.createCard(column, firstCard);
        RestApiMethods.createCard(column, secondCard);

        //test
        RestApiMethods.updateCard("name", "newCardName_first", firstCard.getId());
        Card actualFirstCard = getCard(firstCard.getId());
        Card actualSecondCard = getCard(secondCard.getId());

        Assert.assertNotEquals(firstCard.getName(), actualFirstCard.getName());
        Assert.assertEquals(secondCard.getName(), actualSecondCard.getName());

    }

    @AfterMethod
    public void clearData() {
        RestApiMethods.closedAllBoards();
    }
}
