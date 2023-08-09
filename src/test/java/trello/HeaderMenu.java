package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static resources.ConfProperties.driver;
import static trello.LoginPage.login;

public class HeaderMenu {

    public static final WebElement accountMenuButton = driver.findElement(By.xpath("//*[@data-testid=\"header-member-menu-button\"]"));
    public static final WebElement accountMenuLogOutButton = driver.findElement(By.xpath("//*[@data-testid=\"account-menu-logout\"]"));
    public static final WebElement logOutButton = driver.findElement(By.id("logout-submit"));

    public void logOut() throws InterruptedException {
        //логинимся
        login();
        // разлогинмся
        accountMenuButton.click();
        accountMenuLogOutButton.click();
        logOutButton.click();

    }
}
