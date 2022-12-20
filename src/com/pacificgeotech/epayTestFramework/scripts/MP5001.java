package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;

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

import junit.framework.Assert;

public class MP5001 {

	private String clientNumber;
	private String transactionId;
	private String claimId;
	private String pin;
	private String pid;
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

		// Approve the created claim SRO
		// This script works based on client Number and claim ID created in
		// MP1001

		MP1001 mp1001 = new MP1001();
		mp1001.test();
		setClientNumber(mp1001.getClientNumber());
		setClaimId(mp1001.getClaimId());
		setPin(mp1001.getPin());
		setPid(mp1001.getPid());
		Thread.sleep(1000);

		String[] urlInfo = LGN1001.getLoginUrl();
		LGN1001 lgn1001 = new LGN1001();
		// Submit Notice of Claim Abandonment
		Thread.sleep(1000);
		// String [] loginURL = LGN1001.getLoginUrl();
		driver.get(urlInfo[2]);
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		element.sendKeys("admin");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
		element.sendKeys("@MLAS4you");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		Thread.sleep(2000);
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By
		// .xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[5]/a/span[1]")));
		// element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(mp1001.getClaimId());

		Thread.sleep(4000);
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='dropdownMenu1']")));
		Thread.sleep(4000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		Thread.sleep(1000);
		// assign to all
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Customer Support Clerk")));
		element.click();
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(mp1001.getClaimId());

		Thread.sleep(3000);
		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();
		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("noticeRequireId"))))
				.selectByVisibleText("Yes");
		Thread.sleep(1000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.name("sroNumber")));
		element.clear();
		
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.name("sroNumber")));
		element.sendKeys("1");	

//		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='sroNumber']")))
//				.sendKeys("2");

		Thread.sleep(1000);

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("isnrSummarynextBtnId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		Thread.sleep(1500);
		// driver.getPageSource().contains("Submission to identify the presence
		// of surface owners for notification has been successful.");
//		String bodyText = driver.findElement(By.tagName("body")).getText();
//		Assert.assertTrue("Text not found!", bodyText.contains(
//				"Submission to identify the presence of surface owners for notification has been successful."));
		
		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Submission to identify the presence of surface owners for notification has been successful.')]")));
		// search if claim is active
		/*
		 * ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
		 * "window.scrollTo(0, 0)");
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By
		 * .xpath(
		 * "//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[2]/a[2]"))
		 * ) .click();
		 * 
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.
		 * linkText("Search Tenure"))).click();
		 * 
		 * Thread.sleep(2000); element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.
		 * xpath("//input[@placeholder='Tenure Number']"))); Thread.sleep(2000);
		 * driver.findElement(By.xpath("//input[@placeholder='Tenure Number']"
		 * )).sendKeys(this.claimId);
		 * 
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By
		 * .xpath("/html/body/div[2]/div[3]/div/div[2]/div/div[2]/div/div/a")))
		 * .click();
		 * 
		 * ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
		 * "window.scrollTo(0, 2300)");
		 * 
		 * String actualString =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By
		 * .xpath(
		 * "html/body/div[2]/div[3]/div/div[3]/div/div/div[2]/table/tbody/tr/td[3]"
		 * ))).getText(); Assert.assertTrue(actualString.contains(
		 * "Active Pending"));
		 */

	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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
