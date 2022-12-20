package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
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

public class DISP712345 {

	private WebDriver driver;
	private String clientNumber;
	private String LeaseNo;
	private Long NewLeaseNo;

//	@After
//	public void tearDown() {
//		driver.getCurrentUrl();
//	}
//
//	@AfterClass
//	public static void afterClass() {
//		WebDriverManager.instance = null;
//	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		DISP61234 disp61234 = new DISP61234();
		disp61234.test();
		setClientNumber(disp61234.getClientNumber());
		setLeaseNo(disp61234.getLeaseNo());

		Thread.sleep(4000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(4000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();
		Thread.sleep(1000);

		WebElement appElement = null;
		try {
			element = driverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
			element.click();
			element = driverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
			element.clear();
			Thread.sleep(2000);
			element = driverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
			element.sendKeys(disp61234.getLeaseNo());
			Thread.sleep(2000);
			appElement = driverWait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//*[contains(text(), 'Lease Number: " + disp61234.getLeaseNo() + "')]")));

		} catch (Throwable e) {
		}
		while (appElement == null || !appElement.isDisplayed()) {
			System.out.println("is looping");
			try {
				element = driverWait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
				element.click();
				element = driverWait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
				element.clear();
				Thread.sleep(2000);
				element = driverWait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
				element.sendKeys(disp61234.getLeaseNo());
				Thread.sleep(2000);
				appElement = driverWait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//*[contains(text(), 'Lease Number: " + disp61234.getLeaseNo() + "')]")));

			} catch (Throwable e) {
				appElement = null;
			}
		}

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'Lease Renewal Application Assessment')]")));
		Thread.sleep(1000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		element.clear();
		Thread.sleep(1000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		element.sendKeys(disp61234.getLeaseNo());

		Thread.sleep(1000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		Thread.sleep(1000);
		// assign to all
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("System Administrator")));
		element.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(disp61234.getLeaseNo());

		Thread.sleep(4000);

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();
		
		Long maxId = NewLeaseNo();
		Thread.sleep(1000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("miningRightId")));
		element.clear();
		
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("miningRightId")));
		element.sendKeys(String.valueOf(maxId));
		System.out.println("New Mining right ID is: " + maxId);
		
		setNewLeaseNo(maxId);
		
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
				.selectByVisibleText("Lease Renewal Document");
		Thread.sleep(1500);
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Lease renewal has been recorded successfully')]")));

	}

	public Long getNewLeaseNo() {
		return NewLeaseNo;
	}

	public void setNewLeaseNo(Long newLeaseNo) {
		NewLeaseNo = newLeaseNo;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getLeaseNo() {
		return LeaseNo;
	}

	public void setLeaseNo(String leaseNo) {
		LeaseNo = leaseNo;
	}
	
	public Long NewLeaseNo() throws ClassNotFoundException, SQLException {
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
	

