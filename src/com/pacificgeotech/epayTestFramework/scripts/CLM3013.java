package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

public class CLM3013 {

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

		EXPVS001 expvs001 = new EXPVS001();
		expvs001.test();

		String[] loginURL = LGN1001.getLoginUrl();
		driver.get(loginURL[2]);
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		element.sendKeys("admin");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
		element.sendKeys("@MLAS4you");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		System.out.println("Reached main test script...");
		driver.navigate().refresh();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[11]/a[2]/span"))).click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Cancel Claim"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")))
				.sendKeys(expvs001.getClaimId());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("decisionType"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.btn.btn-default"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[46]")))
				.click();

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sf.format(cal.getTime());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reopenPostingDateInput"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reopenPostingDateInput")))
				.sendKeys(dateStr);

		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("decisionCode"))))
				.selectByVisibleText(
						"CANCELLED PURSUANT TO SECTION/SUBSECTION (<SECT....>) OF THE MINING ACT R.S.O. 1990�");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='6']"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("subsection"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("subsection"))).sendKeys("test");

		Thread.sleep(3000);
		Object file = null;
		try {
			file = new File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file).exists());
		WebElement browseButton = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("uploadFileId")));
		browseButton.sendKeys(((File) file).getAbsolutePath());

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("comments"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("comments"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("comments")))
				.sendKeys("This is a test. This is a test. This is a test. This is a test.");

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//div[@id='content']/div/form/div/div/div[3]/button")))
				.click();

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/div/div/div/div[3]/a/span")))
				.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Claim has been cancelled successfully.')]")));

	}

}
