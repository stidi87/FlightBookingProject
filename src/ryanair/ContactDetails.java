package ryanair;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactDetails {
	private WebDriver driver;
	
	@FindBy(xpath="//input[@placeholder='Enter email address']")
    WebElement email;
	
	@FindBy(xpath="(//select[@name='country'])[1]")
	WebElement country;
	
	@FindBy(xpath="//input[@name='phoneNumber']")
	WebElement phoneNumber;
	
	@FindBy(xpath="(//button[contains(text(), 'Continue')])[2]")
	WebElement continueButton;
	
	public ContactDetails(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void provideEmail(String email) throws InterruptedException {
		this.email.sendKeys(email);
		Thread.sleep(2000);
	}
	
	public void selectTheCountry(String country) throws InterruptedException {
		this.country.click();
		Thread.sleep(2000);
		this.country.findElement(By.xpath("(//option[@label='"+country+"'])[1]")).click();
		Thread.sleep(2000);
	}
	
	public void provideThePhoneNumber(String phoneNumber) throws InterruptedException {
		this.phoneNumber.sendKeys(phoneNumber);
		Thread.sleep(2000);
	}
	
	public void clickContinue() throws InterruptedException {
		continueButton.click();
		Thread.sleep(5000);	
	}
}
