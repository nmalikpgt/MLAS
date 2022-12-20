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

public class CLM2C009 {
	
	private WebDriver driver;
	private String claimId;
	
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
		
		CLM2B004 clm2b004 = new CLM2B004();
		clm2b004.test();
		setClaimId(clm2b004.getClaimId());
		
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
								.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/div[2]/div/div/a")))
						.click();

				((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

				String actualString = driverWait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("html/body/div[2]/div[3]/div/div[3]/div/div/div[2]/table/tbody/tr/td[3]"))).getText();
				Assert.assertTrue(actualString.contains("Active"));

		
		
		
	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

}
