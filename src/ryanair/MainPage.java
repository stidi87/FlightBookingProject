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
   
    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
   
    public void clickDepartureAirport() {
        departureAirport.click();
    }
    
    public void clickDestinationAirport() {
        destinationAirport.click();
    }
   
    public void setTheCountry(String country) {
    	destinationAirport.findElement(By.xpath("//div[contains(text(), '"+country+"')]")).click();
    }
   
    public void setTheCity(String city) {
    	destinationAirport.findElement(By.xpath("//div[contains(text(), '"+city+"')]")).click();
    }
}