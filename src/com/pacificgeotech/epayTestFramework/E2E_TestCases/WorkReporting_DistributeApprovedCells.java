package com.pacificgeotech.epayTestFramework.E2E_TestCases;

import com.pacificgeotech.epayTestFramework.core.CommonUtils;
import com.pacificgeotech.epayTestFramework.core.WebDriverManager;
import com.pacificgeotech.epayTestFramework.scripts.LGN1001;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.pacificgeotech.epayTestFramework.scripts.LGN1001.getLoginUrl;
import static com.pacificgeotech.epayTestFramework.scripts.LGN1001.user;

//@RunWith(ConcurrentJunitRunner.class)
//@Concurrent(threads = 3)

public class WorkReporting_DistributeApprovedCells {
	public static String clientNumber;
	public static String pid;
	public static String pin;
	public static WebDriver driver;

	public static String shoppingCartValue;

	public static String transactionId;

	public static String claimId;

	public static String claimStatus;

	public static String eventId_SubmitReportWork;
	public static String tenureId_SubmitReportWork;
	public static String assessmentWorkReportNumber;
	public static int amountInInt;
	String eventIdDistributeApproveCredits;

	@Test
	public void ClaimAcquisition_ApproveClaim() throws Exception {

		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();
		this.setPid(lgn1001.getPid());
		this.setPin(lgn1001.getPin());
		System.out.println("pid from lgn1001 : " + lgn1001.getPid());
		System.out.println("pin from lgn1001 : " + lgn1001.getPin());
		setPin(lgn1001.getPin());
		setClientNumber(lgn1001.getClientNumber());


		String[] urlInfo = getLoginUrl();
		System.out.println("current url is: " + urlInfo[2]);
		///  CRD
		driver.navigate().refresh();
		driver.navigate().to(urlInfo[2]);

		CommonUtils.login();
		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Client Management")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update Client Profile")));
		element.click();


		Thread.sleep(1500);


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder=\"Enter Submitter Id\"]")));
		element.sendKeys(clientNumber);
		Thread.sleep(1500);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"clientSltWdgFrmId\"]/div[2]/div/a")));
		element.click();

		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"widget-grid\"]/div[2]/div/div[3]/a/span")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@ng-model=\"clientData.maapInfo.maapCompleted\"]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@ng-model=\"clientData.maapInfo.maapCompleted\"]/option[@value='Y']")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"maapCompletedDate\"]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"wid-id-1\"]/div/div/div[9]/div[1]/fieldset/div[2]/div/p/div/ul/li[2]/span/button[1]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div//textarea[@id='commentsInputId']")));
		element.sendKeys("Entering comments");


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"widget-grid\"]/div[2]/div/div[3]/button/span")));
		element.click();


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("updLnkId")));
		element.click();


		Thread.sleep(1000);


		Thread.sleep(2000);

		// ----------------------------- Apply for Prospectors Licence begin ---------------------
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Prospector Licensing")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Apply for Prospector's Licence")));
		element.click();

		Thread.sleep(2000);

		System.out.println("Enter Submitter Id");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='clientselect']")));
		element.sendKeys(clientNumber);


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='bnGoNextBtnId']")));
		element.click();


		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='bnGoNextBtnId']")));
		element.click();

		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//a[@id='confirmLnkId']")))
				.click();

		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId")));
		element.click();
		Thread.sleep(2000);


		System.out.println("Capture shopping cart value");
		shoppingCartValue= driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//b[contains(text(),'Shopping Cart Total')]//parent::td/following-sibling::td"))).getText();
		System.out.println("Shopping cart value:" + shoppingCartValue);
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='otcPaymentSltId']"))).click(); // click on Over The Counter Payment radio button

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click(); // Click on next



	 /*   element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("onlinePaymentSltId")));
		element.click();*/
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='col-md-12']/input[@id='searchInput']"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='col-md-12']/input[@id='searchInput']"))).sendKeys(clientNumber);

		// Select dropdown value of Payer
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='searchInput']//parent::div[@class='col-md-12']//div[@class='search_client_list col-md-12']//following-sibling::div/a"))).click();

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='paymentTypeInput_0_id']")));
		element.click(); // click Payment Type dropdown
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='paymentTypeInput_0_id']//option[@label='Cash']")));
		element.click(); // Select cash from dropdown

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='paymentAmoutInput_0_id']")));
		element.sendKeys(shoppingCartValue); // Enter Amount

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='paymentTypeNumber_0_id']")));
		element.sendKeys("123456789"); // Enter Payment Type Number

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]")));
		element.click(); // click Next

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Confirm']")));
		element.click(); // click on confirm

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]")));
		element.click(); // click Next

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid")));
		element.click(); // Click Show Details button

		Thread.sleep(3000);
		// Verify Licence Status (It should be active)
		String successMessage=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"wid-id-0\"]/div/div/form/div[2]/fieldset/div[2]/div/label"))).getText();
		Assert.assertEquals(successMessage,"Active");

