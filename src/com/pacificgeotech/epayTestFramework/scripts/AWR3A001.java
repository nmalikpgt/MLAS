package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;

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

public class AWR3A001 {
	
	private WebDriver driver;
	private String ClaimNo;
	private String pid;
	private String pin;
	private String clientNumber;
	private String claimId;
	private String SecondClaimNo;
	private String ThirdClaimNo;
	

//	@After
//	public void tearDown() {
//		driver.getCurrentUrl();
//	}
//
//	@AfterClass
//	public static void afterClass() {
//		WebDriverManager.instance = null;
//	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		AWRER003 awrer003 = new AWRER003();
		awrer003.test();

		setClaimNo(awrer003.getClaimNo());
		setClientNumber(awrer003.getClientNumber());
		setPin(awrer003.getPin());
		setPid(awrer003.getPid());
		setSecondClaimNo(awrer003.getSecondClaimNo());
		setThirdClaimNo(awrer003.getThirdClaimNo());
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		element.clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(awrer003.getSecondClaimNo());

		Thread.sleep(4000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		// assign to all
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Customer Support Clerk")));
		element.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(awrer003.getSecondClaimNo());

		Thread.sleep(4000);

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("noticeRequireId"))))
				.selectByVisibleText("No");

		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='sroNumber']"))).sendKeys("2");

		Thread.sleep(3000);
		Object file = null;
		try {
			file = new File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file).exists());
		WebElement browseButton = driver.findElement(By.name("fileInput"));
		browseButton.sendKeys(((File) file).getAbsolutePath());

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
				.selectByVisibleText("Notification Letter");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("isnrSummarynextBtnId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		Thread.sleep(500);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Submission to identify the presence of surface owners for notification has been successful.')]")));


		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/a/span")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span")));
		element.click();

		String[] urlInfo = LGN1001.getLoginUrl();
		LGN1001 lgn1001 = new LGN1001();
		// Submit Notice of Claim Abandonment
		Thread.sleep(1000);
		// String [] loginURL = LGN1001.getLoginUrl();
		driver.get(urlInfo[0]);
		Thread.sleep(1000);
		if ("remote".equals(urlInfo[1])) {
			lgn1001.getLoginInfoExternalRemote(urlInfo[1], urlInfo[0]);
		} else {

			// lgn1001.getLoginInfoExternalLocalTest(urlInfo[1], urlInfo[0],
			// mp5003.getPid(), mp5003.getPin());

			driver.navigate().refresh();
			driver.navigate().to(urlInfo[0]);

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
			element.sendKeys(awrer003.getPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(awrer003.getPin());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();
		}
		
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Work Reporting"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Distribute Approved Credits"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect"))); 
		 element.sendKeys(awrer003.getClientNumber());
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("createSubmissionBtnId"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("receiverClaimIdsCsv"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("receiverClaimIdsCsv"))); 
		 element.sendKeys(awrer003.getSecondClaimNo());
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("providerTenureIdsCsv"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("providerTenureIdsCsv"))); 
		 element.sendKeys(awrer003.getClaimNo());
		 
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("bridgingTenureIdsCsv"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("bridgingTenureIdsCsv"))); 
		 element.sendKeys(awrer003.getThirdClaimNo());
		 
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))); 
		 element.click();
		 Thread.sleep(1500);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[2]"))); 
		 element.click();
		    new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("providerTenureAtIdx0OfReceiverAtIdx0")))).selectByVisibleText(awrer003.getClaimNo());

		    new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("providerReserveTypeAtIdx0OfReceiverAtIdx0")))).selectByVisibleText("Exploration Reserve");
	
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("providerDistributionReserveAtIdx0OfReceiverAtIdx0"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("providerDistributionReserveAtIdx0OfReceiverAtIdx0"))); 
		 element.sendKeys("100");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))); 
		 element.click();
		 Thread.sleep(1500);
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))); 
		 element.click();
		 new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"//*[contains(text(), 'Distribution of approved credits completed successfully')]")));
		

}

	public String getThirdClaimNo() {
		return ThirdClaimNo;
	}

	public void setThirdClaimNo(String thirdClaimNo) {
		ThirdClaimNo = thirdClaimNo;
	}

	public String getSecondClaimNo() {
		return SecondClaimNo;
	}

	public void setSecondClaimNo(String secondClaimNo) {
		SecondClaimNo = secondClaimNo;
	}

	public String getClaimNo() {
		return ClaimNo;
	}

	public void setClaimNo(String claimNo) {
		ClaimNo = claimNo;
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

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
}