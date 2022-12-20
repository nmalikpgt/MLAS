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

public class PlanAddAmendremoveListItemsConfirmList {

	private WebDriver driver;
	private String LicenceId;

	 @After public void tearDown() { driver.getCurrentUrl(); }
	
	 @AfterClass public static void afterClass() { WebDriverManager.instance =
	 null; }

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		PlanDo3rdPartyVerifications plando3rdpartyverifications = new PlanDo3rdPartyVerifications();
		plando3rdpartyverifications.test();
		setLicenceId(plando3rdpartyverifications.getLicenceId());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(6000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(6000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']"))
				.sendKeys(plando3rdpartyverifications.getLicenceId());

		Thread.sleep(4000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("MEDC")));
		element.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']"))
				.sendKeys(plando3rdpartyverifications.getLicenceId());

		Thread.sleep(4000);
		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();
		Thread.sleep(4000);

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[3]")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[5]")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[7]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("editReferralPackage0")));
		element.click();
		Thread.sleep(1500);
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-0']/div/div/div/div/div[2]/div[4]/div[2]/select"))))
						.selectByVisibleText("Pending");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("responseDueDate")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[28]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("saveBtnId")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[8]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("editReferralPackage1")));
		element.click();
		Thread.sleep(1500);
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-0']/div/div/div/div/div[2]/div[4]/div[2]/select"))))
						.selectByVisibleText("Pending");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("responseDueDate")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[28]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("saveBtnId")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[8]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("editReferralPackage2")));
		element.click();
		Thread.sleep(1500);
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-0']/div/div/div/div/div[2]/div[4]/div[2]/select"))))
						.selectByVisibleText("Pending");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("responseDueDate")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[28]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("saveBtnId")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[8]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("saveBtnId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Add, amend, remove list items and/or confirm list has been successfully completed')]")));

	}

	public String getLicenceId() {
		return LicenceId;
	}

	public void setLicenceId(String licenceId) {
		LicenceId = licenceId;
	}

}
