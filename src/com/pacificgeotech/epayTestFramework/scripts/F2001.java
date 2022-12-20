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

public class F2001 {

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

		PL1001 pl1001 = new PL1001();
		pl1001.test();

		// check payment
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[6]/a[2]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("View Payment")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchId")));
		element.click();
		Thread.sleep(1500);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		org.junit.Assert.assertTrue(bodyText.contains("Apply for Prospector's Licence"));

		String bodyText1 = driver.findElement(By.tagName("body")).getText();
		org.junit.Assert.assertTrue(bodyText1.contains("Successful"));
		String bodyText2 = driver.findElement(By.tagName("body")).getText();
		org.junit.Assert.assertTrue(bodyText2.contains("Hanks Tom"));
		String bodyText3 = driver.findElement(By.tagName("body")).getText();
		org.junit.Assert.assertTrue(bodyText3.contains("$40.00"));

	}

}
