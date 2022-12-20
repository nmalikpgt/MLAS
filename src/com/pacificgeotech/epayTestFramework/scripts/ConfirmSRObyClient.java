package com.pacificgeotech.epayTestFramework.scripts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

public class ConfirmSRObyClient {

	private String clientNumber;
	private String claimId;
	private String pin;
	private String pid;
	private WebDriver driver;

//	 @After
//	 public void tearDown() {
//	 driver.getCurrentUrl();
//	 }
//	
//	 @AfterClass
//	 public static void afterClass() {
//	 WebDriverManager.instance = null;
//	 }

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		// Approve the created claim SRO
		// This script works based on client Number and claim ID created in
		// MP1001

		MP5001 mp5001 = new MP5001();
		mp5001.test();
		setClientNumber(mp5001.getClientNumber());
		setClaimId(mp5001.getClaimId());
		setPin(mp5001.getPin());
		setPid(mp5001.getPid());
		Thread.sleep(1000);

		String[] urlInfo = LGN1001.getLoginUrl();
		LGN1001 lgn1001 = new LGN1001();
		Thread.sleep(1000);
		driver.get(urlInfo[0]);
		Thread.sleep(1000);
		if ("remote".equals(urlInfo[1])) {
			lgn1001.getLoginInfoExternalRemote(urlInfo[1], urlInfo[0]);
		} else {

			lgn1001.getLoginInfoExternalLocalTest(urlInfo[1], urlInfo[0], getPid(), getPin());

		}

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Claim Acquisition")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Confirm Notification to Surface Right Owners of Claim Registration")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(mp5001.getClientNumber());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element.sendKeys(mp5001.getClaimId());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoDetailButtonId")));
		element.click();

		// TODAY date

		Calendar calendar3 = Calendar.getInstance();
		calendar3.add(Calendar.DAY_OF_YEAR, 0);
		Date today3 = calendar3.getTime();
		DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
		String today = dateFormat3.format(today3);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"[class='form-control ng-pristine ng-untouched ng-isolate-scope ng-empty ng-valid-future-date-vdr ng-valid ng-valid-required ng-valid-date']")));
		element.clear();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"[class='form-control ng-pristine ng-untouched ng-isolate-scope ng-empty ng-valid-future-date-vdr ng-valid ng-valid-required ng-valid-date']")));
		element.sendKeys(today);

		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("noticeRequireId"))))
				.selectByVisibleText("No");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='N']")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("reasonCode")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("reasonCode"))))
				.selectByVisibleText("Unable to find most recent address");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option.ng-binding.ng-scope")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId")));
		element.click();
		Thread.sleep(2500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoConfirmButtonId")));
		element.click();

		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'Submission of the Confirm SRO Notification has been successful.')]")));
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

}
