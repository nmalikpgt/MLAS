package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class PL4004 {

	private String pin;

	private String pid;
	private String clientNumber;
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
		PL1001 pl1001 = new PL1001();
		pl1001.test();
		String clientNumber = pl1001.getClientNumber();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
				.click();
		Thread.sleep(2000);

		WebElement _element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out")));

		Actions actions = new Actions(driver);

		actions.moveToElement(_element).click().perform();
		Thread.sleep(2000);

		new LGN1002().test();
		Thread.sleep(2000);

		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.linkText("Admin")));

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Admin"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update Prospector Licence"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(clientNumber);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();
		Thread.sleep(2000);

		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("optionType"))))
				.selectByVisibleText("Revoked");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='R']")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("commentinputid")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("commentinputid")));
		element.sendKeys("test");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Confirm")));
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm"))).click();
		Thread.sleep(2000);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Text not found!", bodyText.contains(
				"The licence status was successfully changed and a notification has been sent to the Prospector"));

		/*
		 * new WebDriverWait(driver,
		 * 50).until(ExpectedConditions.visibilityOfElementLocated( By.xpath(
		 * "//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[13]/a[2]/b/em"
		 * )));
		 * 
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(
		 * By.xpath(
		 * "//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[13]/a[2]/b/em"
		 * ))).click();
		 */
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Change Prospector Licence Expiry Date")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(clientNumber);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();
		Thread.sleep(2000);

	}

}
