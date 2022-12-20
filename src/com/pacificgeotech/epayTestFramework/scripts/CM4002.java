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

public class CM4002 {

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

		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();
		// log out
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Automatic Test")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.pull-left.ng-binding")));
		element.click();
		LGN1002 lgn1002 = new LGN1002();
		lgn1002.test();
		setClientNumber(lgn1001.getClientNumber());
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.linkText("Client Management")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Client Management")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Change Client Name")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(lgn1001.getClientNumber());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName")));
		element.sendKeys("John");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
		element.sendKeys("Doe");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Client name has changed successfully.')]")));

	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

}
