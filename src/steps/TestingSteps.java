package steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import ryanair.MainPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestingSteps {
	
	WebDriver driver;
	String baseUrl;
	MainPage mainSearch;
	
	@Given("^A user books a flight from Poland/Warsaw to United Kingdom/London from (\\d+)-(\\d+)-(\\d+) to (\\d+)-(\\d+)-(\\d+) for two adults and one child$")
	public void a_user_books_a_flight_from_Poland_Warsaw_to_United_Kingdom_London_from_to_for_adults_and_child(String flyOutDay, String flyOutMonth, String flyOutYear, String flyBackDay, String flyBackMonth, String flyBackYear) throws Throwable {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\workspace\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.ryanair.com/ie/en";       
        mainSearch = new MainPage(driver);
       
        // Maximize the browser's window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
        
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
        WebElement monthhh = driver.findElement(By.xpath("//core-datepicker[@fly-out='true']"));
        
        // Move to August 2016
        while (!monthhh.getText().contains("August 2016")) {
		nextButton = driver.findElement(By.xpath("//button[@class='arrow right']"));
		nextButton.click();
		monthhh = driver.findElement(By.xpath("//core-datepicker[@fly-out='true']"));
        }
        Thread.sleep(2000);
        
        // Pick the fly out day
        driver.findElement(By.xpath("//h1[contains(text(), 'August 2016')]//following::ul[@class='days']//li[@data-id='"+flyOutDay+"-"+flyOutMonth+"-"+flyOutYear+"']")).click();
        Thread.sleep(2000);
        
        // Pick the fly back day
        driver.findElement(By.xpath("//h1[contains(text(), 'August 2016')]//following::ul[@class='days']//li[@data-id='"+flyBackDay+"-"+flyBackMonth+"-"+flyBackYear+"']")).click();
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
	}

	@Given("^Correctly provide the rest of the details up to the payment page$")
	public void correctly_provide_the_rest_of_the_details_up_to_the_payment_page() throws Throwable {
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
        
        // Scroll down
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0, 250);");
        Thread.sleep(2000);
        
        // The first adult details 
        driver.findElement(By.xpath("(//label[contains(text(), 'Title')]//following-sibling::div[@class='core-select'])[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(.//option[@label='Mr'])[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//input[@name='firstName'])[1]")).sendKeys("Mathew");
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//input[@name='surname'])[1]")).sendKeys("Smith");
        Thread.sleep(2000);
        
        // The second adult details
        driver.findElement(By.xpath("(//label[contains(text(), 'Title')]//following-sibling::div[@class='core-select'])[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(.//option[@label='Mrs'])[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//input[@name='firstName'])[2]")).sendKeys("Martha");
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//input[@name='surname'])[2]")).sendKeys("Smith");
        Thread.sleep(2000);
        
        // The child details
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
	}

	@When("^The user provides invalid credit card number details, i\\.e\\. \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
	public void the_user_provides_invalid_credit_card_number_details_i_e_and(String creditCardNumber, String expiryDate, String cvv) throws Throwable {
		// Card number
        driver.findElement(By.xpath("//input[@placeholder='Enter card number']")).sendKeys(creditCardNumber);
        Thread.sleep(2000);
        
        char[] charArrayExpiryDate = expiryDate.toCharArray(); 

        // Expiry month, year and CVV
        driver.findElement(By.xpath("//select[@name='expiryMonth']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//option[@label='"+charArrayExpiryDate[0]+"']")).click();	//  Gets the expiry month (5 in this case).
        Thread.sleep(2000);
        driver.findElement(By.xpath("//select[@name='expiryYear']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//option[@label='2020']")).click();						//  Gets the expiry year (2020 in this case).
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='CVV']")).sendKeys(cvv);
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
	}

	@When("^Clicks Pay Now$")
	public void clicks_Pay_Now() throws Throwable {
		
		// Click 'Pay Now'
        driver.findElement(By.xpath("(//span[contains(text(), 'Pay Now')])[1]")).click();
        Thread.sleep(2000);  
	}

	@Then("^He gets an error message saying that the payment was not authorised$")
	public void he_gets_an_error_message_saying_that_the_payment_was_not_authorised() throws Throwable {
		
	    System.out.println("wrong");    
	}
}
