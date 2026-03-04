package ERPpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class AdminLogin {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(xpath = "//button[@class='ajs-button btn btn-primary']")
    WebElement alert;
    @FindBy(xpath = "//input[@id='username']")
    WebElement username;
    @FindBy(xpath = "//input[@id='password']")
    WebElement password;
    @FindBy(xpath = "//button[@id='btnsubmit']")
    WebElement lgnBtn;

    public AdminLogin(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public void login(String user, String pass){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(alert)).click();
        }catch (Exception e){
            System.out.println("Alert not present. Continuing login...");
        }
        username.clear();
        wait.until(ExpectedConditions.visibilityOf(username)).sendKeys(user);
        password.clear();
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pass);
        wait.until(ExpectedConditions.elementToBeClickable(lgnBtn)).click();
    }
}