// --------------------------- Claim Acquisition Starts from here-------------------------------------------
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"left-panel\"]/navigation-widget/div/nav/ul/li[9]/a[2]/span")));
		element.click(); // Click on Claim Acquisition on left panel

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Register Mining Claim')]")));
		element.click(); // Click on Register Mining Claim

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='clientselect']")));
		element.sendKeys(clientNumber); // enter client number

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox']")));
		element.click(); // click on checkbox

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]")));
		element.click(); // click Next


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@id='selectedGeoMapIds']")));
		element.clear();

		// ----- Capture available Cell Ids from data base and put in  ---------------------

		List<String> list=new ArrayList<>();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@rat3:1521:test",
				"PROXY_MAM", "PROXY_MAM");
		String queryToGetActiveCells="SELECT NGC.CELL_KEY_ID FROM MAM_SPATIAL.MTA_NTS_GRID_CELL NGC " +
				"WHERE NGC.CELL_STATUS_CODE='A' " +
				"AND NGC.CELL_KEY_ID NOT IN ('ownership') " +
				"AND NGC.CELL_KEY_ID NOT IN (SELECT GLS.CELL_KEY_ID FROM MAM.MTA_GRID_LOCK_STATUS GLS) AND ROWNUM = 1";
		Statement stmt = con.createStatement();
		ResultSet rs =stmt.executeQuery(queryToGetActiveCells);
        String cell1 = null;
		if (rs.next())
			cell1 = rs.getString("CELL_KEY_ID");
		System.out.print("Available Cell Id:"+cell1);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@id='selectedGeoMapIds']")));
		element.click();
		element.sendKeys(cell1);
		con.close();
		rs.close();
		stmt.close();
		// -----------------------------------------------------------------------------------------------------------------------------------------
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click(); // Click on next
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='owneridInput_0']"))).sendKeys(clientNumber); // Ownership
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"wid-id-0\"]/div/div/div/table/tbody/tr[1]/td[2]/input"))).sendKeys("100"); // Ownership percentage //////////
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Confirm']")));
		element.click(); // click on confirm
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='shoppingCartNextId']"))).click(); // Next button on Shopping cart


		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='otcPaymentSltId']"))).click(); // click on Over The Counter Payment radio button

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click(); // Click on next
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='col-md-12']/input[@id='searchInput']"))).click();

		// -------------- Input Payment Detail section -------------
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='col-md-12']/input[@id='searchInput']"))).sendKeys(clientNumber);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"payorAutoSelectDivId\"]/div[1]/div/div[2]/div/a"))).click();

		System.out.println("Capture shopping cart value");
		shoppingCartValue= driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//b[contains(text(),'Shopping Cart Total')]//parent::td/following-sibling::td"))).getText();
		System.out.println("Shopping cart value:" + shoppingCartValue);
		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='paymentTypeInput_0_id']")));
		element.click(); // click Payment Type dropdown
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='paymentTypeInput_0_id']//option[@label='Cash']")));
		element.click(); // Select cash from dropdown

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='paymentAmoutInput_0_id']")));
		element.sendKeys(shoppingCartValue); // Enter Amount

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='paymentTypeNumber_0_id']")));
		element.sendKeys("123456789"); // Enter Payment Type Number

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]")));
		element.click(); // click Next

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Confirm']")));
		element.click(); // click on confirm
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]")));
		element.click(); // click Next

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid")));
		element.click(); // Click Show Details button

        // ----------- Capture Transaction Id -----------

		transactionId=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//strong[contains(text(),'Transaction ID:')]//parent::td/following-sibling::td[@class='ng-binding']"))).getText();
        System.out.print("Transaction Id:" +transactionId);
		Thread.sleep(4000);
		//------------------------------------------------------------------------------------------
		// ------------------ Approve Claim (Group Task)--------------
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Group Tasks')]"))).click(); // click on Group Task
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='basic-addon1']/following-sibling::input"))).sendKeys(transactionId); // Ernter transaction Id
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']"))).click(); //click on Assign task to me
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']//parent::span//ul//li/a[contains(text(),'MLAS Administrator')]"))).click(); // click on MLAS Administrator in dropdown
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr[1]//td[@class='actions-cell no-wrap hidden-xs']//div//a[1]//i"))).click(); // click on Execute Task
		Thread.sleep(4000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='isnrSummarynextBtnId']//span[contains(text(),'Confirm')]"))).click(); // Click on Confirm button
		// -------------------------------------------------------------

		//------------ Verify that Claim Status should be Active -----------
		claimStatus=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"wid-id-0\"]/div/div/div/table/tbody/tr/td[2]/span"))).getText();
		Assert.assertEquals(claimStatus,"Active");
		// ---------------------------------------------------------------

		// ----- Capture Claim Id -----
		claimId=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"wid-id-0\"]/div/div/div/table/tbody/tr/td[1]"))).getText();
		System.out.println("Claim ID:" + claimId);
		// -------------------------------------



		// ************* Submit Report of work Start*******************************

		// -------- Open External client login -------------
		LGN1001 lGN1001=new LGN1001();

		String[] externalUrl=LGN1001.getLoginUrl();
		driver.navigate().refresh();
		driver.navigate().to(externalUrl[0]);

		String[] pidPin = lGN1001.getPidPin();

		String PID = lGN1001.decryptMlasOneKeyString(pidPin[0]);
		String PIN = lGN1001.decryptMlasOneKeyString(pidPin[1]);

		System.out.println("Pid: " + PID);
		System.out.println("Pin: " + PIN);

		/*element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
		element.sendKeys(PID);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();
*/
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
		element.sendKeys(PIN);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();
		// -----------------------------------------
		Thread.sleep(4000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"left-panel\"]/navigation-widget/div/nav/ul/li[9]/a[2]/span"))).click(); // Click on Work Reporting
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='#/p_MlwaRwsrCreateSubmission']"))).click(); // click 		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Submit Plan/Permit for Early Exploration Activities')]"))).click(); //
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='clientselect']"))).sendKeys(clientNumber); // Enter Submitted For
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='createSubmissionBtnId']//span[contains(text(),'Create Submission')]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='submissionPartOfSameReportId']"))).click(); // click Is this submission part of a series of submissions for the same assessment work report?
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='submissionPartOfSameReportId']//option[text()='No']"))).click(); // Click No in dropdown

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='fieldWorkFromDateId']"))).sendKeys("2021-08-01"); // Field Work From Date
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='fieldWorkToDateId']"))).click(); // Click on Field Work To Date
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Today']"))).click(); // Click on Today button


		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='commoditiesExploredForId']//option[text()='SALT']"))).click(); // Select SALT from Commodities Explored For

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@id='inputTenureNumbersId']"))).sendKeys(claimId); // Enter tenure numbers

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='planOrPermitRequiredIndCheckboxId']"))).click(); // Click on Plan or Permit Not required checkbox

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='uploadFileId']"))).sendKeys("E:\\schinna\\MLAS\\dummy.pdf"); // Attach Pdf

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='docTypeCode']"))).click(); // click on Document type
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='docTypeCode']//option[text()='Technical Report']"))).click(); // click on Technical Report in dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click(); // Click on next

		//---- Add Exploration Activity -----
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Add Exploration Activity')]"))).click(); // Click on Add Exploration Activity button
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@data-ng-model='workTypeCodeDescrVO']"))).click(); // click on Work Type dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@data-ng-model='workTypeCodeDescrVO']//option[text()='Physical Work']"))).click(); // click on Physical Work in dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='confirmBtnId']"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='surveyTypeCodeId']"))).click(); // click on Work Subtype dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='surveyTypeCodeId']//option[text()='Manual Stripping']"))).click(); // click on Work Subtype dropdown

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" //input[@name='PHYWsurveyTypeFromDate0']"))).sendKeys("2021-08-01"); //Enter From date
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" //input[@name='PHYWsurveyTypeToDate0']"))).click(); // Click on Field Work To Date
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Today']"))).click(); // Click on Today button

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" //input[@id='unitOfWorkInputId']"))).sendKeys("20"); //nit of work
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" //input[@id='costUnitInputId']"))).sendKeys("20"); // cost per unit
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" //input[@id='actualCostInputId']"))).sendKeys("10000"); // cost per unit /////////////


		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='pendingDistributionSelectId']"))).click(); // Do you intend to submit a pending distribution in association with this report of work? dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='pendingDistributionSelectId']//option[text()='Yes']"))).click(); ///////


		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='acknowledgeSelectId']"))).click(); // Agree or Disagree dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='acknowledgeSelectId']//option[text()='I agree']"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='explorationWorkExpensesInputId']"))).sendKeys("10000"); // Exploration Work Expenses ////////
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='aboriginalConsultationExpensesInputId']"))).sendKeys("0");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click();
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click();

		// ---- Verify Submit Report Success message ------
		String submitReportSuccessMessage=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"widget-grid\"]/div[1]/div[@class='col-md-12 ng-binding']"))).getText();

		System.out.println("Success message is : "+submitReportSuccessMessage);
		Assert.assertEquals(submitReportSuccessMessage,"Work report has been submitted successfully.");
		// --------------------------------------

		// ------ Capture Transaction ID AND Work Report Number ------
		// To capture transaction ID (tenureNumbIdchrome), first we need to capture tenure number id which is 'Mining Lands' on page
		String tenureNumbId=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@data-ng-repeat='miningRightExpenseVO in eventInfo.eventDetail.reportOfWorkVO.miningRightExpenseVOs track by $index']/td[1]/div"))).getText();
        System.out.println("Tenure number ID:" +tenureNumbId);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con1 = DriverManager.getConnection(
		"jdbc:oracle:thin:@rat3:1521:test",
				"PROXY_MAM", "PROXY_MAM");
		String query_TransactionIdAndWorkReportNumber="select ev.EVENT_NUMBER_ID, rpow.TRANSACTION_ID, rpow.REPORT_OF_WORK_ID from mam.mta_tenure ten inner join mam.mta_tenure_event_xref tex on ten.TENURE_NUMBER_ID = tex.TENURE_NUMBER_ID inner join mam.mta_event ev on tex.EVENT_NUMBER_ID = ev.EVENT_NUMBER_ID inner join mam.mta_transaction_event_xref txex on ev.EVENT_NUMBER_ID = txex.EVENT_NUMBER_ID inner join mam.mta_report_of_work rpow on txex.TRANSACTION_ID = rpow.TRANSACTION_ID where ten.TENURE_NUMBER_ID ='"+tenureNumbId+"' and ev.EVENT_TYPE_CODE = 'P_MLWA_RWSR'";
		System.out.println("Query:" + query_TransactionIdAndWorkReportNumber);
		Statement stmt1 = con1.createStatement();
		ResultSet rs1 =stmt1.executeQuery(query_TransactionIdAndWorkReportNumber);
		while(rs1.next())
		{
			tenureId_SubmitReportWork = rs1.getString("TRANSACTION_ID");
			assessmentWorkReportNumber=rs1.getString("REPORT_OF_WORK_ID");
		}
		System.out.println("TRANSACTION iD on REPORT WORK PAGE:"+tenureId_SubmitReportWork);
		System.out.println("WORK ID:"+assessmentWorkReportNumber);
		con1.close();
		rs1.close();
		stmt1.close();

		// ************* Submit Report of work End*******************************

		// ****************** Approve Report Creation (Group Task) Start *************************

		// --- Login as Internal-------
		String[] urlInfo1 = getLoginUrl();
		System.out.println("current url is: " + urlInfo1[2]);
		///  CRD
		driver.navigate().refresh();
		driver.navigate().to(urlInfo1[2]);

		CommonUtils.login();
		Thread.sleep(2000);
		// ------------
