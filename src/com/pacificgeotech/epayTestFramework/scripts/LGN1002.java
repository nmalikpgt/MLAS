package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class LGN1002 {

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
		WebDriverManager.getElements();

		String[] urlInfo = LGN1001.getLoginUrl();
		LGN1001 lgn1001 = new LGN1001();
		// Submit Notice of Claim Abandonment
		Thread.sleep(1000);
		// String [] loginURL = LGN1001.getLoginUrl();
		driver.get(urlInfo[2]);
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		element.sendKeys("admin");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
		element.sendKeys("@MLAS4you");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(By.id("mlasadminmenu")));
		Thread.sleep(1000);
		String bodyText4 = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Text not found!", bodyText4.contains("Admin"));

	}

}
