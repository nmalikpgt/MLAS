package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.CommonUtils;
import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class PL2003 {

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
		WebDriverManager.getElements();
		PL1001 pl1001 = new PL1001();
		pl1001.test();
		String clientNumber = pl1001.getClientNumber();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("scroll(0, -250);");

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
				.click();
		Thread.sleep(2000);

		WebElement _element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out")));

		Actions actions = new Actions(driver);

		actions.moveToElement(_element).click().perform();
		Thread.sleep(2000);

	LGN1002 lgn1002 = new LGN1002();
	lgn1002.test();

		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[6]/a[2]/b/em")));

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[6]/a[2]/b/em"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Apply for Prospector's Licence"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(clientNumber);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();

		Thread.sleep(2000);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		org.junit.Assert.assertTrue(bodyText.contains("Client already holds a valid licence"));

	}

}
