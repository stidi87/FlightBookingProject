package ryanair;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
 
public class TestClassChrome {
    private WebDriver driver;
    private String baseUrl;
    MainPage mainSearch;
   
    @Before
    public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\workspace\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.ryanair.com/ie/en";
              
        mainSearch = new MainPage(driver);
       
        // Maximize the browser's window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
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
        
        // Create a calendar object
        WebElement nextButton;
        WebElement month = driver.findElement(By.xpath("//core-datepicker[@fly-out='true']"));
        
        // Move to August 2016
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
        
        // Increment Adults by one
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
        
        // Inbound direction
        driver.findElement(By.xpath("(//div[@id='inbound']//span[@class='standard-fare'])[1]")).click();
        Thread.sleep(2000);
        
        // Continue to the next page
        driver.findElement(By.id("continue")).click();
        Thread.sleep(5000);
        
        // Check out
        driver.findElement(By.xpath("//button[@class='core-btn-primary core-btn-medium']")).click();
        Thread.sleep(2000);
        
        // The data of the first adult
        // Scroll down
        jse.executeScript("scroll(0, 250);");
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//label[contains(text(), 'Title')]//following-sibling::div[@class='core-select'])[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(.//option[@label='Mr'])[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//input[@name='firstName'])[1]")).sendKeys("Mathew");
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//input[@name='surname'])[1]")).sendKeys("Smith");
        Thread.sleep(2000);
        
        // Data for the second Adult
        driver.findElement(By.xpath("(//label[contains(text(), 'Title')]//following-sibling::div[@class='core-select'])[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(.//option[@label='Mrs'])[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//input[@name='firstName'])[2]")).sendKeys("Martha");
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//input[@name='surname'])[2]")).sendKeys("Smith");
        Thread.sleep(2000);
        
        // Data for the child
        driver.findElement(By.xpath("(//input[@name='firstName'])[3]")).sendKeys("Adam");
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//input[@name='surname'])[3]")).sendKeys("Smith");
        Thread.sleep(2000);
        
        // Continue
        driver.findElement(By.xpath("(//button[contains(text(), 'Continue')])[1]")).click();
        Thread.sleep(2000);
        
        // Email address
        driver.findElement(By.xpath("//input[@placeholder='Enter email address']")).sendKeys("johndoe@johndoe.com");
        Thread.sleep(2000);
        
        // Phone number
        driver.findElement(By.xpath("(//select[@name='country'])[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//option[@label='Poland'])[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys("543543543");
        Thread.sleep(2000);
        
        // Continue
        driver.findElement(By.xpath("(//button[contains(text(), 'Continue')])[2]")).click();
        Thread.sleep(2000);
        
        // Card type
        driver.findElement(By.xpath("(//label[contains(text(), 'Card type')]//following::div)[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//option[@label='MasterCard']")).click();
        Thread.sleep(2000);
        
        // Card number
        driver.findElement(By.xpath("//input[@placeholder='Enter card number']")).sendKeys("5555555555554444");
        Thread.sleep(2000);
        
        // Expiry month, year and cvv
        driver.findElement(By.xpath("//select[@name='expiryMonth']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//option[@label='5']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//select[@name='expiryYear']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//option[@label='2020']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='CVV']")).sendKeys("111");
        Thread.sleep(2000);
        
        // Cardholder's name
        driver.findElement(By.xpath("//input[@name='cardHolderName']")).sendKeys("John Smith");
        Thread.sleep(2000);
        
        // Cardholder Address
        driver.findElement(By.xpath("//input[@name='street']")).sendKeys("21 Sun Lane");
        Thread.sleep(2000);
        
        // City
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Dublin");
        Thread.sleep(2000);
        
        // Postal Code
        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("12345");
        Thread.sleep(2000);
        
        // Country
        driver.findElement(By.xpath("(//select[@name='country'])[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//option[@label='Poland'])[2]")).click();
        Thread.sleep(2000);
        
        // Accept the conditions
        driver.findElement(By.xpath("(//input[@name='acceptPolicy']//following::span)[1]")).click();
        Thread.sleep(2000);
        
        // Click 'Pay Now'
        driver.findElement(By.xpath("(//span[contains(text(), 'Pay Now')])[1]")).click();
        Thread.sleep(2000);  
    }
    	
    @After
    public void afterClass() {
       
    }
}