package com.bdd.source;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class LocatorsInDataPicker {
	static WebDriver driver;
	
	public static String URL="http://demoqa.com/datepicker/";
	public static WebElement element=null;
	public static WebElement myLocators(){
		return element=driver.findElement(By.id("datepicker1"));
	}
	
	public static void clickFunction(WebElement element){
			element.click();
	}
	
	public static void  Mywait(){
		WebDriverWait wait = new WebDriverWait(driver, 30);
	    wait.until(new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver wdriver) {
	            return ((JavascriptExecutor) driver).executeScript(
	                "return document.readyState"
	            ).equals("complete");
	        }
	    });

	}
	
	public static WebElement monthChecker(){
		WebElement  element= driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span[1]"));
		return element;
	}
	public static WebElement YearChecker(){
		WebElement  element= driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span[2]"));
		return element;
	}
	public static boolean monthYearChecker(String month,String year){
		
		
		return (monthChecker().getText().contains(month) && YearChecker().getText().contains(year) );
		
	}
	
	public static WebElement clickOnNext(){
		WebElement element= driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span"));
		return element;
	}
	public static WebElement clickOnRequiredDate(String date, String month, String year){
		WebElement element= driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/table/tbody//tr/td[@data-year='"+year+"' and @data-month='"+
	((Integer.parseInt(month))-1)+"']/a[text()="+date+"]"));
		return element;
	}
	
	public static void initializer(){
		driver=new FirefoxDriver();
		driver.navigate().to(URL);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Mywait();
		
	}
	
	public String data="2017-10-11";
	@Test
	public void datePicker() throws ParseException{
		 List<String>  myData=dataSplitter(data);
		String month=myData.get(1);
		String day=myData.get(2);
		String year=myData.get(0);
		String MonthInINT= myData.get(3);
		initializer();
		clickFunction(myLocators());
		Mywait();

		while(true){
			boolean flag=monthYearChecker(month,year);
			if(!flag){
				clickFunction(clickOnNext());
			}
			else{
				break;
			}
		}
		
		clickFunction(clickOnRequiredDate(day,MonthInINT,year));

	}
	
	public static List<String> dataSplitter(String dateData) throws ParseException{
		String[] getInTmonth=dateData.split("-");
		Date testData1=new SimpleDateFormat("yyyy-MM-dd").parse(dateData);
		String newstring = new SimpleDateFormat("yyyy-MMMM-dd").format(testData1);
		System.out.println(newstring);
		String[] myData=newstring.split("-");
		List<String> listFromArray = Arrays.asList(myData);
		List<String> tempList = new ArrayList<String>(listFromArray);
		tempList.add(getInTmonth[1]);
		System.out.println(tempList.get(0)+" "+ tempList.get(1)+ " "+ tempList.get(2)+" "+tempList.get(3));
		return tempList;
			
	}





	

}
