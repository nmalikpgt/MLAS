package com.pacificgeotech.epayTestFramework.scripts;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.pacificgeotech.epayTestFramework.core.CommonUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;
import com.pacificgeotech.epayTestFramework.dbConnection.DbConnection;

import junit.framework.Assert;

public class MP1001 {

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

		PL1001 pl1001 = new PL1001();
		pl1001.test();
		setClientNumber(pl1001.getClientNumber());

		setPin(pl1001.getPin());
		setPid(pl1001.getPid());
		Thread.sleep(1500);
		updateCellLockTable();

		Thread.sleep(4000);
		updateCellgridTable();

/*


		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Claim Acquisition")))
				.click();
		Thread.sleep(1000);
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Register a Mining Claim")))
				.click();

//		Thread.sleep(1000);
//		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("regMethodId"))))
//				.selectByVisibleText("Input or Generate Cell IDs");

		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'The registration of a mining claim or the acquisition of any right or interest in a mining claim by any person does not confer upon that person')]")));

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("declarationChkbox"))).click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();
*/


		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element = driver.findElement(By.id("selectedGeoMapIds"));
		element.sendKeys("43B03C239,43B03C238,43B03C219,43B03C218");
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1500);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id=\"gotoIdendityButtonId\"]")));
		element.click();
		Thread.sleep(1000);

	//System.out.println(	driver.findElement(By.xpath("//input[@ng-model=\"formData.claimTyp\"]")).isEnabled());

	//System.out.println(	driver.findElement(By.xpath("//input[@value='MCMC']")).isSelected());

	//System.out.println(	driver.findElement(By.xpath("//input[@value='MCMC']")).isDisplayed());
	//	driver.findElement(By.xpath("//input[@value='MCMC']")).click();

		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.presenceOfElementLocated(By
						.xpath("//input[@value='MCMC']")))
				.click();


	/*	WebDriverWait wait = new WebDriverWait(this.driver, 5);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				WebElement button = driver.findElement(By.xpath("//input[@value='MCMC']"));
				String enabled = button.getAttribute("unselectable");
				return enabled.equals("off");
			}
		});

*/
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.presenceOfElementLocated(By
						.xpath("/html/body/div[2]/div[3]/form/div/section/div[1]/article[4]/div/div/div/div/table/tbody/tr[1]/td[1]/div[1]/input")))
				.sendKeys(this.clientNumber);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/form/div/section/div[1]/article[4]/div/div/div/div/table/tbody/tr[1]/td[2]/input")));
		element.sendKeys("100");
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		// To Summary page
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();
		// To payment
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1500);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm"))).click();



		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId")));
		element.click();
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("otcPaymentSltId")));
		element.click();




		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showSelectedPaymentId"))).click();

		makePayment(element, driverWait, driver);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid")));
		element.click();

		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Claims have been registered successfully. You have 60 days to notify any surface rights owners which the PRO identifies as being required for any or all of your claims. If you fail to notify any required surface rights owners, your claims will automatically cancel on the 61st day.')]")));

		transactionId = driver
				.findElement(
						By.xpath("//*[@id='widget-grid']/div[1]/confirmation-message/div/div/table/tbody/tr[2]/td[3]"))
				.getText();

		String claimIdNumber;
		claimIdNumber = driver.findElement(By.id("claim_id_0")).getText();

		setClaimId(claimIdNumber);
		System.out.println("Claim ID: " + getClaimId());

		// search if claim is active pending
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[2]/a[2]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Search Claims"))).click();

		Thread.sleep(2000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Claim Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Claim Number']")).sendKeys(getClaimId());

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("[class='btn btn-labeled btn-primary']")))
				.click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		String actualString = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[@id='tenureSearchTblId']/tbody/tr/td[3]"))).getText();
		Assert.assertTrue(actualString.contains("Active Pending"));
		
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
				.click();

		Thread.sleep(2000);
	//	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"header\"]/cata-header/div[1]/div[2]/ul[2]/li[3]/ul/li[3]/a"))).click();

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

	public void makePayment(WebElement element, WebDriverWait driverWait, WebDriver driver) {
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
		new WebDriverWait(driver, 50).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nextToCfmPgIndexBtnId']/span")));
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

	public String getClaimId() {
		return this.claimId;
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
