package com.pacificgeotech.epayTestFramework.scripts;

import junit.framework.Assert;

import java.sql.Driver;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class CLM1A002 {

	private String clientNumber;
	private String transactionId;
	private String claimId;
	private static String pid;
	private static String pin;
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

		new LGN1001().test();

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Claim Management")));
		element.click();

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Initiate Transfer of Mining Claim(s)")))
				.click();

		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#buyerInputId > div.form-group > div.col-md-12 > #searchInput")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#buyerInputId > div.form-group > div.col-md-12 > #searchInput")));
		element.sendKeys("1");

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element.sendKeys("1");
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();

		Thread.sleep(1500);

		String bodyText1 = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Text not found!", bodyText1.contains("Following claim IDs are not valid: 1"));

	}

}
