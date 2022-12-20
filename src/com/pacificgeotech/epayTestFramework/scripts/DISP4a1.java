package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
import com.pacificgeotech.epayTestFramework.dbConnection.DbConnection;

import junit.framework.Assert;

public class DISP4a1 {

	private WebDriver driver;
	private String ClaimNo;
	private String clientNumber;
	private String pid;
	private String pin;
	private String LeaseNo;

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
		WebDriverManager.getElements();

		ARAL aral = new ARAL();
		aral.test();
		setClientNumber(aral.getClientNumber());
		setPin(aral.getPin());
		setPid(aral.getPid());
		setClaimNo(aral.getClaimNo());
		setLeaseNo(aral.getLeaseNo());

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
				.click();
		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out"))).click();

//		String[] loginInfo = LGN1001.getLoginUrl();
//		new LGN1001().switchLocalRemote(loginInfo[1], loginInfo[0], getPid(), getPin());

		
		new LGN1002().test();
		// DB update for Lease renewal

		updateLeaseStatus();
		Thread.sleep(1000);

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Disposition Management")));
		element.click();

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Submit Application to Renew Lease")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(aral.getClientNumber());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningRightId0")));
		element.click();

		Thread.sleep(1000);

		Object file1 = null;
		try {
			file1 = new File(
					CLM2B003.class.getClassLoader().getResource("Claim History Report (106090) (1).pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file1).exists());
		WebElement browseButton1 = driver.findElement(By.name("fileInput"));
		browseButton1.sendKeys(((File) file1).getAbsolutePath());

		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
				.selectByVisibleText("Parcel Register Identification Number (PIN) from LRO");
		Thread.sleep(1500);

		Object file2 = null;
		try {
			file2 = new File(
					CLM2B003.class.getClassLoader().getResource("Claim History Report (106090) (1).pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file2).exists());
		WebElement browseButton2 = driver.findElement(By.name("fileInput"));
		browseButton2.sendKeys(((File) file2).getAbsolutePath());

		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("docTypeCode_1"))))
				.selectByVisibleText("Renewal Report");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnPayNowId")));
		element.click();
		Thread.sleep(500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("onlinePaymentSltId")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='showSelectedPaymentId']/span")));
		element.click();
		
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		
		(new MP1001()).makePayment(element, driverWait, driver);
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid")));
		element.click();
		
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Application for lease renewal was submitted successfully')]")));

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

	public String getLeaseNo() {
		return LeaseNo;
	}

	public void setLeaseNo(String leaseNo) {
		LeaseNo = leaseNo;
	}

	public void updateLeaseStatus() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("UPDATE MAM.MTA_TENURE SET TENURE_STATUS_CODE = 'PR' WHERE TENURE_NUMBER_ID = '" + getLeaseNo())
				+ "'";
		stmt.executeUpdate(sql);
		con.commit();
		con.close();

	}

}
