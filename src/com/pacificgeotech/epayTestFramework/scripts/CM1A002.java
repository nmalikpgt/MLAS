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

public class CM1A002 {

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
		java.util.List<WebElement> elements = WebDriverManager.getElements();
		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("honorificSelectId"))))
				.selectByVisibleText("Mr.");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"2\"]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName"))).sendKeys("Blue");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName"))).sendKeys("Ray");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondName"))).sendKeys("H");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("thirdName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("thirdName"))).sendKeys("J");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("prefName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("prefName"))).sendKeys("Blue");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("declarationChkbox"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("addressLine1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("addressLine1"))).sendKeys("96 Totem");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("city"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("city"))).sendKeys("Alberta");
		new Select(driver.findElement(By.name("country"))).selectByVisibleText("CANADA");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"37\"]"))).click();
		new Select(driver.findElement(By.name("province"))).selectByVisibleText("ALBERTA");
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("select[name=\"province\"] > option[value=\"1\"]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).sendKeys("V5G 2H2");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("services_addressLine1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("services_addressLine1")))
				.sendKeys("96 Colaria");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("services_city"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("services_city"))).sendKeys("Toronto");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("services_postalCode"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("services_postalCode")))
				.sendKeys("V4G 5H2");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("services_careOf"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("services_careOf"))).sendKeys("Ray");
		new Select(driver.findElement(By.name("primary_phoneType"))).selectByVisibleText("Home");
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("select[name=\"primary_phoneType\"] > option[value=\"0\"]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@value=''])[18]"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@value=''])[18]")))
				.sendKeys("1");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("primPhone"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("primPhone"))).sendKeys("(111) 111-1111");
		new Select(driver.findElement(By.xpath("//div[@id='wid-id-1']/div/div/div[15]/div/fieldset[2]/div/div/select")))
				.selectByVisibleText("Mobile");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='1'])[5]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondary_countryCode"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondary_countryCode"))).sendKeys("1");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondary_phoneNumber"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondary_phoneNumber")))
				.sendKeys("(111) 111-1111");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fax_countryCode"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fax_countryCode"))).sendKeys("1");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fax_phoneNumber"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fax_phoneNumber")))
				.sendKeys("(111) 111-1111");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("emailAddress"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("emailAddress")))
				.sendKeys("gcontor@pacificgeotech.com");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next"))).click();

	}

}
