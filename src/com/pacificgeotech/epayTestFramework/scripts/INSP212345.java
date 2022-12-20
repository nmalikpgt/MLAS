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

public class INSP212345 {
	
	private WebDriver driver;
	private String IncidentNo;
	private String clientNumber;
	private String LeaseNo;

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

		INSP1123456 insp1123456 = new INSP1123456();
		insp1123456.test();

		setClientNumber(insp1123456.getClientNumber());
		setLeaseNo(insp1123456.getLeaseNo());
		setIncidentNo(insp1123456.getIncidentNo());
	

		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();
		
		Thread.sleep(4000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();
		Thread.sleep(1000);		
		
		
		WebElement appElement = null;
		try {
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
			element.click();
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
			element.clear();
			Thread.sleep(4000);
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
			element.sendKeys("Incident ID: " + insp1123456.getIncidentNo());	
			Thread.sleep(4000);
			appElement = driverWait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Incident ID: " + insp1123456.getIncidentNo() + "')]")));
			
			
		} catch (Throwable e) {
		}
		while (appElement == null || !appElement.isDisplayed()) {System.out.println("is looping");
			try {
				element = driverWait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
				element.click();
				element = driverWait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
				element.clear();
				Thread.sleep(4000);
				element = driverWait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
				element.sendKeys("Incident ID: " + insp1123456.getIncidentNo());
				Thread.sleep(4000);
				appElement = driverWait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Incident ID: " + insp1123456.getIncidentNo() + "')]")));

			} catch (Throwable e) {
				appElement = null;
			}
		}
		
		Thread.sleep(4000);		
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		element.clear();
		Thread.sleep(4000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		element.sendKeys("Incident ID: " + insp1123456.getIncidentNo());	
	
		Thread.sleep(4000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		Thread.sleep(1000);
		// assign to all
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("System Administrator")));
		element.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys("Incident ID: " + insp1123456.getIncidentNo());

		Thread.sleep(4000);

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();
		
		//finish Task 
		 new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("decisionSltId")))).selectByVisibleText("Do Inspection");
		 Thread.sleep(1000);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		 element.click();
		 Thread.sleep(1000);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		 element.click();
		 
		 new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//*[contains(text(), 'Determine Further Action or Do Early Resolve task has been completed successfully.')]")));
		

	}

	public String getIncidentNo() {
		return IncidentNo;
	}

	public void setIncidentNo(String incidentNo) {
		IncidentNo = incidentNo;
	}

	public String getLeaseNo() {
		return LeaseNo;
	}

	public void setLeaseNo(String leaseNo) {
		LeaseNo = leaseNo;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}


	}


