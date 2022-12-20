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

public class MP5007 {

	private WebDriver driver;
	private String ClaimNo;
	private String clientNumber;
	private String pid;
	private String pin;
	private String SecondClaimNo;
	private String ThirdClaimNo;
	
	public String getThirdClaimNo() {
		return ThirdClaimNo;
	}

	public void setThirdClaimNo(String thirdClaimNo) {
		ThirdClaimNo = thirdClaimNo;
	}

	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}



	public String getSecondClaimNo() {
		return SecondClaimNo;
	}



	public void setSecondClaimNo(String secondClaimNo) {
		SecondClaimNo = secondClaimNo;
	}



	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		MP1B006 mp1b006 = new MP1B006();
		mp1b006.test();
		setClientNumber(mp1b006.getClientNumber());
		setPin(mp1b006.getPin());
		setPid(mp1b006.getPid());
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.id("claim_id_0")));
		WebElement TxtBoxContent = driver.findElement(By.id("claim_id_0"));
		String ClaimNo = TxtBoxContent.getText();
		System.out.println("Claim Number " + ClaimNo);
		setClaimNo(ClaimNo);
		//second claim
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.id("claim_id_2")));
		WebElement TxtBoxContent1 = driver.findElement(By.id("claim_id_2"));
		String SecondClaimNo = TxtBoxContent1.getText();
		System.out.println("Second Claim Number " + SecondClaimNo);
		setSecondClaimNo(SecondClaimNo);
		//3rd claim
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.id("claim_id_1")));
		WebElement TxtBoxContent2 = driver.findElement(By.id("claim_id_1"));
		String ThirdClaimNo = TxtBoxContent2.getText();
		System.out.println("Third Claim Number " + ThirdClaimNo);
		setThirdClaimNo(ThirdClaimNo);
		
		//make claim active

		Thread.sleep(1000);
		String[] loginURL = LGN1001.getLoginUrl();
		driver.get(loginURL[2]); // for local Test
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		element.sendKeys("admin");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
		element.sendKeys("@MLAS4you");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();
		Thread.sleep(2000);		

		WebElement appElement = null;
		try {
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
			element.click();
			Thread.sleep(2000);	
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
			element.clear();
			Thread.sleep(3000);
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
			element.sendKeys(ClaimNo);	
			Thread.sleep(2000);
			appElement = driverWait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Claim ID: " + ClaimNo + "')]")));
			Thread.sleep(2000);
			
		} catch (Throwable e) {
		}
		while (appElement == null /*|| !appElement.isDisplayed()*/) {System.out.println("is looping");
			try {
				element = driverWait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
				element.click();
				Thread.sleep(2000);
				element = driverWait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
				element.clear();
				Thread.sleep(2000);
				element = driverWait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
				element.sendKeys(ClaimNo);	
				Thread.sleep(2000);
				appElement = driverWait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Claim ID: " + ClaimNo + "')]")));


			} catch (Throwable e) {
				appElement = null;
			}
		}
		Thread.sleep(2000);		
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		element.clear();
		Thread.sleep(2000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		element.sendKeys(ClaimNo);	
	
		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		Thread.sleep(3000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		Thread.sleep(2000);
		// assign to all
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("System Administrator")));
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(2000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(ClaimNo);

		Thread.sleep(4000);

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();


		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("noticeRequireId"))))
				.selectByVisibleText("No");

		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='sroNumber']"))).sendKeys("2");

		Thread.sleep(1500);
//		Object file = null;
//		try {
//			file = new File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI());
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
//		Assert.assertTrue(((File) file).exists());
//		WebElement browseButton = driver.findElement(By.name("fileInput"));
//		browseButton.sendKeys(((File) file).getAbsolutePath());
//
//		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
//		Thread.sleep(1000);
//		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
//				.selectByVisibleText("Notification Letter");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("isnrSummarynextBtnId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		Thread.sleep(500);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Submission to identify the presence of surface owners for notification has been successful.')]")));

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


	public String getClaimNo() {
		return ClaimNo;
	}



	public void setClaimNo(String claimNo) {
		ClaimNo = claimNo;
	}

}
