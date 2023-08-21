package tests.UI;

import entities.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.ConfProperties;
import resources.RestApiMethods;
import trello.BasePage;

import static resources.ConfProperties.driver;

public class TestUIAuthorization {

    @Test
    public void sigInUI() throws InterruptedException{
        driver = ConfProperties.preconditionWithLogin();
        User user = new User();
        BasePage basePage = new BasePage();

        Assert.assertEquals(basePage.getCurrentUserName(),  RestApiMethods.getInfoUser(user).getFullName());

    }

    @Test
    public void logOutUI () throws InterruptedException{
        driver = ConfProperties.preconditionWithLogin();
        BasePage basePage = new BasePage();

        basePage.logOut();
        Assert.assertTrue(basePage.mainPageIsOpen());

    }

}
