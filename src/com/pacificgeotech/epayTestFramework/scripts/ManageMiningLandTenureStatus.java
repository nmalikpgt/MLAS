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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;
import com.pacificgeotech.epayTestFramework.dbConnection.DbConnection;

public class ManageMiningLandTenureStatus {
	

	private WebDriver driver;
	public String getLeaseNo() {
		return LeaseNo;
	}

	public void setLeaseNo(String string) {
		LeaseNo = string;
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

		ARAL aral = new ARAL();
		aral.test();
		setLeaseNo(aral.getLeaseNo());
		
		//addDisposition(aral.getLeaseNo());
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Disposition Management"))).click();
		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage Mining Land Tenure Status"))).click();
		Thread.sleep(1000);
	
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningRightNumber")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningRightNumber")));
		element.sendKeys(aral.getLeaseNo());
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		
		
		Thread.sleep(1000);
//		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("dispositionStatus"))))
//		.selectByVisibleText("ACR Land");
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		
		 new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//*[contains(text(), 'Mining tenure land status updated successfully')]")));
		
	}
	
	
	public void addDisposition(String val1) throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("INSERT INTO MAM.MTA_DISPOSITION_SUBACCOUNT (TENURE_NUMBER_ID, DISPOSITION_ACCOUNT_NUM, DISPOSITION_SUBACCOUNT_NUM)  VALUES ('" + val1 + "', 'T***0887' , '0091')");
		stmt.executeUpdate(sql);
		con.commit();
		con.close();
		
	}

}
