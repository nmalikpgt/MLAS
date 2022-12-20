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

public class AWR3A002 {
	
	private String pin;
	private String pid;
	private String clientNumber;


	private WebDriver driver;

	@After
	public void tearDown() {
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

		// Approve the created claim SRO
		// This script works based on client Number and claim ID created in
		// MP1001
		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();

		setPin(lgn1001.getPin());
		setPid(lgn1001.getPid());
		setClientNumber(lgn1001.getClientNumber());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Work Reporting")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Submit Report of Work")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys("1111111111");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("createSubmissionBtnId")));
		element.click();
		Thread.sleep(1500);
		driver.getPageSource().contains("Client ID doesn't exist");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(lgn1001.getClientNumber());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("createSubmissionBtnId")));
		element.click();

		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputTenureNumbersId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputTenureNumbersId")));
		element.sendKeys("abc123");
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1500);
		driver.getPageSource().contains("Following claim IDs are not valid: abc123"); 
			 
			 
			 
			 
		
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

}
