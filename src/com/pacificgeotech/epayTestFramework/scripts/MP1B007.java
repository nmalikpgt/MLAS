package com.pacificgeotech.epayTestFramework.scripts;

import java.sql.SQLException;

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

public class MP1B007 {

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

		PL2001 pl2001 = new PL2001();
		pl2001.test();
		registerMiningClaimInternal(pl2001);

	}

	private void registerMiningClaimInternal(PL2001 pl2001)
			throws ClassNotFoundException, SQLException, InterruptedException {

		System.out.println("registerMiningClaimInternal method...");

		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		/**
		 * Register Mining Claim - Internal User
		 * 
		 **/
		MP1001 mp1001 = new MP1001();

		mp1001.updateCellLockTable();
		mp1001.updateCellgridTable();

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[9]/a[2]"))).click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Register Mining Claim")))
				.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")))
				.sendKeys(pl2001.getClientNumber());


		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'The registration of a mining claim or the acquisition of any right or interest in a mining claim by any person does not confer upon that person')]")));

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("declarationChkbox"))).click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds"))).clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element = driver.findElement(By.id("selectedGeoMapIds"));
		element.sendKeys("43B03C239,43B03C238,43B03C219,43B03C218");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoIdendityButtonId"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("owneridInput_0"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("owneridInput_0")))
				.sendKeys(pl2001.getClientNumber());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("percentage_0"))).clear();
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("percentage_0"))).sendKeys("100");

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='wid-id-0']/div/div/div/table/tbody/tr[3]/td[3]/span/i"))).click();

		Thread.sleep(2000);
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Distribute ownership percentage equally")))
				.click();

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();

		Thread.sleep(1000);

		// To payment
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/a/span")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId"))).click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("otcPaymentSltId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showSelectedPaymentId"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))).sendKeys("DARYL SMITH");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("412908 DARYL SMITH"))).click();

		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeInput_0_id"))))
				.selectByVisibleText("Cash");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("paymentAmount_0"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentAmoutInput_0_id"))).sendKeys("102");

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeNumber_0_id"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeNumber_0_id"))).sendKeys("1234");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='nextToConfirmLnkId']/span")))
				.click();

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='nextToCfmPgIndexId']/span")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid"))).click();
	}

}
