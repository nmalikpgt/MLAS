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

public class EXP1004 {

	private String pid;
	private String pin;
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

		CLM2C001 clm2c001 = new CLM2C001();
		clm2c001.test();
		setPid(clm2c001.getPid());
		setPin(clm2c001.getPin());
		setClientNumber(clm2c001.getClientNumber());
		setClaimId(clm2c001.getClaimId());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='header']/cata-header/div[1]/div[2]/ul[2]/li[3]/a/span[1]"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='header']/cata-header/div[1]/div[2]/ul[2]/li[3]/ul/li[3]/a/span"))); 
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
			element.sendKeys(clm2c001.getPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(clm2c001.getPin());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();
		}

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[10]/a[2]/b/em")));
		element.click();
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.linkText("Submit Plan/Permit for Early Exploration Activities")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(clm2c001.getClientNumber());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("disclaimerIndId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("createSubmissionBtnId")));
		element.click();
		new Select(element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedMethodSlctId")))).selectByVisibleText(
						"Inputting or pasting a list of comma delimited claim and/or tenure numbers that can be validated");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("claimIds")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("claimIds")));
		element.sendKeys(clm2c001.getClaimId());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'is not available because it is not currently active')]")));

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
