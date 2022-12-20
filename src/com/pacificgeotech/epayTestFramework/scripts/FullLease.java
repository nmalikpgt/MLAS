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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

import junit.framework.Assert;

public class FullLease {
	
	private String clientNumber;
	private String transactionId;
	private String claimId;
	private static String pid;
	private static String pin;
	private WebDriver driver;
	private String LicenceId;

	/*@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}*/

	@SuppressWarnings("deprecation")
	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();
		Actions builder = new Actions(driver);
		EXPVS001 expvs001 = new EXPVS001();
		expvs001.test();

		setClientNumber(expvs001.getClientNumber());
		setClaimId(expvs001.getClaimId());
		setTransactionId(expvs001.getTransactionId());
		setLicenceId(expvs001.getLicenceId());
		setPid(expvs001.getPid());
		setPin(expvs001.getPin());

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
				.click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out"))).click();

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
			element.sendKeys(expvs001.getPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(expvs001.getPin());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();
			
		}

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Work Reporting")));
			element.click();

			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Submit Report of Work")));
			element.click();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
			element.sendKeys(expvs001.getClientNumber());

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("createSubmissionBtnId")));
			element.click();

			Calendar calendar3 = Calendar.getInstance();
			calendar3.add(Calendar.DAY_OF_YEAR, -30);
			Date today3 = calendar3.getTime();
			DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
			String fewDayAgo = dateFormat3.format(today3);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fieldWorkFromDate")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fieldWorkFromDate")));
			element.sendKeys(fewDayAgo);

			Calendar calendar4 = Calendar.getInstance();
			calendar4.add(Calendar.DAY_OF_YEAR, -1);
			Date today4 = calendar4.getTime();
			DateFormat dateFormat4 = new SimpleDateFormat("yyyy-MM-dd");
			String Yesterday = dateFormat4.format(today4);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fieldWorkToDate")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fieldWorkToDate")));
			element.sendKeys(Yesterday);

			element = driverWait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[@id='commoditiesGroupExploredForId']/option[5]")));
			element.click();

			// add Comodities expored
			builder.keyDown(Keys.CONTROL).perform();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[1]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[2]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[3]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[4]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[5]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[6]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[7]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[8]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[9]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[10]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[11]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[12]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[13]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[14]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[15]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[16]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[17]")))
					.click();
			driverWait
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id='commoditiesExploredForId']/option[18]")))
					.click();
			builder.keyUp(Keys.CONTROL).perform();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputTenureNumbersId")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputTenureNumbersId")));
			element.sendKeys(expvs001.getClaimId());
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox']")));
			element.click();
			new Select(element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.id("pendingDistributionSelectId"))))
							.selectByVisibleText("No");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='N']")));
			element.click();

			Object file = null;
			try {
				file = new File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			Assert.assertTrue(((File) file).exists());
			WebElement browseButton = driver.findElement(By.name("fileInput"));
			browseButton.sendKeys(((File) file).getAbsolutePath());
			Thread.sleep(500);
			new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
					.selectByVisibleText("Technical Report");
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option.ng-binding.ng-scope")));
			element.click();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
			element.click();
			Thread.sleep(1500);
//			element = driverWait
//					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button']")));
//			element.click();
//			Thread.sleep(1000);
//			new Select(element = driverWait.until(ExpectedConditions
//					.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div/form/div[2]/div/div/select"))))
//							.selectByVisibleText("Ground Geoscience");
//
//			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("confirmBtnId")));
//			element.click();
//			Thread.sleep(1500);
//			new Select(
//					element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("surveyTypeCodeId"))))
//							.selectByVisibleText("Geology Survey");
//			element = driverWait
//					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"GEOL\"]")));
//			element.click();
//			Calendar calendar1 = Calendar.getInstance();
//			calendar1.add(Calendar.DAY_OF_YEAR, -2190);
//			Date today1 = calendar1.getTime();
//			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
//			String sixYearsAgo = dateFormat1.format(today1);
//			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("GSsurveyTypeFromDate0")));
//			element.clear();
//			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("GSsurveyTypeFromDate0")));
//			element.sendKeys(sixYearsAgo);
//
//			Calendar calendar7 = Calendar.getInstance();
//			calendar7.add(Calendar.DAY_OF_YEAR, -1825);
//			Date today7 = calendar7.getTime();
//			DateFormat dateFormat7 = new SimpleDateFormat("yyyy-MM-dd");
//			String fiveYearsAgo = dateFormat7.format(today7);
//			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("GSsurveyTypeToDate0")));
//			element.clear();
//			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("GSsurveyTypeToDate0")));
//			element.sendKeys(fiveYearsAgo);
//
//			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("GSsurveyTypeToDate0")));
//			element.clear();
//			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("GSsurveyTypeToDate0")));
//			element.sendKeys("2012-08-14");
//			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("unitOfWorkInputId")));
//			element.clear();
//			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("unitOfWorkInputId")));
//			element.sendKeys("survey");
//			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("costUnitInputId")));
//			element.clear();
//			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("costUnitInputId")));
//			element.sendKeys("500");
//			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("actualCostInputId")));
//			element.clear();
//			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("actualCostInputId")));
//			element.sendKeys("1000");
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button']")));
			element.click();
			new Select(element = driverWait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div/form/div[2]/div/div/select"))))
							.selectByVisibleText("Ground Geophysics");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("confirmBtnId")));
			element.click();

			new Select(element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("GEOLsurveyTypeCode0"))))
							.selectByVisibleText("Seismic");
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"SEIS\"]")));
			element.click();
			// add time
			Calendar calendar10 = Calendar.getInstance();
			calendar10.add(Calendar.DAY_OF_YEAR, -30);
			Date today10 = calendar10.getTime();
			DateFormat dateFormat10 = new SimpleDateFormat("yyyy-MM-dd");
			String MONTH = dateFormat10.format(today10);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("GEOLsurveyTypeFromDate0")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("GEOLsurveyTypeFromDate0")));
			element.sendKeys(MONTH);

			Calendar calendar81 = Calendar.getInstance();
			calendar81.add(Calendar.DAY_OF_YEAR, -1);
			Date today81 = calendar81.getTime();
			DateFormat dateFormat81 = new SimpleDateFormat("yyyy-MM-dd");
			String day = dateFormat81.format(today81);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("GEOLsurveyTypeToDate0")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("GEOLsurveyTypeToDate0")));
			element.sendKeys(day);
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("GEOLsurveyTypeUnitOfWorkInput0")));
			element.clear();
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("GEOLsurveyTypeUnitOfWorkInput0")));
			element.sendKeys("earthquake");
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("GEOLsurveyTypeCostUnitInput0")));
			element.clear();
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("GEOLsurveyTypeCostUnitInput0")));
			element.sendKeys("20000");
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("GEOLsurveyTypeActualCost0")));
			element.clear();
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("GEOLsurveyTypeActualCost0")));
			element.sendKeys("20000");
