package tests.Api;

import io.qameta.allure.*;
import entities.Board;
import entities.Card;
import entities.Column;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import resources.RestApiMethods;

import java.util.List;

import static resources.RestApiMethods.*;

public class TestCards {

    @Epic(value = "Api")
    @Feature(value = "Tests with Cards")
    @Test
    @Description(value = "Test check create card")
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

    @Epic(value = "Api")
    @Feature(value = "Tests with Cards")
    @Test
    @Description(value = "Test check update Card")
    public void updateCard() {
        //precondition
        Board board = RestApiMethods.createFullBoard("sd","sd","MyFirstTaskOriginalName", "MySecondTaskOriginalName");
        List<Card> cards = RestApiMethods.getCards(board.getId());

        //test
        Card firstCard = getCard(cards.get(0).getId());
        Card secondCard = getCard(cards.get(1).getId());

        RestApiMethods.updateCard("name", "newCardName_first", cards.get(0).getId());

        Card actualFirstCard = getCard(firstCard.getId());
        Card actualSecondCard = getCard(secondCard.getId());

        //check
        Assert.assertNotEquals(firstCard.getName(), actualFirstCard.getName());
        Assert.assertEquals(secondCard.getName(), actualSecondCard.getName());

    }

    @AfterMethod
    public void clearData() {
        RestApiMethods.closedAllBoards();
    }
}
