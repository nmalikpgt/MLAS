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

public class CM3003 {

	private String OrgclientNum;

	private WebDriver driver;

	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}

	public String getOrgclientNum() {
		return OrgclientNum;
	}

	public void setOrgclientNum(String orgclientNum) {
		OrgclientNum = orgclientNum;
	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		java.util.List<WebElement> elements = WebDriverManager.getElements();

		CM1B001 cm1b001 = new CM1B001();
		cm1b001.test();
		setOrgclientNum(cm1b001.getOrgclientNum());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update Client Profile")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(cm1b001.getOrgclientNum());

		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/a/span")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1")));
		element.sendKeys("90 Test street");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
		element.sendKeys("cvlasceanu@pacificgeotech.com");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Client Profile updated successfully and client notified of changes.')]")));

	}

}
