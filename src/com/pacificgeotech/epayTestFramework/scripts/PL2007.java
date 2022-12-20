package com.pacificgeotech.epayTestFramework.scripts;

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

public class PL2007 {

	private String pin;

	private String pid;

	private WebDriver driver;
	private String clientNumber;

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

		CM2002 cm2002 = new CM2002();
		cm2002.test();
		
		setClientNumber(cm2002.getClientNumber());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update Client Profile"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(cm2002.getClientNumber());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next"))).click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(2000);

		// MAAP update
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='wid-id-1']/div/div/div[9]/div/fieldset/div/div/select")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-1']/div/div/div[9]/div/fieldset/div/div/select")));
		element.click();
		Thread.sleep(2000);
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-1']/div/div/div[9]/div/fieldset/div/div/select"))))
						.selectByVisibleText("Yes");
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("maapInfo.maapCompletedDate")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("maapInfo.maapCompletedDate")));
		element.sendKeys("2015-06-01");
		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();

		Thread.sleep(4000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm"))).click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Client Profile updated successfully and client notified of changes.')]")));

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Prospector Licensing"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Apply for Prospector's Licence"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(cm2002.getClientNumber());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(2000);

//		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm"))).click();

		String bodyText1 = driver.findElement(By.tagName("body")).getText();
		org.junit.Assert.assertTrue(bodyText1.contains(
				"Our records show that you have not completed MAAP within the past 60 days of your application for a Prospector's Licence"));
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

}
