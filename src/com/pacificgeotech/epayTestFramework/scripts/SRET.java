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

public class SRET {

	private String clientNumber;

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

	private String claimId;
	private String pin;
	private String pid;
	private String PermitId;
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
		
		

		Permit Permit = new Permit();
		Permit.test();
		setClientNumber(Permit.getClientNumber());
		setClaimId(Permit.getClaimId());
		setPin(Permit.getPin());
		setPid(Permit.getPid());
		setPermitId(Permit.getPermitId());
		
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
			element.sendKeys(Permit.getPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(Permit.getPin());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();
		}
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Dashboard')]")))
				.getText();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Claim Management"))).click();
		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Submit Request for Exclusion of Time")))
				.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(Permit.getClientNumber());

		anivDateUpdate();
		updatePermitStatus();		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("permitInputId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("permitInputId")));
		element.sendKeys(Permit.getPermitId());
		new Select(element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedMethodSlctId")))).selectByVisibleText(
						"Inputting or pasting a list of comma delimited claim and/or tenure numbers that can be validated");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedClaimsId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedClaimsId")));
		element.sendKeys(Permit.getClaimId());
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(500);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'Request for exclusion of time has been submitted successfully.')]")));

	}

	public String getPermitId() {
		return PermitId;
	}

	public void setPermitId(String permitId) {
		PermitId = permitId;
	}
	
	public void anivDateUpdate() throws ClassNotFoundException, SQLException {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_YEAR, 10);
		Date today1 = calendar1.getTime();
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		String anivDateSet = dateFormat1.format(today1);
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("update MAM.MTA_TENURE set ANNIVERSARY_DATE = TO_DATE('" + anivDateSet + "', 'YYYY-MM-DD') WHERE TENURE_number_id = '" + getClaimId() + "'");
		stmt.executeQuery(sql);
		con.commit();
		con.close();
		
	}
	
	public void updatePermitStatus() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("UPDATE MAM.MTA_EARLY_EXPLORATION SET EARLY_EXP_STATUS_CODE='TPHD' WHERE EARLY_EXPLORATION_NUMBER='" + getPermitId() + "'");
		stmt.executeQuery(sql);
		con.commit();
		con.close();
		
	}

}
