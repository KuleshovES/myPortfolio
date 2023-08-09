package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.ConfProperties;
import trello.BasePage;
import trello.BoardPage;
import trello.LoginPage;

import static resources.ConfProperties.driver;

public class TestsUi {

    @BeforeTest
    public void setUpTest () throws InterruptedException {
        driver = ConfProperties.chrome();
        LoginPage.login();
    }
    @Test
    public void checkCreateNewBoard () throws InterruptedException {
        BasePage basePage = new BasePage();
        basePage.createNewBoard();
    }
    @Test
    public void checkCreateFromTemplate () throws InterruptedException {
        BasePage basePage = new BasePage();
        basePage.createNewBoardFromTemplate();
    }


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

        //проверки?

    }

    @Test
    public void editCard() {

    }
    //LoginPage.login();
    //эелемент профиль(аккаунт)
    //WebElement currentAccount = driver.findElement(By.cssSelector(".DweEFaF5owOe02.V_PnoJ2AynVwLp"));
        //currentAccount.click();

    //сравнение текущ пользака с введеным
   //WebElement currentUserEmail = driver.findElement(By.cssSelector("div.AS8ZlkEoqFiwD_"));
        //Assert.assertEquals(userName, currentUserEmail.getText());


}
