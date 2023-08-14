package tests.UI;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.ConfProperties;
import trello.LoginPage;

import static resources.ConfProperties.driver;

public class TestsUIWithCards {
    @BeforeTest
    public void setUpTest() throws InterruptedException {
        driver = ConfProperties.chrome();
        LoginPage.login();
    }

    @Test
    public void createCardUI() {
        /*Precondition: sigInUI + board exists + open board
        Steps:
        1) click crete Card
        2) input cardName
        3) confirm create */

    }

    @Test
    public void updateCardUI() {
        /*Precondition: sigInUI + board exists + open board + card Exists
        Steps:
        1) choose card and click on edit
        2) change name card
        3) click on "save"
        4) check new card name */
    }

    @Test
    public void copyCardUI() {
        /*Precondition: sigInUI + board exists + open board + card Exists
        Steps:
        1) choose card and click on edit
        2) choose and click "copy"
        3) check new card exists and equals old card */

    }

    @Test
    public void deleteCardUI() {
        /*Precondition: sigInUI + board exists + open board + card Exists
        Steps:
        1) choose card and click on edit
        2) click on "delete"
        3) check card not exists */

    }

    @Test
    public void dragCardUI() {
        /*Precondition: sigInUI + board exists + open board + card Exists
        Steps:
        1) choose card and check column = first
        2) drag card from column first to second
        3) check cards column = second */

    }

    @Test
    public void addLabelToCardUI() {
        /*Precondition: sigInUI + board exists + open board + card Exists
        Steps:
        1) choose card and click on edit
        2) click on add label
        3) choose and add two labels
        4) click on save
        5) check label exists */

    }

    @Test
    public void filteredCardOnBoardUI() {
        /*Precondition: sigInUI + board exists + open board + card exist
        Steps:
        1) click on "filters"
        2) input text (cardName)
        3) check result = 1 card display */
    }

}
