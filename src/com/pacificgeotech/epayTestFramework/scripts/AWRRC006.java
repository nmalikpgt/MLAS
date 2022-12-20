package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;

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

import junit.framework.Assert;

public class AWRRC006 {
	
	private String pin;
	private String pid;
	private String clientNumber;

	private String claimId;
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
		AWR2A006 awr2a006 = new AWR2A006();
		awr2a006.test();

		setClientNumber(awr2a006.getClientNumber());
		setPin(awr2a006.getPin());
		setPid(awr2a006.getPid());
		setClaimId(awr2a006.getClaimId());

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/a/span")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span")));
		element.click();

	LGN1002 lgn1002 = new LGN1002();
	lgn1002.test();
	
	element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
	element.click();

	Thread.sleep(2000);
	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

	Thread.sleep(2000);
	element = driverWait.until(
			ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
	element.clear();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(awr2a006.getClaimId());

	Thread.sleep(4000);
	new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
	element = driverWait
			.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
	element.click();
	// assign to all
	element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Geoscience Assessor")));
	element.click();
	Thread.sleep(4000);
	driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

	Thread.sleep(4000);
	element = driverWait.until(
			ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
	driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(awr2a006.getClaimId());

	Thread.sleep(4000);

	driverWait
			.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
			.click();
	Thread.sleep(1000);
	 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]"))); 
	 element.click();
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("rejectRationaleInputId"))); 
	 element.clear();
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("rejectRationaleInputId"))); 
	 element.sendKeys("not enough supporting evidence for work performed");
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[4]"))); 
	 element.click();
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("GEOLsurveyTypeRejectRationale0"))); 
	 element.clear();
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("GEOLsurveyTypeRejectRationale0"))); 
	 element.sendKeys("not enough supporting evidence for work performed");
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[6]"))); 
	 element.click();
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeRejectRationaleInput0"))); 
	 element.clear();
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeRejectRationaleInput0"))); 
	 element.sendKeys("not enough supporting evidence for work performed");
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[8]"))); 
	 element.click();
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("consultationCostRejectRationaleInput0"))); 
	 element.clear();
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("consultationCostRejectRationaleInput0"))); 
	 element.sendKeys("not enough supporting evidence for work performed");
	 Object file = null;
		try {
			file = new File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file).exists());
		WebElement browseButton = driver.findElement(By.name("fileInput"));
		browseButton.sendKeys(((File) file).getAbsolutePath());

	    new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode")))).selectByVisibleText("Rejection Letter");
	
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningRightRevisedExplorationExpensesInputId"))); 
	 element.clear();
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningRightRevisedExplorationExpensesInputId"))); 
	 element.sendKeys("0");
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningRightRevisedConsultationExpensesInputId"))); 
	 element.clear();
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningRightRevisedConsultationExpensesInputId"))); 
	 element.sendKeys("0");
	 driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reviewOutcomeRequiresInspectionIndInput' and @value='false']"))).click();
	 driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reviewOutcomeRequiresNoticeIndInput' and @value='false']"))).click();
	 
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
	 element.click();
	 Thread.sleep(2500);
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
	 element.click();
	 Thread.sleep(2000);
	
		
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

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

}
