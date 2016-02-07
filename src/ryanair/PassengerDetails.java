package ryanair;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PassengerDetails {
	private WebDriver driver;
	
	@FindBy(xpath="(//label[contains(text(), 'Title')]//following-sibling::div[@class='core-select'])[1]")
    WebElement firstAdult;
	
	@FindBy(xpath="(//label[contains(text(), 'Title')]//following-sibling::div[@class='core-select'])[2]")
    WebElement secondAdult;
	
	@FindBy(xpath="(//input[@name='firstName'])[1]")
	WebElement firstAdultFirstName;
	
	@FindBy(xpath="(//input[@name='surname'])[1]")
	WebElement firstAdultSurname;
	
	@FindBy(xpath="(//input[@name='firstName'])[2]")
	WebElement secondAdultFirstName;
	
	@FindBy(xpath="(//input[@name='surname'])[2]")
	WebElement secondAdultSurname;
	
	@FindBy(xpath="(//input[@name='firstName'])[3]")
	WebElement childFirstName;
	
	@FindBy(xpath="(//input[@name='surname'])[3]")
	WebElement childSurname;
	
	@FindBy(xpath="(//button[contains(text(), 'Continue')])[1]")
	WebElement continueButton;
	
	public PassengerDetails(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void clickTheAdult(int adultNo) throws InterruptedException {
		if (adultNo == 1) {
			firstAdult.click();
			Thread.sleep(2000);
		}		
		if (adultNo == 2) {
			secondAdult.click();
			Thread.sleep(2000);	
		}
	}
	
	public void selectTheTitle(String title, int adultNo) throws InterruptedException {	
		if (adultNo == 1) {
			firstAdult.findElement(By.xpath("(.//option[@label='"+title+"'])['"+adultNo+"']")).click();
			Thread.sleep(2000);
		}		
		if (adultNo == 2) {
			secondAdult.findElement(By.xpath("(.//option[@label='"+title+"'])['"+adultNo+"']")).click();
			Thread.sleep(2000);	
		}
	}
	
	public void provideTheFirstName(String firstName, int passengerNo) throws InterruptedException {
		if (passengerNo == 1) {
			firstAdultFirstName.sendKeys(firstName);
			Thread.sleep(2000);
		}		
		if (passengerNo == 2) {
			secondAdultFirstName.sendKeys(firstName);
			Thread.sleep(2000);	
		}
		if (passengerNo == 3) {
			childFirstName.sendKeys(firstName);
			Thread.sleep(2000);	
		}	
	}
	
	public void provideTheSurname(String surname, int passengerNo) throws InterruptedException {
		if (passengerNo == 1) {
			firstAdultSurname.sendKeys(surname);
			Thread.sleep(2000);
		}		
		if (passengerNo == 2) {
			secondAdultSurname.sendKeys(surname);
			Thread.sleep(2000);	
		}
		if (passengerNo == 3) {
			childSurname.sendKeys(surname);
			Thread.sleep(2000);	
		}
	}
	
	public void clickContinue() throws InterruptedException {
		continueButton.click();
		Thread.sleep(5000);
	}
}
