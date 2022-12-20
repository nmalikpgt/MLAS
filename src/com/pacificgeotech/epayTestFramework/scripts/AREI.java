package com.pacificgeotech.epayTestFramework.scripts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class AREI {
	
	private WebDriver driver;
	private String LeaseNo;
	private String clientNumber;
	

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

		REI rei = new REI();
		rei.test();
		setClientNumber(rei.getClientNumber());
		setLeaseNo(rei.getLeaseNo());
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();
		
		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();
		Thread.sleep(3000);		
		
		
		WebElement appElement = null;
		try {
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
			element.click();
			Thread.sleep(3000);		
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
			element.clear();
			Thread.sleep(3000);
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
			element.sendKeys(rei.getLeaseNo());	
			Thread.sleep(3000);
			appElement = driverWait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Lease ID: " + rei.getLeaseNo() + "')]")));
			
			Thread.sleep(3000);		
		} catch (Throwable e) {
		}
		while (appElement == null || !appElement.isDisplayed()) {System.out.println("is looping");
			try {
				element = driverWait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
				element.click();
				Thread.sleep(3000);		
				element = driverWait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
				element.clear();
				Thread.sleep(3000);
				element = driverWait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
				element.sendKeys(rei.getLeaseNo());	
				Thread.sleep(3000);
				appElement = driverWait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Lease ID: " + rei.getLeaseNo() + "')]")));

			} catch (Throwable e) {
				appElement = null;
			}
		}

		
//		element = driverWait
//				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
//		element.clear();
//		Thread.sleep(4000);
//		element = driverWait
//				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
//		element.sendKeys(mp1001.getClaimId());		
		
		Thread.sleep(3000);		
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		element.clear();
		Thread.sleep(3000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		element.sendKeys(rei.getLeaseNo());	
	
		Thread.sleep(3000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		Thread.sleep(1000);
		// assign to all
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("System Administrator")));
		element.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(1000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(rei.getLeaseNo());

		Thread.sleep(1000);

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();
		
		//Update Lease Spatial Record
		Calendar calendar3 = Calendar.getInstance();
		calendar3.add(Calendar.DAY_OF_YEAR, 0);
		Date today3 = calendar3.getTime();
		DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
		String today = dateFormat3.format(today3);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("effectiveDateInput")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("effectiveDateInput")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("effectiveDateInput")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("effectiveDateInput")));
		element.sendKeys(today);
		
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("tempLeaseGeometryExistsOnActiveTenuresLyr")));
		element.click();
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("leaseCreatedInEditingEnv")));
		element.click();
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("scheduledMappingOperationToCopyFromEditingEnv")));
		element.click();
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("verifiedLeaseGeometryCreatedOnActiveTenuresLyr")));
		element.click();
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("saveBtnId")));
		element.click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		
		 new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"//*[contains(text(), 'Lease Spatial Record was updated successfully.')]")));
		
	}

	public String getLeaseNo() {
		return LeaseNo;
	}

	public void setLeaseNo(String leaseNo) {
		LeaseNo = leaseNo;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

}
