package tests.Api;

import entities.Board;
import entities.User;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import resources.RestApiMethods;

import static resources.RestApiMethods.*;

public class OtherTests {

    @Test
    public void getInfoAboutMe() throws InterruptedException {
        //This test only for check valid token
        User user = new User();
        RestApiMethods.getInfoUser(user);
    }

    @Test
    public void checkGetCard() {
        //test for fail
        getCard("64da38d28fe02c8d11c6c0e5");
    }

    @Ignore
    @Test
    public void checkGetColumn() {
        //test for fail
        getColumn("64da6e3439d127ed04293f70");
    }

    @Ignore
    @Test
    public void checkCreateBoard() {
        Board board = new Board("boardForDebug");
        createBoard(board);
    }
}

