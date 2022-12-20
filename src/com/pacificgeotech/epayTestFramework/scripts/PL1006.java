package com.pacificgeotech.epayTestFramework.scripts;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Set;

import com.pacificgeotech.epayTestFramework.core.CommonUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

import junit.framework.Assert;

import static com.pacificgeotech.epayTestFramework.scripts.LGN1001.getLoginUrl;

public class PL1006 {
	private static String pin;
	private static String pid;
	private static String clientNumber;
	private WebDriver driver;

//	@After
//	public void tearDown() {
//		driver.getCurrentUrl();
//	}
//
//	@AfterClass
//	public static void afterClass() {
//		WebDriverManager.instance = null;
//	}

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

		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Change Prospector Licence Expiry Date")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(clientNumber);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();
		Thread.sleep(2000);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(cal.getTime());

		System.out.println(" date " + date);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).sendKeys(date);
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

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/a/span")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span")));
		element.click();
		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();

		// update MAAP
/*
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update my MAAP")));
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
			// driver.manage().window().maximize();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("purpose-prospector")));
			Actions actions1 = new Actions(driver);
			actions1.moveToElement(element).click().perform();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next")));
			element.click();
			driver.switchTo().window(firstWinHandle);
		}

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[4]/a[2]/b/em")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Apply for Prospector's Licence")));
		element.click();

		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/a/span")));
		Actions actions2 = new Actions(driver);
		actions2.moveToElement(element).click().perform();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next")));
		element.click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("onlinePaymentSltId")));
		element.click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);




		CommonUtils.login();
		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Prospector Licensing")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Apply for Prospector's Licence")));
		element.click();


		Thread.sleep(1500);


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder=\"Enter Submitter Id\"]")));
		element.sendKeys(clientNumber);
		Thread.sleep(1500);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();


		JavascriptExecutor js1 = ((JavascriptExecutor) driver);
		js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions
						.presenceOfElementLocated(By.id("confirmLnkId")))
				.click();




		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId")));
		element.click();
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("otcPaymentSltId")));
		element.click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='showSelectedPaymentId']/span")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder=\"Input part of Client ID or Name\"]")));
		element.sendKeys(clientNumber);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"payorAutoSelectDivId\"]/div[1]/div/div[2]/div/a")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeInput_0_id")));
		element.click();
		Thread.sleep(1000);


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"paymentTypeInput_0_id\"]/option[2]")));
		element.click();
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentAmoutInput_0_id")));
		element.sendKeys("40");


		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeNumber_0_id")));
		element.sendKeys("40");
		Thread.sleep(1500);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1000);


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextToConfirmLnkId")));
		element.click();
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextToCfmPgIndexId")));
		element.click();
		Thread.sleep(1000);


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"header\"]/cata-header/div[1]/div[2]/ul[2]/li[3]/a/span[1]")));
		element.click();
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"header\"]/cata-header/div[1]/div[2]/ul[2]/li[3]/ul/li[3]/a/div")));
		element.click();


		Thread.sleep(1000);
		String[] urlInfo = getLoginUrl();
		Thread.sleep(1000);
		driver.navigate().to(urlInfo[0]);
		Thread.sleep(2000);


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
		element.sendKeys(lgn1001.getPid());

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
		element.sendKeys(lgn1001.getPin());
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Automatic Test")));
		element.click();

*/




		String[] urlInfo = getLoginUrl();
		System.out.println("current url is: " + urlInfo[2]);
		///  CRD
		driver.navigate().refresh();
		driver.navigate().to(urlInfo[2]);

		CommonUtils.login();
		Thread.sleep(2000);




		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Client Management")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update Client Profile")));
		element.click();


		Thread.sleep(1500);


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder=\"Enter Submitter Id\"]")));
		element.sendKeys(clientNumber);
		Thread.sleep(1500);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"clientSltWdgFrmId\"]/div[2]/div/a")));
		element.click();

		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"widget-grid\"]/div[2]/div/div[3]/a/span")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@ng-model=\"clientData.maapInfo.maapCompleted\"]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@ng-model=\"clientData.maapInfo.maapCompleted\"]/option[@value='Y']")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"maapCompletedDate\"]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"wid-id-1\"]/div/div/div[9]/div[1]/fieldset/div[2]/div/p/div/ul/li[2]/span/button[1]")));
		element.click();


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"widget-grid\"]/div[2]/div/div[3]/button/span")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("updLnkId")));
		element.click();




		Thread.sleep(1000);
		driver.navigate().to(urlInfo[0]);
		Thread.sleep(2000);





		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ldap_user")));
		element.sendKeys(lgn1001.getUser());


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ldap_password")));
		element.sendKeys("Pgts_test1234");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("Login")));
		element.click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.linkText("Mining Lands Administration System (DEV)"))).click();


		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Prospector Licensing")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Apply for Prospector's Licence")));
		element.click();

		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='bnGoNextBtnId']")));
		element.click();


		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='bnGoNextBtnId']")));
		element.click();

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
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("cancel")));
		element.click();
		Thread.sleep(1000);
		new WebDriverWait(driver, 50).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nextToCfmPgIndexLnkId']/span")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='nextToCfmPgIndexLnkId']/span")));
		element.click();
		Thread.sleep(500);
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
