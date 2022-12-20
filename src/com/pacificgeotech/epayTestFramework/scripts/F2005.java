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

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class F2005 {
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

		new PL1001().test();
		Thread.sleep(2000);

		driver.navigate().refresh();
		Thread.sleep(2000);

		// check payment
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[6]/a[2]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("View Payment")));
		element.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchId"))).click();
		WebElement _element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("i[name=\"arrows-v\"]")));
		Actions actions = new Actions(driver);

		actions.moveToElement(_element).click().perform();

		WebElement _element1 = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("i[name=\"caret-up\"]")));
		Actions actions1 = new Actions(driver);

		actions1.moveToElement(_element1).click().perform();
		_element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@name='arrows-v'])[2]")));
		actions.moveToElement(_element).click().perform();
		_element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@name='caret-up'])[2]")));
		actions.moveToElement(_element).click().perform();
		_element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@name='arrows-v'])[3]")));
		actions.moveToElement(_element).click().perform();
		_element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@name='caret-up'])[3]")));
		actions.moveToElement(_element).click().perform();
		_element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@name='arrows-v'])[4]")));
		actions.moveToElement(_element).click().perform();
		_element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@name='caret-up'])[4]")));
		actions.moveToElement(_element).click().perform();
		_element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@name='arrows-v'])[5]")));
		actions.moveToElement(_element).click().perform();
		_element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@name='caret-up'])[5]")));
		actions.moveToElement(_element).click().perform();
		_element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@name='arrows-v'])[6]")));
		actions.moveToElement(_element).click().perform();
		_element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@name='caret-up'])[6]")));
		actions.moveToElement(_element).click().perform();
		_element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentDateSortId")));
		actions.moveToElement(_element).click().perform();
		_element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@name='caret-up'])[7]")));
		actions.moveToElement(_element).click().perform();
		_element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@name='arrows-v'])[8]")));
		actions.moveToElement(_element).click().perform();
		_element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@name='caret-up'])[8]")));
		actions.moveToElement(_element).click().perform();

	}

}
