package runner;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
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

public class TestSpice {
    public static WebDriver driver;
    
    @BeforeMethod
    public void openBrowser() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/"), options);
        
        WebDriverListener listener = new EventHandler();
        driver = new EventFiringDecorator<>(listener).decorate(driver);

        driver.get("https://www.globalsqa.com/");
        
    }

    @Test
    public void testMethod(){
        WebElement contactUs = driver.findElement(By.xpath("(//a[text()='Contact Us'])[1]"));
        contactUs.click();
        
        // After clicking the contact us , work on the form
        WebElement name = driver.findElement(By.xpath("//input[@id='comment_name']"));
        name.click();
        name.sendKeys("John");

        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        email.click();
        email.sendKeys("John@example.com");

        WebElement subject = driver.findElement(By.xpath(""));
    }


    @AfterMethod
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}