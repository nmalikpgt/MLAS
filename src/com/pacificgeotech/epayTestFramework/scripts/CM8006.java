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

public class CM8006 {
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

		CM8001 cm8001 = new CM8001();
		cm8001.test();
		setOrgclientNum(cm8001.getOrgclientNum());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage Agents"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(cm8001.getOrgclientNum());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//tr[@id='removeId']/td[5]/a)[2]")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("profileManagerId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("checkedAll")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm"))).click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'Agent status and permissions have been updated')]")));

	}

}