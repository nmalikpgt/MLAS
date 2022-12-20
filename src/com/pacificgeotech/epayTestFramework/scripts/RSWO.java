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

public class RSWO {
	
	private WebDriver driver;
	private String IncidentNo;
	private String SWONo;

	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}


	public String getSWONo() {
		return SWONo;
	}

	public void setSWONo(String sWONo) {
		SWONo = sWONo;
	}

	public String getIncidentNo() {
		return IncidentNo;
	}

	public void setIncidentNo(String incidentNo) {
		IncidentNo = incidentNo;
	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		SWO swo = new SWO();
		swo.test();
		setIncidentNo(swo.getIncidentNo());
		setSWONo(swo.getSWONo());
	
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Revoke Stop Work Order")));
		element.click();
		
		//Revoke Stop Order
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("workOrderInTxtId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("workOrderInTxtId")));
		element.sendKeys(swo.getSWONo());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Revoke Stop Work Order was entered successfully')]")));
		
		
	}
}
