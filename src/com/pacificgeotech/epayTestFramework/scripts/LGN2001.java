package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class LGN2001 {

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
		// CommonUtils.login();
		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();

		// logout
		Thread.sleep(1000);
//		driverWait.until(ExpectedConditions
	//			.presenceOfElementLocated(By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[2]/a/span")))
	//			.click();
	//	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.clearfix"))).click();

		/*
		 * driver.get("http://rat1:20281/mlas/externalLogin");
		 * driver.navigate().to("http://rat1:20281/mlas/externalLogin");
		 * driver.navigate().refresh();
		 * 
		 */

		/*
		 * element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "pid"))); element.sendKeys("kdN2EFNMKIhh"); element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "loginSubmit"))); element.click(); Thread.sleep(4000); element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "pin"))); element.sendKeys("whdUO"); element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "loginSubmit"))); element.click(); Thread.sleep(4000); element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.
		 * cssSelector("li.usr-block > a.dropdown-toggle"))); element.click();
		 * Thread.sleep(4000); element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath
		 * (
		 * "//header[@id='header']/cata-header/div/div[3]/ul[2]/li[5]/ul/li[3]/a/div"
		 * ))); element.click();
		 * 
		 */

	}

}
