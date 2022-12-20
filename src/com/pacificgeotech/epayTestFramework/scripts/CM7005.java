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

public class CM7005 {

	private WebDriver driver;

	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}

	public String getClientNumber1() {
		return clientNumber1;
	}

	public void setClientNumber1(String clientNumber1) {
		this.clientNumber1 = clientNumber1;
	}

	public String getClientNumber2() {
		return clientNumber2;
	}

	public void setClientNumber2(String clientNumber2) {
		this.clientNumber2 = clientNumber2;
	}

	private String clientNumber1;
	private String clientNumber2;

	@SuppressWarnings("unused")
	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		CM7001 cm7001 = new CM7001();
		cm7001.test();
		setClientNumber1(cm7001.getClientNumber1());
		setClientNumber2(cm7001.getClientNumber2());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[5]/a[2]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage Agents")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(getClientNumber2());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Remove")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='btn btn-labeled btn-success']")));
		element.click();
		// assertTrue(closeAlertAndGetItsText().matches("^Are you sure you wish
		// to remove this agent[\\s\\S]$"));
	}

	private String closeAlertAndGetItsText() {
		// TODO Auto-generated method stub
		return null;
	}
}
