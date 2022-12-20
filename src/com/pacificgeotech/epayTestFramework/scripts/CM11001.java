package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

import junit.framework.Assert;

public class CM11001 {

	private WebDriver driver;

//	@After
//	public void tearDown() {
//		driver.getCurrentUrl();
//	}
//
//	@AfterClass
//	public static void afterClass() {
//		WebDriverManager.instance = null;
//	}

	@Test
	public void test() throws Exception {

		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		java.util.List<WebElement> elements = WebDriverManager.getElements();

		// login
		new LGN1002().test();

		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("appFrmId:loginImgId")));
		// element.click();

		// Selecet Admin
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Admin")));
		element.click();
		//element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Admin")));
		//element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Unenroll External User")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("emailInputId")));
		element.sendKeys("gcontor@pacificgeotech.com");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchBtnId")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("row_1")));
		element.click();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript(
				"window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Unenroll")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("confirmBtnId")));
		element.click();
		Thread.sleep(4000);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue(bodyText.contains("User was successfully unenrolled"));

	}

}
