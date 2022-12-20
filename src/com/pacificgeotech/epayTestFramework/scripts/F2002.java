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

public class F2002 {

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

		new LGN1001().test();
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("em.fa.fa-plus-square-o")));

		driver.findElements(By.cssSelector("em.fa.fa-plus-square-o")).get(2).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("View Payment"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button']"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Online Payment"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchId"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("genPaymentReceiptId"))).click();

	}

}
