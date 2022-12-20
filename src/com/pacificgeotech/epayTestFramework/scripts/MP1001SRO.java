package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;
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

public class MP1001SRO {

	private String clientNumber;
	private String transactionId;
	private String claimId;
	private String pin;
	private String pid;
	private WebDriver driver;

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

		PL1001 pl1001 = new PL1001();
		pl1001.test();
		this.setClientNumber(pl1001.getClientNumber());
		this.setPin(pl1001.getPin());
		this.setPid(pl1001.getPid());

		updateCellgridTable();
		updateCellLockTable();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[7]/a[2]"))).click();

		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Register a Mining Claim")))
				.click();

		Thread.sleep(1000);
	
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("declarationChkbox"))).click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element = driver.findElement(By.id("selectedGeoMapIds"));
		element.sendKeys("41M10L097,41M10L098,41M10L099");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoIdendityButtonId"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='MCMC']"))).click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("owneridInput_0")));
		element.sendKeys(clientNumber);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("percentage_0")));
		element.sendKeys("100");
		Thread.sleep(1500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1500);
		// To Summary page
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();
		Thread.sleep(1500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1500);
		// To payment
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='onlinePaymentSltId']")))
				.click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showSelectedPaymentId"))).click();

		makePayment(element, driverWait);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid")));
		element.click();

		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Claims have been registered successfully. You have 60 days to notify any surface rights owners which the PRO identifies as being required for any or all of your claims. If you fail to notify any required surface rights owners, your claims will automatically cancel on the 61st day.')]")));

		this.transactionId = driver
				.findElement(
						By.xpath("//*[@id='widget-grid']/div[1]/confirmation-message/div/div/table/tbody/tr[2]/td[3]"))
				.getText();
		this.claimId = driver.findElement(By.xpath("//*[@id='wid-id-0']/div/div/table/tbody/tr/td[1]")).getText();
		System.out.println("Claim ID: " + claimId);

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
				.click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out"))).click();

		String[] loginURL = LGN1001.getLoginUrl();
		driver.get(loginURL[2]); // for local Test
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		element.sendKeys("admin");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
		element.sendKeys("@MLAS4you");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();
		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[5]/a/span[1]")));

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[5]/a/span[1]")));
		element.click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(this.claimId);

		Thread.sleep(3000);
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		Thread.sleep(1000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("dropdownMenu1")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("System Administrator")));
		element.click();
			
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(this.claimId);

		Thread.sleep(3000);
		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();
		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("noticeRequireId"))))
				.selectByVisibleText("Yes");
		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='sroNumber']")))
				.sendKeys("1");

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("isnrSummarynextBtnId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		/*
		 * String bodyText = driver.findElement(By.tagName("body")).getText();
		 * Assert.assertTrue("Text not found!", bodyText.contains(
		 * "Submission to identify the presence of surface owners for notification has been successful"
		 * ));
		 */
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Submission to identify the presence of surface owners for notification has been successful.')]")));
		//driver.getPageSource().contains(
			//	"Submission to identify the presence of surface owners for notification has been successful.");

		// Confirm SRO as Internal User
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[9]/a[2]/span"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Confirm SRO Notification of Claim Registration"))).click();

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='clientselect']")));
		element.sendKeys(this.clientNumber);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("selectedCellIds")));
		element.sendKeys(this.claimId);

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoDetailButtonId"))).click();

		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String todayAsString = dateFormat.format(today);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/form/div/section/div[1]/article[2]/div/div/div/div/table/tbody/tr/td[2]/div[1]/input")));
		element.sendKeys("test1");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/form/div/section/div[1]/article[2]/div/div/div/div/table/tbody/tr/td[3]/div[1]/table/tbody/tr/td[1]/input")));
		element.sendKeys(todayAsString);

		/*element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/form/div/section/div[1]/article[2]/div/div/div/div/table/tbody/tr/td[2]/div[2]/input")));
		element.sendKeys("test2");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/form/div/section/div[1]/article[2]/div/div/div/div/table/tbody/tr/td[3]/div[2]/table/tbody/tr/td[1]/input")));
		element.sendKeys(todayAsString);*/
		
		  new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("noticeRequireId")))).selectByVisibleText("Yes");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoConfirmButtonId"))).click();

		driver.getPageSource().contains("Submission of the Confirm SRO Notification has been successful.");

		// search if claim is active
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[2]/a[2]"))).click();
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Search Claims"))).click();

		Thread.sleep(2000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Claim Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Claim Number']")).sendKeys(this.claimId);

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("[class='btn btn-labeled btn-primary']")))
				.click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		String actualString = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("html/body/div[2]/div[3]/div/div[3]/div/div/div[2]/table/tbody/tr/td[3]"))).getText();
		Assert.assertTrue(actualString.contains("Active"));

	}

	public void updateCellgridTable() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		Statement stmt = con.createStatement();
		String sql = ("UPDATE MAM_SPATIAL.MTA_NTS_GRID_CELL SET CELL_STATUS_CODE = 'A', CELL_REASON_CODE = 'N', CELL_REOPENING_DATE = CELL_REOPENING_DATE  - 2  WHERE CELL_KEY_ID IN ('41M10L097','41M10L098','41M10L099')");
		stmt.executeUpdate(sql);
		con.commit();
		con.close();
	}

	public void updateCellLockTable() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		Statement stmt = con.createStatement();
		String sql = ("DELETE FROM MAM.MTA_GRID_LOCK_STATUS WHERE CELL_KEY_ID IN ('43B03C239','43B03C238','43B03C219','43B03C218')");
		stmt.executeQuery(sql);
		con.commit();
		con.close();
	}

	private void makePayment(WebElement element, WebDriverWait driverWait) {
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("visa")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("submit")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardOwner")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardOwner")));
		element.sendKeys("test");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardNumber")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardNumber")));
		element.sendKeys("4030000010001234");
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnExpYear"))))
				.selectByVisibleText("2020");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardCvd")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardCvd")));
		element.sendKeys("123");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("submitButton")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("cancel")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='nextToCfmPgIndexBtnId']/span")));
		element.click();

	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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