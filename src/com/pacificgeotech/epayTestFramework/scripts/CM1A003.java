package com.pacificgeotech.epayTestFramework.scripts;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.CommonUtils;
import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

import junit.framework.Assert;

public class CM1A003 {

	private String pin;
	private String pid;
	private String clientNumber;
	private Integer orgClientId;
	private String orgCode;
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

//		updateOrdCodeUsedStatus();
//		getOrgClientIdAndCode();

		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();
		setClientNumber(lgn1001.getClientNumber());

		// update client profile

		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(

				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[7]/a[2]/b/em")));

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[7]/a[2]/b/em"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update Client Profile"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(lgn1001.getClientNumber());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1"))).sendKeys("12 oak st");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")))
				.click();

		Thread.sleep(10000);

		// MAAP update

		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-1']/div/div/div[9]/div/fieldset/div/div/select"))))
						.selectByVisibleText("Yes");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.btn.btn-default")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[47]")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-1']/div/div/div[9]/div[2]/fieldset/div/div/select"))))
						.selectByVisibleText("Yes");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='Y'])")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("maapExpiryDate")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("maapExpiryDate")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("maapExpiryDate")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("maapExpiryDate")));
		element.sendKeys("2020 January 01");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-1']/div/div/div[11]/div/fieldset/div/div/textarea")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-1']/div/div/div[11]/div/fieldset/div/div/textarea")));
		element.sendKeys(
				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, ");
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();

		Thread.sleep(10000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();
		Thread.sleep(1000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Client Profile updated successfully and client notified of changes.')]")));

		// prospector licence
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[6]/a[2]/b/em"))).click();

		// Thread.sleep(1000);
		/*
		 * element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(
		 * By.xpath(
		 * "//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[6]/a[2]/b/em"
		 * )));
		 */

		// element.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Apply for Prospector's Licence"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(lgn1001.getClientNumber());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();

		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/a/span")));
		// Actions actions = new Actions(driver);
		// actions.moveToElement(element).click().perform();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next")));
		// element.click();
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next")));
		element.click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next")));
		Thread.sleep(1000);
		Actions actions1 = new Actions(driver);
		actions1.moveToElement(element).click().perform();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next")));
		Actions actions2 = new Actions(driver);
		actions2.moveToElement(element).click().perform();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId")));
		element.click();
		Thread.sleep(1000);

		// new

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("otcPaymentSltId"))).click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1500);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))).sendKeys("Hanks Tom");
		Thread.sleep(1500);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("30000478 Hanks Tom"))).click();
		Thread.sleep(1500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeInput_0_id"))))
				.selectByVisibleText("Cash");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"object:771\"]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("paymentAmount_0"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("paymentAmount_0"))).sendKeys("40.00");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("paymentTypeNumber_0"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("paymentTypeNumber_0")))
				.sendKeys("122121212");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();
		Thread.sleep(1500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextToConfirmLnkId")));
		element.click();
		Thread.sleep(1500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextToCfmPgIndexId")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid")));
		element.click();

		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Text not found!", bodyText.contains(
				"Application for Prospector's Licence accepted and Licence issued and activated. Click the link below to view or print the Prospector's Licence in PDF."));

	}

	private void updateOrdCodeUsedStatus() throws ClassNotFoundException, SQLException {

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@rat3:1521:test", "PROXY_MAM", "PROXY_MAM");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Statement stmt = con.createStatement();

		String sql = "update mam.MTA_ORGANIZATION o set o.REGISTRATION_CODE_USED_IND = 'N' where o.ORGANIZATION_ID='388'";
		int s = stmt.executeUpdate(sql);

		con.commit();
		con.close();

	}

	private void getOrgClientIdAndCode() throws ClassNotFoundException, SQLException {

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@rat3:1521:test", "PROXY_MAM", "PROXY_MAM");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Statement stmt = con.createStatement();

		String sql1 = "select c.CLIENT_NUMBER_ID,o.ENCRYPTED_REGISTRATION_CODE from mam.MTA_ORGANIZATION o "
				+ "inner join mam.mta_person_organization po on o.ORGANIZATION_ID = po.ORGANIZATION_ID "
				+ "inner join mam.mta_mem_client c on po.PERSON_ORGANIZATION_ID = c.PERSON_ORGANIZATION_ID "
				+ "where o.ORGANIZATION_ID = 388 and o.REGISTRATION_CODE_USED_IND = 'N'";

		ResultSet rs = stmt.executeQuery(sql1);
		while (rs.next()) {
			setOrgClientId(rs.getInt(1));
			setOrgCode(rs.getString(2));
		}
		con.commit();
		con.close();
	}

	private String getPid() {
		return this.pid;
	}

	private void setPid(String pid) {
		this.pid = pid;
	}

	private String getPin() {
		return this.pin;
	}

	private void setPin(String pin) {
		this.pin = pin;
	}

	public String getClientNumber() {
		return this.clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public Integer getOrgClientId() {
		return this.orgClientId;
	}

	public void setOrgClientId(Integer orgClientId) {
		this.orgClientId = orgClientId;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

}
