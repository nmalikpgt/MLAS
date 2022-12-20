package com.pacificgeotech.epayTestFramework.scripts;

import java.sql.Connection;
import java.sql.ResultSet;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;
import com.pacificgeotech.epayTestFramework.dbConnection.DbConnection;

public class ARAL {

	private WebDriver driver;
	private String ClaimNo;
	private String clientNumber;
	private String pid;
	private String pin;
	private String LeaseNo;
	private String AccountNo;

	 public String getAccountNo() {
		return AccountNo;
	}

	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}

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

		RAL ral = new RAL();
		ral.test();
		setClientNumber(ral.getClientNumber());
		setPin(ral.getPin());
		setPid(ral.getPid());
		setClaimNo(ral.getClaimNo());
		setAccountNo(getAccountNo());
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();
		Thread.sleep(4000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		Thread.sleep(4000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();
		Thread.sleep(4000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(4000);
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")))
				.sendKeys(ral.getClaimNo());
		Thread.sleep(4000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.id("dropdownMenu1")));
		Thread.sleep(2000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		Thread.sleep(2500);
		// assign to all
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Customer Support Clerk")));
		element.click();
		Thread.sleep(2500);
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")))
				.clear();

		Thread.sleep(6000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")))
				.sendKeys(ral.getClaimNo());

		Thread.sleep(4000);

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();

		// Record Lease
		// Calendar calendar3 = Calendar.getInstance();
		// calendar3.add(Calendar.DAY_OF_YEAR, 0);
		// Date today3 = calendar3.getTime();
		// DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
		// String today = dateFormat3.format(today3);
		//
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("effectiveDateInput")));
		// element.click();
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("effectiveDateInput")));
		// element.click();
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("effectiveDateInput")));
		// element.clear();
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("effectiveDateInput")));
		// element.sendKeys(today);
		Thread.sleep(1000);
		Long maxId = LeaseNo();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningRightId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningRightId")));
		element.sendKeys(String.valueOf(maxId));
		System.out.println("Mining right ID IS: " + maxId);

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 0);
		Date today1 = calendar.getTime();
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		String todayA = dateFormat1.format(today1);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("taxRentEffectiveDate")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("taxRentEffectiveDate")));
		element.sendKeys(todayA);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("landAreaHa")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("landAreaHa")));
		element.sendKeys("100");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
		element.sendKeys("123");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("surveyPlan")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("surveyPlan")));
		element.sendKeys("1");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shortLegalDescr")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shortLegalDescr")));
		element.sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean ");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("longLegalDescr")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("longLegalDescr")));
		element.sendKeys(
				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. ");

		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("miningDivision"))))
				.selectByVisibleText("Kenora");

		new Select(
				element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("landRegistryOffice"))))
						.selectByVisibleText("ALGOMA");

		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.id("addOwners")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("leaseOwnershipClient0")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("leaseOwnershipClient0")));
		element.sendKeys(ral.getClientNumber());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[@id='wid-id-0']/div/div/div/table/tbody/tr[1]/td[2]/autocomplete-client/div/div[2]/div/a")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("leaseOwnershipPercentage0")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("leaseOwnershipPercentage0")));
		element.sendKeys("100");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("saveBtnId")));
		element.click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Lease was recorded successfully.')]")));

		new WebDriverWait(driver, 50).until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='col-sm-4 col-lg-2 ng-binding']")));
		WebElement TxtBoxContent = driver.findElement(By.cssSelector("[class='col-sm-4 col-lg-2 ng-binding']"));
		String LeaseNo = TxtBoxContent.getText();
		System.out.println("Lease Number " + LeaseNo);
		setLeaseNo(LeaseNo);

		// create account sub-account

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.row > a.ng-binding")));
		element.click();
		Thread.sleep(1000);

		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("selectTenureType"))))
				.selectByVisibleText("Lease");

		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("selectDistrict"))))
				.selectByVisibleText("DISTRICT OF ALGOMA");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningLandNumbesCsv")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningLandNumbesCsv")));
		element.sendKeys(LeaseNo);
		Thread.sleep(1000);
//		Actions action = new Actions(driver); 
//		action.sendKeys(Keys.DELETE);
//		action.sendKeys(webelement,value).build().perform();
		
//		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
//				"//*[@id='billingPartyId']")));
//		element.click();
		Thread.sleep(1500);
		element.sendKeys(Keys.DELETE);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
			"//*[@id='billingPartyId']//input")));
		element.clear();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[@id='billingPartyId']//input")));
		element.sendKeys(ral.getClientNumber());
		Thread.sleep(1500);
//		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(aral.getClientNumber())));
//		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();	
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='accountNumberLnkId']")));
		Thread.sleep(1500);
		WebElement TxtBoxContent1 = driver.findElement(By.xpath("//*[@id='accountNumberLnkId']"));
		String AccountNo = TxtBoxContent1.getText();
		System.out.println("Account Nuber " + AccountNo);
		setAccountNo(AccountNo);
		Thread.sleep(500);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'You have successfully completed a Create Account and Sub-Account')]")));

	}

	public String getLeaseNo() {
		return LeaseNo;
	}

	public void setLeaseNo(String leaseNo) {
		LeaseNo = leaseNo;
	}

	public String getClaimNo() {
		return ClaimNo;
	}

	public void setClaimNo(String claimNo) {
		ClaimNo = claimNo;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
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

	public Long LeaseNo() throws ClassNotFoundException, SQLException {
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
