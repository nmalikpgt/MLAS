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

public class ASRETYears {
	
	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	private String claimId;

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

		SRET sret = new SRET();
		sret.test();
		setClaimId(sret.getClaimId());
		
		Thread.sleep(1000);

		LGN1002 lgn1002 = new LGN1002();
		lgn1002.test();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(sret.getClaimId());

		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		Thread.sleep(3000);
		// assign to all
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("System Administrator")));
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(sret.getClaimId());

		Thread.sleep(2000);

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(sret.getClaimId())));
		element.click();
		
		 new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectLengthOfTimeId")))).selectByVisibleText("Number of Years from Current Date");

		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectOfYearId"))); 
		 element.click();
		    new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectOfYearId")))).selectByVisibleText("2 years");

			new Select(element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.id("decisionOrderTypeId")))).selectByVisibleText(
							"MINISTER EXCLUDES <DAYS....> DAYS (SECTION 67) AND SETS NEW ANNIVERSARY DATE <DATE....>");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
			element.click();
			Thread.sleep(1000);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
			element.click();
			Thread.sleep(1000);
			new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//*[contains(text(), 'Request for Exclusion of time has been completed successfully.')]")));
		
	}

}
