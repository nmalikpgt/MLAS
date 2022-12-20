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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

import junit.framework.Assert;

public class CM5009 {
	private String claimId;
	private String clientNumber;
	private String orgNum;
	private WebDriver driver;

	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		MP5006 mp5006 = new MP5006();
		mp5006.test();
		setClientNumber(mp5006.getClientNumber());
		setClaimId(mp5006.getClaimId());
		setOrgNum(mp5006.getOrgNum());

		// client dissolution
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Client Management")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Record Client Death or Dissolution")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(mp5006.getOrgNum());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.btn.btn-default")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[47]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-0']/div/div/div/fieldset/div[2]/div[2]/textarea")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-0']/div/div/div/fieldset/div[2]/div[2]/textarea")));
		element.sendKeys("test");
		// Upload
		Object file = null;
		try {
			file = new File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file).exists());
		WebElement browseButton = driver.findElement(By.name("fileInput"));
		browseButton.sendKeys(((File) file).getAbsolutePath());
		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		Thread.sleep(2500);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/div/div/div[3]/a/span")));
		element.click();
		Thread.sleep(10000);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Text not found!", bodyText.contains("Client profile was updated successfully to record date of dissolution. Active Claims have been placed on special status and will not forfeit for 3 years."));
		Thread.sleep(1000);
		String bodyText2 = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Text not found!", bodyText2.contains("Active"));
		String bodyText1 = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Text not found!", bodyText1.contains(mp5006.getClaimId()));

	}

	public String getOrgNum() {
		return orgNum;
	}

	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}

}
