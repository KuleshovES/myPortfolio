package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import resources.ConfProperties;

import static resources.ConfProperties.driver;

public class LoginPage {
    static String userName = "testuser@senthy.com";
    static String userPass = "asdfg1234";
    //public static final By INPUT_USER = By.id("user");

    public static void login() throws InterruptedException {
        driver.get("https://trello.com/login");
        Thread.sleep(1000);

        WebElement inputUser = driver.findElement(By.id("user"));
        inputUser.sendKeys(userName);

        WebElement buttonLogin = driver.findElement(By.id("login"));
        buttonLogin.click();
        Thread.sleep(1000); //BAD!

        WebElement inputPassword = driver.findElement(By.id("password"));
        Thread.sleep(1000); //BAD!
        inputPassword.sendKeys(userPass);

        WebElement buttonLoginSubmit = driver.findElement(By.id("login-submit"));
        buttonLoginSubmit.click();

    }

}
