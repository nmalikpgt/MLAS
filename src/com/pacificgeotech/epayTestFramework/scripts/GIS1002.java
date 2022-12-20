package com.pacificgeotech.epayTestFramework.scripts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

public class GIS1002 {
	private String claimId;
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

		CLM3005 clm3005 = new CLM3005();
		clm3005.test();
		setClaimId(clm3005.getClaimId());
		System.out.println("Claim ID is: " + clm3005.getClaimId());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Grid Management")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage Grid Cell Status")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element.sendKeys("43B03C239,43B03C238,43B03C219,43B03C218");
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/a/span")));
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/a/span")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("cellStatusId"))))
				.selectByVisibleText("Available");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"A\"]")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("cellStatus_1"))))
				.selectByVisibleText("Available");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='A'])[2]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("cellStatus_2")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("cellStatus_2"))))
				.selectByVisibleText("Available");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='A'])[3]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("cellStatus_3")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("cellStatus_3"))))
				.selectByVisibleText("Available");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='A'])[4]")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("reasonCode"))))
				.selectByVisibleText("Rehabilitation");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"1\"]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("uploadFileId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("uploadFileId")));
		element.sendKeys("C:\\Users\\cvlasceanu\\Desktop\\linux2.pdf");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		Thread.sleep(1500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'The cell status has been updated')]")));

	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public void checkStatusAndReason(String[] cellIds) throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		Statement stmt = con.createStatement();
		WebDriver driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		String sql = "select COUNT(CELL_KEY_ID) FROM MAM_SPATIAL.MTA_NTS_GRID_CELL WHERE CELL_KEY_ID IN ('43B03C239','43B03C238','43B03C219','43B03C218') and CELL_STATUS_CODE = 'A'AND CELL_REASON_CODE = 'M'";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println("ss" + rs.getString(1));
			if (rs.getInt(1) < 4) {
				System.out.println("abc");
				driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));

			}

		}

		con.commit();
		con.close();

	}

}
