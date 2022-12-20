package com.pacificgeotech.epayTestFramework.scripts;

import java.sql.Connection;
import java.sql.ResultSet;
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

public class DISP26 {
	
	private WebDriver driver;
	private String ClaimNo;
	private String clientNumber;
	private String pid;
	private String pin;
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

		RAL ral = new RAL();
		ral.test();
		setClientNumber(ral.getClientNumber());
		setPin(ral.getPin());
		setPid(ral.getPid());
		setClaimNo(ral.getClaimNo());

		Thread.sleep(6000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();
		Thread.sleep(6000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		Thread.sleep(6000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();
		Thread.sleep(10000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));

		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(6000);
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")))
				.sendKeys(ral.getClaimNo());
		Thread.sleep(6000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		Thread.sleep(500);

		Thread.sleep(6000);
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")))
				.clear();

		Thread.sleep(6000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")))
				.sendKeys(ral.getClaimNo());

		Thread.sleep(4000);

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();

		// Record Lease
		Calendar calendar3 = Calendar.getInstance();
		calendar3.add(Calendar.DAY_OF_YEAR, 0);
		Date today3 = calendar3.getTime();
		DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
		String today = dateFormat3.format(today3);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("effectiveDateInput")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("effectiveDateInput")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("effectiveDateInput")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("effectiveDateInput")));
		element.sendKeys(today);

		Thread.sleep(1500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1500);
		
		String bodyText = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		Assert.assertTrue("Text not found!", bodyText.contains("Mining Right ID is required"));
		
		String bodyText1 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		Assert.assertTrue("Text not found!", bodyText1.contains("Tax/Rent Effective Date is required"));
		
		String bodyText2 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		Assert.assertTrue("Text not found!", bodyText2.contains("Land Area (Ha) is required"));
		
		String bodyText3 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		Assert.assertTrue("Text not found!", bodyText3.contains("PIN is required"));
		
		String bodyText4 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		Assert.assertTrue("Text not found!", bodyText4.contains("Survey Plan is required"));
		
		String bodyText5 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		Assert.assertTrue("Text not found!", bodyText5.contains("Short Legal Description is required"));
		
		String bodyText6 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		Assert.assertTrue("Text not found!", bodyText6.contains("Long Legal Description is required"));
		
		String bodyText7 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		Assert.assertTrue("Text not found!", bodyText7.contains("Mining Division is required"));
		
		String bodyText8 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		Assert.assertTrue("Text not found!", bodyText8.contains("Land Registry Office is required"));
		
		String bodyText9 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		Assert.assertTrue("Text not found!", bodyText9.contains("Lease Ownership is required"));
		
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

	public Long LeaseNo() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("SELECT MAM.MTA_TENURE_NUMBER_ID_SEQ.NEXTVAL AS MAXID FROM DUAL");
		ResultSet res = stmt.executeQuery(sql);
		Long maxTenureNumberIdDemisedTo = 0L;
		if (res.next()) {
			maxTenureNumberIdDemisedTo = res.getLong("MAXID");
		}
		res.close();
		con.commit();
		con.close();
		return maxTenureNumberIdDemisedTo;
		
	}

}
