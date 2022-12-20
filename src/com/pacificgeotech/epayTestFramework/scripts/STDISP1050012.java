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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;
import com.pacificgeotech.epayTestFramework.dbConnection.DbConnection;

import junit.framework.Assert;

public class STDISP1050012 {
	private WebDriver driver;
	private String clientNumber;
	private String AccountNo;

	public String getLeaseNo() {
		return LeaseNo;
	}

	public void setLeaseNo(String leaseNo) {
		LeaseNo = leaseNo;
	}

	private String LeaseNo;

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

		ARAL aral = new ARAL();
		aral.test();
		setLeaseNo(aral.getLeaseNo());
		setClientNumber(aral.getClientNumber());
		//addDisposition(aral.getLeaseNo());
		setAccountNo(aral.getAccountNo());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Disposition Management"))).click();
		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Record Transfer of Mining Land")))
				.click();
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("accountNumber")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("accountNumber")));
		element.sendKeys(aral.getAccountNo());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("lroRegistrationConfirmation0")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("lroRegistrationConfirmation0")));
		element.sendKeys("4545");
		Thread.sleep(1500);
		element.sendKeys(Keys.DELETE);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
			"//*[@id='billingPartyAtIdx0']//input")));
		element.clear();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[@id='billingPartyAtIdx0']//input")));
		element.sendKeys(aral.getClientNumber());
		Thread.sleep(1500);

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
				.selectByVisibleText("Registered Transfer Document from LRO");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'You have successfully completed a Transfer Mining Land')]")));
		Thread.sleep(1000);
		String bodyText = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		Assert.assertTrue("Text not found!", bodyText.contains("Event ID:"));
		String bodyText1 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		Assert.assertTrue("Text not found!", bodyText1.contains("Transaction ID:"));

	}

	public String getAccountNo() {
		return AccountNo;
	}

	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public void addDisposition(String val1) throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("INSERT INTO MAM.MTA_DISPOSITION_SUBACCOUNT (TENURE_NUMBER_ID, DISPOSITION_ACCOUNT_NUM, DISPOSITION_SUBACCOUNT_NUM)  VALUES ('"
				+ val1 + "', 'T***0887' , '0091')");
		stmt.executeUpdate(sql);
		con.commit();
		con.close();

	}
}