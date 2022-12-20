package com.pacificgeotech.epayTestFramework.scripts;

import java.io.StringWriter;
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

public class CM10004 {

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

		new CM10001().test();

		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		// java.sql.Connection con =
		// DriverManager.getConnection("jdbc:oracle:thin:@orca3:1521:test",
		// "PROXY_MAM", "PROXY_MAM");
		java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@rat3:1521:test", "PROXY_MAM",
				"PROXY_MAM");

		StringWriter Deactivate = new StringWriter();

		Deactivate.append(

				" UPDATE MAM.MTA_SEC_USER" + " SET ACTIVATED_IND = 'Y'" + " WHERE USER_NAME = 'pdeploymentp'");

		java.sql.Statement st = con.createStatement();
		System.out.println(Deactivate.toString());
		ResultSet rs = st.executeQuery(Deactivate.toString());
		con.setAutoCommit(false);
		con.commit();

		StringWriter Deactivate1 = new StringWriter();

		Deactivate1.append(" UPDATE TP_MAM.TP_USER" + " SET DISABLED = 1" + " WHERE USERNAME = 'pdeploymentp'");

		java.sql.Statement st1 = con.createStatement();
		System.out.println(Deactivate1.toString());
		ResultSet rs1 = st1.executeQuery(Deactivate1.toString());
		con.setAutoCommit(false);
		con.commit();

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextToLogInId")));
		element.click();

		// Login as admin and try to re-enroll

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		element.sendKeys("admin");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
		element.sendKeys("@MLAS4you");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		// WebElement dashboard = (new WebDriverWait(driver,
		// 50)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//aside[@id='left-panel']/nav/ul/li[5]/a/b/em")));
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//aside[@id='left-panel']/nav/ul/li[5]/a/b/em")));
		// element.click();
		WebElement successMesage = (new WebDriverWait(driver, 50)).until(ExpectedConditions.visibilityOfElementLocated(
				By.linkText("Admin")));
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Admin"))).click();
		Thread.sleep(1000);

		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.ng-binding
		// > b.collapse-sign > em.fa.fa-plus-square-o")));
		// element.click();
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.linkText("Generate Enrolment Credentials for Internal User")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNameInputId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNameInputId")));
		element.sendKeys("pdeploymentp");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		element.sendKeys("cvlasceanu@pacificgeotech.com");

		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"5\"]")));
		// element.click();

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='groupSelection']/option[1]")));
		element.click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		WebElement successMesage1 = (new WebDriverWait(driver, 50))
				.until(ExpectedConditions.elementToBeClickable(By.id("nextBtnId")));
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Dashboard')]")));

		String hashkey = null;
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		// java.sql.Connection con1 =
		// DriverManager.getConnection("jdbc:oracle:thin:@orca3:1521:test",
		// "PROXY_MAM", "PROXY_MAM");
		java.sql.Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@rat3:1521:test", "PROXY_MAM",
				"PROXY_MAM");
		try {
			java.sql.Statement st2 = con1.createStatement();
			String sql = ("select user_session_hash_key as hashkey from MAM.MTA_GECI_EVENT where event_number_id in (SELECT MAX(EVENT_NUMBER_ID) AS USER_SESSION_HASH_KEY FROM MAM.MTA_GECI_EVENT)");
			ResultSet rs2 = st2.executeQuery(sql);

			if (rs2.next()) {

				hashkey = rs2.getString("hashkey");
				System.out.println("Haskey is " + hashkey);
			}

		} finally {
			con1.close();
		}

		if (hashkey == null) {
			throw new IllegalAccessError("Application number is empty");
		}

		String pin = null;
		String pass = null;
		// java.sql.Connection con2 =
		// DriverManager.getConnection("jdbc:oracle:thin:@orca3:1521:test",
		// "PROXY_MAM", "PROXY_MAM");
		java.sql.Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@rat3:1521:test", "PROXY_MAM",
				"PROXY_MAM");
		try {
			java.sql.Statement st3 = con2.createStatement();
			String sql1 = ("update MAM.MTA_GECI_EVENT set HASHED_PIN = 'f7GYun2AMdBp8N/FZcGXe6msvC6buHvQtJkWMfps+SI=$f8VBu++bKDwW3Ymv8f05y3YnQAUeTXzCu6itTndEl1M=', HASHED_ONE_TIME_PASSWORD = 'CjtaqzNGcHUpFrYor37oqVfoQko9LiqfowJ5cO2z7ww=$64B/TVcyoVcgc0AcHqe2OxunqHrCG4Lz5TaCBAzIWZw='  WHERE USERNAME = 'pdeploymentp'");
			st3.executeUpdate(sql1);

		} finally {
			con2.close();
		}

		// driver.quit();

		driver.get("http://rat1:20281/mlas/enrol/enliIndex.html#/admin/staffMgm/enli/enliInputForm?user=" + hashkey);
		driver.navigate().refresh();
		driver.get("http://rat1:20281/mlas/enrol/enliIndex.html#/admin/staffMgm/enli/enliInputForm?user=" + hashkey);
		driver.navigate()
				.to("http://rat1:20281/mlas/enrol/enliIndex.html#/admin/staffMgm/enli/enliInputForm?user=" + hashkey);

		// Enroll internal user

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNameInputId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNameInputId")));
		element.sendKeys("pdeploymentp");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("passwordInputId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("passwordInputId")));
		element.sendKeys("79v^7eWE");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pinInputId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pinInputId")));
		element.sendKeys("c88oB");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		Thread.sleep(1000);
		String bodyText = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))).getText();
		org.junit.Assert.assertTrue(
				bodyText.contains("You have successfully completed user enrollment. Please click 'Next' to login."));

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextToLogInId")));
		element.click();

		// Login as admin and check the role

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		element.sendKeys("pdeploymentp");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
		element.sendKeys("Deploy2009");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();
		// new WebDriverWait(driver,
		// 50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//aside[@id='left-panel']/nav/ul/li[3]/a/b/em")));
		// element = driverWait.until(
		// ExpectedConditions.presenceOfElementLocated(By.xpath("//aside[@id='left-panel']/nav/ul/li[3]/a/b/em")));
		// element.click();

		// Check Profile
		/*
		 * element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.
		 * cssSelector("#profileMenuId > span.header-label.ng-binding")));
		 * element.click(); new WebDriverWait(driver,
		 * 50).until(ExpectedConditions.elementToBeClickable(By.cssSelector(
		 * "li.usr-block.open > ul.dropdown-menu > li > a > span.pull-left.ng-binding"
		 * ))); element = driverWait.until(
		 * ExpectedConditions.presenceOfElementLocated(By.cssSelector(
		 * "li.usr-block.open > ul.dropdown-menu > li > a > span.pull-left.ng-binding"
		 * ))); element.click();
		 */
		// stage2
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/a/span")))
				.click();
		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li/a/div"))).click();
		Thread.sleep(1000);

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Registrar Administrator')]")));

		// new WebDriverWait(driver,
		// 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),
		// 'User is already active')]")));

	}

}
