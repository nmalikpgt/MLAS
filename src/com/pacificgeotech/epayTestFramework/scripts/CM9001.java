package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class CM9001 {

	private String clientNumber;
	private String transactionId;
	private String claimId;
	private String pid;
	private String pin;
	private static String buyerClientNumber;
	private String jointClient;
	private String secondPid;
	private String secondPin;
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

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		MP5003 mp5003 = new MP5003();
		mp5003.test();
		setClientNumber(mp5003.getClientNumber());
		setClaimId(mp5003.getClaimId());
		setPid(mp5003.getPid());
		setPin(mp5003.getPin());
		System.out.println("Remote Client Number " + getClientNumber());

		// client management
		// sign out
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
				.click();
		Thread.sleep(1000);

		WebElement _element1 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out")));
		_element1.click();

		PL1001 pl1001 = new PL1001();
		pl1001.test();
		setJointClient(pl1001.getClientNumber());
		setSecondPid(pl1001.getPid());
		setSecondPin(pl1001.getPin());
		System.out.println("second pin and pid: " + pl1001.getPid() + " " + pl1001.getPin());
		System.out.println("Joint Client Number " + getJointClient());

		new LGN1002().test();
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.linkText("Client Management")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Client Management")));
		element.click();
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
		element.sendKeys(getJointClient());

		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='search_client_list col-md-12']")));
		element.click();
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimNumberId")));
		element.clear();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimNumberId")));
		element.sendKeys(mp5003.getClaimId());
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'New Joint Tenant has been added successfully.')]")));
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
		driver.findElement(By.xpath("//input[@placeholder='Claim Number']")).sendKeys(getClaimId());

		driverWait
		.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("[class='btn btn-labeled btn-primary']")))
		.click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("tenureNumberLnkId"))).click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'Single Ownership Transferred to Joint Tenancy Ownership')]")));

	}

	public String getSecondPid() {
		return secondPid;
	}

	public void setSecondPid(String secondPid) {
		this.secondPid = secondPid;
	}

	public String getSecondPin() {
		return secondPin;
	}

	public void setSecondPin(String secondPin) {
		this.secondPin = secondPin;
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

	public String getBuyerClientNumber() {
		return buyerClientNumber;
	}

	public void setBuyerClientNumber(String buyerClientNumber) {
		this.buyerClientNumber = buyerClientNumber;
	}

}