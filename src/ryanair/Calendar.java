package ryanair;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Calendar {
	 private WebDriver driver;
	 private String chosenMonth;
	 
	 @FindBy(xpath="//button[@class='arrow right']")
	 WebElement nextButton;
     
	 @FindBy(xpath="//core-datepicker[@fly-out='true']")
     WebElement month;
     
     public Calendar(WebDriver driver) {
         this.driver = driver;
         PageFactory.initElements(driver, this);
     }
     
     public void moveToAGivenMonth(String monthName) throws InterruptedException {
         while (!month.getText().contains(monthName + " " + 2016)) {
        	this.chosenMonth = monthName;
    		nextButton.click();
    		month = driver.findElement(By.xpath("//core-datepicker[@fly-out='true']"));
         }
         Thread.sleep(2000);
     } 
     
     public void setTheFlyOutDay(String day, String month, String year) {
    	 driver.findElement(By.xpath("//h1[contains(text(), '"+this.chosenMonth+" 2016')]//following::ul[@class='days']//li[@data-id='"+day+"-"+month+"-"+year+"']")).click();
     }
     
     public void setTheFlyBackDay(String day, String month, String year) {
    	 driver.findElement(By.xpath("//h1[contains(text(), '"+this.chosenMonth+" 2016')]//following::ul[@class='days']//li[@data-id='"+day+"-"+month+"-"+year+"']")).click();
     }   
}