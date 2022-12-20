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

public class CM10003 {

	// private static final Object Webelement = null;
	private String clientNumber;
	private String pin;
	private String pid;
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

		// login

		new CM10001().test();

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

		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//aside[@id='left-panel']/nav/ul/li[5]/a/b/em")));
		// element.click();
		Thread.sleep(1000);
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.ng-binding
		// > b.collapse-sign > em.fa.fa-plus-square-o")));
		// element.click();
		WebElement successMesage = (new WebDriverWait(driver, 50)).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[13]/a[2]/b/em")));
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[13]/a[2]/b/em"))).click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.linkText("Generate Enrolment Credentials for Internal User")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNameInputId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNameInputId")));
		element.sendKeys("pdeploymentp");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		element.sendKeys("cvlasceanu@pacificgeotech.com");

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"number:11\"]")));
		element.click();
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"0\"]")));
		// element.click();
		Thread.sleep(500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'User is already active')]")));

	}

}
