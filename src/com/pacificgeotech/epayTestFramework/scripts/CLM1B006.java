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

public class CLM1B006 {
	
	private String claimId;	
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
		CLM1A001 clm1a001 = new CLM1A001();
		clm1a001.test();				
		setClaimId(clm1a001.getClaimId());
		loginAsBuyer(clm1a001);
	}
	
	private void loginAsBuyer(CLM1A001 clm1a001) throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
				
		System.out.println("PID from CLM1a001: " + clm1a001.getBuyerPid());
		System.out.println("PIN from CLM1a001: " + clm1a001.getBuyerPin());
		System.out.println("Buyer client number: " + clm1a001.getBuyerClientNumber());
		
		String[] loginUrl = LGN1001.getLoginUrl();
			
		driver.navigate().refresh();	
		driver.navigate().to(loginUrl[0]);
		
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
		element.sendKeys(clm1a001.getBuyerPid());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();
		
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
		element.sendKeys(clm1a001.getBuyerPin());
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();
		
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Claim Management")));
		element.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Complete Transfer of Mining Claim(s)"))).click();
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))).sendKeys(clm1a001.getBuyerClientNumber());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();
		
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='wid-id-0']/div/div/fieldset/div[2]/div/ol/li/a"))).click();
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@name='acceptTransfer'])[2]"))).click();	
		
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();
		
		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnConfirmBtnId"))).click();

		
	}

	public String getClaimId() {
		return this.claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	
	

}
