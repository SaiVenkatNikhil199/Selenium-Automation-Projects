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
    
            driver.get("https://demoqa.com/");   
            Thread.sleep(1000);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMethod1(){
        try {
            WebElement elements = driver.findElement(By.xpath("(//div[@class='card-up'])[1]"));
            elements.click();

            WebElement textBox = driver.findElement(By.xpath("//span[text()='Text Box']"));
            textBox.click();
            
            WebElement fullName = driver.findElement(By.xpath("//input[@id='userName']"));
            fullName.click();
            fullName.sendKeys("John");

            WebElement email = driver.findElement(By.xpath("//input[@id='userEmail']"));
            email.click();
            email.sendKeys("John@example.com");
            
            WebElement submit = driver.findElement(By.xpath("//button[@id='submit']"));
            submit.click();

            WebElement radioBox = driver.findElement(By.xpath("//span[text()='Radio Button']"));
            radioBox.click();

            WebElement impressiveRadio = driver.findElement(By.xpath("//input[@id='impressiveRadio']"));
            impressiveRadio.click();
            Thread.sleep(3000);

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