package com.pacificgeotech.epayTestFramework.scripts;

import com.pacificgeotech.epayTestFramework.core.CommonUtils;
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

public class LGN1003 {

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

		// login
		// CommonUtils.login();

		new LGN1001().test();




		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
		element.sendKeys("kdN2EFNMKIhh");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
		element.sendKeys("3333");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();
		Thread.sleep(4000);
		String bodyText = driver.findElement(By.id("incorrectPin")).getText();
		Assert.assertTrue("Text not found!", bodyText.contains("The PIN you entered was incorrect."));

	}

}
