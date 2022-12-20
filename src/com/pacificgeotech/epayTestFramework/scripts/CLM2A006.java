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

public class CLM2A006 {
	
	private String clientNumber;
	private String transactionId;
	private String claimId;
	private static String pid;
	private static String pin;
	private static String buyerPid;
	private static String buyerPin;
	private static String buyerClientNumber;
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

        MP5003 mp5003 = new MP5003();
		mp5003.test();
		setClientNumber(mp5003.getClientNumber());
		setClaimId(mp5003.getClaimId());
		System.out.println("claim ID and Client number are : " + mp5003.getClaimId() + " " + mp5003.getClientNumber());

		setPid(mp5003.getPid());
		setPin(mp5003.getPin());
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("MLAS Admin"))); 
		 element.click();
		 Thread.sleep(1000);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span"))); 
		 element.click();
		 Thread.sleep(1000);
		String[] urlInfo = LGN1001.getLoginUrl();
		LGN1001 lgn1001 = new LGN1001();
		//Submit Notice of Claim Abandonment
		Thread.sleep(1000);
		//String [] loginURL = LGN1001.getLoginUrl();
		driver.get(urlInfo[0]); 
	    Thread.sleep(1000);
	    if ("remote".equals(urlInfo[1])) {
	    	lgn1001.getLoginInfoExternalRemote(urlInfo[1],urlInfo[0]);
		} else {
					
			lgn1001.getLoginInfoExternalLocalTest(urlInfo[1],urlInfo[0], mp5003.getPid(),mp5003.getPin());

		}
        
        Thread.sleep(2000);
        element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[8]/a[2]/span")));
        element.click();
        
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Submit Notice of Claim Abandonment"))).click();
        
        //partial abandonment
        element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("/html/body/div[2]/div[3]/div/section/div/form/article[2]/div/div/div[1]/fieldset/div/div[2]/label[2]/input")));
        element.click();
        
        
        element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
        element.sendKeys(this.claimId);
        
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputCliamCellsTxtId_0")));
        element.clear();
		Thread.sleep(1000);
        element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputCliamCellsTxtId_0")));
        element.sendKeys("43B03C239,43B03C218");
        Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();
		Thread.sleep(1500);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		org.junit.Assert.assertTrue(bodyText.contains("Cells selected for registration must be abutting. They must have one common side"));


		driverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("li.usr-block > a.dropdown-toggle > span.caret"))).click();
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
}

