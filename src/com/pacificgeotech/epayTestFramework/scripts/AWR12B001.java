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

public class AWR12B001 {
	
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
		Actions builder = new Actions(driver);
		MP5007 mp5007 = new MP5007();
		mp5007.test();
		setClientNumber(mp5007.getClientNumber());
		setPin(mp5007.getPin());
		setPid(mp5007.getPid());
		setClaimNo(mp5007.getClaimNo());
		setSecondClaimNo(mp5007.getSecondClaimNo());
		setThirdClaimNo(mp5007.getThirdClaimNo());
		
		
		AWR12A002 awr12a002 = new AWR12A002();
		awr12a002.updateAnivDate();
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Work Reporting")));
		element.click();
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Payment in Place")));
		element.click();
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(mp5007.getClientNumber());
		
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimsNumbersCsv")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimsNumbersCsv")));
		element.sendKeys(mp5007.getClaimNo());
		Thread.sleep(1500);
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1000);
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimPaymentAmountAtIdx0")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimPaymentAmountAtIdx0")));
		element.sendKeys("400");
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1000);
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1000);
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId"))).click();
		Thread.sleep(500);
		
		  element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("otcPaymentSltId"))); 
		  element.click();
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='showSelectedPaymentId']/span"))); 
		  element.click();
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))); 
		  element.clear();
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))); 
		  element.sendKeys("JOHN ARCHIBALD");
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("102825 JOHN ARCHIBALD"))); 
		  element.click();
		     new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeInput_0_id")))).selectByVisibleText("Cash");

		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentAmoutInput_0_id"))); 
		  element.click();
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentAmoutInput_0_id"))); 
		  element.clear();
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentAmoutInput_0_id"))); 
		  element.sendKeys("400");
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeNumber_0_id"))); 
		  element.click();
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeNumber_0_id"))); 
		  element.clear();
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeNumber_0_id"))); 
		  element.sendKeys("1");
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))); 
		  element.click();
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='nextToConfirmLnkId']/span")));
		  element.click();
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='nextToCfmPgIndexId']/span")));
		  element.click();
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid"))); 
		  element.click();
		  
		  new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"//*[contains(text(), 'You have successfully completed a payment in place of assessment work.')]")));
		  new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"//*[contains(text(), '2020')]")));
		
		
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
