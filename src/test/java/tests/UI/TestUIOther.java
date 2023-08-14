package tests.UI;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.ConfProperties;
import trello.BasePage;
import trello.BoardPage;
import trello.LoginPage;

import static resources.ConfProperties.driver;

public class TestUIOther {

    @BeforeTest
    public void setUpTest () throws InterruptedException {
        driver = ConfProperties.chrome();
        LoginPage.login();
    }

    //will be deleted
    @Test
    public void fillBoard () throws InterruptedException {
        BasePage basePage = new BasePage();
        basePage.createNewBoard();

        BoardPage board = new BoardPage();
        board.createColumn("Backlog");
        board.createColumn("To-do");
        board.createColumn("Done");
        board.createCard("testCard");
        board.createCard("testTask");
        board.createCard("TaskInWork");
        board.createCard("TaskDone");


    }

}
