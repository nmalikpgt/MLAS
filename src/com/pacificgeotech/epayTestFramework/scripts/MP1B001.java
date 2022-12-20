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

public class MP1B001 {

	private String clientNumberId;
	private String cellId;
	private String pin;
	private String pid;
	private String claimId;
	private WebDriver driver;

	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		PL2001 pl2001 = new PL2001();
		pl2001.test();
		setPid(pl2001.getPid());
		setPin(pl2001.getPin());

		MP1001 mp1001 = new MP1001();
		mp1001.updateCellLockTable();
		mp1001.updateCellgridTable();

		updateCellgridTable();
		updateCellLockTable();

		System.out.println("Client number ID: " + pl2001.getClientNumber());
		setClientNumberId(pl2001.getClientNumber());

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[9]/a[2]"))).click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Register Mining Claim")))
				.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")))
				.sendKeys(pl2001.getClientNumber());

		Thread.sleep(1000);
	
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("declarationChkbox"))).click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds"))).clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element = driver.findElement(By.id("selectedGeoMapIds"));
		element.sendKeys("43B03C222,43B03C223,43B03C224,43B03C225");
		setCellId("43B03C222");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoIdendityButtonId"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("owneridInput_0"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("owneridInput_0")))
				.sendKeys(pl2001.getClientNumber());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("percentage_0"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("percentage_0"))).sendKeys("100");

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
		Thread.sleep(1000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.id(
				"shoppingCartNextId")));
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId"))).click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("otcPaymentSltId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showSelectedPaymentId"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))).sendKeys("412901");
		Thread.sleep(1500);
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='search_client_list col-md-12']")))
				.click();

		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("412908
		// DARYL SMITH"))).click();

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
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid"))).click();

		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Claims have been registered successfully. You have 60 days to notify any surface rights owners which the PRO identifies as being required for any or all of your claims. If you fail to notify any required surface rights owners, your claims will automatically cancel on the 61st day.')]")));

		String claimIdNumber;
		claimIdNumber = driver.findElement(By.xpath("//*[@id='wid-id-0']/div/div/table/tbody/tr/td[2]")).getText();

		setClaimId(claimIdNumber);
		System.out.println("Claim ID: " + getClaimId());
	}

	public void updateCellgridTable() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		Statement stmt = con.createStatement();
		String sql = ("UPDATE MAM_SPATIAL.MTA_NTS_GRID_CELL SET CELL_STATUS_CODE = 'A', CELL_REASON_CODE = 'N', CELL_REOPENING_DATE = CELL_REOPENING_DATE  - 2  WHERE CELL_KEY_ID IN ('43B03C222','43B03C223','43B03C224','43B03C225')");
		stmt.executeUpdate(sql);
		con.commit();
		con.close();
	}

	public void updateCellLockTable() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		Statement stmt = con.createStatement();
		String sql = ("DELETE FROM MAM.MTA_GRID_LOCK_STATUS WHERE CELL_KEY_ID IN ('43B03C222','43B03C223','43B03C224','43B03C225')");
		stmt.executeQuery(sql);
		con.commit();
		con.close();

	}

	public String getClientNumberId() {
		return this.clientNumberId;
	}

	public void setClientNumberId(String clientNumberId) {
		this.clientNumberId = clientNumberId;
	}

	public String getCellId() {
		return cellId;
	}

	public void setCellId(String cellId) {
		this.cellId = cellId;
	}

	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
