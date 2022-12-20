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

public class CM9005 {

	private String clientNumber;
	private String claimId;
	private String jointClient;
	private WebDriver driver;


	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}

	public String getJointClient() {
		return jointClient;
	}

	public void setJointClient(String jointClient) {
		this.jointClient = jointClient;
	}

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

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		CM9001 cm9001 = new CM9001();
		cm9001.test();
		setClientNumber(cm9001.getClientNumber());
		setClaimId(cm9001.getClaimId());
		setJointClient(cm9001.getJointClient());
		System.out.println("client is: " + cm9001.getClientNumber());

		// Acquire another claim with the same user
		updateCellgridTable();
		updateCellLockTable();

		Thread.sleep(3000);
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Claim Acquisition")))
				.click();
		Thread.sleep(1000);
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"left-panel\"]/navigation-widget/div/nav/ul/li[9]/ul/li[1]/a")))
				.click();

		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'The registration of a mining claim or the acquisition of any right or interest in a mining claim by any person does not confer upon that person')]")));

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder=\"Enter Submitter Id\"]")));
		element.sendKeys(clientNumber);
		Thread.sleep(1500);

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("declarationChkbox"))).click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element = driver.findElement(By.id("selectedGeoMapIds"));
		element.sendKeys("42M16A189");

		// add owner

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoIdendityButtonId"))).click();
		/*
		 * new WebDriverWait(driver,
		 * 50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//input[@value='MCMC']")));
		 * 
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath
		 * ("//input[@value='MCMC']"))).click();
		 */
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.presenceOfElementLocated(By
						.xpath("/html/body/div[2]/div[3]/form/div/section/div[1]/article[4]/div/div/div/div/table/tbody/tr[1]/td[1]/div[1]/input")))
				.sendKeys(cm9001.getClientNumber());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/form/div/section/div[1]/article[4]/div/div/div/div/table/tbody/tr[1]/td[2]/input")));
		element.sendKeys("100");
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		// To Summary page
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();
		Thread.sleep(1000);
		// To payment
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId"))).click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		// new WebDriverWait(driver,
		// 50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='onlinePaymentSltId']")));
		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("otcPaymentSltId"))).click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showSelectedPaymentId"))).click();

		// pay over the counter

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput")))
				.sendKeys(cm9001.getClientNumber());
		Thread.sleep(3000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='search_client_list col-md-12']")));
		element.click();
		Thread.sleep(1000);
		new Select(
				element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeInput_0_id"))))
						.selectByVisibleText("Cash");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentAmoutInput_0_id")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentAmoutInput_0_id")));
		element.sendKeys("50.00");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeNumber_0_id")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeNumber_0_id")));
		element.sendKeys("1001");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextToConfirmLnkId")));
		element.click();
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextToCfmPgIndexId")));
		element.click();
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid")));
		element.click();
		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Claims have been registered successfully. You have 60 days to notify any surface rights owners which the PRO identifies as being required for any or all of your claims. If you fail to notify any required surface rights owners, your claims will automatically cancel on the 61st day.')]")));

		// this.transactionId =
		// driver.findElement(By.xpath("//*[@id='widget-grid']/div[1]/confirmation-message/div/div/table/tbody/tr[2]/td[3]")).getText();
		String claimIdNumber;
		claimIdNumber = driver.findElement(By.id("claim_id_0")).getText();

		setClaimId(claimIdNumber);
		System.out.println("Claim ID: " + getClaimId());

		// add submiter
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage Joint Tenants")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(getClientNumber());
		Thread.sleep(2000);
		// element = driverWait.until(
		// ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='search_client_list']")));
		// element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput")));
		element.sendKeys(cm9001.getJointClient());

		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='search_client_list col-md-12']")));
		element.click();
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimNumberId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimNumberId")));
		element.sendKeys(getClaimId());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'New Joint Tenant has been added successfully.')]")));

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage Joint Tenants")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(cm9001.getClientNumber());
		Thread.sleep(500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		Thread.sleep(500);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span > button.ng-binding")));
		element.click();
		Thread.sleep(500);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[2]")));
		element.click();
		Thread.sleep(500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("confirmBtnId")));
		element.click();
		Thread.sleep(500);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("nextBtnId")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'Joint Tenant has been deleted successfully.')]")));

		// Search
		// search if claim is active pending
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[2]/a[2]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Search Claims"))).click();

		Thread.sleep(2000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Claim Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Claim Number']")).sendKeys(cm9001.getClaimId());

		driverWait
		.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("[class='btn btn-labeled btn-primary']")))
		.click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("tenureNumberLnkId"))).click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'Joint Tenancy Ownership Transferred to Single Ownership')]")));

	}

	public void updateCellgridTable() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("UPDATE MAM_SPATIAL.MTA_NTS_GRID_CELL SET CELL_STATUS_CODE = 'A', CELL_REASON_CODE = 'N', CELL_REOPENING_DATE = CELL_REOPENING_DATE  - 2  WHERE CELL_KEY_ID = '42M16A189'");
		stmt.executeUpdate(sql);
		con.commit();
		con.close();
	}

	public void updateCellLockTable() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("DELETE FROM MAM.MTA_GRID_LOCK_STATUS WHERE CELL_KEY_ID = '42M16A189'");
		stmt.executeQuery(sql);
		con.commit();
		con.close();
	}

}