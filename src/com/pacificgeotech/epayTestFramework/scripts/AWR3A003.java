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

public class AWR3A003 {
	
	private WebDriver driver;
	private String ClaimNo;
	private String pid;
	private String pin;
	private String clientNumber;
	private String SecondClaimNo;
	private String ThirdClaimNo;

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

	public String getClaimNo() {
		return ClaimNo;
	}

	public void setClaimNo(String claimNo) {
		ClaimNo = claimNo;
	}

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
		WebDriverManager.getElements();
		AWRER001 awrer001 = new AWRER001();
		awrer001.test();
		setClaimNo(awrer001.getClaimNo());
		setPin(awrer001.getPin());
		setPid(awrer001.getPid());
		setClientNumber(awrer001.getClientNumber());
		setSecondClaimNo(awrer001.getSecondClaimNo());
		setThirdClaimNo(awrer001.getThirdClaimNo());
		
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/a/span")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span")));
		element.click();

		String[] urlInfo = LGN1001.getLoginUrl();
		LGN1001 lgn1001 = new LGN1001();
		// Submit Notice of Claim Abandonment
		Thread.sleep(1000);
		// String [] loginURL = LGN1001.getLoginUrl();
		driver.get(urlInfo[0]);
		Thread.sleep(1000);
		if ("remote".equals(urlInfo[1])) {
			lgn1001.getLoginInfoExternalRemote(urlInfo[1], urlInfo[0]);
		} else {

			// lgn1001.getLoginInfoExternalLocalTest(urlInfo[1], urlInfo[0],
			// mp5003.getPid(), mp5003.getPin());

			driver.navigate().refresh();
			driver.navigate().to(urlInfo[0]);

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
			element.sendKeys(awrer001.getPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(awrer001.getPin());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();
		}
		
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Work Reporting")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Distribute Approved Credits")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(awrer001.getClientNumber());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("createSubmissionBtnId")));
		element.click();
		
		  element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect"))); 
			 element.clear();
			    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect"))); 
			 element.sendKeys(awrer001.getClientNumber());
			    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("createSubmissionBtnId"))); 
			 element.click();
			    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("receiverClaimIdsCsv"))); 
			 element.clear();
			    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("receiverClaimIdsCsv"))); 
			 element.sendKeys(awrer001.getSecondClaimNo());
			    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("providerTenureIdsCsv"))); 
			 element.clear();
			    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("providerTenureIdsCsv"))); 
			 element.sendKeys(awrer001.getClaimNo());
			 
			 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("bridgingTenureIdsCsv"))); 
			 element.clear();
			    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("bridgingTenureIdsCsv"))); 
			 element.sendKeys(awrer001.getThirdClaimNo());
			 
			    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))); 
			 element.click();
			 Thread.sleep(1500);

		
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getSecondClaimNo() {
		return SecondClaimNo;
	}

	public void setSecondClaimNo(String secondClaimNo) {
		SecondClaimNo = secondClaimNo;
	}

	public String getThirdClaimNo() {
		return ThirdClaimNo;
	}

	public void setThirdClaimNo(String thirdClaimNo) {
		ThirdClaimNo = thirdClaimNo;
	}

}
