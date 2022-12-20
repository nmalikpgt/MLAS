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

public class CM6002 {

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

		CM2002 cm2002 = new CM2002();
		cm2002.test();

		WebElement TxtBoxContent1 = driver.findElement(By.id("organizationNumberId"));
		String orgNum = TxtBoxContent1.getText();
		System.out.println("Org number 1 : " + orgNum);

		LGN1002 lgn1002 = new LGN1002();
		lgn1002.test();

		// amalgamate client
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.linkText("Client Management")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Client Management")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Amalgamate Clients")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("effectiveDateInput")));
		element.clear();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Effective Date is Required')]")));

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Client ID is Required')]")));
	}

}
