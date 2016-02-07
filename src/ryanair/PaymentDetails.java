package ryanair;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentDetails {
	private WebDriver driver;
	
	@FindBy(xpath="(//label[contains(text(), 'Card type')]//following::div)[1]")
    WebElement cardType;
	
	@FindBy(xpath="//input[@placeholder='Enter card number']")
	WebElement cardNumber;
	
	@FindBy(xpath="//select[@name='expiryMonth']")
	WebElement month;
	
	@FindBy(xpath="//select[@name='expiryYear']")
	WebElement year;
	
	@FindBy(xpath="//input[@placeholder='CVV']")
	WebElement cvv;
	
	@FindBy(xpath="//input[@name='cardHolderName']")
	WebElement name;
	
	@FindBy(xpath="//input[@name='street']")
	WebElement address;
	
	@FindBy(xpath="//input[@name='city']")
	WebElement city;
	
	@FindBy(xpath="//input[@name='postcode']")
	WebElement postcode;
	
	@FindBy(xpath="(//select[@name='country'])[2]")
	WebElement country;
	
	@FindBy(xpath="(//input[@name='acceptPolicy']//following::span)[1]")
	WebElement conditionsButton;
	
	@FindBy(xpath="(//div[@class='pay-now']//descendant::button[@type='submit'])[1]")
	WebElement payNowButton;
	
	public PaymentDetails(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void chooseTheCardType(String cardType) throws InterruptedException {
		this.cardType.click();
		Thread.sleep(2000);
		this.cardType.findElement(By.xpath("//option[@label='"+cardType+"']")).click();
		Thread.sleep(2000);
	}
	
	public void provideTheCardNumber(String cardNumber) throws InterruptedException {
		this.cardNumber.sendKeys(cardNumber);
		Thread.sleep(2000);
	}
	
	public void provideTheExpiryMonthAndYear(int month, int year) throws InterruptedException {
		this.month.click();
		Thread.sleep(2000);
		this.month.findElement(By.xpath("//option[@label='"+month+"']")).click();
		Thread.sleep(2000);
		this.year.click();
		Thread.sleep(2000);
		this.year.findElement(By.xpath("//option[@label='"+year+"']")).click();
		Thread.sleep(2000);
	}
	
	public void provideTheCVV(String cvv) throws InterruptedException {
		this.cvv.sendKeys(cvv);
		Thread.sleep(2000);
	}
	
	public void provideTheCardholdersName(String name) throws InterruptedException {
		this.name.sendKeys(name);
		Thread.sleep(2000);
	}
	
	public void provideTheCardholderAddress(String address) throws InterruptedException {
		this.address.sendKeys(address);
		Thread.sleep(2000);
	}
	
	public void provideTheCity(String city) throws InterruptedException {
		this.city.sendKeys(city);
		Thread.sleep(2000);
	}
	
	public void provideThePostcode(String postcode) throws InterruptedException {
		this.postcode.sendKeys(postcode);
		Thread.sleep(2000);
	}
	
	public void provideTheCountry(String country) throws InterruptedException {
		this.country.sendKeys(country);
		Thread.sleep(2000);
	}
	
	public void acceptTheConditions() throws InterruptedException {
		this.conditionsButton.click();
		Thread.sleep(2000);
	}
	
	public void clickPayNow() throws InterruptedException {
		this.payNowButton.click();
		Thread.sleep(5000);
	}
	
}
