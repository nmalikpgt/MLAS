package com.pacificgeotech.epayTestFramework.scripts;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.CommonUtils;
import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class ADMIN2004 {
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

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("viewAllNotificationsId")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Delete")));
		element.click();

		Thread.sleep(4000);
		assertTrue(((String) closeAlertAndGetItsText())
				.matches("^Are you sure you would like to delete the selected message\\(s\\)[\\s\\S]$"));

	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

}
