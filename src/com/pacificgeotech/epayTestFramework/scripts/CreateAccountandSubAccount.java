package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class CreateAccountandSubAccount {

	private WebDriver driver;
	private String clientNumber;
	private String LeaseNo;
	private String AccountNo;

	
//	 @After public void tearDown() { driver.getCurrentUrl(); }
//	  
//	  @AfterClass public static void afterClass() { WebDriverManager.instance =
//	  null; }
	 

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();
		

		ARAL aral = new ARAL();
		aral.test();
		setClientNumber(aral.getClientNumber());
		setLeaseNo(aral.getLeaseNo());
		setAccountNo(aral.getAccountNo());


	}

	public String getAccountNo() {
		return AccountNo;
	}

	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getLeaseNo() {
		return LeaseNo;
	}

	public void setLeaseNo(String leaseNo) {
		LeaseNo = leaseNo;
	}

}
