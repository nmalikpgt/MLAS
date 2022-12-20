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

public class CM1B002 {

	private WebDriver driver;

	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}

	@SuppressWarnings("unused")
	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		java.util.List<WebElement> elements = WebDriverManager.getElements();

		new LGN1001().test();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[5]/a[2]/b/em")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register Organization")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgName")));
		element.sendKeys("Trodan Ltd.");

		new Select(driver.findElement(By.name("orgType"))).selectByVisibleText("Partnership");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"1\"]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpNumber")));
		element.sendKeys("121212");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpPlace")));
		element.sendKeys("Victoria");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1")));
		element.sendKeys("52 Bay Street");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddressCity")));
		element.sendKeys("Alberta");
		new Select(driver.findElement(By.name("mailingCountry"))).selectByVisibleText("CANADA");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"37\"]")));
		element.click();
		new Select(driver.findElement(By.name("province"))).selectByVisibleText("ALBERTA");
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("select[name=\"province\"] > option[value=\"1\"]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode")));
		element.sendKeys("B5G 8H5");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("services_addressLine1")));
		element.sendKeys("96 Otawa Street");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("services_city")));
		element.sendKeys("Toronto");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("services_postalCode")));
		element.sendKeys("V5G 5G5");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("services_careOf")));
		element.sendKeys("ANA");
		new Select(driver.findElement(By.name("primary_phoneType"))).selectByVisibleText("Home");
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("select[name=\"primary_phoneType\"] > option[value=\"1\"]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_prefix")));
		element.sendKeys("1");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneNumber")));
		element.sendKeys("(111) 111-1111");
		new Select(driver.findElement(By.name("phone2Type"))).selectByVisibleText("Mobile");
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("select[name=\"phone2Type\"] > option[value=\"2\"]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondary_countryCode")));
		element.sendKeys("1");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondary_phoneNumber")));
		element.sendKeys("(111) 111-1111");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fax_countryCode")));
		element.sendKeys("1");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fax_phoneNumber")));
		element.sendKeys("(111) 111-1111");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
		element.sendKeys("gcontor@pacificgeotech.com");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next")));
		element.click();
		;

	}

}
