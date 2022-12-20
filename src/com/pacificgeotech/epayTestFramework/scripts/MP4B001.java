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

public class MP4B001 {

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

		// Assess Request for Waiver for SRO Notification (Denied)
		// This script works based on client Number and claim ID created in
		// MP1001

		new MP2B002().test();

		this.setClientNumber(new MP1001().getClientNumber());
		this.setClaimId(new MP1001().getClaimId());

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[5]/a/span[1]")));
		element.click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(this.claimId);

		Thread.sleep(2000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();

		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(this.claimId);

		Thread.sleep(3000);
		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();

		Thread.sleep(2000);
		driver.getPageSource().contains("Assess Request for Waiver for SRO Notification");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("commentsInputId")))
				.sendKeys("This claim is denied.");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/div/div/div/form/div/section/div[1]/article[4]/div/div/div/div[2]/table/tbody/tr/td[2]/label/input")));
		element.click();
		// *[@id="wid-id-0"]/div/div/div[2]/table/tbody/tr/td[2]/label/input
		// /html/body/div[2]/div[3]/div/div/div/form/div/section/div[1]/article[4]/div/div/div/div[2]/table/tbody/tr/td[2]/label/input

		// This can be done creating a generic method
		Thread.sleep(3000);
		Object file = null;
		try {
			file = new File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file).exists());
		WebElement browseButton = driver.findElement(By.name("fileInput"));
		browseButton.sendKeys(((File) file).getAbsolutePath());

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
				.selectByVisibleText("Waiver Approved");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId")));
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");

		driver.getPageSource()
				.contains("Submission of the Assess Request for Waiver for SRO Notification has been successful.");

		// search if claim is active
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[2]/a[2]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Search Tenures"))).click();

		Thread.sleep(2000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Tenure Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Tenure Number']")).sendKeys(this.claimId);

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/div[2]/div/div/a")))
				.click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		String actualString = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("html/body/div[2]/div[3]/div/div[3]/div/div/div[2]/table/tbody/tr/td[3]"))).getText();
		Assert.assertTrue(actualString.contains("Active Pending"));
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
