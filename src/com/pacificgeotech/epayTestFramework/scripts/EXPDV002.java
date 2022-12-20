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

public class EXPDV002 {
	private String claimId;
	private WebDriver driver;
	private String LicenceId;

	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}
	 
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
		EXPEC001 expec001 = new EXPEC001();
		expec001.test();
		setClaimId(expec001.getClaimId());
		setLicenceId(expec001.getLicenceId());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(expec001.getLicenceId());

		Thread.sleep(4000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("MEDC Manager")));
		element.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(expec001.getLicenceId());

		Thread.sleep(4000);
		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//*[@id='content']/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();
		
//		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
//				By.xpath("//*[contains(text(), 'No stakeholder has been identified for this plan or permit. Click Add Stakeholder to manually add one.')]")));
		
		  element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("addStakeholderBtnId"))); 
		  element.click();
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@id='confirmBtnId'])[2]"))); 
		  element.click();
		     new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("contactNameId3")))).selectByVisibleText("Andrew Aguonie");

		  Thread.sleep(1500);
		 /* new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
					By.id("notificationTypeId0")));
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("notificationTypeId0"))); 
		  element.click();*/
		     new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("notificationTypeId3")))).selectByVisibleText("Regular Mail");

		     new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("circulationModeId3")))).selectByVisibleText("Email");
		     
		     new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("notificationTypeId0")))).selectByVisibleText("Regular Mail");

		     new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("circulationModeId0")))).selectByVisibleText("Email");
		     
		     new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("notificationTypeId1")))).selectByVisibleText("Regular Mail");

		     new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("circulationModeId1")))).selectByVisibleText("Email");
		     
		     new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("notificationTypeId2")))).selectByVisibleText("Regular Mail");

		     new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("circulationModeId2")))).selectByVisibleText("Email");
	
		  new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='wid-id-0']/div/div/fieldset/div/div[2]/textarea")));
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='wid-id-0']/div/div/fieldset/div/div[2]/textarea"))); 
		  element.click();
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='wid-id-0']/div/div/fieldset/div/div[2]/textarea"))); 
		  element.clear();
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='wid-id-0']/div/div/fieldset/div/div[2]/textarea"))); 
		  element.sendKeys("test");
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		  element.click();
		     element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		  element.click();
		  

			new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//*[contains(text(), 'The 3rd party verifications have been completed successfully')]")));
		
		
	}
	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

}
