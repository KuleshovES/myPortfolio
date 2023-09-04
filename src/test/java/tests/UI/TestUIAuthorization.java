package tests.UI;

import entities.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import resources.ConfProperties;
import resources.RestApiMethods;
import trello.BasePage;

import static resources.ConfProperties.driver;

public class TestUIAuthorization {

    @Epic(value = "UI")
    @Feature(value = "Tests with Auth")
    @Description(value = "Test check login By UI")
    @Test
    public void sigInUI() throws InterruptedException {
        driver = ConfProperties.preconditionWithLogin();
        User user = new User();
        BasePage basePage = new BasePage();

        Assert.assertEquals(basePage.getCurrentUserName(), RestApiMethods.getInfoUser(user).getFullName());

    }

    @Epic(value = "UI")
    @Feature(value = "Tests with Auth")
    @Description(value = "Test check logout By UI")
    @Flaky
    @Test
    public void logOutUI() throws InterruptedException {
        driver = ConfProperties.preconditionWithLogin();
        BasePage basePage = new BasePage();

        basePage.logOut();
        Thread.sleep(2000);
        Assert.assertTrue(basePage.mainPageIsOpen());

    }

    @AfterMethod
    public void driverClose() {
        driver.close();
    }
}
