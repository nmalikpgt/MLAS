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

public class LGN1004 {

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

		// login
		new LGN1002().test();

		element = driver.findElement(By.id("username"));
		element.sendKeys("test");

		element = driver.findElement(By.id("password"));
		element.sendKeys("test");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("logInError")));
		element.click();
		String bodyText = driver.findElement(By.id("logInError")).getText();
		Assert.assertTrue("Text not found!", bodyText.equals("There was a problem logging in. Please try again."));

	}

}
