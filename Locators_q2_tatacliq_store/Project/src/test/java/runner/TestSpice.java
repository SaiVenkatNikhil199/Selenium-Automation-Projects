package runner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
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
    public static Actions actions;
    @BeforeMethod
    public void openBrowser() throws MalformedURLException {
        try {
            ChromeOptions options = new ChromeOptions();
            driver = new RemoteWebDriver(new URL("http://localhost:4444/"), options);
            actions = new Actions(driver);
            
            WebDriverListener listener = new EventHandler();
            driver = new EventFiringDecorator<>(listener).decorate(driver);
            driver.manage().window().maximize();
    
            driver.get("https://www.tatacliq.com/");   

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMethod1(){
        try {
            WebElement categories = driver.findElement(By.xpath("//div[text()='Categories']"));
            Actions actions = new Actions(driver);
            actions.moveToElement(categories).perform();
            Thread.sleep(4000);

            WebElement mens_fashion = driver.findElement(By.xpath("//div[contains(@aria-label,\"Men's Fashion\")]"));
            actions.moveToElement(mens_fashion).perform();
            Thread.sleep(4000);
            
            WebElement t_shirts = driver.findElement((By.xpath("//a[text()='T-shirts']")));
            t_shirts.click();
            Thread.sleep(4000);
            
            WebElement first_t_shirt = driver.findElement(By.xpath("//div[@class='PlpComponent__base']/child::a[contains(@title,'Louis')]"));
            first_t_shirt.click();
            Thread.sleep(4000);

            Set<String> windows = driver.getWindowHandles();
            for (String window : windows) {
                if(window != driver.getWindowHandle()) {
                    driver.switchTo().window(window);
                }
            }

            Thread.sleep(3000);
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