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

public class ClaimDatabaseAmendment {

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	private String claimId;
	private WebDriver driver;


	  @After public void tearDown() { driver.getCurrentUrl(); }
	  
	  @AfterClass public static void afterClass() { WebDriverManager.instance =
	  null; }
	 

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		// Approve the created claim SRO
		// This script works based on client Number and claim ID created in
		// MP1001
		MP5003 mp5003 = new MP5003();
		mp5003.test();
		setClaimId(mp5003.getClaimId());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Admin"))).click();
		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Claim Database Amendment"))).click();
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimId")));
		element.sendKeys(mp5003.getClaimId());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@name='pendingProceeding' and @value='Y']")))
				.click();
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[@name='specialCircumstancesApply' and @value='Y']")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='specialStatus' and @value='N']")))
				.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("totalWork")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("totalWork")));
		element.sendKeys("1600");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("totalPaymentInPlace")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("totalPaymentInPlace")));
		element.sendKeys("1600");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("consultationReserve")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("consultationReserve")));
		element.sendKeys("800");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("explorationReserve")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("explorationReserve")));
		element.sendKeys("800");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'None of these changes will apply a wording code to the claim abstract. If you made a change that requires a public abstract entry, please go to Add Abstract Entry and provide the necessary code. None of these changes will trigger the geoproessing queue. If you made a change that requires a spatial update, please run the Mapping Operation to update the spatial views.')]")));

		
	}

}
