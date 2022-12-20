package com.pacificgeotech.epayTestFramework.scripts;

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

import com.pacificgeotech.epayTestFramework.core.CommonUtils;
import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class ADMIN2001 {

	private WebDriver driver;

	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}

	@SuppressWarnings("unused")
	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		java.util.List<WebElement> elements = WebDriverManager.getElements();

		// login

		driver.get("http://cheetah-vm2:10281/mlas/login");
		driver.navigate().to("http://cheetah-vm2:10281/mlas/login");
		driver.navigate().refresh();

		CommonUtils.login();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("appFrmId:loginImgId")));
		element.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//aside[@id='left-panel']/nav/ul/li[3]/a/b/em")))
				.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update Client Profile"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys("10000075");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();

		/*
		 * driverWait.until( ExpectedConditions.presenceOfElementLocated(By
		 * .cssSelector("option[value=\"3\"]"))).click();
		 */

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgName"))).sendKeys("steve's chop shop");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1")))
				.sendKeys("123 Maple Ave");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")))
				.sendKeys("gcontor@pacificgeotech.com");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();
		Thread.sleep(4000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Client Profile updated successfully and client notified of changes.')]")));

		// login
		// CommonUtils.login();
		// new LGN1001().test();

		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().refresh();
		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().to("http://rat1:20281/mlas/externalLogin");
		String pid1 = null;
		String pin1 = null;
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		java.sql.Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@orca3:1521:test", "PROXY_MAM",
				"PROXY_MAM");
		try {
			java.sql.Statement st = con1.createStatement();
			String sql = ("select HASHED_ONE_KEY_PID,HASHED_ONE_KEY_PIN from MAM.MTA_SEC_USER where ACCESS_LOGIN_ID in (select max(ACCESS_LOGIN_ID) from MAM.MTA_SEC_USER)");
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				pid1 = rs.getString("HASHED_ONE_KEY_PID");
				pin1 = rs.getString("HASHED_ONE_KEY_PIN");
			}

		} finally {
			con1.close();
		}
		if (pin1 == null) {
			throw new IllegalAccessError("Application number is empty");
		}
		if (pid1 == null) {
			throw new IllegalAccessError("Application number is empty");
		}
		String pinStr1 = String.valueOf(pin1);
		System.out.println("Submitted application number: " + pinStr1);
		String pidStr1 = String.valueOf(pid1);
		System.out.println("Submitted application number: " + pidStr1);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
		element.sendKeys(pid1);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
		element.sendKeys(pin1);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("viewMessageDetail_0_Id")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Download in PDF")));
		element.click();

	}

}
