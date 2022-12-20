package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class CM3010 {

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

	public String getClientNum() {
		return clientNumber;
	}

	public void setClientNum(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	@Test
	public void test() throws Exception {

		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		java.util.List<WebElement> elements = WebDriverManager.getElements();

		CM5001 cm5001 = new CM5001();
		cm5001.test();
		setClientNum(cm5001.getClientNumber());
		System.out.println("Client num is: " + cm5001.getClientNumber());
		// update profile
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update Client Profile")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(cm5001.getClientNumber());

		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/a/span")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientStatusId"))))
				.selectByVisibleText("Active");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"string:A\"]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/div[2]/div/div[3]/a/span")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Client Profile updated successfully and client notified of changes.')]")));
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Active')]")));

	}

}
