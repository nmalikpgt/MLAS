package com.pacificgeotech.epayTestFramework.scripts;


import java.io.File;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import com.pacificgeotech.epayTestFramework.dbConnection.DbConnection;

import junit.framework.Assert;

public class AWR2A001 {

	private WebDriver driver;
	private String ClaimNo;
	private String clientNumber;
	private String pid;
	private String pin;
	private int reportNo;
	private String SecondClaimNo;
	private String ThirdClaimNo;
	
	@After public void tearDown() { driver.getCurrentUrl(); }
	  
	  @AfterClass public static void afterClass() { WebDriverManager.instance =
	  null; }

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();
		Actions builder = new Actions(driver);
		MP5007 mp5007 = new MP5007();
		mp5007.test();
		setClientNumber(mp5007.getClientNumber());
		setPin(mp5007.getPin());
		setPid(mp5007.getPid());
		setClaimNo(mp5007.getClaimNo());
		setSecondClaimNo(mp5007.getSecondClaimNo());
		setThirdClaimNo(mp5007.getThirdClaimNo());
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
			element.sendKeys(mp5007.getPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(mp5007.getPin());
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
		element.sendKeys(mp5007.getClientNumber());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("createSubmissionBtnId")));
		element.click();

		Calendar calendar3 = Calendar.getInstance();
		calendar3.add(Calendar.DAY_OF_YEAR, -3);
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

		element =
				driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='commoditiesGroupExploredForId']/option[1]")));
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
		builder.keyUp(Keys.CONTROL).perform();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputTenureNumbersId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputTenureNumbersId")));
		element.sendKeys(mp5007.getClaimNo());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox']")));
		element.click();
		new Select(element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("pendingDistributionSelectId"))))
						.selectByVisibleText("Yes");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='Y']")));
		element.click();
		/*
		 * element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "uploadFileId"))); element.clear(); element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "uploadFileId")));
		 * element.sendKeys("C:\\Users\\cvlasceanu\\Desktop\\linux2.pdf");
		 */

		Object file = null;
		try {
			file = new File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file).exists());
		WebElement browseButton = driver.findElement(By.name("fileInput"));
		browseButton.sendKeys(((File) file).getAbsolutePath());

		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
				.selectByVisibleText("Technical Report");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option.ng-binding.ng-scope")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[2]")));
		element.click();
		Thread.sleep(1000);
		new Select(element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("communityNameSelectionId"))))
						.selectByVisibleText("Aamjiwnaang First Nation (Sarnia)");

		Thread.sleep(1000);
		new Select(element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("contactPersonSelectionId"))))
						.selectByVisibleText("Joanne Rogers");

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[4]")));
		element.click();
		new Select(element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("aboriginalConsultationCostSelectionId"))))
						.selectByVisibleText("Administration");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"AD\"]")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.btn.btn-default")));
		element.click();

		// date a year from current date
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -365);
		Date today = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String yearAgo = dateFormat.format(today);

		// date a year from current date
				Calendar calendar121 = Calendar.getInstance();
				calendar121.add(Calendar.DAY_OF_YEAR, -1);
				Date today121 = calendar121.getTime();
				DateFormat dateFormat121 = new SimpleDateFormat("yyyy-MM-dd");
				String TODAY = dateFormat121.format(today121);
		
		
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput0")));
		element.click();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput0")));
		element.clear();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput0")));
		element.sendKeys(yearAgo);
		
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostToDateInput_0")));
		element.click();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostToDateInput_0")));
		element.clear();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostToDateInput_0")));
		element.sendKeys(TODAY);
		
		/*element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[6]")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[22]")));
		element.click();*/
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("aboriginalConsultationCostDescriptionInputId")));
		element.clear();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("aboriginalConsultationCostDescriptionInputId")));
		element.sendKeys("Test123@#");
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("aboriginalConsultationCostActualCostInputId")));
		element.clear();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("aboriginalConsultationCostActualCostInputId")));
		element.sendKeys("100");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[4]")));
		element.click();
		new Select(element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostSelection1"))))
						.selectByVisibleText("Document Preparation");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='DP'])[2]")));
		element.click();

		// date a year from current date
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_YEAR, -1500);
		Date today1 = calendar1.getTime();
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		String FoutYAgo = dateFormat1.format(today1);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput1")));
		element.click();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput1")));
		element.click();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput1")));
		element.clear();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput1")));
		element.sendKeys(FoutYAgo);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_YEAR, -1460);
		Date today2 = calendar2.getTime();
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		String Exact4y = dateFormat2.format(today2);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostToDateInput_1")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostToDateInput_1")));
		element.clear();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostToDateInput_1")));
		element.sendKeys(Exact4y);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostDescriptionInput1")));
		element.clear();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostDescriptionInput1")));
		element.sendKeys("test");
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostActualCostInput1")));
		element.clear();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostActualCostInput1")));
		element.sendKeys("300");
		
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("explorationWorkExpensesInputId"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("explorationWorkExpensesInputId"))); 
		 element.sendKeys("300");
		    new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("acknowledgeSelectId")))).selectByVisibleText("I agree");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='true']"))); 
		 element.click();
		 
		 //4 yaers ago
		 Calendar calendar5 = Calendar.getInstance();
			calendar5.add(Calendar.DAY_OF_YEAR, -1460);
			Date today5 = calendar5.getTime();
			DateFormat dateFormat5 = new SimpleDateFormat("yyyy-MM-dd");
			String fourYaGO = dateFormat5.format(today5);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput1"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput1"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput1"))); 
		 element.sendKeys(fourYaGO);
		 
		 //4years and few days 
		 Calendar calendar6 = Calendar.getInstance();
			calendar6.add(Calendar.DAY_OF_YEAR, -1463);
			Date today6 = calendar6.getTime();
			DateFormat dateFormat6 = new SimpleDateFormat("yyyy-MM-dd");
			String fOURyANDdAYS = dateFormat6.format(today6);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput1"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput1"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("aboriginalConsultationCostFromDateInput1"))); 
		 element.sendKeys(fOURyANDdAYS);
		 
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("explorationWorkExpensesInputId"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("explorationWorkExpensesInputId"))); 
		 element.sendKeys("0");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("aboriginalConsultationExpensesInputId"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("aboriginalConsultationExpensesInputId"))); 
		 element.sendKeys("400");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))); 
		 element.click();
		 Thread.sleep(1500);
		 ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))); 
		 element.click();
		 
		 new WebDriverWait(driver, 50)
			.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='widget-grid']/div[2]/div/button")));
		 Thread.sleep(1000);		 
		 driver.getPageSource().contains("Work report has been submitted successfully.");
		 
		 setReportNo(extractReportNo());
		 System.out.println("Report number is: " + getReportNo());
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

		private int extractReportNo() throws ClassNotFoundException, SQLException {
				int reportNo = 0;

				Connection con = DbConnection.getConnection();
				try {
					Statement st = (Statement) con.createStatement();
					String sql = ("SELECT max(REPORT_OF_WORK_ID) as REPORT_OF_WORK_ID FROM MAM.MTA_REPORT_OF_WORK");
					ResultSet rs = st.executeQuery(sql);
					if (rs.next()) {
						reportNo = rs.getInt("REPORT_OF_WORK_ID");
						
					}

				} finally {
					con.close();
				}
				if (reportNo == 0) {
					throw new IllegalAccessError("Report number is empty");
			
				}
				return reportNo; 

	}

	public String getClaimNo() {
		return ClaimNo;
	}

	public void setClaimNo(String claimNo) {
		ClaimNo = claimNo;
	}

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


}
