package runner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
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
        driver.get("https://qabrains.com/practice-site");
        
    }

    @Test
    public void testMethod(){
        try{
            WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
            email.click();
            email.sendKeys("qa_testers@qabrains.com");

            WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
            password.click();
            password.sendKeys("qa_testers@qabrains.com");
            
            WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
            button.click();


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