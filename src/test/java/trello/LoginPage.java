package trello;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static resources.ConfProperties.driver;

public class LoginPage {

    static String userName = "testuser@senthy.com";
    static String userPass = "asdfg1234";
    public static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    static String buttonViewAllBoards = "//button[contains(@class, 'view-all-closed-boards-button')]";

    @Step("LogIn")
    public static void login() throws InterruptedException {

        driver.get("https://trello.com/en/login");
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("user"))))
                .sendKeys(userName);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("login"))))
                .click();

        Thread.sleep(1000); //BAD!
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("password"))))
                .sendKeys(userPass);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("login-submit"))))
                .click();

        Thread.sleep(5000); //BAD!
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonViewAllBoards)));
    }



}
