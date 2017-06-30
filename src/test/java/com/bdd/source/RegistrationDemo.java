package com.bdd.source;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class RegistrationDemo {
	
	static WebDriver driver;
	static WebElement element= null;
	public static final String URL="http://demoqa.com/registration/";
	
	
	public static void clickMethod(WebElement element){
		element.click();
	}
	
	public static void myWait(){
		WebDriverWait wait= new WebDriverWait(driver,30);
	    wait.until(new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver wdriver) {
	            return ((JavascriptExecutor) driver).executeScript(
	                "return document.readyState"
	            ).equals("complete");
	        }
	    });
	}
	
	public static void initialize(){
		driver= new FirefoxDriver();
		driver.get(URL);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	public static WebElement  InputTagFirstName(){
		 element= driver.findElement(By.xpath(".//*[@id='name_3_firstname']"));
		 return element;
	}
	
	public static WebElement  InputTagLastName(){
		 element= driver.findElement(By.xpath(".//*[@id='name_3_lastname']"));
		 return element;
	}
	public static WebElement  RadioButtonTagSingle(){
		 element= driver.findElement(By.xpath(".//*[@id='pie_register']/li[2]/div/div/input[@value='single']"));
		 return element;
	}
	public static WebElement  RadioButtonTagMarried(){
		 element= driver.findElement(By.xpath(".//*[@id='pie_register']/li[2]/div/div/input[@value='married']"));
		 return element;
	}
	public static WebElement  RadioButtonTagDivorced(){
		 element= driver.findElement(By.xpath(".//*[@id='pie_register']/li[2]/div/div/input[@value='divorced']"));
		 return element;
	}
	public static WebElement  CheckBoxTagDance(){
		 element= driver.findElement(By.xpath(".//*[@id='pie_register']/li[3]/div/div/input[@value='dance']"));
		 return element;
	}
	public static WebElement  CheckBoxTagReading(){
		 element= driver.findElement(By.xpath(".//*[@id='pie_register']/li[3]/div/div/input[@value='reading']"));
		 return element;
	}
	public static WebElement  CheckBoxTagCricket(){
		 element= driver.findElement(By.xpath(".//*[@id='pie_register']/li[3]/div/div/input[@value='cricket ']"));
		 return element;
	}
	public static WebElement  SelectTagCountry(){
		 element= driver.findElement(By.xpath(".//*[@id='dropdown_7']"));
		 return element;
	}
	public static WebElement  SelectTagMonth(){
		 element= driver.findElement(By.xpath(".//*[@id='mm_date_8']"));
		 return element;
	}
	public static WebElement  SelectTagDay(){
		 element= driver.findElement(By.xpath(".//*[@id='dd_date_8']"));
		 return element;
	}
	public static WebElement  SelectTagYear(){
		 element= driver.findElement(By.xpath(".//*[@id='yy_date_8']"));
		 return element;
	}
	public static WebElement  InputTagPhoneNumber(){
		 element= driver.findElement(By.xpath(".//*[@id='phone_9']"));
		 return element;
	}
	public static WebElement  InputTagUsername(){
		 element= driver.findElement(By.xpath(".//*[@id='username']"));
		 return element;
	}
	public static WebElement  InputTagEmail(){
		 element= driver.findElement(By.xpath(".//*[@id='email_1']"));
		 return element;
	}
	public static WebElement  InputTagPassword(){
		 element= driver.findElement(By.xpath(".//*[@id='password_2']"));
		 return element;
	}
	public static WebElement  InputTagConfirmPassword(){
		 element= driver.findElement(By.xpath(".//*[@id='confirm_password_password_2']"));
		 return element;
	}
	public static WebElement  SubmitTagSubmit(){
		 element= driver.findElement(By.xpath(".//*[@id='pie_register']//div/input[@name='pie_submit']"));
		 return element;
	}
	public static String RandomAlphaNumGenertor(){
		
		 SecureRandom random = new SecureRandom();
		 return new BigInteger(40, random).toString(32);
	
	}
	public static long RandomNumGenertor(){
	
		 return (long)(Math.random()*100000 + 9845700000L);
	
	}
	
	public void MyCustomSendkeys(WebElement element, String text){
		clickFunction(element);
		element.clear();
		element.sendKeys(text);
	}
	
	public void clickFunction(WebElement element){
		element.click();
	}
	
	public void SelectTagElement(WebElement element,String value){
		Select select= new Select(element);
		select.selectByValue(value);
		
	}
	
	
	@Test
	public void userRegistration(){
		initialize();
		myWait();
		MyCustomSendkeys(InputTagFirstName(),"sujit");
		MyCustomSendkeys(InputTagLastName(),"Pandey");
		clickFunction(RadioButtonTagSingle());
		clickFunction(CheckBoxTagDance());
		SelectTagElement(SelectTagCountry(),"India");
		SelectTagElement(SelectTagMonth(),"10");
		SelectTagElement(SelectTagDay(),"5");
		SelectTagElement(SelectTagYear(),"2014");
		String text=RandomAlphaNumGenertor();
		MyCustomSendkeys(InputTagUsername(),text);
		MyCustomSendkeys(InputTagPhoneNumber(),Long.toString(RandomNumGenertor()));
		MyCustomSendkeys(InputTagEmail(),(text+"@yahoo.com"));
		MyCustomSendkeys(InputTagPassword(),text);
		MyCustomSendkeys(InputTagConfirmPassword(),text);
		clickFunction(SubmitTagSubmit());
		tearddown();
	}
	public void tearddown(){
		driver.quit();
	}
	
	
	
	
	
	

}
