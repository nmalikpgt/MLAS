package com.pacificgeotech.epayTestFramework.scripts;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

public class DISP11123456 {

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

		ARAL aral = new ARAL();
		aral.test();
		setLeaseNo(aral.getLeaseNo());

		//addDisposition(aral.getLeaseNo());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Disposition Management"))).click();
		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Extend Lease Renewal Application Deadline")))
				.click();
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputLeaseNumberId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputLeaseNumberId")));
		element.sendKeys(aral.getLeaseNo());

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1000);

		WebElement TxtBoxContent = driver.findElement(By.cssSelector("[class='control-label-value ng-binding ng-scope']"));
		String Maxdate = TxtBoxContent.getText();
		System.out.println("App number " + Maxdate);
				
		String dt = Maxdate ;  // Start date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(dt));
		c.add(Calendar.DAY_OF_YEAR, 10);  // number of days to add
		dt = sdf.format(c.getTime());  // dt is now the new date
		System.out.println("Added Days: " + dt);
				
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("renewalApplicationExtensionDate")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("renewalApplicationExtensionDate")));
		element.sendKeys(dt);
			
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//*[contains(text(), 'Extend lease renewal applicaton deadline successfully.')]")));
	}

	public void addDisposition(String val1) throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("INSERT INTO MAM.MTA_DISPOSITION_SUBACCOUNT (TENURE_NUMBER_ID, DISPOSITION_ACCOUNT_NUM, DISPOSITION_SUBACCOUNT_NUM)  VALUES ('"
				+ val1 + "', 'T***0887' , '0091')");
		stmt.executeUpdate(sql);
		con.commit();
		con.close();

	}

}
