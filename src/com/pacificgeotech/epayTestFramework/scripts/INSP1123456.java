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

public class INSP1123456 {

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

		ARAL aral = new ARAL();
		aral.test();
		setClientNumber(aral.getClientNumber());
		setLeaseNo(aral.getLeaseNo());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Incidents & Inspections")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Record Edit Incident")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("createIncidentBtnId")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("incidentTypeSltId"))))
				.selectByVisibleText("Audit Request");

		element.click();
		new Select(
				element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("incidentCategorySltId"))))
						.selectByVisibleText("Administrative");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("incidentNameInTxtId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("incidentNameInTxtId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("incidentNameInTxtId")));
		element.sendKeys("test1");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"1\"]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("additionalCommentsInTxtId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("additionalCommentsInTxtId")));
		element.sendKeys("test1");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedLeasesIds")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedLeasesIds")));
		element.sendKeys(aral.getLeaseNo());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'An incident has been recorded successfully. The incident will be further reviewed.')]")));

		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='ng-binding ng-scope']")));
		WebElement TxtBoxContent = driver.findElement(By.cssSelector("[class='ng-binding ng-scope']"));
		String IncidentNo = TxtBoxContent.getText();
		System.out.println("Incident Number " + IncidentNo);
		setIncidentNo(IncidentNo);

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
