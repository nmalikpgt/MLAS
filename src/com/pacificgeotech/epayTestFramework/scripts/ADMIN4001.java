package com.pacificgeotech.epayTestFramework.scripts;

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

public class ADMIN4001 {
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
		Thread.sleep(4000);
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

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();

	}

}
