package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

public class EXP1008 {
	private String pid;
	private String pin;
	private String clientNumber;
	private String claimId;
	private String transactionId;
	private WebDriver driver;

	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();
		MP5003 mp5003 = new MP5003();
		mp5003.test();
		setPid(mp5003.getPid());
		setPin(mp5003.getPin());
		setClientNumber(mp5003.getClientNumber());
		setClaimId(mp5003.getClaimId());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("MLAS Admin"))); 
		 element.click();
		 Thread.sleep(1500);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span"))); 
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
			element.sendKeys(mp5003.getPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(mp5003.getPin());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();
		}

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[10]/a[2]/b/em")));
		element.click();
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.linkText("Submit Plan/Permit for Early Exploration Activities")));
		element.click();
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

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("claimIds")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("claimIds")));
		element.sendKeys(mp5003.getClaimId());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		Thread.sleep(10000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("planActivityChkId1")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("permitActivityChkId1")));
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

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("estimatedStartDateInTxt")));
		element.clear();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("estimatedStartDateInTxt")));
		element.sendKeys(dt);
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("maapIdInput")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("maapIdInput")));
		element.sendKeys("100");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[17]")))
				.click();

		// choose No Bulk
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='haveConsultedInd'
		// and @value='N']"))).click();
		// choose No Aboriginal

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("certificationInd"))).click();

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

		// new
		// Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("docTypeCode_1")))).selectByVisibleText("Project
		// Scale Map");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='13'])[2]"))).click();

		// new WebDriverWait(driver,
		// 50).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("projectScaleMap.pdf")));
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@name='haveConsultedInd' and @value='N']")))
				.click();
		driverWait
		.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[@name='proceedInd' and @value='Y']")))
		.click();
		Thread.sleep(3000);
		Object file2 = null;
		try {
			file2 = new File(CLM2B003.class.getClassLoader().getResource("Flaring.pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file2).exists());
		WebElement browseButton2 = driver.findElement(By.name("fileInput"));
		browseButton2.sendKeys(((File) file2).getAbsolutePath());

		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("docTypeCode_2")))).selectByVisibleText("Bulk Sample Permission Application");
	    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='10'])[3]"))); 
	 element.click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("summarySubmissionBtnId")));
		element.click();

		Thread.sleep(1000);
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