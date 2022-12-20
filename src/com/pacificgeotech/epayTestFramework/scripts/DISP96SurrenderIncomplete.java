package com.pacificgeotech.epayTestFramework.scripts;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
import com.pacificgeotech.epayTestFramework.dbConnection.DbConnection;

import junit.framework.Assert;

public class DISP96SurrenderIncomplete {

	private WebDriver driver;

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

		//addDisposition(aral.getLeaseNo());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Disposition Management"))).click();
		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Record Surrender of Mining Land")))
				.click();
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("tenureId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("tenureId")));
		element.sendKeys(aral.getLeaseNo());

		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		Thread.sleep(1500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1500);

		String bodyText = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		Assert.assertTrue("Text not found!", bodyText.contains("Re-Open Status is Required"));
		String bodyText1 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		Assert.assertTrue("Text not found!", bodyText1.contains("LRO Registration Date is Required"));
		String bodyText2 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		Assert.assertTrue("Text not found!", bodyText2.contains("LRO Instrument Number is Required"));

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("selectReOpenStatus")));
		element.click();
		new Select(
				element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("selectReOpenStatus"))))
						.selectByVisibleText("Impending open");

		Calendar calendar12 = Calendar.getInstance();
		calendar12.add(Calendar.DAY_OF_YEAR, 0);
		Date today12 = calendar12.getTime();
		DateFormat dateFormat12 = new SimpleDateFormat("yyyy-MM-dd");
		String today = dateFormat12.format(today12);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lroRegistrationDate")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lroRegistrationDate")));
		element.sendKeys(today);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("lroInstrumentNumber")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("lroInstrumentNumber")));
		element.sendKeys("123");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1500);

		String bodyText4 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		Assert.assertTrue("Text not found!", bodyText4.contains(
				"The following supporting documents are mandatory for 'lease' mining lands: 'Parcel Register from LRO', 'Affidavit', and 'Registered Notice of Determination'."));

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
