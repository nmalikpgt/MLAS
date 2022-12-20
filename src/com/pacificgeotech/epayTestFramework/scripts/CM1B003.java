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

public class CM1B003 {

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
		new LGN1001().test();
		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[5]/a[2]/b/em"))).click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register Organization")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(10000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		Thread.sleep(3000);

		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),
		// 'Organization Name is Required')]"))).click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Organization Type is required')]")))
				.click();
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Mailing address line 1 is required')]")))
				.click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Mailing city is required')]")))
				.click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Mailing city is required')]")))
				.click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Mailing Country is required')]")))
				.click();
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Billing address line 1 is required')]")))
				.click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Billing city is required')]")))
				.click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Billing Country is required')]")))
				.click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Primary Phone Type is required')]")))
				.click();

		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Primary Phone Number is required')]")))
				.click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Email address is required')]")))
				.click();

	}
}
