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

public class AddBulletinBoard {

	private WebDriver driver;
	private String clientNumber;

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getLeaseNo() {
		return LeaseNo;
	}

	public void setLeaseNo(String string) {
		LeaseNo = string;
	}

	private String LeaseNo;

//	 @After
//	 public void tearDown() {
//	 driver.getCurrentUrl();
//	 }
//	
//	 @AfterClass
//	 public static void afterClass() {
//	 WebDriverManager.instance = null;
//	 }

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();
		setClientNumber(lgn1001.getClientNumber());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Automatic Test")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span")));
		element.click();

		String[] urlInfo = LGN1001.getLoginUrl();
		Thread.sleep(1000);

		// as admin
		driver.get(urlInfo[2]);
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		element.sendKeys("admin");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
		element.sendKeys("@MLAS4you");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Admin"))).click();
		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Add to Bulletin Board"))).click();
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectClientInputId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectClientInputId")));
		element.sendKeys(lgn1001.getClientNumber());
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectClientInputId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectClientInputId")));
		element.sendKeys(lgn1001.getClientNumber());
		Thread.sleep(500);
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.linkText(lgn1001.getClientNumber() + " Automatic Test")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("titleinputid")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("titleinputid")));
		element.sendKeys("test123");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("noticeinputid")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("noticeinputid")));
		element.sendKeys(
				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Null");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/a/span")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'The message was successfully added to Bulletin Board')]")));

	}

}
