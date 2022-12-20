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

public class MP4002 {

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

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		// Assess Request for Waiver for SRO Notification (Approve)
		// This script works based on client Number and claim ID created in
		// MP1001

		
		MP2B002 mp2b002 = new MP2B002();
		mp2b002.test();
		setClientNumber(mp2b002.getClientNumber());
		setClaimId(mp2b002.getClaimId());

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(mp2b002.getClaimId());

		Thread.sleep(1000);
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.presenceOfElementLocated(By.id("dropdownMenu1")));
		Thread.sleep(1000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("dropdownMenu1")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Licence Clerk")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		element.clear();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(mp2b002.getClaimId());

		Thread.sleep(3000);
		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();

		Thread.sleep(2000);
		driver.getPageSource().contains("Assess Request for Waiver for SRO Notification");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("commentsInputId")))
				.sendKeys("This claim is approved.");

		// For deny
		// *[@id="wid-id-0"]/div/div/div[2]/table/tbody/tr/td[2]/label/input
		// /html/body/div[2]/div[3]/div/div/div/form/div/section/div[1]/article[4]/div/div/div/div[2]/table/tbody/tr/td[2]/label/input

		// This can be done creating a generic method
		/*
		 * Thread.sleep(3000); Object file = null; try { file = new
		 * File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI()
		 * ); } catch (URISyntaxException e) { e.printStackTrace(); }
		 * Assert.assertTrue(((File) file).exists()); WebElement browseButton =
		 * driver.findElement(By.name("fileInput"));
		 * browseButton.sendKeys(((File) file).getAbsolutePath());
		 * 
		 * new
		 * Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(
		 * By.id("docTypeCode")))) .selectByVisibleText("Waiver Approved");
		 */

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId")));
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");

		driver.getPageSource().contains("Supporting document is required");

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
