package com.pacificgeotech.epayTestFramework.scripts;

import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class F8004 {

	private String clientNumber;
	private static String transactionId;
	private String claimId;
	private String pid;
	private String pin;
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
		

		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();
		this.setPid(lgn1001.getPid());
		System.out.println("pid from lgn1001 : " + lgn1001.getPid());
		setPin(lgn1001.getPin());
		setClientNumber(lgn1001.getClientNumber());

		// update MAAP

		// driver.get("http://rat1:20281/mlas/#/mlasDashboard?clientId=30001908&purpose=prospector");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update my MAAP (Mining Act Awareness Program)")));
		element.click();

		Thread.sleep(2000);
		// Alert alert = driver.switchTo().alert();

		// wait till two windows are not opened

		waitForNumberofWindowsToEqual(2);// this method is for wait

		Set handles = driver.getWindowHandles();

		String firstWinHandle = driver.getWindowHandle();
		handles.remove(firstWinHandle);

		String winHandle = (String) handles.iterator().next();

		if (winHandle != firstWinHandle) {

			// To retrieve the handle of second window, extracting the handle
			// which does not match to first window handle

			String secondWinHandle = winHandle; // Storing handle of second
												// window handle

			// Switch control to new window

			driver.switchTo().window(secondWinHandle);

			((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
			Thread.sleep(1000);
			WebElement element1 = driver.findElement(By.id("purpose-prospector"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element1);

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next")));
			element.click();
			driver.switchTo().window(firstWinHandle);
		}

		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[4]/a[2]/b/em")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Apply for Prospector's Licence")));
		element.click();

		/*
		 * ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
		 * "window.scrollTo(0, 2300)"); element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath
		 * ("//section[@id='widget-grid']/div[2]/back-next/div/div[3]/button")))
		 * ; Actions actions = new Actions(driver);
		 * actions.moveToElement(element).click().perform();
		 */
		Thread.sleep(1500);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/button")))
				.click();

		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId")));
		element.click();
		Thread.sleep(2000);

		String bodyText = driver.findElement(By.tagName("body")).getText();
		org.junit.Assert.assertFalse(bodyText.contains("Over the Counter Payment"));

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("onlinePaymentSltId")));
		element.click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='showSelectedPaymentId']/span")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("visa")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("submit")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardOwner")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardOwner")));
		element.sendKeys("test");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardNumber")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardNumber")));
		element.sendKeys("4030000010001234");
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnExpYear"))))
				.selectByVisibleText("2020");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardCvd")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardCvd")));
		element.sendKeys("123");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("submitButton")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("cancel")));
		element.click();
		new WebDriverWait(driver, 50).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nextToCfmPgIndexBtnId']/span")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='nextToCfmPgIndexBtnId']/span")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid")));
		element.click();
		Thread.sleep(1000);

		String bodyText1 = driver.findElement(By.tagName("body")).getText();
		org.junit.Assert.assertTrue(bodyText1.contains(
				"Application for Prospector's Licence accepted and Licence issued and activated. Click the link below to view or print the Prospector's Licence in PDF."));
		/*
		 * driverWait .until(ExpectedConditions.presenceOfElementLocated(By
		 * .cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
		 * .click(); Thread.sleep(2000);
		 * 
		 * WebElement _element1 =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name(
		 * "sign-out"))); _element1.click();
		 */

	}

	private void waitForNumberofWindowsToEqual(int i) {
		// TODO Auto-generated method stub

	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

}
