package com.pacificgeotech.epayTestFramework.scripts;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Date;

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

public class ADMIN4005 {

	private boolean acceptNextAlert = true;
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
		driver.get("http://cheetah-vm2:10281/mlas/login");
		driver.navigate().to("http://cheetah-vm2:10281/mlas/login");
		driver.navigate().refresh();
		CommonUtils.login();

		// driver.get("http://cheetah-vm2:10281/mlas/login#/mlasDashboard.jsf");http:

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("appFrmId:loginImgId")));
		element.click();

		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//aside[@id='left-panel']/nav/ul/li[5]/a/b/em")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[3]/ul[2]/li[3]/a/span")));
		element.click();
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[3]/ul[2]/li[3]/ul/li[5]/a/div")));
		element.click();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/div/div/div/div/button")));
		element.click();

		Thread.sleep(4000);
		Long tm = new Date().getTime();

		driver.findElement(By.id("title")).sendKeys("SecRoll" + tm);

		// collect SecRoll value
		String appNum = null;
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@orca3:1521:test", "PROXY_MAM",
				"PROXY_MAM");
		try {
			java.sql.Statement st = con.createStatement();
			String sql = ("SELECT * FROM (SELECT TITLE  FROM TP_mam.tp_ROLE ORDER BY ID DESC) WHERE ROWNUM = 1");
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				appNum = rs.getString(1);
			}

		} finally {
			con.close();
		}
		if (appNum == null) {
			throw new IllegalAccessError("Application number is empty");
		}

		String appNumStr = String.valueOf(appNum);
		System.out.println("Submitted application number: " + appNumStr);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("None")));
		element.click();

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("textarea[name=\"description\"]")));
		element.sendKeys("TEST1234");

		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@id='content']/div/div[2]/div[2]/div/button")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[3]/ul[2]/li[3]/a/span")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[3]/ul[2]/li[3]/ul/li[6]/a/span")));
		element.click();

		Thread.sleep(4000);

	}

}
