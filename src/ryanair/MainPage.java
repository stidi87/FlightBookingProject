package ryanair;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
 
public class MainPage {
    private WebDriver driver;
   
    @FindBy(xpath="//input[@placeholder='Departure airport']")
    WebElement departureAirport;
    
    @FindBy(xpath="//input[@placeholder='Destination airport']")
    WebElement destinationAirport;
    
    @FindBy(xpath="//div[@class='dropdown-handle']")
    WebElement passengers;
    
    @FindBy(xpath="//div[@label='Adults']//button[@class='core-btn inc core-btn-wrap has-disabled-click']")
    WebElement adultsInc;
    
    @FindBy(xpath="//div[@label='Children']//button[@class='core-btn inc core-btn-wrap has-disabled-click']")
    WebElement childrenInc;
    
    @FindBy(xpath="//button[@class='core-btn-primary core-btn-block core-btn-big']")
    WebElement letsGo;
    
    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
   
    public void clickDepartureAirport() throws InterruptedException {
        departureAirport.click();
        Thread.sleep(2000);
    }
    
    public void clickDestinationAirport() throws InterruptedException {
        destinationAirport.click();
        Thread.sleep(2000);
    }
   
    public void setTheCountry(String country) throws InterruptedException {
    	destinationAirport.findElement(By.xpath("//div[contains(text(), '"+country+"')]")).click();
    	Thread.sleep(2000);
    }
   
    public void setTheCity(String city) throws InterruptedException {
    	destinationAirport.findElement(By.xpath("//div[contains(text(), '"+city+"')]")).click();
    	Thread.sleep(2000);
    }
    
    public void setPassengers() throws InterruptedException {
        passengers.click();
        Thread.sleep(2000);
    }
    
    public void incrementAdults() throws InterruptedException {
    	adultsInc.click();
    	Thread.sleep(2000);
    }
    
    public void incrementChildren() throws InterruptedException {
    	childrenInc.click();
    	Thread.sleep(2000);
    }
    
    public void clickLetsGo() throws InterruptedException {
    	letsGo.click();
    	Thread.sleep(5000);
    }
}