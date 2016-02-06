package ryanair;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
 
public class TestClassFirefox {
    private WebDriver driver;
    private String baseUrl;
    MainPage mainSearch;
   
    @Before
    public void beforeClass() throws InterruptedException {
        driver = new FirefoxDriver();
        baseUrl = "https://www.ryanair.com/ie/en";
              
        mainSearch = new MainPage(driver);
       
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
    	Thread.sleep(10000);
    	driver.manage().window().maximize();
    }
   
    @Test
    public void test() throws InterruptedException {   	
    	// Click 'From' field
    	mainSearch.clickDepartureAirport();
        Thread.sleep(2000);
        
        // Scroll down, so that the countries and the cities are visible
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0, 250);");
        Thread.sleep(2000);
        
        // Choose Poland as the country of origin
        mainSearch.setTheCountry("Poland");
        Thread.sleep(2000);
        
        // Choose Warsaw (WMI) airport
        mainSearch.setTheCity("Warsaw (WMI)");
        Thread.sleep(2000);
        
        // Choose United Kingdom as the destination
        mainSearch.setTheCountry("United Kingdom");
        Thread.sleep(2000);
        
        // Choose London (STN) airport
        mainSearch.setTheCity("London (STN)");
        Thread.sleep(2000);
        
        // Move to some distant month to make sure that are free seats
        WebElement nextButton;
        WebElement month = driver.findElement(By.xpath("//core-datepicker[@fly-out='true']"));
        
        while (!month.getText().contains("August 2016")) {
		nextButton = driver.findElement(By.xpath("//button[@class='arrow right']"));
		nextButton.click();
		month = driver.findElement(By.xpath("//core-datepicker[@fly-out='true']"));
        }
        Thread.sleep(2000);
        
        // Pick the fly out day
        driver.findElement(By.xpath("//h1[contains(text(), 'August 2016')]//following::ul[@class='days']//li[@data-id='15-08-2016']")).click();
        Thread.sleep(2000);
        
        // Pick the fly back day
        driver.findElement(By.xpath("//h1[contains(text(), 'August 2016')]//following::ul[@class='days']//li[@data-id='20-08-2016']")).click();
        Thread.sleep(2000);
        
        // Passengers
        driver.findElement(By.xpath("//div[@class='dropdown-handle']")).click();
        Thread.sleep(2000);
        
        // Increment adults by one
        driver.findElement(By.xpath("//div[@label='Adults']//button[@class='core-btn inc core-btn-wrap has-disabled-click']")).click();
        Thread.sleep(2000);
         
        // Add one child
        driver.findElement(By.xpath("//div[@label='Children']//button[@class='core-btn inc core-btn-wrap has-disabled-click']")).click();
        Thread.sleep(2000);
        
        // Let's go
        driver.findElement(By.xpath("//button[@class='core-btn-primary core-btn-block core-btn-big']")).click();
        Thread.sleep(2000);
        
        // Outbound direction
        driver.findElement(By.xpath("(//div[@id='outbound']//span[@class='standard-fare'])[1]")).click();
        Thread.sleep(2000);
        
        // Outbound direction
        driver.findElement(By.xpath("(//div[@id='inbound']//span[@class='standard-fare'])[1]")).click();
        Thread.sleep(2000);
        
        // Continue to the next page
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
        
        // Check out
        driver.findElement(By.xpath("//button[@class='core-btn-primary core-btn-medium']")).click();
        Thread.sleep(2000);
    }
    	
    @After
    public void afterClass() {
       
    }
}

//li[@class='calendar-view']//h1[contains(text(), 'March 2016')]
//li[@class='calendar-view'][1]//ul[contains(text(), 'August 2016')]