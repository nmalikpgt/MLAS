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

public class CLM6007 {

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

		CM4008 cm4008 = new CM4008();
		cm4008.test();
		setClaimId(cm4008.getClaimId());
		System.out.println("claim id is: " + cm4008.getClaimId());
		/*
		 * MP1001 mp1001 = new MP1001(); mp1001.updateCellLockTable();
		 * mp1001.updateCellgridTable();
		 */

		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[10]/a[2]/b/em")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage Grid Cell Status")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element.sendKeys("43B03C239,43B03C238,43B03C219,43B03C218");
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/a/span")));
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/a/span")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("cellStatusId"))))
				.selectByVisibleText("Available");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"A\"]")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("cellStatus_1"))))
				.selectByVisibleText("Available");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='A'])[2]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("cellStatus_2")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("cellStatus_2"))))
				.selectByVisibleText("Available");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='A'])[3]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("cellStatus_3")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("cellStatus_3"))))
				.selectByVisibleText("Available");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='A'])[4]")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("reasonCode"))))
				.selectByVisibleText("Rehabilitation");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"1\"]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("uploadFileId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("uploadFileId")));
		element.sendKeys("C:\\Users\\cvlasceanu\\Desktop\\linux2.pdf");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		Thread.sleep(1500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'The cell status has been updated')]")));

		// ((org.openqa.selenium.JavascriptExecutor)
		// driver).executeScript("window.scrollTo(0, 2300)");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[11]/a[2]/span"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Reinstate Claim"))).click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")))
				.sendKeys(cm4008.getClaimId());

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
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
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("comments"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("comments")))
				.sendKeys("this is a test. this is a test. this is a test.");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='decisionType' and @value='1']"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='extension' and @value='extension']"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='days' and @value='days']"))).click();
		
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("daysExtGranted"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("daysExtGranted"))); 
		 element.sendKeys("1");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("daysExtGranted"))).sendKeys("1");

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm"))).click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[2]/a[2]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Search Tenures"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(cm4008.getClaimId());

		Thread.sleep(4000);
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@id='tenures-container']/div[2]/div/div[2]/div/div/a")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("tenureNumberLnkId"))).click();

		Thread.sleep(1000);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Text not found!", bodyText.contains("Active"));

		Thread.sleep(1000);
		String bodyText1 = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Text not found!", bodyText1.contains("Hanks Tom"));

	}

}
