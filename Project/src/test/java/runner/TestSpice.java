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
        driver.get("https://demo.automationtesting.in/Register.html");
        
    }

    @Test
    public void testMethod(){
        try{
            WebElement fullName = driver.findElement(By.xpath("//input[@placeholder='First Name']"));
            fullName.click(); 
            fullName.sendKeys("John");
            
            WebElement lastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
            lastName.click();
            lastName.sendKeys("Sam");

            WebElement email = driver.findElement(By.xpath("//input[@type='email']"));
            email.click();
            email.sendKeys("John@example.com");

            WebElement mobile = driver.findElement(By.xpath("//input[@type='tel']"));
            mobile.click();
            mobile.sendKeys("9876543210");

            List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='cjeckbox']"));
            for(int i = 0;i<checkboxes.size();i++) {
                checkboxes.get(i).click();
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