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

public class CM3008 {

	private WebDriver driver;
	private String clientNumber;

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

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

		java.util.List<WebElement> elements = WebDriverManager.getElements();

		CM2001 cm2001 = new CM2001();
		cm2001.test();
		setClientNumber(cm2001.getClientNumber());
		
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Client Management")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Client Management")));
		element.click();


		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update Client Profile"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(cm2001.getClientNumber());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1"))).sendKeys("12 oak st");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")))
				.sendKeys("gcontor@pacificgeotech.com");

		// MAAP update

			Thread.sleep(1000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='btn btn-labeled bg-color-greenDark txt-color-white']")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm")));
		element.click();
			driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Client Profile updated successfully and client notified of changes.')]")));

	}

}
