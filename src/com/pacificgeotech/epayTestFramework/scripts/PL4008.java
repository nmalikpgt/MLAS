package com.pacificgeotech.epayTestFramework.scripts;

import java.util.Calendar;

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

public class PL4008 {
	private String clientNumber;
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

		PL2001 pl2001 = new PL2001();
		pl2001.test();
		setClientNumber(pl2001.getClientNumber());

		// change expire date

		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Update Client Profile")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(pl2001.getClientNumber());
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next")));
		element.click();

		// setup date extension of time granted for 2 months in advance

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(cal.getTime());

		System.out.println(" date " + date);
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("maapExpiryDate")));
		element.clear();
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("maapExpiryDate")));
		element.sendKeys(date);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		Thread.sleep(1000);
		JavascriptExecutor js2 = ((JavascriptExecutor) driver);
		js2.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/div[2]/div/div[3]/a/span")))
				.click();
		Thread.sleep(1000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Client Profile updated successfully and client notified of changes.')]")));

	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

}
