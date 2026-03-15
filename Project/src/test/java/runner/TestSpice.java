package runner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import utils.EventHandler;

public class TestSpice {
    public static WebDriver driver;
    
    @BeforeMethod
    public void openBrowser() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/"), options);
        
        WebDriverListener listener = new EventHandler();
        driver = new EventFiringDecorator<>(listener).decorate(driver);
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");
        
    }

    @Test
    public void testMethod(){
        try{
            
            WebElement startTestingButton = driver.findElement(By.xpath("//span[text()='Start Testing Now']"));
            startTestingButton.click();

            Set<String> windows = driver.getWindowHandles();
            String currentWindow = driver.getWindowHandle();

            for(String window : windows){
                if(!window.equals(currentWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }

            WebElement forgotPassword = driver.findElement(By.xpath("//span[text()='Forgot Password']"));
            forgotPassword.click();

            WebElement emailField = driver.findElement(By.xpath("//input[@id='email']"));
            emailField.click();
            emailField.sendKeys("John@example.com");

            WebElement resetButton = driver.findElement(By.xpath("//button[text()='Reset Password']"));
            resetButton.click();
            

        }catch(Exception e){
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