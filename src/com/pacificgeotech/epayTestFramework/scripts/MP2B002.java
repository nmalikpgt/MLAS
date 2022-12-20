package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

public class MP2B002 {

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

		// Confirm SRO as Internal User - valid and complete (select no for
		// notification given to SROs)
		// This script works based on client Number and claim ID created in
		// MP1001

		MP5001 mp5001 = new MP5001();
		mp5001.test();
		setClientNumber(mp5001.getClientNumber());
		setClaimId(mp5001.getClaimId());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[9]/a[2]/span"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Confirm SRO Notification of Claim Registration"))).click();

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='clientselect']")));
		element.sendKeys(mp5001.getClientNumber());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("selectedCellIds")));
		element.sendKeys(mp5001.getClaimId());

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoDetailButtonId"))).click();

		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String todayAsString = dateFormat.format(today);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/form/div/section/div[1]/article[2]/div/div/div/div/table/tbody/tr/td[2]/div[1]/input")));
		element.sendKeys("test1");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/form/div/section/div[1]/article[2]/div/div/div/div/table/tbody/tr/td[3]/div[1]/table/tbody/tr/td[1]/input")));
		element.sendKeys(todayAsString);

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("/html/body/div[2]/div[3]/form/div/section/div[1]/article[2]/div/div/div/div/table/tbody/tr/td[4]/div[1]/table/tbody/tr/td[1]/select"))))
						.selectByVisibleText("No");

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("/html/body/div[2]/div[3]/form/div/section/div[1]/article[2]/div/div/div/div/table/tbody/tr/td[4]/div[1]/table/tbody/tr/td[4]/select"))))
						.selectByVisibleText("Unable to find most recent address");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/form/div/section/div[1]/article[2]/div/div/div/div/table/tbody/tr/td[2]/div[2]/input")));
		element.sendKeys("test2");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/form/div/section/div[1]/article[2]/div/div/div/div/table/tbody/tr/td[3]/div[2]/table/tbody/tr/td[1]/input")));
		element.sendKeys(todayAsString);

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("/html/body/div[2]/div[3]/form/div/section/div[1]/article[2]/div/div/div/div/table/tbody/tr/td[4]/div[2]/table/tbody/tr/td[1]/select"))))
						.selectByVisibleText("No");

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("/html/body/div[2]/div[3]/form/div/section/div[1]/article[2]/div/div/div/div/table/tbody/tr/td[4]/div[2]/table/tbody/tr/td[4]/select"))))
						.selectByVisibleText("Too many owners to give notice");

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

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
				.selectByVisibleText("Waiver Request");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoConfirmButtonId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		driver.getPageSource().contains("Submission of the Confirm SRO Notification has been successful.");

		// TODO: Next step for Waiver
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
		 * linkText("Search Tenures"))).click();
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
		 * ))).getText(); Assert.assertTrue(actualString.contains("Active"));
		 */
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
