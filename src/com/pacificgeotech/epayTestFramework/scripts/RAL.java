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

public class RAL {

	private String clientNumber;

	private String claimNo;
	private WebDriver driver;
	private String pid;
	private String pin;

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

	@After public void tearDown() { driver.getCurrentUrl(); }
	  
	  @AfterClass public static void afterClass() { WebDriverManager.instance =
	  null; }
	 

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
		setClaimNo(mp5007.getClaimNo());
		setPin(mp5007.getPin());
		setPid(mp5007.getPid());
		System.out.println("Claim no is: " + getClaimNo());
		updateAnivDate();
		workApplied();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Claim Management")));
		element.click();

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Record Application for Lease")));
		element.click();
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("clientselect")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(mp5007.getClientNumber());
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.btn.btn-default")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[46]")));
		element.click();

		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("leaseTypeAtIdx0"))))
				.selectByVisibleText("Mining Rights");
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[@name='singleClaimLeaseAtIdx0' and @value='true']")))
				.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimIdsCsvAtIdx0")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimIdsCsvAtIdx0")));
		element.sendKeys(mp5007.getClaimNo());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("leaseApplicationFeePaid")));
		element.click();

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("totalRentalAmountForFirstYearPaid")));
		element.clear();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("totalRentalAmountForFirstYearPaid")));
		element.sendKeys("400");

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

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("comments")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("comments")));
		element.sendKeys(
				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis p");
		
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
		.selectByVisibleText("Claimholder Consent");
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(2500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Application for lease was recorded successfully')]")));
		
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
		String sql = ("UPDATE MAM.MTA_TENURE SET ISSUE_DATE = ISSUE_DATE - 730  WHERE TENURE_NUMBER_ID = '"
				+ getClaimNo()) + "'";
		stmt.executeUpdate(sql);
		con.commit();
		con.close();

	}

	public void workApplied() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("UPDATE MAM.MTA_TENURE SET TOTAL_WORK_APPLIED = 2000  WHERE TENURE_NUMBER_ID = '" + getClaimNo())
				+ "'";
		stmt.executeUpdate(sql);
		con.commit();
		con.close();
	}

}
