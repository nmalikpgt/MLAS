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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;
import com.pacificgeotech.epayTestFramework.dbConnection.DbConnection;

public class MP1B006 {

	private WebDriver driver;
	private String clientNumber;
	private String pid;
	private String pin;
	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

/*	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}*/

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}

	@Test
	public void test() throws Exception {

		PL2001 pl2001 = new PL2001();
		pl2001.test();
		setClientNumber(pl2001.getClientNumber());
		setPin(pl2001.getPin());
		setPid(pl2001.getPid());
		// CommonUtils.login();
		registerMiningClaimInternal(pl2001);

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

	private void registerMiningClaimInternal(PL2001 pl2001)
			throws ClassNotFoundException, SQLException, InterruptedException {

		System.out.println("registerMiningClaimInternal method...");

		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		/**
		 * Register Mining Claim - Internal User
		 * 
		 **/
		updateCellLockTable();
		updateCellgridTable();

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Claim Acquisition"))).click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Register Mining Claim")))
				.click();
		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")))
				.sendKeys(pl2001.getClientNumber());
//
//		Thread.sleep(1000);
//		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("regMethodId"))))
//				.selectByVisibleText("Input or Generate Cell IDs");

		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'The registration of a mining claim or the acquisition of any right or interest in a mining claim by any person does not confer upon that person')]")));

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("declarationChkbox"))).click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

//		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("generationChkbox"))).click();
//		driverWait.until(ExpectedConditions.presenceOfElementLocated(
//				By.xpath("//div[@id='wid-id-0']/div/div/div/fieldset/div/table/tbody/tr/td[3]/input"))).clear();
//		driverWait
//				.until(ExpectedConditions.presenceOfElementLocated(
//						By.xpath("//div[@id='wid-id-0']/div/div/div/fieldset/div/table/tbody/tr/td[3]/input")))
//				.sendKeys("4");
//		driverWait.until(ExpectedConditions.presenceOfElementLocated(
//				By.xpath("//div[@id='wid-id-0']/div/div/div/fieldset/div/table/tbody/tr/td[5]/input"))).clear();
//		driverWait
//				.until(ExpectedConditions.presenceOfElementLocated(
//						By.xpath("//div[@id='wid-id-0']/div/div/div/fieldset/div/table/tbody/tr/td[5]/input")))
//				.sendKeys("43B03C235");
//		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Generate"))).click();
		
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element = driver.findElement(By.id("selectedGeoMapIds"));
		element.sendKeys("43B03C239,43B03C238,43B03C219,43B03C218");
	
		Thread.sleep(1500);

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoIdendityButtonId"))).click();
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("owneridInput_0"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("owneridInput_0")))
				.sendKeys(pl2001.getClientNumber());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("percentage_0"))).clear();
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("percentage_0"))).sendKeys("100");

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='wid-id-0']/div/div/div/table/tbody/tr[3]/td[3]/span/i"))).click();

		Thread.sleep(2000);
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Distribute ownership percentage equally")))
				.click();

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();

		Thread.sleep(1000);

		// To payment
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/a/span")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId"))).click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("otcPaymentSltId"))).click();
		Thread.sleep(1000);

		// To payment
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showSelectedPaymentId"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))).sendKeys("DARYL NEUSTATER");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("175221 DARYL NEUSTATER"))).click();

		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeInput_0_id"))))
				.selectByVisibleText("Cash");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("paymentAmount_0"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentAmoutInput_0_id"))).sendKeys("200");

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeNumber_0_id"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeNumber_0_id"))).sendKeys("1234");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='nextToConfirmLnkId']/span")))
				.click();

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='nextToCfmPgIndexId']/span")))
				.click();
		Thread.sleep(500);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid"))).click();
	}

	public void updateCellgridTable() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("UPDATE MAM_SPATIAL.MTA_NTS_GRID_CELL SET CELL_STATUS_CODE = 'A', CELL_REASON_CODE = 'N', CELL_REOPENING_DATE = CELL_REOPENING_DATE  - 1000  WHERE CELL_KEY_ID IN ('43B03C239','43B03C238','43B03C219','43B03C218')");
		stmt.executeUpdate(sql);
		con.commit();
		con.close();
	}

	public void updateCellLockTable() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("DELETE FROM MAM.MTA_GRID_LOCK_STATUS WHERE CELL_KEY_ID IN ('43B03C239','43B03C238','43B03C219','43B03C218')");
		stmt.executeQuery(sql);
		con.commit();
		con.close();
	}

}
