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

public class RejectRenewalOfLease {

	private WebDriver driver;
	private String clientNumber;
	private String LeaseNo;
	private String pid;
	private String pin;

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

		DISP4a1 disp4a1 = new DISP4a1();
		disp4a1.test();
		setClientNumber(disp4a1.getClientNumber());
		setLeaseNo(disp4a1.getLeaseNo());
		setPin(disp4a1.getPin());
		setPid(disp4a1.getPid());

		LGN1002 lgn1002 = new LGN1002();
		lgn1002.test();

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
			Thread.sleep(1000);
			element = driverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
			element.sendKeys(disp4a1.getLeaseNo());
			Thread.sleep(1000);
			appElement = driverWait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//*[contains(text(), 'Lease Number: " + disp4a1.getLeaseNo() + "')]")));

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
				Thread.sleep(1000);
				element = driverWait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
				element.sendKeys(disp4a1.getLeaseNo());
				Thread.sleep(1000);
				appElement = driverWait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//*[contains(text(), 'Lease Number: " + disp4a1.getLeaseNo() + "')]")));

			} catch (Throwable e) {
				appElement = null;
			}
		}

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Lease Renewal Application Assessment')]")));
		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		element.clear();
		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		element.sendKeys(disp4a1.getLeaseNo());

		Thread.sleep(4000);
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
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(disp4a1.getLeaseNo());

		Thread.sleep(4000);

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();

		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("initialReviewAcceptedIndInputId")));
		// element.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//input[@name='initialReviewAcceptedIndInput' and @value='refused']"))).click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("initialReviewCommentId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("initialReviewCommentId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("initialReviewCommentId")));
		element.sendKeys("test");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'The application for renewal of Mining Lease has been assessed successfully')]")));
		
		checkStatus();

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
	public void checkStatus() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("select TENURE_STATUS_CODE FROM MAM.MTA_TENURE WHERE TENURE_NUMBER_ID = '" + getLeaseNo() + "' AND TENURE_STATUS_CODE = 'NR'");
		stmt.executeQuery(sql);
		System.out.println("Status is : " + sql);
		con.commit();
		con.close();
		
	}

}
