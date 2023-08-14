package tests.UI;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.ConfProperties;
import trello.LoginPage;

import static resources.ConfProperties.driver;

public class TestsUIWithColumns {
    @BeforeTest
    public void setUpTest() throws InterruptedException {
        driver = ConfProperties.chrome();
        LoginPage.login();
    }

    @Test
    public void createColumnUI() {
        /*Precondition: sigInUI + board exists + open board
        Steps:
        1) click crete columns
        2) input columnName
        3) confirm create */
    }
}
