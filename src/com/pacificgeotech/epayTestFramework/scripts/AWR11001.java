package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class AWR11001 {

	private WebDriver driver;
	private String ClaimNo;
	private String clientNumber;
	private String pid;
	private String pin;
	private int reportNo;
	private String SecondClaimNo;
	private String ThirdClaimNo;

	
	  @After public void tearDown() { driver.getCurrentUrl(); }
	  
	  @AfterClass public static void afterClass() { WebDriverManager.instance =
	 null; }
	 

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();
		AWR2A001 awr2a001 = new AWR2A001();
		awr2a001.test();
		setClientNumber(awr2a001.getClientNumber());
		setPin(awr2a001.getPin());
		setPid(awr2a001.getPid());
		setClaimNo(awr2a001.getClaimNo());
		setReportNo(awr2a001.getReportNo());
		setSecondClaimNo(awr2a001.getSecondClaimNo());
		setThirdClaimNo(awr2a001.getThirdClaimNo());

		// submit pending distribution

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Submit Pending Distribution")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(awr2a001.getClientNumber());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("submissionTermsAccepted")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("createSubmissionBtnId")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("reportOfWorkId")));
		element.clear();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("reportOfWorkId")));
		element.sendKeys(String.valueOf(awr2a001.getReportNo()));
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[2]"))); 
		 element.click();
		new Select(element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.name("providerTenureAtIdx0OfReceiverAtIdx0"))))
						.selectByVisibleText(awr2a001.getClaimNo());

		new Select(element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("providerReserveTypeAtIdx0OfReceiverAtIdx0"))))
						.selectByVisibleText("Consultation Reserve");

		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.name("providerDistributionReserveAtIdx0OfReceiverAtIdx0")));
		element.click();
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.name("providerDistributionReserveAtIdx0OfReceiverAtIdx0")));
		element.clear();
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.name("providerDistributionReserveAtIdx0OfReceiverAtIdx0")));
		element.sendKeys("400");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("saveDraft")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(2500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		
		
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'submitted amounts (excluding rejections or approvals without amendments) you will be given 15 days to revisit and/or edit and accept your pending distribution.You will not be able to add or remove tenures from the original submission in this 15 day period. This 15 day period cannot be extended. If you do not revisit and accept your pending distribution within the 15 day period, the pending distribution will be removed from the system.')]")));
		
		

	}

	public String getThirdClaimNo() {
		return ThirdClaimNo;
	}

	public void setThirdClaimNo(String thirdClaimNo) {
		ThirdClaimNo = thirdClaimNo;
	}

	public String getSecondClaimNo() {
		return SecondClaimNo;
	}

	public void setSecondClaimNo(String secondClaimNo) {
		SecondClaimNo = secondClaimNo;
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

}
