package com.pacificgeotech.epayTestFramework.scripts;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import com.pacificgeotech.epayTestFramework.dbConnection.DbConnection;

public class DISP12123456 {

	private WebDriver driver;

	public String getLeaseNo() {
		return LeaseNo;
	}

	public void setLeaseNo(String leaseNo) {
		LeaseNo = leaseNo;
	}

	private String LeaseNo;

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

		DISP912345 disp912345 = new DISP912345();
		disp912345.test();
		setLeaseNo(disp912345.getLeaseNo());

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage Mining Land Reopening")))
				.click();
		Thread.sleep(1000);

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("updateType")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("reopenStatusId")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("reopenStatusId"))))
				.selectByVisibleText("Not To Open");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Dashboard")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage Mining Land Reopening")));
		element.click();
		// ERROR: Caught exception [Error: Dom locators are not implemented
		// yet!]
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("i[name=\"arrows-v\"]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("i[name=\"caret-up\"]")));
		element.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='listType' and @value='NO']")))
				.click();
		// select tenure
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("reopenStatusId"))))
				.selectByVisibleText("Open");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Dashboard")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage Mining Land Reopening")));
		element.click();

		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='listType' and @value='O']")))
				.click();

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("reopenStatusId"))))
				.selectByVisibleText("Impending open");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option.ng-binding.ng-scope")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'You have successfully completed Manage Mining Land Reopening.')]")));

	}
}
