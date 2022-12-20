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

public class CLM1D001 {

	private String Pid;
	private String Pin;
	private String SecondPid;
	private String SecondPin;
	private WebDriver driver;
	private String clientNumber;
	
	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	@After
	public void tearDown(){
		driver.getCurrentUrl();
	}	
	
	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;		
	}

	public String getSecondPid() {
		return SecondPid;
	}

	public void setSecondPid(String secondPid) {
		SecondPid = secondPid;
	}

	public String getSecondPin() {
		return SecondPin;
	}

	public void setSecondPin(String secondPin) {
		SecondPin = secondPin;
	}

	public String getPid() {
		return Pid;
	}

	public void setPid(String pid) {
		Pid = pid;
	}

	public String getPin() {
		return Pin;
	}

	public void setPin(String pin) {
		Pin = pin;
	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		// Create new client as Buyer
		CLM1A005 clm1a005 = new CLM1A005();
		clm1a005.test();
		setClientNumber(clm1a005.getClientNumber());
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Automatic Test"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span"))); 
		 element.click();
		 Thread.sleep(1500);
		String[] loginURL = LGN1001.getLoginUrl();
		driver.get(loginURL[0]); // for local Test
		LGN1001 lgn1001 = new LGN1001();

		setPid(clm1a005.getSecondPid());
		setPin(clm1a005.getSecondPin());
		System.out.println("Pid and Pin:  " + clm1a005.getSecondPid() + "; " + clm1a005.getSecondPin());

		if ("localTest".equals(loginURL[1])) {

			driver.navigate().refresh();
			driver.navigate().to(loginURL[0]);

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
			element.sendKeys(clm1a005.getSecondPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(clm1a005.getSecondPin());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

		} else {

			lgn1001.getLoginInfoExternalRemote(loginURL[1], loginURL[0]);
		}

		// select claim management tab

		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Claim Management")));
		element.click();
		
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Joint Tenant Approval For Transfer of Mining Claim"))); 
		 element.click();
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"/html/body/div[2]/div[3]/div/section/div/form/article[2]/div/div/div/fieldset/div[2]/div/ol/li/a")));
			element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("acceptTransfer"))); 
		 element.click();
		 Thread.sleep(1500);
		 ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		 element.click();
		 Thread.sleep(1500);
		 ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnConfirmBtnId")));
		 element.click();
		 new WebDriverWait(driver, 50)
			.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Transfer has been approved by the Joint Tenant successfully. All parties will be notified of their pending transfer.')]")));
		 
		 

	}

}
