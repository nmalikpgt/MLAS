package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Calendar;

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

public class CLM6005 {

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

		GIS1002 gis1002 = new GIS1002();
		gis1002.test();

		setClaimId(gis1002.getClaimId());
		System.out.println("Claim ID is: " + gis1002.getClaimId());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[11]/a[2]/b/em")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Reinstate Claim")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element.sendKeys(gis1002.getClaimId());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		Thread.sleep(1000);
		Object file = null;
		try {
			file = new File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file).exists());
		WebElement browseButton = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("uploadFileId")));
		browseButton.sendKeys(((File) file).getAbsolutePath());
		// minister
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='date' and @value='date']")))
				.click();

		// setup date extension of time granted for 2 months in advance

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 62);
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(cal.getTime());

		System.out.println(" date " + date);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("dateGranted")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("dateGranted")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("dateGranted")));
		element.sendKeys(date);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("comments")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("comments")));
		element.sendKeys(
				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imper");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='decisionType' and @value='1']"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='extension' and @value='extension']"))).click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm"))).click();

		Thread.sleep(1000);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Text not found!", bodyText.contains(
				"Claim successfully reinstated to claim holder(s) in place prior to forfeiture. Claim status has been updated and notification sent to the claim holder(s)."));

	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

}
