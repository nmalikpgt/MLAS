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

public class CM5001STAGE1 {
	
	private WebDriver driver;
	private String clientNumber;
	
	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

//	@After
//	public void tearDown() {
//		driver.getCurrentUrl();
//	}
//
//	@AfterClass
//	public static void afterClass() {
//		WebDriverManager.instance = null;
//	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		java.util.List<WebElement> elements = WebDriverManager.getElements();

		// login
		
		LGN1001STG1 lgn1001stg1 = new LGN1001STG1();
		lgn1001stg1.test();
		setClientNumber(lgn1001stg1.getClientNumber());
		
		LGN1002 lgn1002 = new LGN1002();
		lgn1002.test();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Client Management")))
		.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Record Client Death or Dissolution")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(lgn1001stg1.getClientNumber());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		WebElement date = (new WebDriverWait(driver, 50))
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-default")));
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.btn.btn-default"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Today')]"))).click();
		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@title, 'Comments')]")))
				.clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@title, 'Comments')]")))
				.sendKeys("comments");

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();
		Thread.sleep(3000);

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		Thread.sleep(3000);
		// driverWait.until(
		// ExpectedConditions.presenceOfElementLocated(By
		// .xpath("//span[contains(.,'Confirm')]"))).click();

		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/div/div/div[3]/a/span")))
				.click();

		Thread.sleep(3000);
		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By
						.xpath("//*[contains(text(), 'Client profile was updated successfully to record date of dissolution. Active Claims have been placed on special status and will not forfeit for 3 years.')]")))
				.click();

	}


}

