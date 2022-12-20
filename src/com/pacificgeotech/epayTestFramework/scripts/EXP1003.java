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

import junit.framework.Assert;

public class EXP1003 {

	private String clientNumber;
	private String claimId;
	private static String pid;
	private static String pin;
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

	public static String getPid() {
		return pid;
	}

	public static void setPid(String pid) {
		EXP1003.pid = pid;
	}

	public static String getPin() {
		return pin;
	}

	public static void setPin(String pin) {
		EXP1003.pin = pin;
	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		
		MP5003 mp5003 = new MP5003();
		mp5003.test();
		setPid(mp5003.getPid());
		setPin(mp5003.getPin());
		setClientNumber(mp5003.getClientNumber());
		setClaimId(mp5003.getClaimId());

		// logout
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='header']/cata-header/div[1]/div[2]/ul[2]/li[3]/a/span[1]"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='header']/cata-header/div[1]/div[2]/ul[2]/li[3]/ul/li[3]/a/span"))); 
		 element.click();

		// PL1001 pl1001 = new PL1001();

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
			element.sendKeys(mp5003.getPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(mp5003.getPin());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();
		}

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Early Exploration Activities")));
		element.click();
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.linkText("Submit Plan/Permit for Early Exploration Activities")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys("1");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("disclaimerIndId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("createSubmissionBtnId")));
		element.click();
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(mp5003.getClientNumber());
		Thread.sleep(1500);
//		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("disclaimerIndId")));
//		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("createSubmissionBtnId")));
		element.click();
		new Select(element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedMethodSlctId")))).selectByVisibleText(
						"Inputting or pasting a list of comma delimited claim and/or tenure numbers that can be validated");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("tenureIds")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("tenureIds")));
		element.sendKeys("11");
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1500);
//		new WebDriverWait(driver, 50).until(ExpectedConditions
//				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Tenures IDs 11 don't exist')]")));
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Text not found!", bodyText.contains("Tenures IDs 11 don't exist"));
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("tenureIds")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("tenureIds")));
		element.sendKeys(mp5003.getClaimId());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(
				By.xpath("(//button[@type='button'])[2]")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("(//button[@type='button'])[2]")));
		element.click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.id("summarySubmissionBtnId")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("summarySubmissionBtnId")));
		element.click();
		// error messages
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Project Name is required')]")));
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'The Estimated Start Date must be a future date.')]")));
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'Qualified Supervisor MAAP ID is required')]")));
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Certification is required')]")));

	}

}