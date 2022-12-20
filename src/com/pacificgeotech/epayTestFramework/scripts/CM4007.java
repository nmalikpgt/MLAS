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

public class CM4007 {
	private String clientNumber;
	private String orgNum;
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

		MP1B004 mp1b004 = new MP1B004();
		mp1b004.test();
		setClientNumber(mp1b004.getClientNumber());
		setOrgNum(mp1b004.getOrgNum());
		setClaimId(mp1b004.getClaimId());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Change Client Name")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(mp1b004.getClientNumber());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName")));
		element.sendKeys("John");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
		element.sendKeys("Doe");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Client name has changed successfully.')]")));

		// CHANGE ORG NAME

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Change Client Name")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(mp1b004.getOrgNum());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("organizationName")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("organizationName")));
		element.sendKeys("PGTS Ltd");
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

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option.ng-binding.ng-scope")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
				.selectByVisibleText("Change Client Name");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Client name has changed successfully.')]")));

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[2]/a[2]"))).click();
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Search Tenures"))).click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Tenure Number']")));
		element.sendKeys(mp1b004.getClaimId());

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/div[2]/div/div/a")))
				.click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		String actualString = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("html/body/div[2]/div[3]/div/div[3]/div/div/div[2]/table/tbody/tr/td[6]"))).getText();
		Assert.assertTrue(actualString.contains("PGTS Ltd"));

	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public String getOrgNum() {
		return orgNum;
	}

	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

}
