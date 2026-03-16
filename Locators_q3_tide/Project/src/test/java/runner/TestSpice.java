package runner;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import utils.EventHandler;
import java.lang.Thread;

public class TestSpice {
    public static WebDriver driver;
    
    @BeforeMethod
    public void openBrowser() throws MalformedURLException {
        try {
            ChromeOptions options = new ChromeOptions();
            driver = new RemoteWebDriver(new URL("http://localhost:4444/"), options);
            
            WebDriverListener listener = new EventHandler();
            driver = new EventFiringDecorator<>(listener).decorate(driver);
            driver.manage().window().maximize();
    
            driver.get("https://tide.com/en-us");   

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMethod1(){
        try {
            WebElement signIn = driver.findElement(By.xpath("//button[text()='Sign in']"));
            signIn.click();
            
            WebElement username = driver.findElement(By.cssSelector("input[id='username']"));
            username.click();
            username.sendKeys("example@gmail.com");
            
            WebElement password = driver.findElement(By.cssSelector("input[id='password']"));
            password.click();
            password.sendKeys("example");
            
            WebElement signInButton = driver.findElement(By.xpath("//button[text()='Sign in']"));
            signInButton.click();
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    @AfterMethod
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}