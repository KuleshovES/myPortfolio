package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static resources.ConfProperties.driver;

public class LoginPage {
    static String userName = "testuser@senthy.com";
    static String userPass = "asdfg1234";
    public static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    //public static final By INPUT_USER = By.id("user");

    public static void login() throws InterruptedException {
        driver.get("https://trello.com/login");
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

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".content-all-boards")));
    }

}
