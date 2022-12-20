package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

import junit.framework.Assert;

public class EXP1001 {

	private String clientNumber;
	private String transactionId;
	private String claimId;
	private String pid;
	private String pin;
	private String LicenceId;
	public String getLicenceId() {
		return LicenceId;
	}

	public void setLicenceId(String licenceId) {
		LicenceId = licenceId;
	}

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

		MP5003 mp5003 = new MP5003();
		mp5003.test();
		setClientNumber(mp5003.getClientNumber());
		setClaimId(mp5003.getClaimId());
		System.out.println("claim ID and Client number are : " + mp5003.getClaimId() + " " + mp5003.getClientNumber());
		setPid(mp5003.getPid());
		setPin(mp5003.getPin());

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

			lgn1001.getLoginInfoExternalLocalTest(urlInfo[1], urlInfo[0], mp5003.getPid(), mp5003.getPin());

		}

		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.linkText("Early Exploration Activities")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Early Exploration Activities")));
		element.click();
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.linkText("Submit Plan/Permit for Early Exploration Activities")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(mp5003.getClientNumber());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("disclaimerIndId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("createSubmissionBtnId")));
		element.click();

		new Select(element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedMethodSlctId")))).selectByVisibleText(
						"Inputting or pasting a list of comma delimited claim and/or tenure numbers that can be validated");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("tenureIds")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("tenureIds")));
		element.sendKeys(mp5003.getClaimId());
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(
				By.xpath("(//button[@type='button'])[2]")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("(//button[@type='button'])[2]")));
		element.click();
		//element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='btn btn-labeled btn-success']"))); 
		//element.click();
		Thread.sleep(15000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("planActivityChkId1")));
		element.click();
		// Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("projectNameInput")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("projectNameInput")));
		element.sendKeys("test1");

		// add date in the future

		String dt = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, 1); // number of days to add
		dt = sdf.format(c.getTime()); // dt is now the new date

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("estimatedStartDateTxtId")));
		element.clear();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("estimatedStartDateTxtId")));
		element.sendKeys(dt);
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("qualifiedSupervisorClientIdInput")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("qualifiedSupervisorClientIdInput")));
		element.sendKeys(mp5003.getClientNumber());

		//driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[17]")))
		//		.click();

		// choose No Bulk
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='haveConsultedInd'
		// and @value='N']"))).click();
		// choose No Aboriginal

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("certificationInd"))).click();
		Thread.sleep(1500);
		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(
				By.name("fileInput")));
		Thread.sleep(3000);
		Object file = null;
		try {
			file = new File(CLM2B003.class.getClassLoader().getResource("Flaring.pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file).exists());
		WebElement browseButton = driver.findElement(By.name("fileInput"));
		browseButton.sendKeys(((File) file).getAbsolutePath());

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
				.selectByVisibleText("Regional Scale Map");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='12']"))).click();

		Thread.sleep(1000);

		Object file1 = null;
		try {
			file1 = new File(
					CLM2B003.class.getClassLoader().getResource("Claim History Report (106090) (1).pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file1).exists());
		WebElement browseButton1 = driver.findElement(By.name("fileInput"));
		browseButton1.sendKeys(((File) file1).getAbsolutePath());

		
		Thread.sleep(1000);
		// Activity detail report
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("docTypeCode_1"))))
		.selectByVisibleText("Activity Details Report");
		//driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='12'])[2]"))).click();

		Thread.sleep(1000);

		Object file2 = null;
		try {
			file2 = new File(
					CLM2B003.class.getClassLoader().getResource("Claim History Report (106090) (1).pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file2).exists());
		WebElement browseButton2 = driver.findElement(By.name("fileInput"));
		browseButton2.sendKeys(((File) file2).getAbsolutePath());

		
		Thread.sleep(1000);
		// Activity detail report
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("docTypeCode_2"))))
		.selectByVisibleText("Project Scale Map");
		Thread.sleep(1000);
//		 new WebDriverWait(driver,	
//		 50).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("projectScaleMap.pdf")));
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@name='haveConsultedInd' and @value='N']")))
				.click();
		Thread.sleep(1000);
		/*driverWait
		.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[@name='proceedInd' and @value='N']")))
		.click();*/
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("summarySubmissionBtnId")));
		element.click();

		Thread.sleep(1000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//button[@type='button'])[2]")));
		// To Confirmation page
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[2]")))
				.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'Early Exploration Plan/Permit has been submitted successfully')]")));
		Thread.sleep(1500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, -2300)");
		String transactionId;
		transactionId = driver
				.findElement(
						By.xpath("//*[@id='eventInfoUserFormHtmlId']/div/event-header/div/div/table/tbody/tr[2]/td[3]"))
				.getText();
		setTransactionId(transactionId);
		System.out.println("Transaction ID: " + getTransactionId());
		
		String LicenceId;
		LicenceId = driver
				.findElement(
						By.xpath("//*[@id='wid-id-0']/div/div/div[1]/div[1]/div[2]/div"))
				.getText();
		setLicenceId(LicenceId);
		System.out.println("Licence ID: " + getLicenceId());


	}

	private void waitForNumberofWindowsToEqual(int i) {
		// TODO Auto-generated method stub
		
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
