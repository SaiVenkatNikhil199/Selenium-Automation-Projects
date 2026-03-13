package runner;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
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
    
            driver.get("https://adactinhotelapp.com/index.php");   
            Thread.sleep(1000);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMethod1(){
        try {
            WebElement forgotPassword = driver.findElement(By.xpath("//a[text()='Forgot Password?']"));
            forgotPassword.click();

            WebElement emailField = driver.findElement(By.xpath("//input[@id='emailadd_recovery']"));
            emailField.click();
            emailField.sendKeys("example@gmail.com");
            
            WebElement emailPasswordBtn = driver.findElement(By.xpath("//input[@id='Submit']"));
            emailPasswordBtn.click();

            WebElement redirectLink = driver.findElement(By.xpath("//a[text()='Click here to login']"));
            String redirectText = redirectLink.getText();
            Assert.assertTrue(redirectText.contains("login"));
            
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