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

public class RRWMLTaxOrInterest {
	

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
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Record Reduction or Waiver of Mining Land Tax or Interest"))).click();
		Thread.sleep(1000);
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("tenureNumberId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("tenureNumberId")));
		element.sendKeys(aral.getLeaseNo());
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1000);
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ministryDecisionOnInterestId"))))
		.selectByVisibleText("Reduction");
		
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ministryDecisionOnInterestReductionAmountId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ministryDecisionOnInterestReductionAmountId")));
		element.sendKeys("1");
		
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("approverCommentsId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("approverCommentsId")));
		element.sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Null");
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		
		Thread.sleep(1500);
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		
		 new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//*[contains(text(), 'Reduction or Waiver of Mining Land Tax or Interest has successfully recorded.')]")));
		
		
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
