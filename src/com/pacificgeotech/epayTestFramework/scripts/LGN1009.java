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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class LGN1009 {

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
		java.util.List<WebElement> elements = WebDriverManager.getElements();

		// login
		new LGN1002().test();

		// CommonUtils.login();
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@orca3:1521:test", "PROXY_MAM",
				"PROXY_MAM");

		con.setAutoCommit(false);
		StringWriter Deactivate = new StringWriter();

		Deactivate.append(

				" UPDATE MAM.MTA_SEC_USER" + " SET ACTIVATED_IND = 'Y'" + " WHERE USER_NAME = 'gcontor'");

		java.sql.Statement st = con.createStatement();
		System.out.println(Deactivate.toString());
		ResultSet rs = st.executeQuery(Deactivate.toString());
		// con.commit();

		StringWriter Deactivate1 = new StringWriter();

		Deactivate1.append(" UPDATE TP_MAM.TP_USER" + " SET DISABLED = 1" + " WHERE USERNAME = 'gcontor'");

		java.sql.Statement st1 = con.createStatement();
		System.out.println(Deactivate1.toString());
		ResultSet rs1 = st1.executeQuery(Deactivate1.toString());
		con.commit();

		// Thread.sleep(30000);

		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//aside[@id='left-panel']/nav/ul/li[5]/a/b/em")));
		element.click();
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("a.ng-binding > b.collapse-sign > em.fa.fa-plus-square-o")));
		element.click();
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.linkText("Generate Enrolment Credentials for Internal User")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("lastNameInputId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("lastNameInputId")));
		element.sendKeys("Contor");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("firstNameInputId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("firstNameInputId")));
		element.sendKeys("Geta");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("engPrefInputId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNameInputId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNameInputId")));
		element.sendKeys("gcontor");
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("#roleSelection > option[value=\"0\"]")));
		element.click();

		Thread.sleep(4000);
		new Select(driver.findElement(By.id("primaryPhoneType"))).selectByVisibleText("Home");
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("select[name=\"primary_phoneType\"] > option[value=\"0\"]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("primaryCountryCode")));
		element.sendKeys("1");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("primPhone")));
		element.sendKeys("(111) 111-1111");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		element.sendKeys("gcontor@pacificgeotech.com");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Event Number Id:')]")))
				.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		// String pid1 = null;
		String pin = null;
		String pass = null;
		String session = null;
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		java.sql.Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@orca3:1521:test", "PROXY_MAM",
				"PROXY_MAM");
		try {
			java.sql.Statement st2 = con1.createStatement();

			String sql3 = (" SELECT substr(TEXT, 591, 5) as TEXT FROM  MAM.MTA_NOTICE_EMAIL  WHERE EVENT_NUMBER_ID =  (SELECT MAX(EVENT_NUMBER_ID) FROM MAM.MTA_GECI_EVENT WHERE  SUBJECT = 'GECI - Generate Enrollment Credentials Internal')");

			ResultSet rs3 = st2.executeQuery(sql3);
			if (rs3.next()) {
				pin = rs3.getString("TEXT");
			}

			String sql2 = (" SELECT EVENT_NUMBER_ID, USERNAME, USER_SESSION_HASH_KEY " + " FROM  MAM.MTA_GECI_EVENT "
					+ " WHERE EVENT_NUMBER_ID = (SELECT MAX(EVENT_NUMBER_ID) FROM MAM.MTA_GECI_EVENT WHERE  USERNAME = 'gcontor')");
			ResultSet rs2 = st2.executeQuery(sql2);
			if (rs2.next()) {
				session = rs2.getString("USER_SESSION_HASH_KEY");
			}

			String sql4 = (" SELECT substr(utl_i18n.unescape_REFERENCE(TEXT), 708, 8) AS TEXT FROM  MAM.MTA_NOTICE_EMAIL  WHERE EVENT_NUMBER_ID = (SELECT MAX(EVENT_NUMBER_ID) FROM MAM.MTA_GECI_EVENT WHERE  SUBJECT = 'GECI - Generate Enrollment Credentials Internal')");
			ResultSet rs4 = st2.executeQuery(sql4);
			if (rs4.next()) {
				pass = rs4.getString("TEXT");
			}

		} finally {
			con1.close();
		}
		if (pin == null) {
			throw new IllegalAccessError("Application number is empty");
		}
		if (pass == null) {
			throw new IllegalAccessError("Application number is empty");
		}
		String pinStr1 = String.valueOf(pin);
		System.out.println("Submitted application number: " + pass);
		String pidStr1 = String.valueOf(pass);
		System.out.println("Submitted application number: " + pin);

		con.close();

		driver.get(
				"http://cheetah-vm2:10281/mlas/app/mlas/admin/staffMgm/enli/enliIndex.html#/admin/staffMgm/enli/enliInputForm?user="
						+ session);
		driver.navigate().refresh();
		// driver.get("http://rat1:20281/mlas/externalLogin");
		// driver.navigate().to("http://rat1:20281/mlas/externalLogin");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNameInputId")));
		element.sendKeys("gcontor");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("passwordInputId")));
		element.sendKeys(pass);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pinInputId")));
		element.sendKeys(pin);
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextToLogInId")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		element.sendKeys("gcontor");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
		element.sendKeys("Octubre_2015");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
		element.sendKeys(pin);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("appFrmId:loginImgId")));
		element.click();

	}

}
