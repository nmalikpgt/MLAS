package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.Concurrent;
import com.pacificgeotech.epayTestFramework.core.ConcurrentJunitRunner;
import com.pacificgeotech.epayTestFramework.core.WebDriverManager;
import com.pacificgeotech.epayTestFramework.core.CommonUtils;

import junit.framework.Assert;

import static com.pacificgeotech.epayTestFramework.scripts.LGN1001.getLoginUrl;
import static com.pacificgeotech.epayTestFramework.scripts.LGN1001.user;

//@RunWith(ConcurrentJunitRunner.class)
//@Concurrent(threads = 3)

public class PL1001 {
	private String clientNumber;
	private String pid;
	private String pin;
	private WebDriver driver;




//	@After
//	public void tearDown() {
//		driver.getCurrentUrl();
//	}



	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();
		this.setPid(lgn1001.getPid());
		this.setPin(lgn1001.getPin());
		System.out.println("pid from lgn1001 : " + lgn1001.getPid());
		System.out.println("pin from lgn1001 : " + lgn1001.getPin());
		setPin(lgn1001.getPin());
		setClientNumber(lgn1001.getClientNumber());

		// update MAAP

		// driver.get("http://rat1:20281/mlas/#/mlasDashboard?clientId=30001908&purpose=prospector");
/*
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
			driver.manage().window().maximize();
		/*	((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
			Thread.sleep(1000);
			WebElement element1 = driver.findElement(By.id("purpose-prospector"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element1);
			((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next")));
			element.click();



			driver.close();

			driver.switchTo().window(firstWinHandle);
		}

		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Prospector Licensing")));
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
	/*	Thread.sleep(1500);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
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

*/
		///  CRD





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

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div//textarea[@id='commentsInputId']")));
		element.sendKeys("Entering comments");


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"widget-grid\"]/div[2]/div/div[3]/button/span")));
		element.click();


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("updLnkId")));
		element.click();


		Thread.sleep(1000);
		/* ---driver.navigate().to(urlInfo[0]);
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
*/

		Thread.sleep(2000);

		// ------- Apply for Prospectors Licence begin --------
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Prospector Licensing")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Apply for Prospector's Licence")));
		element.click();

		Thread.sleep(2000);

		System.out.println("Enter Submitter Id");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='clientselect']")));
		element.sendKeys(clientNumber);


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

		String bodyText = driver.findElement(By.tagName("body")).getText();
		org.junit.Assert.assertTrue(bodyText.contains(
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

	public String user() {
		return user;
	}


	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	
	/*@Test
	public void test1() throws Exception {
		Thread.sleep(500);
		test();
	}
	
	@Test
	public void test2() throws Exception {
		Thread.sleep(1000);
		test();
	}

	@Test
	public void test3() throws Exception {
	Thread.sleep(1500);
	test();

	}*/
}
