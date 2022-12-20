package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class CLM1A006 {
	
	private String clientNumber;
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
		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();

		System.out.println("Buyer Client Number " + lgn1001.getClientNumber());
		setBuyerClientNumber(lgn1001.getClientNumber());
		setBuyerPid(lgn1001.getPid());
		setBuyerPin(lgn1001.getPin());
		
		//claim management 
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Claim Management")));
		element.click();

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Initiate Transfer of Mining Claim(s)")))
				.click();
		
		
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput")));
		element.clear();
		element.sendKeys("102313");
		
		driverWait
		.until(ExpectedConditions.presenceOfElementLocated(By.linkText("102313 CHARLES ANDERSON")))
		.click();

		// buyer
				element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
						"/html/body/div[2]/div[3]/section/div/form/section[1]/div/div/div/fieldset/div[2]/div[1]/div[1]/div/input")));
				element.clear();
				element.sendKeys("102498");

				driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("102498 MICHAEL ANDREWS")))
				.click();
				
				((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

				driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();

				
				new WebDriverWait(driver, 50)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'has no permission to initiate transfer for seller CHARLES ANDERSON (102313).')]")));		


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
