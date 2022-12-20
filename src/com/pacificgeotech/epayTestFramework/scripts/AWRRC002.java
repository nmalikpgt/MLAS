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

public class AWRRC002 {
	
	private WebDriver driver;
	private String LicenceId;
	
	 /* @After public void tearDown() { driver.getCurrentUrl(); }
	  
	  @AfterClass public static void afterClass() { WebDriverManager.instance =
	  null; }*/
	
	public String getLicenceId() {
		return LicenceId;
	}

	public void setLicenceId(String licenceId) {
		LicenceId = licenceId;
	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();
		AWR2A002 awr2a002 = new AWR2A002();
		awr2a002.test();
		setLicenceId(awr2a002.getLicenceId());
		
		LGN1002 lgn1002 = new LGN1002();
		lgn1002.test();
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(6000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(6000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(6000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(awr2a002.getLicenceId());

		Thread.sleep(6000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		// assign to all
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("MEDC Manager")));
		element.click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(6000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(awr2a002.getLicenceId());

		Thread.sleep(6000);

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();
		
		
		
		
	}

}
