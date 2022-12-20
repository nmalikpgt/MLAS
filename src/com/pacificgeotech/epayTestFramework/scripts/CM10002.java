package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class CM10002 {

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

		new LGN1002().test();
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//aside[@id='left-panel']/nav/ul/li[5]/a/b/em"))).click();
		// driverWait.until(
		// ExpectedConditions.presenceOfElementLocated(By.linkText("Staff"))).click();
		WebElement successMesage = (new WebDriverWait(driver, 50)).until(ExpectedConditions.visibilityOfElementLocated(
				By.linkText("Admin")));
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Admin"))).click();
		Thread.sleep(1000);
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.linkText("Generate Enrolment Credentials for Internal User")))
				.click();

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

		new WebDriverWait(driver, 50).until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'User Name Required')]")));
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'User Group is required')]")));
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Email address is required')]")));

	}

}