package com.pacificgeotech.epayTestFramework.scripts;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class CLM2A001 {
	
	private String clientNumber;
	private static String transactionId;
	private String claimId;
	private String pid;
	private String pin;
	private WebDriver driver;
	
	@After
	public void tearDown(){
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
		
		MP5003 mp5003 = new MP5003();
		mp5003.test();
		setClientNumber(mp5003.getClientNumber());
		setClaimId(mp5003.getClaimId());
		System.out.println("claim ID and Client number are : " + mp5003.getClaimId() + " " + mp5003.getClientNumber());
		setPid(mp5003.getPid());
		setPin(mp5003.getPin());
		
		String[] urlInfo = LGN1001.getLoginUrl();
		LGN1001 lgn1001 = new LGN1001();
		//Submit Notice of Claim Abandonment
		Thread.sleep(1000);
		//String [] loginURL = LGN1001.getLoginUrl();
		driver.get(urlInfo[0]); 
	    Thread.sleep(1000);
	    if ("remote".equals(urlInfo[1])) {
	    	lgn1001.getLoginInfoExternalRemote(urlInfo[1],urlInfo[0]);
		} else {
					
			lgn1001.getLoginInfoExternalLocalTest(urlInfo[1],urlInfo[0], getPid(),getPin());

		}
	    new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[8]/a[2]/span")));
        element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[8]/a[2]/span")));
        element.click();
        
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Submit Notice of Claim Abandonment"))).click();
        
        element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
        element.sendKeys(mp5003.getClaimId());
        
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();
		
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1500);
		new WebDriverWait(driver, 50)
		.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1500);
//		driver.findElement(By.xpath("//[@id='eventInfoUserFormHtmlId']/event-header/div/div/table/tbody/tr[2]/td[3]")).getText(); 
		//Get transaction Id from confirmation page
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1500);
		//this.setTransactionId(driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/section/event-info/div/event-header/div/div/table/tbody/tr[2]/td[3]")).getText());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		//search if claim is active
		//((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[2]/a[2]")))
				.click();
		new WebDriverWait(driver, 50)
		.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Search Claims")));
		Thread.sleep(1500);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Search Claims"))).click();

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Claim Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Claim Number']")).sendKeys(mp5003.getClaimId());
		Thread.sleep(2000);
		driverWait
		.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("[class='btn btn-labeled btn-primary']")))
		.click();
		
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		
		/*String actualString = driverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//*[@id='tenureSearchTblId']/tbody/tr/td[3]"))).getText();
		Assert.assertTrue(actualString.contains("Active Abandonment Pending"));*/
		
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("li.usr-block > a.dropdown-toggle > span.caret"))).click();
		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out"))).click();
	}
	
	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
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

}

