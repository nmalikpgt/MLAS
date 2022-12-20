package com.pacificgeotech.epayTestFramework.scripts;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class ADMIN1004 {

	private WebDriver driver;

	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}

	@SuppressWarnings("unused")
	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		java.util.List<WebElement> elements = WebDriverManager.getElements();

		// login
		// CommonUtils.login();

		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().to("http://rat1:20281/mlas/externalLogin");
		driver.navigate().refresh();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
		element.sendKeys("A97uWBPBgAFE");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
		element.sendKeys("Rf5ZT");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		Thread.sleep(4000);

		assertTrue(!isElementPresent(By.linkText("Admin")));

	}

	private boolean isElementPresent(By linkText) {
		// TODO Auto-generated method stub
		return false;
	}

}
