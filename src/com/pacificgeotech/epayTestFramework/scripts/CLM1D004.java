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

import junit.framework.Assert;

public class CLM1D004 {
	
	private String clientNumber;
	private String transactionId;
	private String claimId;
	private String pid;
	private String pin;
	private String buyerClientNumber;
	private String SecondPid;
	private String SecondPin;
	private WebDriver driver;
	
//	@After
//	public void tearDown(){
//		driver.getCurrentUrl();
//	}	
//	
//	@AfterClass
//	public static void afterClass() {
//		WebDriverManager.instance = null;		
//	}
		
	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		
		CLM1D001 clm1d001 = new CLM1D001();
		clm1d001.test();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Automatic Test"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span"))); 
		 element.click();
		
		String[] loginURL = LGN1001.getLoginUrl();
		driver.get(loginURL[0]); // for local Test
		LGN1001 lgn1001 = new LGN1001();
		CLM1A005 clm1a005 = new CLM1A005();
		setSecondPid(clm1d001.getSecondPid());
		setSecondPin(clm1d001.getSecondPin());
		System.out.println("Pid and Pin:  " + clm1a005.getSecondPid() + "; " + clm1a005.getSecondPin());

		if ("localTest".equals(loginURL[1])) {

			driver.navigate().refresh();
			driver.navigate().to(loginURL[0]);

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
			element.sendKeys(clm1d001.getSecondPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(clm1d001.getSecondPin());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

		} else {

			lgn1001.getLoginInfoExternalRemote(loginURL[1], loginURL[0]);
		}
        
        Thread.sleep(2000);
        element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[8]/a[2]/span")));
        element.click();
        
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Complete Transfer of Mining Claim(s)"))).click();

    	// click Next
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/div/section/div/form/article[2]/div/div/div/fieldset/div[2]/div/ol/li/a")));
		element.click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();
		
	

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




	
	
}
