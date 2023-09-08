package tests.UI;

import Robots.BaseRobot;
import entities.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.ConfProperties;
import resources.RestApiMethods;

import static resources.ConfProperties.driver;

public class TestUIAuthorization {
    private BaseRobot baseRobot = new BaseRobot();
    private User user = new User();

    @BeforeMethod
    public void openDriver() throws InterruptedException {
        driver = ConfProperties.preconditionWithLogin();
    }

    @Epic(value = "UI")
    @Feature(value = "Tests with Auth")
    @Description(value = "Test check login By UI")
    @Test
    public void sigInUI() {
        Assert.assertEquals(baseRobot.currentUserNameByUI(), RestApiMethods.getInfoUser(user).getFullName());

    }

    @Epic(value = "UI")
    @Feature(value = "Tests with Auth")
    @Description(value = "Test check logout By UI")
    @Flaky
    @Test
    public void logOutUI() throws InterruptedException {
        baseRobot.logOutByUI();
        Thread.sleep(2000);
        Assert.assertTrue(baseRobot.checkingOpenedMainPageByUI());

    }

    @AfterMethod
    public void driverClose() {
        driver.close();
    }
}
