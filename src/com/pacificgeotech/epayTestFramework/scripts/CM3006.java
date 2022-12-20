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

public class CM3006 {

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

		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Client Management")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update Client Profile")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")));
		element.sendKeys("130013");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next")));
		element.click();

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By
						.xpath("//*[contains(text(), 'You do not have permission to act on behalf of this submitter')]")))
				.click();

	}

}
