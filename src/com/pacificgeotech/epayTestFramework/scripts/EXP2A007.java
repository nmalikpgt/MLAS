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

public class EXP2A007 {

	private String clientNumber;
	private String transactionId;
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

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		EXP1008 exp1008 = new EXP1008();
		exp1008.test();
		setClientNumber(exp1008.getClientNumber());
		setClaimId(exp1008.getClaimId());
		System.out
				.println("claim ID and Client number are : " + exp1008.getClaimId() + " " + exp1008.getClientNumber());
		setTransactionId(exp1008.getTransactionId());



		// make active
		Thread.sleep(1000);
		String[] loginURL = LGN1001.getLoginUrl();
		driver.get(loginURL[2]); // for local Test
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		element.sendKeys("admin");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
		element.sendKeys("@MLAS4you");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText(exp1008.getTransactionId())));
		element.click();

		Thread.sleep(1000);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Text not found!", bodyText.contains("Received"));

	}

}
