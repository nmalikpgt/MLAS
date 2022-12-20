package com.pacificgeotech.epayTestFramework.scripts;

import static org.junit.Assert.assertTrue;

import java.sql.DriverManager;
import java.sql.ResultSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class ADMIN7009 {
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

		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().to("http://rat1:20281/mlas/externalLogin");
		driver.navigate().refresh();

		String pid = null;
		String pin = null;
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@orca3:1521:test", "PROXY_MAM",
				"PROXY_MAM");
		try {
			java.sql.Statement st = con.createStatement();
			String sql = ("select HASHED_ONE_KEY_PID,HASHED_ONE_KEY_PIN from MAM.MTA_SEC_USER where ACCESS_LOGIN_ID in (select max(ACCESS_LOGIN_ID) from MAM.MTA_SEC_USER)");
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				pid = rs.getString("HASHED_ONE_KEY_PID");
				pin = rs.getString("HASHED_ONE_KEY_PIN");
			}

		} finally {
			con.close();
		}
		if (pin == null) {
			throw new IllegalAccessError("Application number is empty");
		}
		if (pid == null) {
			throw new IllegalAccessError("Application number is empty");
		}
		String pinStr = String.valueOf(pin);
		System.out.println("Submitted application number: " + pinStr);
		String pidStr = String.valueOf(pid);
		System.out.println("Submitted application number: " + pidStr);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
		element.sendKeys(pid);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
		element.sendKeys(pin);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		Thread.sleep(4000);

		assertTrue(!isElementPresent(By.linkText("Admin")));

	}

	private boolean isElementPresent(By linkText) {
		// TODO Auto-generated method stub
		return false;

	}
}
