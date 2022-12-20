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

public class CM6005 {

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

		java.util.List<WebElement> elements = WebDriverManager.getElements();

		LGN1002 lgn1002 = new LGN1002();
		lgn1002.test();

		// amalgamate client
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.linkText("Client Management")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Client Management")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Amalgamate Clients")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys("304102");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("304102 HIGH G MINERALS CORPORATION")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#amalgamatedClientId0 > div[name=\"amcClientIdForm\"] > #clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#amalgamatedClientId0 > div[name=\"amcClientIdForm\"] > #clientselect")));
		element.sendKeys("DARYL NEUSTATER");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("175221 DARYL NEUSTATER")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#amalgamatedClientId1 > div[name=\"amcClientIdForm\"] > #clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#amalgamatedClientId1 > div[name=\"amcClientIdForm\"] > #clientselect")));
		element.sendKeys("303569");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("303569 ALEXANDAR JELENIC")));
		element.click();
		Thread.sleep(1000);
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
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode")))).selectByVisibleText("Amalgamation");
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option.ng-binding.ng-scope"))); 
	 element.click();
		
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'Client ID: 175221 is not of Organization Type')]")));

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'Client ID: 303569 is not of Organization Type')]")));

	}

}
