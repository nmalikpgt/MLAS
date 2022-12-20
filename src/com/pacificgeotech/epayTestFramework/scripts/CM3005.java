package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.CommonUtils;
import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class CM3005 {

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

		LGN1002 lgn1002 = new LGN1002();
		lgn1002.test();
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Client Management")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Client Management")));
		element.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update Client Profile"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Submitter is Required')]")))
				.click();

	}

}
