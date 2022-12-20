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

public class STDISP51005 {

	private WebDriver driver;
	private String clientNumber;
	private String LeaseNo;

	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() 
	{
		WebDriverManager.instance = null;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
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

		DISP51 disp51 = new DISP51();
		disp51.test();
		setClientNumber(disp51.getClientNumber());
		setLeaseNo(disp51.getLeaseNo());

		Thread.sleep(4000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(4000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();
		Thread.sleep(1000);

		WebElement appElement = null;
		try {
			element = driverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
			element.click();
			element = driverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
			element.clear();
			Thread.sleep(4000);
			element = driverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
			element.sendKeys(disp51.getLeaseNo());
			Thread.sleep(4000);
			appElement = driverWait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//*[contains(text(), 'Lease Number: " + disp51.getLeaseNo() + "')]")));

		} catch (Throwable e) {
		}
		while (appElement == null || !appElement.isDisplayed()) {
			System.out.println("is looping");
			try {
				element = driverWait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
				element.click();
				element = driverWait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
				element.clear();
				Thread.sleep(4000);
				element = driverWait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
				element.sendKeys(disp51.getLeaseNo());
				Thread.sleep(4000);
				appElement = driverWait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//*[contains(text(), 'Lease Number: " + disp51.getLeaseNo() + "')]")));

			} catch (Throwable e) {
				appElement = null;
			}
		}

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'Generate and Send Requistion for Lease Renewal to MNR')]")));
		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		element.clear();
		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		element.sendKeys(disp51.getLeaseNo());

		Thread.sleep(4000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		Thread.sleep(1000);
		// assign to all
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("System Administrator")));
		element.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(disp51.getLeaseNo());

		Thread.sleep(4000);

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'View Requisition Document')]")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'The requisition has been genearted for renewal of Mining Lease successfully')]")));

	}

}
