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

public class CM4008 {
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

		CLM3001 clm3001 = new CLM3001();
		clm3001.test();
		setClaimId(clm3001.getClaimId());

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[7]/a[2]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Change Client Name"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")))
				.sendKeys(clm3001.getClientNumberId());
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("30002325
		// Hanks Tom"))).click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName"))).sendKeys("TomTom");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName"))).sendKeys("HankHank");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#wid-id-1 > div"))).click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[2]/a[2]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Search Tenures"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(clm3001.getClaimId());

		Thread.sleep(4000);
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@id='tenures-container']/div[2]/div/div[2]/div/div/a")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("tenureNumberLnkId"))).click();

	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

}
