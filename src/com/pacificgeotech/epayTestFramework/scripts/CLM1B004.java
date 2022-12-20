package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class CLM1B004 {
	
	private WebDriver driver;
	
	@After
	public void tearDown(){
		driver.getCurrentUrl();
	}	
	
	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;		
	}
	
	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		// Create new client as Buyer
		CLM1D003 clm1d003 = new CLM1D003();
		clm1d003.test();
		
		Thread.sleep(1000);
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Complete Transfer of Mining Claim(s)"))); 
		 element.click();
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(
					"bnGoNextBtnId")));
			element.click();
			
			new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"//*[contains(text(), 'No pending transfer for buyer')]")));

		
		
		
		
		
	}

}
