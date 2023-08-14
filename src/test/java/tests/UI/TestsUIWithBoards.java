package tests.UI;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.ConfProperties;
import trello.BasePage;
import trello.LoginPage;

import static resources.ConfProperties.driver;

public class TestsUIWithBoards {
    @BeforeTest
    public void setUpTest() throws InterruptedException {
        driver = ConfProperties.chrome();
        LoginPage.login();
    }

    @Test
    public void createBoardSimpleUI() throws InterruptedException {
        /*Precondition: TestUIAuthorization
        Steps:
        1) click crete Board
        2) input boardName
        3) choose space
        4) confirm create */

        BasePage basePage = new BasePage();
        basePage.createNewBoard();

    }

    @Test
    public void createBoardWithTemplateUI() throws InterruptedException {
        /*Precondition: TestUIAuthorization
        Steps:
        1) click crete Board
        2) input boardName
        3) choose template
        4) confirm create */

        BasePage basePage = new BasePage();
        basePage.createNewBoardFromTemplate();
    }

    @Test
    public void updateBoardUI() {

    }

    @Test
    public void showClosedBoards() {
        /*Precondition: TestUIAuthorization + open Main tab + exist closed boards
        Steps:
        1) click show closed Boards
        2) List exists
        3) closed window */

    }

}
