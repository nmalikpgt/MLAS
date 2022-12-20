package com.pacificgeotech.epayTestFramework.scripts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class AWR2A013 {

	private String pin;
	private String pid;
	private String clientNumber;

	private String claimId;
	private WebDriver driver;


	  @After public void tearDown() { driver.getCurrentUrl(); }
	  
	  @AfterClass public static void afterClass() { WebDriverManager.instance =
	  null; }
 

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		Actions builder = new Actions(driver);

		// Approve the created claim SRO
		// This script works based on client Number and claim ID created in
		// MP1001
		MP5003 mp5003 = new MP5003();
		mp5003.test();

		setClientNumber(mp5003.getClientNumber());
		setPin(mp5003.getPin());
		setPid(mp5003.getPid());
		setClaimId(mp5003.getClaimId());

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
			element.sendKeys(mp5003.getPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(mp5003.getPin());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();
		}
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Work Reporting")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Submit Report of Work")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(mp5003.getClientNumber());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("createSubmissionBtnId")));
		element.click();

		// a year ago
		Calendar calendar12 = Calendar.getInstance();
		calendar12.add(Calendar.DAY_OF_YEAR, -30);
		Date today12 = calendar12.getTime();
		DateFormat dateFormat12 = new SimpleDateFormat("yyyy-MM-dd");
		String aYearAgo = dateFormat12.format(today12);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fieldWorkFromDate")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fieldWorkFromDate")));
		element.sendKeys(aYearAgo);

		// yesterday
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_YEAR, -1);
		Date today1 = calendar1.getTime();
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		String yesterday = dateFormat1.format(today1);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fieldWorkToDate")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fieldWorkToDate")));
		element.sendKeys(yesterday);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"IM\"]")));
		element.click();

		// add comodities
		builder.keyDown(Keys.CONTROL).perform();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[1]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[2]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[3]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[4]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[5]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[6]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[7]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[8]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[9]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[10]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[11]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[12]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[13]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[14]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[15]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[16]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[17]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[18]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[19]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[20]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[21]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[22]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[23]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[24]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[25]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[26]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[27]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[28]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[29]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[30]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[31]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[32]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[33]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[34]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[35]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[36]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[37]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[38]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[39]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[40]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[41]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[42]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[43]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[44]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[45]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[46]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[47]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[48]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[49]")))
				.click();
		builder.keyUp(Keys.CONTROL).perform();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputTenureNumbersId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputTenureNumbersId")));
		element.sendKeys(mp5003.getClaimId());
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox']")));
		element.click();
		new Select(element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("pendingDistributionSelectId"))))
						.selectByVisibleText("No");

		// click Save
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("saveDraftBtnId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("i[name=\"home\"]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Submit Report of Work")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("draftLnkId0")));
		element.click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		Thread.sleep(1500);

		// add exploration activities
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button']")));
		element.click();

		new Select(element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div/form/div[2]/div/div/select"))))
						.selectByVisibleText("Pitting and Trenching");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("confirmBtnId")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("surveyTypeCodeId"))))
				.selectByVisibleText("Manual Work");
		Calendar calendar11 = Calendar.getInstance();
		calendar11.add(Calendar.DAY_OF_YEAR, -2190);
		Date today11 = calendar11.getTime();
		DateFormat dateFormat11 = new SimpleDateFormat("yyyy-MM-dd");
		String sixYearsAgo = dateFormat11.format(today11);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("PCsurveyTypeFromDate0")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("PCsurveyTypeFromDate0")));
		element.sendKeys(sixYearsAgo);

		Calendar calendar7 = Calendar.getInstance();
		calendar7.add(Calendar.DAY_OF_YEAR, -1827);
		Date today7 = calendar7.getTime();
		DateFormat dateFormat7 = new SimpleDateFormat("yyyy-MM-dd");
		String fiveYearsAgo = dateFormat7.format(today7);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("PCsurveyTypeToDate0")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("PCsurveyTypeToDate0")));
		element.sendKeys(fiveYearsAgo);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("unitOfWorkInputId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("unitOfWorkInputId")));
		element.sendKeys("survey123!@#");
		Thread.sleep(500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("costUnitInputId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("costUnitInputId")));
		element.sendKeys("1");
		Thread.sleep(500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("actualCostInputId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("actualCostInputId")));
		element.sendKeys("100");

		// add another exploration activities
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button']")));
		element.click();

		new Select(element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div/form/div[2]/div/div/select"))))
						.selectByVisibleText("Mechanized Stripping");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("confirmBtnId")));
		element.click();
		Thread.sleep(500);
		//new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("surveyTypeCodeId"))))
		//		.selectByVisibleText("Mechanized Stripping(<100m2 in 200 metre radius)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"OBSTRIP\"]")));
		element.click();
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_YEAR, -30);
		Date today2 = calendar2.getTime();
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		String sixYearsAgo2 = dateFormat2.format(today2);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("SDsurveyTypeFromDate0")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("SDsurveyTypeFromDate0")));
		element.sendKeys(sixYearsAgo2);

		Calendar calendar3 = Calendar.getInstance();
		calendar3.add(Calendar.DAY_OF_YEAR, -1);
		Date today3 = calendar3.getTime();
		DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
		String fiveYearsAgo3 = dateFormat3.format(today3);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("SDsurveyTypeToDate0")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("SDsurveyTypeToDate0")));
		element.sendKeys(fiveYearsAgo3);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("SDsurveyTypeUnitOfWorkInput0")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("SDsurveyTypeUnitOfWorkInput0")));
		element.sendKeys("Line");
		Thread.sleep(500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("SDsurveyTypeCostUnitInput0")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("SDsurveyTypeCostUnitInput0")));
		element.sendKeys("1");
		Thread.sleep(500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("SDsurveyTypeActualCost0")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("SDsurveyTypeActualCost0")));
		element.sendKeys("200");

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[11]")));
		element.click();

		new Select(element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("communityNameSelectionId"))))
						.selectByVisibleText("Alderville First Nation");
		Thread.sleep(1000);
		new Select(element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("contactPersonSelectionId"))))
						.selectByVisibleText("James Marsden");
		
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[13]")));
		element.click();
		
		new Select(element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("aboriginalConsultationCostSelectionId"))))
						.selectByVisibleText("Administration");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"AD\"]")));
		element.click();

		// a year ago
		Calendar calendar13 = Calendar.getInstance();
		calendar13.add(Calendar.DAY_OF_YEAR, -365);
		Date today13 = calendar13.getTime();
		DateFormat dateFormat13 = new SimpleDateFormat("yyyy-MM-dd");
		String aYearAgo2 = dateFormat13.format(today13);

		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput0")));
		element.clear();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput0")));
		element.sendKeys(aYearAgo2);

		// yesterday
		Calendar calendar15 = Calendar.getInstance();
		calendar15.add(Calendar.DAY_OF_YEAR, -1);
		Date today15 = calendar15.getTime();
		DateFormat dateFormat15 = new SimpleDateFormat("yyyy-MM-dd");
		String yestrday = dateFormat15.format(today15);

		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostToDateInput_0")));
		element.clear();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostToDateInput_0")));
		element.sendKeys(yestrday);

		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("aboriginalConsultationCostDescriptionInputId")));
		element.clear();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("aboriginalConsultationCostDescriptionInputId")));
		element.sendKeys("test123!@#");
		
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("aboriginalConsultationCostActualCostInputId")));
		element.clear();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("aboriginalConsultationCostActualCostInputId")));
		element.sendKeys("250");

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("explorationWorkExpensesInputId")));
		element.clear();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("explorationWorkExpensesInputId")));
		element.sendKeys("100");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("aboriginalConsultationExpensesInputId")));
		element.clear();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("aboriginalConsultationExpensesInputId")));
		element.sendKeys("250");
		

		new Select(element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("acknowledgeSelectId"))))
						.selectByVisibleText("I agree");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='true']")));
		element.click();
		Thread.sleep(500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 5300)");
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='widget-grid']/div[2]/div/button")));
		Thread.sleep(1000);
		driver.getPageSource().contains("Work report has been submitted successfully.");

	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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
