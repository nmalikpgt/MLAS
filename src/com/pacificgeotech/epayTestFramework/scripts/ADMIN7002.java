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

import com.pacificgeotech.epayTestFramework.core.CommonUtils;
import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class ADMIN7002 {

	private boolean acceptNextAlert = true;
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

		// login
		driver.get("http://cheetah-vm2:10281/mlas/login");
		driver.navigate().to("http://cheetah-vm2:10281/mlas/login");
		driver.navigate().refresh();
		CommonUtils.login();

		// driver.get("http://cheetah-vm2:10281/mlas/login#/mlasDashboard.jsf");http:

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("appFrmId:loginImgId")));
		element.click();

		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//aside[@id='left-panel']/nav/ul/li[5]/a/b/em")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Automatic Process")));
		element.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'admin')]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), '2015')]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), '2015')]")))
				.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Filter")));
		element.click();

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[6]")));
		element.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Completed')]")))
				.getText();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Working On')]")))
				.getText();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Failed')]")))
				.getText();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Cancelled')]")))
				.getText();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Cancelled')]")))
				.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Filter")));
		element.click();

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[5]")));
		element.click();

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Add to Bulletin Board')]")))
				.getText();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Apply for Prospector')]")))
				.getText();
		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Manage Agents')]")))
				.getText();
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Change Prospector Licence Expiry Date')]")))
				.getText();

		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Maintain Tables of Constrained Values')]")))
				.getText();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Enroll Internal User')]")))
				.getText();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Expire Prospector')]")))
				.getText();
		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//*[contains(text(), 'Generate Enrolment Credentials for Internal User')]")))
				.getText();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Manage Automatic Process Queue')]")))
				.getText();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Record Migrated from Claims 3.2')]")))
				.getText();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Notice of Expiry of Prospector')]")))
				.getText();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Online Payment Error')]")))
				.getText();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Record client death')]")))
				.getText();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Register Client as Individual')]")))
				.getText();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Register Client as Organization')]")))
				.getText();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Register Client - Internal')]")))
				.getText();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Revoke or Suspend Prospector')]")))
				.getText();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Unenroll Internal User')]")))
				.getText();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Update Client Profile')]")))
				.getText();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage")));
		element.click();
		Thread.sleep(4000);
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),
		// 'Pending')]"))).click();
		new Select(driver.findElement(By.xpath("//div[@id='wid-id-0']/div/div/form/div/fieldset/div/div/select")))
				.selectByVisibleText("Pending");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.btn.btn-default")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("8")));
		element.click();
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[5]/a/span")));
		element.click();
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[5]/a/span")));
		element.click();

	}

}
