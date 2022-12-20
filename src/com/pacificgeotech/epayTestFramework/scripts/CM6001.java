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

public class CM6001 {

	private WebDriver driver;

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

		CM2002 cm2002 = new CM2002();
		cm2002.test();

		WebElement TxtBoxContent1 = driver.findElement(By.id("organizationNumberId"));
		String orgNum = TxtBoxContent1.getText();
		System.out.println("Org number 1 : " + orgNum);
		
		//logout
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("MLAS Admin"))); 
		 element.click();
		 Thread.sleep(1000);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span"))); 
		 element.click();

		cm2002.test();
		WebElement TxtBoxContent2 = driver.findElement(By.id("organizationNumberId"));
		String orgNum1 = TxtBoxContent2.getText();
		System.out.println("Org number 2 : " + orgNum1);
		
		
		//logout
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("MLAS Admin"))); 
		 element.click();
		 Thread.sleep(1000);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span"))); 
		 element.click();
		

		cm2002.test();
		WebElement TxtBoxContent3 = driver.findElement(By.id("organizationNumberId"));
		String orgNum2 = TxtBoxContent3.getText();
		System.out.println("Org number 3 : " + orgNum2);
		

		LGN1002 lgn1002 = new LGN1002();
		lgn1002.test();

		// amalgamate client
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.linkText("Client Management")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Client Management")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Amalgamate Clients")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(orgNum);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#amalgamatedClientId0 > div[name=\"amcClientIdForm\"] > #clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#amalgamatedClientId0 > div[name=\"amcClientIdForm\"] > #clientselect")));
		element.sendKeys(orgNum1);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#amalgamatedClientId1 > div[name=\"amcClientIdForm\"] > #clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#amalgamatedClientId1 > div[name=\"amcClientIdForm\"] > #clientselect")));
		element.sendKeys(orgNum2);
		Thread.sleep(1000);
		// Upload
		Object file = null;
		try {
			file = new File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file).exists());
		WebElement browseButton = driver.findElement(By.name("fileInput"));
		browseButton.sendKeys(((File) file).getAbsolutePath());
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode")))).selectByVisibleText("Amalgamation");
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option.ng-binding.ng-scope"))); 
	    element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(2500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		Thread.sleep(1000);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Text not found!", bodyText.contains(
				"Clients have been amalgamated successfully and the holding ownership has been updated with the amalgamating client's name"));

	}

}
