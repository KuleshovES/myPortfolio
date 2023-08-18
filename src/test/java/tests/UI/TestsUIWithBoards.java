package tests.UI;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.ConfProperties;
import resources.RestApiMethods;
import trello.BasePage;
import trello.LoginPage;

import java.sql.SQLOutput;

import static resources.ConfProperties.driver;

public class TestsUIWithBoards {
   /* @BeforeTest
    public void setUpTest() throws InterruptedException {
        driver = ConfProperties.chrome();
        LoginPage.login();
    }*/

    @Test
    public void createBoardSimpleUI() throws InterruptedException {
        driver = ConfProperties.preconditionForTest();
        System.out.println("id Chrome: " + driver.getWindowHandle());

        BasePage expectedBoard = new BasePage();
        expectedBoard.createNewBoard("createBoardSimpleUI");
        Assert.assertEquals("createBoardSimpleUI", expectedBoard.getActualNameActiveBoard());
        expectedBoard.closedBoard();
        expectedBoard.deleteBoard();
        driver.close();
    }

    @Test
    public void createBoardWithTemplateUI() throws InterruptedException {
        driver = ConfProperties.preconditionForTest();
        System.out.println("id Chrome: " + driver.getWindowHandle());

        BasePage basePage = new BasePage();
        basePage.createNewBoardFromTemplate("createBoardWithTemplateUI");

        Assert.assertEquals("createBoardWithTemplateUI", basePage.getActualNameActiveBoard());

        basePage.closedBoard();
        basePage.deleteBoard();
        driver.close();
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
