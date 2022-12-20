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

public class CM6003 {

	private String orgNum1;
	private String orgNum2;
	private String orgNum3;
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

		java.util.List<WebElement> elements = WebDriverManager.getElements();

		MP1B004 mp1b004 = new MP1B004();
		mp1b004.test();
		setOrgNum1(mp1b004.getOrgNum());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/a/span")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span")));
		element.click();
		mp1b004.test();
		setOrgNum2(mp1b004.getOrgNum());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/a/span")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span")));
		element.click();
		mp1b004.test();
		setOrgNum3(mp1b004.getOrgNum());

		// amalgamate client
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.linkText("Amalgamate Clients")));
//		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
//				By.linkText("Client Management")));
//		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Amalgamate Clients")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(getOrgNum1());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#amalgamatedClientId0 > div[name=\"amcClientIdForm\"] > #clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#amalgamatedClientId0 > div[name=\"amcClientIdForm\"] > #clientselect")));
		element.sendKeys(getOrgNum2());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#amalgamatedClientId1 > div[name=\"amcClientIdForm\"] > #clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#amalgamatedClientId1 > div[name=\"amcClientIdForm\"] > #clientselect")));
		element.sendKeys(getOrgNum3());
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

	public String getOrgNum1() {
		return orgNum1;
	}

	public void setOrgNum1(String orgNum1) {
		this.orgNum1 = orgNum1;
	}

	public String getOrgNum2() {
		return orgNum2;
	}

	public void setOrgNum2(String orgNum2) {
		this.orgNum2 = orgNum2;
	}

	public String getOrgNum3() {
		return orgNum3;
	}

	public void setOrgNum3(String orgNum3) {
		this.orgNum3 = orgNum3;
	}

}
