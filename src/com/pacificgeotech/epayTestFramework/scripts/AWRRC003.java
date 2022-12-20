package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

import junit.framework.Assert;

public class AWRRC003 {

	private WebDriver driver;
	private String ClaimNo;
	private String pid;
	private String pin;
	private String clientNumber;
	private String SecondClaimNo;
	private String ThirdClaimNo;

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
		Actions builder = new Actions(driver);
		AWR2A004 awr2a004 = new AWR2A004();
		awr2a004.test();

		setClaimNo(awr2a004.getClaimNo());
		setPin(awr2a004.getPin());
		setPid(awr2a004.getPid());
		setClientNumber(awr2a004.getClientNumber());
		setSecondClaimNo(awr2a004.getSecondClaimNo());
		setThirdClaimNo(awr2a004.getThirdClaimNo());
		
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/a/span"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span"))); 
		 element.click();
		 
		LGN1002 lgn1002 = new LGN1002();
		lgn1002.test();
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(6000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(6000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(6000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(awr2a004.getClaimNo());

		Thread.sleep(4000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		// assign to all
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Geoscience Assessor")));
		element.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(awr2a004.getClaimNo());

		Thread.sleep(4000);

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();
		
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("rejectRationaleInputId"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("rejectRationaleInputId"))); 
		 element.sendKeys("not enough supporting evidence for work performed");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[2]"))); 
		 element.click();

		 new Select(element = driverWait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div/form/div[2]/div/div/select"))))
							.selectByVisibleText("Pitting and Trenching");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("confirmBtnId")));
			element.click();

		    new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("surveyTypeCodeId")))).selectByVisibleText("Manual Work");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"Manual Work\"]"))); 
		 element.click();
		 //3 yrs ago
		 Calendar calendar12 = Calendar.getInstance();
			calendar12.add(Calendar.DAY_OF_YEAR, -1095);
			Date today12 = calendar12.getTime();
			DateFormat dateFormat12 = new SimpleDateFormat("yyyy-MM-dd");
			String aYearAgo = dateFormat12.format(today12);
			
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("PCsurveyTypeFromDate0"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("PCsurveyTypeFromDate0"))); 
		 element.sendKeys(aYearAgo);
		   //2 yrs ago

			Calendar calendar1 = Calendar.getInstance();
			calendar1.add(Calendar.DAY_OF_YEAR, -745);
			Date today1 = calendar1.getTime();
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			String yrsAgo1 = dateFormat1.format(today1);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("PCsurveyTypeToDate0"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("PCsurveyTypeToDate0"))); 
		 element.sendKeys(yrsAgo1);
		 
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("unitOfWorkInputId"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("unitOfWorkInputId"))); 
		 element.sendKeys("hourly");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("costUnitInputId"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("costUnitInputId"))); 
		 element.sendKeys("20");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("actualCostInputId"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("actualCostInputId"))); 
		 element.sendKeys("100");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("actualCostInputId"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("actualCostInputId"))); 
		 element.sendKeys("0");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("PCsurveyTypeRevisedAdjustedCost0"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("PCsurveyTypeRevisedAdjustedCost0"))); 
		 element.sendKeys("100");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeRevisedAdjustedCostInput0"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeRevisedAdjustedCostInput0"))); 
		 element.sendKeys("250");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("generateDocTypeSelectionId"))); 
		 element.click();
		    new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("generateDocTypeSelectionId")))).selectByVisibleText("Approval Letter");

		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[9]"))); 
		 element.click();
		    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp |  | 30000]]
			
			Object file = null;
			try {
				file = new File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			Assert.assertTrue(((File) file).exists());
			WebElement browseButton = driver.findElement(By.name("fileInput"));
			browseButton.sendKeys(((File) file).getAbsolutePath());

		    new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode")))).selectByVisibleText("Approval Letter");
		    driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reviewOutcomeRequiresInspectionIndInput' and @value='false']"))).click();
		    driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reviewOutcomeRequiresNoticeIndInput' and @value='false']"))).click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		 element.click();
		 Thread.sleep(2500);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		 element.click();

			new WebDriverWait(driver, 50).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='wid-id-0']/div/div/fieldset/div/div[1]/div[1]/div/table/tbody/tr[1]/td[11]/div/div")));
			Thread.sleep(1000);
			driver.getPageSource().contains("Rejection Rationale: not enough supporting evidence for work performed");
		
		

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

}