// ------------------ (Group Task - 1)--------------
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Group Tasks')]"))).click(); // click on Group Task
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='basic-addon1']/following-sibling::input"))).sendKeys(tenureId_SubmitReportWork); // Ernter submit report work Id
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']"))).click(); //click on Assign task to me
		// ---driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']//parent::span//ul//li/a[contains(text(),'MLAS Administrator')]"))).click(); // click on MLAS Administrator in dropdown
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr[1]//td[@class='actions-cell no-wrap hidden-xs']//div//a[1]//i"))).click(); // click on Execute Task
		Thread.sleep(4000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='phoneNumber']"))).sendKeys("(585) 858-5858");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='reviewOutcomeRequiresInspectionIndInputId'][2]"))).click(); // Click NO on Requires Inspection ?

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='reviewOutcomeRequiresNoticeIndInputId'][2]"))).click(); // Click No on Requires Notice of Determination ?
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Confirm']"))).click(); // Click on Confirm button

		// ---- Validate Recommend Outcome----
		String groupTaskSuccessMessage1=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='btn btn-default btn-circle']//parent::div[@class='col-md-12']/span"))).getText();
		Assert.assertEquals(groupTaskSuccessMessage1,"Task Recommend Outcome has been completed successfully.");

		// ------------------ (Group Task - 2)--------------
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Group Tasks')]"))).click(); // click on Group Task
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='basic-addon1']/following-sibling::input"))).sendKeys(tenureId_SubmitReportWork); // Ernter submit report work Id
		Thread.sleep(5000);

		// Validate Task Type ///////
		String taskType=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@data-ng-repeat='task in tasks track by task.id']//td[4]"))).getText();
		Assert.assertEquals(taskType,"Evaluate Recommender Outcome for report of work submission");
		////////////////////////////
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']"))).click(); //click on Assign task to me
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr[1]//td[@class='actions-cell no-wrap hidden-xs']//div//a[1]//i"))).click(); // click on Execute Task
		Thread.sleep(4000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='agreementSelectId']"))).click(); //click on Are you in agreement with recommender's outcome Dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='agreementSelectId']//option[text()='Yes']"))).click(); // click on YES in Are you in agreement with recommender's outcome Dropdown
		Thread.sleep(3000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Confirm']"))).click(); // Click on Confirm button

		// ---- Validate Recommend Outcome----
		String groupTaskSuccessMessage2=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='btn btn-default btn-circle']//parent::div[@class='col-md-12']/span"))).getText();
		Assert.assertEquals(groupTaskSuccessMessage2,"Task Evaluate Recommender Outcome has been completed successfully.");

		// ****************** Approve Report Creation (Group Task) END *************************

		// ***************** Work Reporting (Distribute Approved Credits) START ****************

		//------ Login as External Client-----
		String[] externalUrl1=LGN1001.getLoginUrl();
		driver.navigate().refresh();
		driver.navigate().to(externalUrl[0]);
		// ---String[] pidPin1 = lGN1001.getPidPin();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
		element.sendKeys(PIN);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();
		//------------
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"left-panel\"]/navigation-widget/div/nav/ul/li[9]/a[2]/span"))).click(); // Click on Work Reporting
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Distribute Approved Credits')]"))).click(); // Click Distribute Approved Credits
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='clientselect']")));
		element.sendKeys(clientNumber); // Enter submitter Id
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='createSubmissionBtnId']//span[text()='Create Distribution']"))).click(); // Click on Create Distribution button
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@id='receiverClaimIdsCsv']"))).sendKeys(tenureNumbId); // Enter Input Receiver Claim Numbers
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@id='providerTenureIdsCsv']"))).sendKeys(tenureNumbId); // Enter Input Receiver Claim Numbers

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click();

		// ---- Service is taking time to update entry. That's why this piece of code checks if Next button is still enabled then keep on clicking it.----------------
		element=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]")));
		while(element.isDisplayed()==true)
			{
				Thread.sleep(5000);
				driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click();
				try {
					if(element.isDisplayed()==false)
						break;
				}
				catch (Exception e) {
					break;
				}

			}
			//--------------------------------------------
		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@aria-label='Years Applied']"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@aria-label='Years Applied']//option[text()='5']"))).click(); // Select year 5 from dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-uib-typeahead='providerTenureId for providerTenureId in filterReserveProviderTenureIds($viewValue)']"))).sendKeys(tenureNumbId); // Enter Reserve Provider

		// ---- Here we are extracting Work Required (amount) value to put in Reserve Amount field
		String amountInString=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@ng-if='receiver.yearsApplied > 0']"))).getText();
		String amountInString1=amountInString.replace(",","");
		String amountInString2 = amountInString1.substring(0, amountInString1.indexOf("."));
		// -------------
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='providerDistributionReserveAtIdx0OfReceiverAtIdx0']"))).sendKeys(amountInString2); // Enter Reserve Amount
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Confirm']")));
		element.click(); // click on confirm

		eventIdDistributeApproveCredits=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='table-responsive']//tbody//tr//td[3]"))).getText();
		System.out.println("Event ID at Distribute Approved Credits: "+eventIdDistributeApproveCredits);

		// ----- Validate Success message --
		String approvedCreditsSuccessMessage=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']//conf-msg[@msg='confirmationMessage']//div[@class='col-md-12']//span"))).getText();
		Assert.assertEquals(approvedCreditsSuccessMessage,"Distribution of approved credits completed successfully");
		// --------
		// ***************** Work Reporting (Distribute Approved Credits) END ****************

		driver.quit();













































	}

	private void waitForNumberofWindowsToEqual(int i) {
		// TODO Auto-generated method stub

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

	public String user() {
		return user;
	}


	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}


}
