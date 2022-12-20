package com.pacificgeotech.epayTestFramework.scripts;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class CLM1A004 {
	
	private String clientNumber;
	private String transactionId;
	private String claimId;
	private String pid;
	private String pin;
	private String buyerPid;
	private String buyerPin;
	private String buyerClientNumber;
	private String user;
	private WebDriver driver;
	
	@After
	public void tearDown(){
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

		// Create new client as Buyer
		PL1001 newPl1001 = new PL1001();
		newPl1001.test();

		System.out.println("Buyer Client Number " + newPl1001.getClientNumber());
		setBuyerClientNumber(newPl1001.getClientNumber());
		setBuyerPid(newPl1001.getPid());
		setBuyerPin(newPl1001.getPin());

		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Automatic Test"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span"))); 
		 element.click();

		// MP1001 mp1001 = new MP1001();

		// Initiate Transfer
		MP5003 mp5003 = new MP5003();
		mp5003.test();
		setClientNumber(mp5003.getClientNumber());
		setClaimId(mp5003.getClaimId());
		setPid(mp5003.getPid());
		setPin(mp5003.getPin());
		this.setUser(LGN1001.user);
		System.out.println("Seller Client Number " + mp5003.getClientNumber());

		/*
		 * String[] loginUrl = LGN1001.getLoginUrl();
		 * driver.navigate().refresh(); driver.navigate().to(loginUrl[0]);
		 * 
		 * Thread.sleep(2000); element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "pid"))); element.sendKeys(getPid());
		 * 
		 * element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "loginSubmit"))); element.click();
		 * 
		 * element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "pin"))); element.sendKeys(getPin());
		 * 
		 * element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "loginSubmit"))); element.click();
		 */
		// mine
		String[] loginInfo = LGN1001.getLoginUrl();
		new LGN1001().switchLocalRemote(loginInfo[1], loginInfo[0],getPid(), getPin());
	

		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Claim Management")));
		element.click();

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Initiate Transfer of Mining Claim(s)")))
				.click();

		Thread.sleep(2000);
		// seller
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/section/div/form/section[1]/div/div/div/fieldset/div[1]/div[1]/div[1]/div/input")));
		element.clear();
		element.sendKeys(getClientNumber());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/section/div/form/section[1]/div/div/div/fieldset/div[1]/div[1]/div[1]/div/div[2]/div")));
		element.click();

		// buyer
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/section/div/form/section[1]/div/div/div/fieldset/div[2]/div[1]/div[1]/div/input")));
		element.clear();
		element.sendKeys(getBuyerClientNumber());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/section/div/form/section[1]/div/div/div/fieldset/div[2]/div[1]/div[1]/div/div[2]/div")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element.sendKeys(this.claimId);

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/section/div/form/section/div/div/div/fieldset[2]/div/div/table/tbody/tr/td[7]/div[1]/table/tbody/tr/td[1]/input")));
		element.sendKeys("100");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		Thread.sleep(2000);

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnConfirmBtnId")));
		element.click();

		Thread.sleep(2000);
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
		Assert.assertTrue(actualString.contains("Active Pending Transfer"));

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
				.click();
		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out"))).click();
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

	public String getBuyerPid() {
		return buyerPid;
	}

	public void setBuyerPid(String buyerPid) {
		this.buyerPid = buyerPid;
	}

	public String getBuyerPin() {
		return buyerPin;
	}

	public void setBuyerPin(String buyerPin) {
		this.buyerPin = buyerPin;
	}

	private void setUser(String user) {
		this.user = user;
	}

	private String getUser() {
		return user;
	}

}
