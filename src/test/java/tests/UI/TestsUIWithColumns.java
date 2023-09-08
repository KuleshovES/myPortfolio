package tests.UI;

import Robots.BoardRobot;
import entities.Board;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.ConfProperties;
import resources.RestApiMethods;
import trello.BoardPage;

import static resources.ConfProperties.driver;

public class TestsUIWithColumns {
    private BoardRobot boardRobot = new BoardRobot();
    private final String newColumnName = "myTestColumn";

    @BeforeMethod
    public void openDriver() throws InterruptedException {
        driver = ConfProperties.preconditionWithLogin();
    }

    @Epic(value = "UI")
    @Feature(value = "Tests with Columns")
    @Description(value = "Test check create Column By UI")
    @Test
    public void createColumnUI() {

        Board newBoard = boardRobot.createEmptyBoardByRest("MyTestBoard");
        boardRobot.createColumnByUI(newBoard, newColumnName);
        Assert.assertTrue(boardRobot.checkingExistenceColumnByUI(newColumnName));

    }

    @AfterMethod
    public void clearAndCloseAfterTest() throws InterruptedException {
        RestApiMethods.closedAllBoards();
        Thread.sleep(1000);
        driver.close();
    }

}
