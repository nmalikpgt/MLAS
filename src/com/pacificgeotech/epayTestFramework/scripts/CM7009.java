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

public class CM7009 {

	private String clientNumber;
	private String OrgclientNum;
	private WebDriver driver;

	/*@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}*/

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		java.util.List<WebElement> elements = WebDriverManager.getElements();

		CM1B008 cm1b008 = new CM1B008();
		cm1b008.test();
		setOrgclientNum(cm1b008.getOrgclientNum());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage Agents")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(cm1b008.getOrgclientNum());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Automatic Test')]")));
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'YES')]")));

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchClientId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchClientId")));
		element.sendKeys("158411");
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("158411 REGGIE LEBOEUF")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("158411 REGGIE LEBOEUF")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("addAgentBtnId")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("profileManagerId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("checkedAll")));
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

	}

	public String getOrgclientNum() {
		return OrgclientNum;
	}

	public void setOrgclientNum(String orgclientNum) {
		OrgclientNum = orgclientNum;
	}

}
