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

public class EXP1005 {
	private String pin;
	private String pid;
	private String clientNumber;
	private String transactionId;
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

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[7]/a[2]"))).click();

		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Register a Mining Claim - External Client")))
				.click();

		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("regMethodId"))))
				.selectByVisibleText("Input or Generate Cell IDs");

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
		element.sendKeys("42M02H095,42M02H096,42M02H097");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoIdendityButtonId"))).click();
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='SCMC']")));

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='SCMC']"))).click();

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
		Thread.sleep(1000);
		// To payment
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='onlinePaymentSltId']")))
				.click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showSelectedPaymentId"))).click();

		makePayment(element, driverWait, driver);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid")));
		element.click();

		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Claims have been registered successfully. You have 60 days to notify any surface rights owners which the PRO identifies as being required for any or all of your claims. If you fail to notify any required surface rights owners, your claims will automatically cancel on the 61st day.')]")));

		this.transactionId = driver
				.findElement(
						By.xpath("//*[@id='widget-grid']/div[1]/confirmation-message/div/div/table/tbody/tr[2]/td[3]"))
				.getText();
		String claimIdNumber1;
		claimIdNumber1 = driver.findElement(By.id("claim_id_0")).getText();
		String claimIdNumber2;
		claimIdNumber2 = driver.findElement(By.id("claim_id_2")).getText();

		// make active
		Thread.sleep(1000);
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
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By
		// .xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[5]/a/span[1]")));
		// element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(claimIdNumber1);

		Thread.sleep(4000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		// assign to all
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Licence Clerk")));
		element.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(claimIdNumber1);

		Thread.sleep(4000);

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("noticeRequireId"))))
				.selectByVisibleText("No");

		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='sroNumber']"))).sendKeys("2");

		Thread.sleep(3000);
		Object file = null;
		try {
			file = new File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file).exists());
		WebElement browseButton = driver.findElement(By.name("fileInput"));
		browseButton.sendKeys(((File) file).getAbsolutePath());

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
				.selectByVisibleText("Notification Letter");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("isnrSummarynextBtnId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		Thread.sleep(500);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Submission to identify the presence of surface owners for notification has been successful.')]")));
		// search if claim is active

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(claimIdNumber2);

		Thread.sleep(4000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		// assign to all
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Licence Clerk")));
		element.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(claimIdNumber2);

		Thread.sleep(4000);

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("noticeRequireId"))))
				.selectByVisibleText("No");

		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='sroNumber']"))).sendKeys("2");

		Thread.sleep(3000);
		Object file1 = null;
		try {
			file1 = new File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file1).exists());
		WebElement browseButton1 = driver.findElement(By.name("fileInput"));
		browseButton1.sendKeys(((File) file1).getAbsolutePath());

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
				.selectByVisibleText("Notification Letter");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("isnrSummarynextBtnId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		Thread.sleep(500);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Submission to identify the presence of surface owners for notification has been successful.')]")));
		// exploration plan
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/a/span")));
		element.click();
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
			element.sendKeys(pl1001.getPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(pl1001.getPin());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();
		}

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[10]/a[2]/b/em")));
		element.click();
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.linkText("Submit Plan/Permit for Early Exploration Activities")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(pl1001.getClientNumber());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("disclaimerIndId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("createSubmissionBtnId")));
		element.click();
		new Select(element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedMethodSlctId")))).selectByVisibleText(
						"Inputting or pasting a list of comma delimited claim and/or tenure numbers that can be validated");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("claimIds")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("claimIds")));
		element.sendKeys(claimIdNumber2 + "," + claimIdNumber1);
		Thread.sleep(5000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'The following claims are not contiguous:')]")));

	}

	public void updateCellgridTable() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		Statement stmt = con.createStatement();
		String sql = ("UPDATE MAM_SPATIAL.MTA_NTS_GRID_CELL SET CELL_STATUS_CODE = 'A', CELL_REASON_CODE = 'N', CELL_REOPENING_DATE = CELL_REOPENING_DATE  - 3  WHERE CELL_KEY_ID IN ('42M02H095','42M02H096','42M02H097')");
		stmt.executeUpdate(sql);
		con.commit();
		con.close();
	}

	public void updateCellLockTable() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		Statement stmt = con.createStatement();
		String sql = ("DELETE FROM MAM.MTA_GRID_LOCK_STATUS WHERE CELL_KEY_ID IN ('42M02H095','42M02H096','42M02H097')");
		stmt.executeQuery(sql);
		con.commit();
		con.close();
	}

	private void makePayment(WebElement element, WebDriverWait driverWait, WebDriver driver) {
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

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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
