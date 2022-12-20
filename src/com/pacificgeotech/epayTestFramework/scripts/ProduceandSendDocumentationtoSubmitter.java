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

public class ProduceandSendDocumentationtoSubmitter {
	
	
	private WebDriver driver;
	private String PermitId;

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

		ReviewPermitSubmission reviewpermitsubmission = new ReviewPermitSubmission();
		reviewpermitsubmission.test();
		setPermitId(reviewpermitsubmission.getPermitId());
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(6000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(6000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']"))
				.sendKeys(reviewpermitsubmission.getPermitId());

		Thread.sleep(4000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("MEDC Manager")));
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']"))
				.sendKeys(reviewpermitsubmission.getPermitId());

		Thread.sleep(2000);
		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();
		Thread.sleep(4000);

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Object file = null;
		try {
			file = new File(CLM2B003.class.getClassLoader().getResource("Flaring.pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file).exists());
		WebElement browseButton = driver.findElement(By.name("fileInput"));
		browseButton.sendKeys(((File) file).getAbsolutePath());
		
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
		.selectByVisibleText("Permit Document");
		
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("saveBtnId")));
		element.click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sendDocumentBtn")));
		element.click();
		
		new WebDriverWait(driver, 50).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(By.name("sendDocumentBtn"))));
		
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'Documentation has been produced and sent to Submitter')]")));
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Search"))).click();
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Search Instruments"))).click();

		Thread.sleep(2000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Instrument Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Instrument Number']")).sendKeys(reviewpermitsubmission.getPermitId());

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("[class='btn btn-labeled btn-primary']")))
				.click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		String actualString = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[@id='wulSearchTblId']/tbody/tr/td[3]"))).getText();
		Assert.assertTrue(actualString.contains("Active"));
		
		
		
	}

	public String getPermitId() {
		return PermitId;
	}

	public void setPermitId(String permitId) {
		PermitId = permitId;
	}

}
