package com.pacificgeotech.epayTestFramework.scripts;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class PL4001 {

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

		Thread.sleep(2000);

		new LGN1002().test();
		Thread.sleep(2000);

		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.linkText("Admin")));

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Admin"))).click();

		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Change Prospector Licence Expiry Date")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(clientNumber);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();
		Thread.sleep(2000);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, +60);
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(cal.getTime());

		System.out.println(" date " + date);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).sendKeys(date);
		Thread.sleep(3000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@id='wid-id-0']/div/div/div/fieldset/div[4]/div/textarea")));
		element.clear();
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@id='wid-id-0']/div/div/div/fieldset/div[4]/div/textarea")));
		element.sendKeys(
				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim.");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm"))).click();

		Thread.sleep(2000);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		org.junit.Assert.assertTrue(bodyText.contains(
				"The Prospector Licence Expiry Date was successfully changed and a notification sent to the Prospector"));

	}

}
