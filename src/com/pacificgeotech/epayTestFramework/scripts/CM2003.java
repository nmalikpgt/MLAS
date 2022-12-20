package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.CommonUtils;
import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class CM2003 {

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
		WebDriverManager.getElements();

		java.util.List<WebElement> elements = WebDriverManager.getElements();

		// login
		driver.get("http://cheetah-vm2:10281/mlas/login");
		driver.navigate().to("http://cheetah-vm2:10281/mlas/login");
		driver.navigate().refresh();
		CommonUtils.login();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("appFrmId:loginImgId")));
		element.click();

		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//aside[@id='left-panel']/nav/ul/li[3]/a/b/em")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register Client"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("clientTypeRdio"))).click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/div[2]/div/div[2]/a/span")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Last Name Required')]")))
				.click();
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'First Name is required')]"))).click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Age declaration is required')]")))
				.click();
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Mailing address line 1 is required')]")))
				.click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Mailing city is required')]")))
				.click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Mailing Country is required')]")))
				.click();
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Billing address line 1 is required')]")))
				.click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Billing city is required')]")))
				.click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Primary Phone Type is required')]")))
				.click();
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Primary Phone Number is required')]")))
				.click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Email address is required')]")))
				.click();

	}

}
