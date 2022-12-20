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

public class CM8001 {

	private String OrgclientNum;
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

		CM1B001 cm1b001 = new CM1B001();
		cm1b001.test();
		setOrgclientNum(cm1b001.getOrgclientNum());
		setClientNumber(cm1b001.getClientNumber());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage Agents"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(cm1b001.getOrgclientNum());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchClientId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchClientId")));
		element.sendKeys("158411");
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("158411 REGGIE LEBOEUF")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("158411 REGGIE LEBOEUF")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("addAgentBtnId")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("assessmentWorkManagerId")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'Agent status and permissions have been updated')]")));

		new WebDriverWait(driver, 50).until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'REGGIE LEBOEUF')]")));
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'YES')]")));
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Assessment Work Manager')]")));

	}


	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getOrgclientNum() {
		return OrgclientNum;
	}

	public void setOrgclientNum(String orgclientNum) {
		OrgclientNum = orgclientNum;
	}

}