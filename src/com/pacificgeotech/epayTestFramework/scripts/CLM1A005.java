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

public class CLM1A005 {
	
	private String clientNumber;
	private String transactionId;
	private String claimId;
	private static String pid;
	private static String pin;
	private static String buyerPid;
	private static String buyerPin;
	private static String buyerClientNumber;
	private String jointClient;
	private String newBuyerClient;
	private String firstPin;
	private String firstPid;
	private String SecondPid;
	private String SecondPin;
	
	private WebDriver driver;
	
/*	@After
	public void tearDown(){
		driver.getCurrentUrl();
	}*/
	
	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;		
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
		setFirstPid(cm9001.getPid());
		setFirstPin(cm9001.getPin());
		setJointClient(cm9001.getClientNumber());
		setSecondPid(cm9001.getSecondPid());
		setSecondPin(cm9001.getSecondPin());
		System.out.println("sec pid + pin " + cm9001.getSecondPid() + "; " + cm9001.getSecondPin());


		// sign out
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[@id=\"header\"]/cata-header/div[1]/div[2]/ul[2]/li[3]/a/span[1]")))
				.click();
		Thread.sleep(2000);

		WebElement _element1 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"header\"]/cata-header/div[1]/div[2]/ul[2]/li[3]/ul/li[3]/a/div")));
		_element1.click();

		PL1001 pl1001 = new PL1001();
		pl1001.test();
		setNewBuyerClient(pl1001.getClientNumber());
		setPid(pl1001.getPid());
		setPin(pl1001.getPin());

		// sign out
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Automatic Test")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span")));
		element.click();

		Thread.sleep(1000);
		String[] loginURL = LGN1001.getLoginUrl();
		driver.get(loginURL[0]); // for local Test
		LGN1001 lgn1001 = new LGN1001();
		
		if ("localTest".equals(loginURL[1])) {
			
			
			driver.navigate().refresh();
		  	driver.navigate().to(loginURL[0]);
		

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
			element.sendKeys(cm9001.getPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(cm9001.getPin());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();
			

		} else {

			lgn1001.getLoginInfoExternalRemote(loginURL[1], loginURL[0]);
		}

		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Claim Management")));
		element.click();

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Initiate Transfer of Mining Claim(s)")))
				.click();

		Thread.sleep(2000);

		// seller
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput")));
		element.clear();
		element.sendKeys(getClientNumber());
	

		// buyer
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/section/div/form/section[1]/div/div/div/fieldset/div[2]/div[1]/div[1]/div/input")));
		element.clear();
		element.sendKeys(getNewBuyerClient());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/section/div/form/section[1]/div/div/div/fieldset/div[2]/div[1]/div[1]/div/div[2]/div")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element.sendKeys(getClaimId());

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();
		
		Thread.sleep(1500);

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		
		
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("percentageInputId_0")));
		element.clear();
		element.sendKeys("100");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();
		Thread.sleep(1500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnConfirmBtnId"))).click();
	

	}
	
	public String getSecondPid() {
		return SecondPid;
	}

	public void setSecondPid(String secondPid) {
		SecondPid = secondPid;
	}

	public String getSecondPin() {
		return SecondPin;
	}

	public void setSecondPin(String secondPin) {
		SecondPin = secondPin;
	}

	

	public String getFirstPin() {
		return firstPin;
	}

	public void setFirstPin(String firstPin) {
		this.firstPin = firstPin;
	}

	public String getFirstPid() {
		return firstPid;
	}

	public void setFirstPid(String firstPid) {
		this.firstPid = firstPid;
	}

	public String getNewBuyerClient() {
		return newBuyerClient;
	}

	public void setNewBuyerClient(String newBuyerClient) {
		this.newBuyerClient = newBuyerClient;
	}

	public String getJointClient() {
		return jointClient;
	}

	public void setJointClient(String jointClient) {
		this.jointClient = jointClient;
	}

	public String getclientNumber() {
		return jointClient;
	}

	public void setclientNumber(String clientNumber) {
		this.jointClient = jointClient;
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
}