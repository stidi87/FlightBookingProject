package ryanair;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FarePage {
	private WebDriver driver;
	
	@FindBy(xpath="(//div[@id='outbound']//span[@class='standard-fare'])[1]")
    WebElement outboundFare;
	
	@FindBy(xpath="(//div[@id='inbound']//span[@class='standard-fare'])[1]")
    WebElement inboundFare;
	
	@FindBy(id="continue")
    WebElement continueButton;
	
	@FindBy(xpath="//button[@class='core-btn-primary core-btn-medium']")
	WebElement checkOutButton;
	
	public FarePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void chooseOutboundFare() throws InterruptedException {
		outboundFare.click();
		Thread.sleep(2000);
	}
	
	public void chooseInboundFare() throws InterruptedException {
		inboundFare.click();
		Thread.sleep(2000);
	}

	public void clickContinue() throws InterruptedException {
		continueButton.click();
		Thread.sleep(5000);
	}
	
	public void clickCheckOut() throws InterruptedException {
		checkOutButton.click();
		Thread.sleep(5000);
	}
}
