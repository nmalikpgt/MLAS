package com.pacificgeotech.epayTestFramework.scripts;

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

import junit.framework.Assert;

public class F8003 {
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

	@SuppressWarnings("deprecation")
	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();
		// register external user

		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();
		setClientNumber(lgn1001.getClientNumber());

		setPid(lgn1001.getPid());
		setPin(lgn1001.getPin());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Automatic Test"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span"))); 
		 element.click();

		String[] urlInfo = LGN1001.getLoginUrl();
		Thread.sleep(1000);

		/*
		 * driver.get(urlInfo[0]); Thread.sleep(1000); if
		 * ("remote".equals(urlInfo[1])) {
		 * lgn1001.getLoginInfoExternalRemote(urlInfo[1], urlInfo[0], getPid(),
		 * getPin()); } else {
		 * 
		 * lgn1001.getLoginInfoExternalLocalTest(urlInfo[1], urlInfo[0],
		 * getPid(), getPin());
		 * 
		 * }
		 */

		// as admin
		driver.get(urlInfo[2]);
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		element.sendKeys("admin");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
		element.sendKeys("@MLAS4you");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		// update client profile
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[7]/a[2]/b/em")));

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[7]/a[2]/b/em"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update Client Profile"))).click();
		Thread.sleep(500);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();
		Thread.sleep(500);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(getClientNumber());
		Thread.sleep(500);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1"))).sendKeys("12 oak st");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")))
				.click();
		/*
		 * driverWait.until(
		 * ExpectedConditions.presenceOfElementLocated(By.name("email")))
		 * .clear(); Thread.sleep(1000); driverWait.until(
		 * ExpectedConditions.presenceOfElementLocated(By.name("email")))
		 * .sendKeys("gcontor@pacificgeotech.com");
		 */
		Thread.sleep(4000);

		// MAAP update

		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-1']/div/div/div[9]/div/fieldset/div/div/select"))))
						.selectByVisibleText("Yes");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.btn.btn-default")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[47]")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-1']/div/div/div[9]/div[2]/fieldset/div/div/select"))))
						.selectByVisibleText("Yes");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='Y'])")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("maapExpiryDate")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("maapExpiryDate")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("maapExpiryDate")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("maapExpiryDate")));
		element.sendKeys("2020 January 01");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-1']/div/div/div[11]/div/fieldset/div/div/textarea")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-1']/div/div/div[11]/div/fieldset/div/div/textarea")));
		element.sendKeys(
				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, ");
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();

		Thread.sleep(1000);
		JavascriptExecutor js2 = ((JavascriptExecutor) driver);
		js2.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/div[2]/div/div[3]/a/span")))
				.click();
		Thread.sleep(1000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Client Profile updated successfully and client notified of changes.')]")));

		// prospector licence

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[6]/a[2]/b/em")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Apply Licence Internal")));
		element.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(getClientNumber());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();

		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/a/span")));
		// Actions actions = new Actions(driver);
		// actions.moveToElement(element).click().perform();

		Thread.sleep(1000);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/a/span")));
		element.click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId")));
		element.click();
		Thread.sleep(1000);

		// new

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("otcPaymentSltId"))).click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1500);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))).sendKeys("DAVID BURDA");
		Thread.sleep(1500);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("113513 DAVID BURDA"))).click();
		Thread.sleep(1500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeInput_0_id"))))
				.selectByVisibleText("Cash");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"object:771\"]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("paymentAmount_0"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("paymentAmount_0"))).sendKeys("30.00");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("paymentTypeNumber_0"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("paymentTypeNumber_0")))
				.sendKeys("122121212");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();
		Thread.sleep(1500);

		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Text not found!",
				bodyText.contains("Total payment amount is not equal to the Shopping Cart total cost"));

	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getClientNumber() {
		return this.clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

}
