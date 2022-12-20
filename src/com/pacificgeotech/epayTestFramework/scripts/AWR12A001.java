package com.pacificgeotech.epayTestFramework.scripts;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;
import com.pacificgeotech.epayTestFramework.dbConnection.DbConnection;

public class AWR12A001 {
	
	private String pin;
	private String pid;
	private String clientNumber;

	private String claimNo;
	private WebDriver driver;

	
//	 @After public void tearDown() { driver.getCurrentUrl(); }
//	  
//	  @AfterClass public static void afterClass() { WebDriverManager.instance =
//	  null; }
	 

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		// Approve the created claim SRO
		// This script works based on client Number and claim ID created in
		// MP1001
		MP5007 mp5007 = new MP5007();
		mp5007.test();

		setClientNumber(mp5007.getClientNumber());
		setPin(mp5007.getPin());
		setPid(mp5007.getPid());
		setClaimNo(mp5007.getClaimNo());
		System.out.println("Claim no is: " + getClaimNo());
		updateAnivDate();
		workApplied();
		
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
			element.sendKeys(mp5007.getPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(mp5007.getPin());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();
		}
		
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

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='onlinePaymentSltId']")))
				.click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showSelectedPaymentId"))).click();
		
		MP1001 mp1001 = new MP1001();
		mp1001.makePayment(element, driverWait, driver);
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Payment In Place - Payment Confirmation')]")));
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), '2020')]")));
		
		
		
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

	public String getClaimNo() {
		return claimNo;
	}

	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	
	public void updateAnivDate() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("UPDATE MAM.MTA_TENURE SET ISSUE_DATE = ISSUE_DATE - 730  WHERE TENURE_NUMBER_ID = '" + getClaimNo()) + "'";
		stmt.executeUpdate(sql);
		con.commit();
		con.close();
		
	}
	

	public void workApplied() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("UPDATE MAM.MTA_TENURE SET TOTAL_WORK_APPLIED = 400  WHERE TENURE_NUMBER_ID = '" + getClaimNo()) + "'";
		stmt.executeUpdate(sql);
		con.commit();
		con.close();
		
	}
}
