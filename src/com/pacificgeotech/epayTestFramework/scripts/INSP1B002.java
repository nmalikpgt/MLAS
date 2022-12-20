package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class INSP1B002 {

	private WebDriver driver;
	private String clientNumber;
	private String pid;
	private String pin;

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

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

		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();
		setClientNumber(lgn1001.getClientNumber());
		setPin(lgn1001.getPin());
		setPid(lgn1001.getPid());
		Thread.sleep(1000);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertFalse("Text found!", bodyText.contains("Incidents & Inspections"));

	}

}
