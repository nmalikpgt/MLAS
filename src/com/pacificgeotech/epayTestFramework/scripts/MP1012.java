package com.pacificgeotech.epayTestFramework.scripts;

import java.sql.Connection;
import java.sql.DriverManager;
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

public class MP1012 {

	private static String clientNumber;
	private String transactionId;
	private static String claimId;
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
		WebDriverManager.getElements();

		new PL1001().test();
		this.setClientNumber(new PL1001().getClientNumber());
		Thread.sleep(1500);
		updateCellLockTable();
		Thread.sleep(2000);

		updateCellgridTable();
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[7]/a[2]"))).click();

		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Register a Mining Claim")))
				.click();


		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'The registration of a mining claim or the acquisition of any right or interest in a mining claim by any person does not confer upon that person')]")));

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("declarationChkbox"))).click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element = driver.findElement(By.id("selectedGeoMapIds"));
		element.sendKeys(
				"43B03C233,43B03C213,43B03C254,43B03C174,43B03C195,43B03C217,43B03C260,43B03C194,43B03C258,43B03B221,43B03C252,43B03C172,43B03C234,43B03C256,43B03C236,43B03C237,43B03C218,43B03C219,43B03C240,43B03C180,43B03B201,43B03C177,43B03C259,43B03C192,43B03C215,43B03C196,43B03C239,43B03C179,43B03B241,43B03C232,43B03C193,43B03C214,43B03C216,43B03C257,43B03C197,43B03C198,43B03C212,43B03C176,43B03C199,43B03C220,43B03C200,43B03C253,43B03C173,43B03C255,43B03C235,43B03C175,43B03C238,43B03C178,43B03B181,43B03B161,43B03B242");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoIdendityButtonId"))).click();

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Please select max 50 cells')]")));

		Thread.sleep(1000);
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
				.click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out"))).click();

	}
		
		public void updateCellgridTable() throws ClassNotFoundException, SQLException {
			Connection con = DbConnection.getConnection();
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			String sql = ("UPDATE MAM_SPATIAL.MTA_NTS_GRID_CELL SET CELL_STATUS_CODE = 'A', CELL_REASON_CODE = 'N', CELL_REOPENING_DATE = CELL_REOPENING_DATE  - 1000  WHERE CELL_KEY_ID IN ('43B03C233,43B03C213,43B03C254,43B03C174,43B03C195,43B03C217,43B03C260,43B03C194,43B03C258,43B03B221,43B03C252,43B03C172,43B03C234,43B03C256,43B03C236,43B03C237,43B03C218,43B03C219,43B03C240,43B03C180,43B03B201,43B03C177,43B03C259,43B03C192,43B03C215,43B03C196,43B03C239,43B03C179,43B03B241,43B03C232,43B03C193,43B03C214,43B03C216,43B03C257,43B03C197,43B03C198,43B03C212,43B03C176,43B03C199,43B03C220,43B03C200,43B03C253,43B03C173,43B03C255,43B03C235,43B03C175,43B03C238,43B03C178,43B03B181,43B03B161,43B03B242')");
			stmt.executeUpdate(sql);
			con.commit();
			con.close();
	}

	private void updateCellLockTable() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("DELETE FROM MAM.MTA_GRID_LOCK_STATUS WHERE CELL_KEY_ID IN ('43B03C233,43B03C213,43B03C254,43B03C174,43B03C195,43B03C217,43B03C260,43B03C194,43B03C258,43B03B221,43B03C252,43B03C172,43B03C234,43B03C256,43B03C236,43B03C237,43B03C218,43B03C219,43B03C240,43B03C180,43B03B201,43B03C177,43B03C259,43B03C192,43B03C215,43B03C196,43B03C239,43B03C179,43B03B241,43B03C232,43B03C193,43B03C214,43B03C216,43B03C257,43B03C197,43B03C198,43B03C212,43B03C176,43B03C199,43B03C220,43B03C200,43B03C253,43B03C173,43B03C255,43B03C235,43B03C175,43B03C238,43B03C178,43B03B181,43B03B161,43B03B242')");
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

}
