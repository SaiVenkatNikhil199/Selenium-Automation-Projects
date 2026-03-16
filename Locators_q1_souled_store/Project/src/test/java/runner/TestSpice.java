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
    
            driver.get("https://www.thesouledstore.com/men");   

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMethod1(){
        try {
            Thread.sleep(1000);
            WebElement elem = driver.findElement(By.cssSelector("input[id='search']"));
            elem.click();
            elem.sendKeys("Shoes",Keys.ENTER);
            Thread.sleep(3000);
            WebElement productOne = driver.findElement(By.xpath("(//div[contains(@class,'productlist')])[1]"));
            productOne.click();
            Thread.sleep(4000);
            WebElement wishList = driver.findElement(By.xpath("//span[text()='Add to Wishlist']"));
            wishList.click();
            Thread.sleep(4000);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @AfterMethod
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}