//			element = driverWait
//					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[10]")));
//			element.click();
			
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='wid-id-0']/div/fieldset/div[1]/div[2]/div/button")));
			element.click();
			new Select(element = driverWait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div/form/div[2]/div/div/select"))))
							.selectByVisibleText("Assays");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("confirmBtnId")));
			element.click();
			Calendar calendar8 = Calendar.getInstance();
			calendar8.add(Calendar.DAY_OF_YEAR, -1095);
			Date today8 = calendar8.getTime();
			DateFormat dateFormat8 = new SimpleDateFormat("yyyy-MM-dd");
			String threeYaGO = dateFormat8.format(today8);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeVOFromDate0")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeVOFromDate0")));
			element.sendKeys(threeYaGO);

			Calendar calendar9 = Calendar.getInstance();
			calendar9.add(Calendar.DAY_OF_YEAR, -912);
			Date today9 = calendar9.getTime();
			DateFormat dateFormat9 = new SimpleDateFormat("yyyy-MM-dd");
			String AGO = dateFormat9.format(today9);

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeVOToDate0")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeVOToDate0")));
			element.sendKeys(AGO);
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeVOUnitOfWorkInput0")));
			element.clear();
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeVOUnitOfWorkInput0")));
			element.sendKeys("500");
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeVOCostUnitInput0")));
			element.clear();
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeVOCostUnitInput0")));
			element.sendKeys("");
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeVOUnitOfWorkInput0")));
			element.clear();
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeVOUnitOfWorkInput0")));
			element.sendKeys("1");
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeVOCostUnitInput0")));
			element.clear();
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeVOCostUnitInput0")));
			element.sendKeys("500");
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeVOActualCostInput0")));
			element.clear();
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.name("costTypeVOActualCostInput0")));
			element.sendKeys("500");
//			element = driverWait
//					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[14]")));
//			element.click();
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='wid-id-0']/div/fieldset/div/div/div/button")));
			element.click();
			
			new Select(element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.id("communityNameSelectionId"))))
							.selectByVisibleText("Abitibiwinni, Conseil de la Première Nation");
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"2\"]")));
			element.click();
			Thread.sleep(1500);
			new Select(element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.id("contactPersonSelectionId"))))
							.selectByVisibleText("Bruno Kistabish");
			element = driverWait.until(ExpectedConditions
					.presenceOfElementLocated(By.cssSelector("#contactPersonSelectionId > option[value=\"3\"]")));
			element.click();
//			element = driverWait
//					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[16]")));
//			element.click();
			
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='wid-id-0']/div/fieldset/div/div/div/button")));
			element.click();

			new Select(element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.id("aboriginalConsultationCostSelectionId"))))
							.selectByVisibleText("Administration");
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"AD\"]")));
			element.click();

			// a year ago
			Calendar calendar12 = Calendar.getInstance();
			calendar12.add(Calendar.DAY_OF_YEAR, -365);
			Date today12 = calendar12.getTime();
			DateFormat dateFormat12 = new SimpleDateFormat("yyyy-MM-dd");
			String aYearAgo = dateFormat12.format(today12);

			element = driverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput0")));
			element.clear();
			element = driverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput0")));
			element.sendKeys(aYearAgo);

			// yesterday
			Calendar calendar13 = Calendar.getInstance();
			calendar13.add(Calendar.DAY_OF_YEAR, -1);
			Date today13 = calendar13.getTime();
			DateFormat dateFormat13 = new SimpleDateFormat("yyyy-MM-dd");
			String yestrday = dateFormat13.format(today13);

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
			new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("instrumentId"))))
					.selectByVisibleText(expvs001.getLicenceId());

			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.id("explorationWorkExpensesInputId")));
			element.clear();
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.id("explorationWorkExpensesInputId")));
			element.sendKeys("20000");
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
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
			element.click();
			Thread.sleep(1500);
			((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
			element.click();

			new WebDriverWait(driver, 50).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='widget-grid']/div[2]/div/button")));
			Thread.sleep(1000);
			driver.getPageSource().contains("Work report has been submitted successfully.");
			
//			new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
//					"//*[contains(text(), 'Work report has been submitted successfully.')]")));

		}


	public String getLicenceId() {
		return LicenceId;
	}

	public void setLicenceId(String licenceId) {
		LicenceId = licenceId;
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

	public static String getPid() {
		return pid;
	}

	public static void setPid(String pid) {
		AWR2A002.pid = pid;
	}

	public static String getPin() {
		return pin;
	}

	public static void setPin(String pin) {
		AWR2A002.pin = pin;
	}

}


