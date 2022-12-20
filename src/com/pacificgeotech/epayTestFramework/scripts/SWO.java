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

public class SWO {

	private WebDriver driver;
	private String IncidentNo;
	private String clientNumber;
	private String LeaseNo;
	private String SWONo;
	

	public String getSWONo() {
		return SWONo;
	}

	public void setSWONo(String sWONo) {
		SWONo = sWONo;
	}

	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}

	public String getLeaseNo() {
		return LeaseNo;
	}

	public void setLeaseNo(String leaseNo) {
		LeaseNo = leaseNo;
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
		setIncidentNo(rei.getIncidentNo());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Stop Work Order")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("incidentNumberInputId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("incidentNumberInputId")));
		element.sendKeys(rei.getIncidentNo());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("tenureIdsCsv")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("tenureIdsCsv")));
		element.sendKeys(rei.getLeaseNo());
		Thread.sleep(500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Stop Work Order was created successfully')]")));
		
		
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='control-label-value col-md-4 text-left ng-binding']")));
		WebElement TxtBoxContent = driver.findElement(By.cssSelector("[class='control-label-value col-md-4 text-left ng-binding']"));
		String SWONo = TxtBoxContent.getText();
		System.out.println("SWO Number " + SWONo);
		setSWONo(SWONo);

	}

	public String getIncidentNo() {
		return IncidentNo;
	}

	public void setIncidentNo(String incidentNo) {
		IncidentNo = incidentNo;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

}
