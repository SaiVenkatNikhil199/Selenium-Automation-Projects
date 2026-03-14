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
            
            WebElement simpleAlert = driver.findElement(By.xpath("//button[@id='alertBtn']"));
            simpleAlert.click();
            Alert alert = driver.switchTo().alert();
            alert.accept();
            
            WebElement confirmAlert = driver.findElement(By.xpath("//button[@id='confirmBtn']"));
            confirmAlert.click();
            Alert alert2 = driver.switchTo().alert();
            alert2.dismiss();

            WebElement newTab = driver.findElement(By.xpath("//button[text()='New Tab']"));
            newTab.click();

            Set<String> windows = driver.getWindowHandles();
            String currentWindow = driver.getWindowHandle();

            for(String window : windows) {
                if(!window.equals(current)) {
                    driver.switchTo().window(window);
                }   
            }
            
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