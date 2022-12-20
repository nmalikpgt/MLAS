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

public class CLM1D002 {
	
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
		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();
		
		
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[8]/a[2]/span")));
		element.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Joint Tenant Approval For Transfer of Mining Claim"))).click();


		// click Next
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();
		
		new WebDriverWait(driver, 50)
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'No pending transfer for approval.')]")));

		
	}

}
