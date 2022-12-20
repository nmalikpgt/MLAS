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

public class REncumbrance {

	private String claimId;
	private WebDriver driver;

	
	  @After public void tearDown() { driver.getCurrentUrl(); }
	  
	  @AfterClass public static void afterClass() { WebDriverManager.instance =
	  null; }
	 

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		MP5003 mp5003 = new MP5003();
		mp5003.test();
		setClaimId(mp5003.getClaimId());

		addEnc(mp5003.getClaimId());

		// remove encumbrance

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Claim Management"))).click();
		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Remove Encumbrance"))).click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimIds")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimIds")));
		element.sendKeys(mp5003.getClaimId());
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'The removal of encumbrances on the identified claims has been successful. Work requirements will be updated on the claim on the next anniversary date.')]")));
	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public void addEnc(String val1) throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		String sql = ("INSERT INTO MAM.MTA_TENURE_ENCUMBRAN_GRID_XREF (TENURE_NUMBER_ID, CELL_KEY_ID) VALUES ('" + val1
				+ "', '43B03C219')");
		stmt.executeUpdate(sql);
		con.commit();
		con.close();
	}

}
