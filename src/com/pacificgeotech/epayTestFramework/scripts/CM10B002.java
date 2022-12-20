package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class CM10B002 {

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

		// login

		new LGN1002().test();

		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Dashboard')]")));

		// Select Admin
		// element = driverWait.until(
		// ExpectedConditions.presenceOfElementLocated(By.xpath("//aside[@id='left-panel']/nav/ul/li[5]/a/b/em")));
		// element.click();
		// stage2
		WebElement successMesage11 = (new WebDriverWait(driver, 50))
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[13]/a[2]/b/em")));
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[13]/a[2]/b/em"))).click();
		Thread.sleep(1000);
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Staff")));
		// element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Unenroll Internal User")));
		element.click();

		driver.findElements(By.id("searchBtnId")).isEmpty();

	}

}
