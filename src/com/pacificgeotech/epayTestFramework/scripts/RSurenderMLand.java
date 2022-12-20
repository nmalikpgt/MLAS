package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;
import com.pacificgeotech.epayTestFramework.dbConnection.DbConnection;

import junit.framework.Assert;

public class RSurenderMLand {
	
	
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
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Record Surrender of Mining Land"))).click();
		Thread.sleep(1000);
		
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("tenureId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("tenureId")));
		element.sendKeys(aral.getLeaseNo());
		
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1000);
		
		WebElement element1=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(("selectReOpenStatus"))));
		Select toOpenStatus=new Select(element1);
		toOpenStatus.selectByIndex(1);
				
		
		int attach = 3;
		for (int i = 0; i < attach; i++)
		{
			attach(driver, driverWait, element);
		}

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
				.selectByVisibleText("Parcel Register Identification Number (PIN) from LRO");		
		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("docTypeCode_1"))))
				.selectByVisibleText("Registered Notice of Determination");
		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("docTypeCode_2"))))
				.selectByVisibleText("Affidavit");
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 0);
		Date today1 = calendar.getTime();
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		String todayA = dateFormat1.format(today1);
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("lroRegistrationDate")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("lroRegistrationDate")));
		element.sendKeys(todayA);
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("lroInstrumentNumber")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("lroInstrumentNumber")));
		element.sendKeys("1");
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'You have successfully completed a Record Surrender of Mining Land.')]")));
		
		
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
	private static void attach(WebDriver driver, WebDriverWait driverWait, WebElement element) throws InterruptedException{
		
		Thread.sleep(1000);
		/*Object file = null;
		try {
			file = new File(CLM2B003.class.getClassLoader().getResource("Flaring.pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file).exists());
		WebElement browseButton = driver.findElement(By.id("uploadFileId"));
		browseButton.sendKeys(((File) file).getAbsolutePath());*/
		driver.findElement(By.id("uploadFileId")).clear();
	    driver.findElement(By.id("uploadFileId")).sendKeys("C:\\Users\\cvlasceanu\\Desktop\\guide linux.pdf");
		
	}
	

}
