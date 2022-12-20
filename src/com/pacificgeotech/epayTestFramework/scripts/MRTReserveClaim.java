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

public class MRTReserveClaim {

	private WebDriver driver;
	private String ClaimId;
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
		WebDriverManager.getElements();

		ARAL aral = new ARAL();
		aral.test();
		setClientNumber(aral.getClientNumber());
		setLeaseNo(aral.getLeaseNo());

		MP5003 mp5003 = new MP5003();
		mp5003.test();

		setClaimId(mp5003.getClaimId());

		// manage reserves
		 driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Claim Management"))).click();
		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage Reserves"))).click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("transfer")));
		element.click();
		Thread.sleep(500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1000);
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@name='selectedToId' and @value='claimIdTo']")))
				.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningRightIdFrom")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningRightIdFrom")));
		element.sendKeys(aral.getLeaseNo());
		Thread.sleep(500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningClaimId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("miningClaimId")));
		element.sendKeys(mp5003.getClaimId());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("explorationReserve0")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("explorationReserve0")));
		element.sendKeys("100");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("consultationReserve0")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("consultationReserve0")));
		element.sendKeys("100");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("explorationReserve1")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("explorationReserve1")));
		element.sendKeys("100");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("consultationReserve1")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("consultationReserve1")));
		element.sendKeys("100");
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("abstractEntry"))))
				.selectByVisibleText("Notice of Lease Exchange [ mining right number: " + aral.getLeaseNo() + " ]");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'Transfer to reserves completed successfully')]")));

	}

	public String getClaimId() {
		return ClaimId;
	}

	public void setClaimId(String claimId) {
		ClaimId = claimId;
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

}
