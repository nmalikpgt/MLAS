package com.pacificgeotech.epayTestFramework.scripts;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;
import com.pacificgeotech.epayTestFramework.dbConnection.DbConnection;

public class CLM3014 {

	private String tenureNumberId = "101270";
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

		LGN1002 lgn1002 = new LGN1002();
		lgn1002.test();

		updateTenureStatus();
		// Thread.sleep(4000);
		System.out.println("Reached main test script...");
		driver.navigate().refresh();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[11]/a[2]/span"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Cancel Claim"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")))
				.sendKeys(tenureNumberId);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

	}

	public void updateTenureStatus() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		Statement stmt = con.createStatement();
		String sql = ("UPDATE MAM.MTA_TENURE SET TENURE_STATUS_CODE = 'C' WHERE TENURE_NUMBER_ID = '" + tenureNumberId
				+ "'");
		stmt.executeUpdate(sql);
		con.commit();
		con.close();
	}

}
