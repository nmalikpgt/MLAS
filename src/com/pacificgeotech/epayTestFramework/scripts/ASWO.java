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

public class ASWO {

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

		SWO swo = new SWO();
		swo.test();
		setClientNumber(swo.getClientNumber());
		setLeaseNo(swo.getLeaseNo());
		setIncidentNo(swo.getIncidentNo());

		Thread.sleep(6000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();
		Thread.sleep(6000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		Thread.sleep(6000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();
		Thread.sleep(10000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));

		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(6000);
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")))
				.sendKeys("Incident ID: " + swo.getIncidentNo());
		Thread.sleep(6000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[2]")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.linkText("Determine Further Action or Do Early Resolve")));
		element.click();

		Thread.sleep(6000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		// assign to all
				element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("System Administrator")));
				element.click();
				Thread.sleep(1000);
		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("decisionSltId"))))
				.selectByVisibleText("Do Inspection");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Determine Further Action or Do Early Resolve task has been completed successfully.')]")));

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
