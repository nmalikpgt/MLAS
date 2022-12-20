package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;

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

import junit.framework.Assert;

public class CLM3005 {
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

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		MP5003 mp5003 = new MP5003();
		mp5003.test();
		setClaimId(mp5003.getClaimId());
		System.out.println("Claim ID is: " + mp5003.getClaimId());
		String[] loginURL = LGN1001.getLoginUrl();
		driver.get(loginURL[2]); // for local Test
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		element.sendKeys("admin");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
		element.sendKeys("@MLAS4you");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		System.out.println("Reached main test script...");
		driver.navigate().refresh();

		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.elementToBeClickable(By.linkText("Claim Management")));
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Claim Management"))).click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Cancel Claim"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")))
				.sendKeys(mp5003.getClaimId());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("decisionType"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.btn.btn-default"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[46]")))
				.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reopenPostingDateInput"))).clear();
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reopenPostingDateInput"))).sendKeys("2020
		// January 01");

		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("decisionCode"))))
				.selectByVisibleText("CANCELLED BY ORDER OF COMMISSIONER");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option.ng-binding.ng-scope")))
				.click();

		Thread.sleep(3000);
		Object file = null;
		try {
			file = new File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file).exists());
		WebElement browseButton = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("uploadFileId")));
		browseButton.sendKeys(((File) file).getAbsolutePath());

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("comments"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("comments"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("comments")))
				.sendKeys("This is a test. This is a test. This is a test. This is a test.");

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//div[@id='content']/div/form/div/div/div[3]/button")))
				.click();

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/div/div/div/div[3]/a/span")))
				.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Claim has been cancelled successfully.')]")));

	}

}
