package steps;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;	
//import org.openqa.selenium.firefox.FirefoxDriver;	// uncomment when using Firefox


import ryanair.Calendar;
import ryanair.ContactDetails;
import ryanair.FarePage;
import ryanair.MainPage;
import ryanair.PassengerDetails;
import ryanair.PaymentDetails;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestingSteps {
	
	WebDriver driver;
	String baseUrl;
	MainPage mainSearch;	// object for actions performed on the first page
	Calendar calendar;		// object for the calendar
	FarePage farePage;		// object for actions performed on the 'fare' page
	PassengerDetails passengerDetails;		// object for handling the passengers details
	ContactDetails contactDetails;			// object for handling the contact details
	PaymentDetails paymentDetails;			// object for handling the payment details
	
	
	@Given("^A user books a flight from Poland/Warsaw to United Kingdom/London from (\\d+)-(\\d+)-(\\d+) to (\\d+)-(\\d+)-(\\d+) for two adults and one child$")
	public void a_user_books_a_flight_from_Poland_Warsaw_to_United_Kingdom_London_from_to_for_adults_and_child(String flyOutDay, String flyOutMonth, String flyOutYear, String flyBackDay, String flyBackMonth, String flyBackYear) throws Throwable {	
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\workspace\\chromedriver.exe");	// The path to the chromedriver. Comment it out when using Firefox.
        driver = new ChromeDriver();	
		//driver = new FirefoxDriver();	// uncomment when using Firefox
        baseUrl = "https://www.ryanair.com/ie/en";
        
        mainSearch = new MainPage(driver);
       
        // Maximize the browser's window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
        
        // Click 'From' field
        mainSearch.clickDepartureAirport();
        
        // Scroll down, so that the countries and the cities are visible
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0, 250);");
        Thread.sleep(2000);    
        
        // Choose Poland as the country of origin
        mainSearch.setTheCountry("Poland");
        
        // Choose Warsaw (WMI) airport
        mainSearch.setTheCity("Warsaw (WMI)");
        
        // Choose United Kingdom as the destination
        mainSearch.setTheCountry("United Kingdom");
        
        // Choose London (STN) airport
        mainSearch.setTheCity("London (STN)");
        
        calendar = new Calendar(driver);
        
        // Move to a specific month and pick the fly out/fly back dates
        calendar.moveToAGivenMonth("August");
        calendar.setTheFlyOutDay(flyOutDay, flyOutMonth, flyOutYear);
        calendar.setTheFlyBackDay(flyBackDay, flyBackMonth, flyBackYear);
        
        // Choose 2 adults and 1 child
        mainSearch.setPassengers();
        mainSearch.incrementAdults();
        mainSearch.incrementChildren();
        
        // Click Let's go
        mainSearch.clickLetsGo();
	}

	@Given("^Correctly provide the rest of the details up to the payment page$")
	public void correctly_provide_the_rest_of_the_details_up_to_the_payment_page() throws Throwable {
		
        farePage = new FarePage(driver);
		
		// Outbound direction
		farePage.chooseOutboundFare();		// pick the first option from the available fares
        
        // Inbound direction
        farePage.chooseInboundFare();		// pick the first option from the available fares
        
        // Continue to the next page
        farePage.clickContinue();
        
        // Check out
        farePage.clickCheckOut();
        
        passengerDetails = new PassengerDetails(driver);
        
        // The first adult details 
        passengerDetails.clickTheAdult(1);
        passengerDetails.selectTheTitle("Mr", 1);
        passengerDetails.provideTheFirstName("Tony", 1);
        passengerDetails.provideTheSurname("Smith", 1);
        
        // The second adult details
        passengerDetails.clickTheAdult(2);
        passengerDetails.selectTheTitle("Mrs", 2);
        passengerDetails.provideTheFirstName("Martha", 2);
        passengerDetails.provideTheSurname("Smith", 2); 
        
        // The child details
        passengerDetails.provideTheFirstName("Patrick", 3);
        passengerDetails.provideTheSurname("Smith", 3); 
        
        // Continue
        passengerDetails.clickContinue();
        
        contactDetails = new ContactDetails(driver);
        
        // Email address
       	contactDetails.provideEmail("johndoe@johndoe.com");
        
        // Phone number
       	contactDetails.selectTheCountry("Poland");
       	contactDetails.provideThePhoneNumber("545343232");
        
        // Continue
        contactDetails.clickContinue();
	}

	@When("^The user provides invalid credit card number details, i\\.e\\. \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
	public void the_user_provides_invalid_credit_card_number_details_i_e_and(String creditCardNumber, String expiryDate, String cvv) throws Throwable {
        
        paymentDetails = new PaymentDetails(driver);
      
        // Card type and number
        paymentDetails.chooseTheCardType("MasterCard");
		paymentDetails.provideTheCardNumber(creditCardNumber);
        
        char[] charArrayExpiryDate = expiryDate.toCharArray();				// Converts string to charArray.
        char[] yearChar = Arrays.copyOfRange(charArrayExpiryDate, 2, 6);	// Extracting the year from '5/2020'
        char[] monthChar = Arrays.copyOfRange(charArrayExpiryDate, 0, 1);	// Extracting the month from '5/2020'
        int year = Integer.parseInt(new String(yearChar));					// Converting char[] to int.
        int month = Integer.parseInt(new String(monthChar));				// Converting char[] to int.
        
        // Expiry month, year and CVV
        paymentDetails.provideTheExpiryMonthAndYear(month, year);
        paymentDetails.provideTheCVV(cvv);
        
        // Cardholer's name, address, city, postcode and country
        paymentDetails.provideTheCardholdersName("John Smith");
        paymentDetails.provideTheCardholderAddress("21 Sun Lane");
        paymentDetails.provideTheCity("Dublin");
        paymentDetails.provideThePostcode("12345");
        paymentDetails.provideTheCountry("Ireland");
        
        // Accept the conditions
        paymentDetails.acceptTheConditions();
	}

	@When("^Clicks Pay Now$")
	public void clicks_Pay_Now() throws Throwable {
		// Click 'Pay Now'
		paymentDetails.clickPayNow(); 
	}

	@Then("^He gets an error message saying that the payment was not authorised$")
	public void he_gets_an_error_message_saying_that_the_payment_was_not_authorised() throws Throwable {	
	    System.out.println("The payment was not authorised");    
	}
}
