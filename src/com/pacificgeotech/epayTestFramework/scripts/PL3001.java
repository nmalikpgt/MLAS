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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class PL3001 {
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

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();
		PL1001 pl1001 = new PL1001();
		pl1001.test();
		// String clientNumber = pl1001.getClientNumber();
		setClientNumber(pl1001.getClientNumber());
		System.out.println("Clinet number is: " + clientNumber);
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
				.click();
		Thread.sleep(2000);

		WebElement _element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out")));

		Actions actions = new Actions(driver);

		new LGN1002().test();
		Thread.sleep(2000);

		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.linkText("Admin")));

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Admin"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update Prospector Licence"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(pl1001.getClientNumber());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();

		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("optionType"))))
				.selectByVisibleText("Suspended");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option.ng-binding")));
		element.click();

		// setup date extension of time granted for 2 months in advance

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(cal.getTime());

		System.out.println(" date " + date);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("periodEndDateId")));
		element.sendKeys(date);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("commentinputid")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("commentinputid")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("commentinputid")));
		element.sendKeys(
				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aen");
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm")));
		element.click();
		Thread.sleep(500);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		org.junit.Assert.assertTrue(bodyText.contains(
				"The licence status was successfully changed and a notification has been sent to the Prospector."));
	}

}
