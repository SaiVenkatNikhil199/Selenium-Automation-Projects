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
        driver.get("https://demo.automationtesting.in/Register.html");
        
    }

    @Test
    public void testMethod(){
        try{
            WebElement year = driver.findElement(By.xpath("//select[@id='yearbox']"));
            Select yearDropdown = new Select(year);
            yearDropdown.selectByVisibleText("2015");

            WebElement month = driver.findElement(By.xpath("//select[@ng-model=\"monthbox\"]"));
            Select monthDropdown = new Select(month);
            monthDropdown.selectByVisibleText("May");

            WebElement day = driver.findElement(By.xpath("//select[@id='daybox']"));
            Select dayDropdown = new Select(day);
            dayDropdown.selectByVisibleText("1");

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