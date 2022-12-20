package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class EXPEC002 {
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

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		EXPVS001 expvs001 = new EXPVS001();
		expvs001.test();

		setClaimId(expvs001.getClaimId());
		setLicenceId(expvs001.getLicenceId());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(expvs001.getLicenceId());
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
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(expvs001.getLicenceId());

		Thread.sleep(4000);
		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//*[@id='content']/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
		// do not elevate

		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[@name='elevatePlanToPermitApproval' and @value='Y']")))
				.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(2500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Check If Submission Is Elevation Candidate has been successfully completed')]")));

	}

	public String getLicenceId() {
		return LicenceId;
	}

	public void setLicenceId(String licenceId) {
		LicenceId = licenceId;
	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

}