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

public class MP1B013 {

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

	private void registerMiningClaimInternal(PL2001 pl2001) throws Exception {

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
				ExpectedConditions.presenceOfElementLocated(By.linkText("Register Mining Claim - Internal User")))
				.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")))
				.sendKeys(pl2001.getClientNumber());

		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("regMethodId"))))
				.selectByVisibleText("Input or Generate Cell IDs");

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

		Thread.sleep(1800000);

		mp1001.test();

		// TODO: repeat Register Mining Claim - Internal User

	}

}
