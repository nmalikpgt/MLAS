package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

import junit.framework.Assert;

public class CM6001STAGE1 {
	
	private WebDriver driver;

//	@After
//	public void tearDown() {
//		driver.getCurrentUrl();
//	}
//
//	@AfterClass
//	public static void afterClass() {
//		WebDriverManager.instance = null;
//	}

	@Test
	public void test() throws Exception {

		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		java.util.List<WebElement> elements = WebDriverManager.getElements();

		CM2002STAGE1 cm2002stage1 = new CM2002STAGE1();
		cm2002stage1.test();

		WebElement TxtBoxContent1 = driver.findElement(By.id("organizationNumberId"));
		String orgNum = TxtBoxContent1.getText();
		System.out.println("Org number 1 : " + orgNum);
		
		//logout
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("MLAS Admin"))); 
		 element.click();
		 Thread.sleep(1000);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header[@id='header']/cata-header/div[1]/div[2]/ul[2]/li[3]/ul/li/a/div"))); 
		 element.click();

		 cm2002stage1.test();
		WebElement TxtBoxContent2 = driver.findElement(By.id("organizationNumberId"));
		String orgNum1 = TxtBoxContent2.getText();
		System.out.println("Org number 2 : " + orgNum1);
		
		
		//logout
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("MLAS Admin"))); 
		 element.click();
		 Thread.sleep(1000);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header[@id='header']/cata-header/div[1]/div[2]/ul[2]/li[3]/ul/li/a/div"))); 
		 element.click();
		

		 cm2002stage1.test();
		 
		WebElement TxtBoxContent3 = driver.findElement(By.id("organizationNumberId"));
		String orgNum2 = TxtBoxContent3.getText();
		System.out.println("Org number 3 : " + orgNum2);
		

		LGN1002 lgn1002 = new LGN1002();
		lgn1002.test();

		// amalgamate client
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.linkText("Client Management")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Client Management")));
		element.click();
		
		Thread.sleep(1000);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Text not found!", bodyText.contains(
				"Re-send Client PIN"));
	}


}
