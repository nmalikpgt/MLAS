package com.pacificgeotech.epayTestFramework.scripts;

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

public class MRTReserve {
	
	private WebDriver driver;
	private String ClaimNo;
	private String clientNumber;
	private String pid;
	private String pin;
	private String LeaseNo1;
	private String LeaseNo2;

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
		WebDriverManager.getElements();

		ARAL aral = new ARAL();
		aral.test();
		setClientNumber(aral.getClientNumber());
		setPin(aral.getPin());
		setPid(aral.getPid());
		setClaimNo(aral.getClaimNo());
		setLeaseNo1(aral.getLeaseNo());

		aral.test();
		setLeaseNo2(aral.getLeaseNo());
		
		//manage reserves 
		Thread.sleep(1000);
		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage Reserves")))
				.click();
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("transfer"))); 
		 element.click();
		 Thread.sleep(500);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("selectedToId"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningRightIdFrom"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningRightIdFrom"))); 
		 element.sendKeys(getLeaseNo1());
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningRightIdTo"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningRightIdTo"))); 
		 element.sendKeys(getLeaseNo2());
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))); 
		 element.click();
		
		 
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("explorationReserve0"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("explorationReserve0"))); 
		 element.sendKeys("100");
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("consultationReserve0"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("consultationReserve0"))); 
		 element.sendKeys("100");
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("explorationReserve1"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("explorationReserve1"))); 
		 element.sendKeys("100");
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("consultationReserve1"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("consultationReserve1"))); 
		 element.sendKeys("100");
		 new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("abstractEntry")))).selectByVisibleText("Notice of Lease Exchange [ mining right number: " + getLeaseNo1() + " ]");
		 
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))); 
		 element.click();
		 Thread.sleep(1000);
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))); 
		 element.click();
		 
		 new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//*[contains(text(), 'Transfer to reserves completed successfully')]")));
		 
		 
		 
		 
		 
	}

	public String getClaimNo() {
		return ClaimNo;
	}

	public void setClaimNo(String claimNo) {
		ClaimNo = claimNo;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getLeaseNo1() {
		return LeaseNo1;
	}

	public void setLeaseNo1(String leaseNo1) {
		LeaseNo1 = leaseNo1;
	}

	public String getLeaseNo2() {
		return LeaseNo2;
	}

	public void setLeaseNo2(String leaseNo2) {
		LeaseNo2 = leaseNo2;
	}

}
