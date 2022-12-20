package com.pacificgeotech.epayTestFramework.scripts;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class CM7010 {

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

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		java.util.List<WebElement> elements = WebDriverManager.getElements();

		CM7009 cm7009 = new CM7009();
		cm7009.test();
		setOrgclientNum(cm7009.getOrgclientNum());

		// logout
		/*
		 * element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath
		 * ("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/a/span"))
		 * ); element.click(); element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath
		 * (
		 * "//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span"
		 * ))); element.click();
		 */
		// remove agent
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage Agents")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(cm7009.getOrgclientNum());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Remove")));
		element.click();
		assertTrue(closeAlertAndGetItsText().matches("^Are you sure you wish to remove this agent[\\s\\S]$"));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("OK")));
		element.click();

	}

	private String closeAlertAndGetItsText() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getOrgclientNum() {
		return OrgclientNum;
	}

	public void setOrgclientNum(String orgclientNum) {
		OrgclientNum = orgclientNum;
	}

}
