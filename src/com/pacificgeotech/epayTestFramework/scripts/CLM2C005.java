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

import junit.framework.Assert;

public class CLM2C005 {
	
	
	private String clientNumber;
	private String transactionId;
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
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		CLM2A004 clm2a004 = new CLM2A004();
		clm2a004.test();
		setClientNumber(clm2a004.getClientNumber());
		setClaimId(clm2a004.getClaimId());
		setTransactionId(clm2a004.getTransactionId());
		
		//Rejection of claim abandonment
		new LGN1002().test();
        
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

        Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(clm2a004.getClaimId());

		Thread.sleep(4000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Claims Administrator")));
		element.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(clm2a004.getClaimId());

		Thread.sleep(4000);
		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();

		Thread.sleep(4000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		//Approve abandonment
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='approvalSelectRadio' and @value='RPFR']"))).click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		driver.getPageSource().contains("Assessment of the notice of claim abandonment has been completed successfully. The claim has been cancelled and associated cells have been queued for reopening.");

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Active')]")));
		//search if claim is active
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[2]/a[2]")))
				.click();
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Search Tenures"))).click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Tenure Number']")));
		element.sendKeys(this.claimId);
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("/html/body/div[2]/div[3]/div/div[2]/div/div[2]/div/div/a")))
				.click();
		
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		
		String actualString = driverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("html/body/div[2]/div[3]/div/div[3]/div/div/div[2]/table/tbody/tr/td[3]"))).getText();
		Assert.assertTrue(actualString.contains("Active"));
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
	
}

