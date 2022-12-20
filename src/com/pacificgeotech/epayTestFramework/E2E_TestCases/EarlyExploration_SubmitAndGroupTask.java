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

import java.io.File;
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

public class EarlyExploration_SubmitAndGroupTask {
	public static String clientNumber;
	public static String pid;
	public static String pin;
	public static WebDriver driver;

	public static String shoppingCartValue;

	public static String transactionId;

	public static String claimStatus;
	public static String claimID;

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



		/*element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("onlinePaymentSltId")));
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
		System.out.print("Claim Id:"+cell1);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@id='selectedGeoMapIds']")));
		element.click();
		element.sendKeys(cell1);
		con.close();
		// -----------------------------------------------------------------------------------------------------------------------------------------
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click(); // Click on next
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='owneridInput_0']"))).sendKeys(clientNumber); // Ownership
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"wid-id-0\"]/div/div/div/table/tbody/tr[1]/td[2]/input"))).sendKeys("100"); // Ownership percentage
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
		// ------------------ Approve Claim --------------
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

		// -------- Capture Claim ID --------
		claimID=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@data-ng-repeat='claimVO in formData.claimVOs track by $index']//td[1]"))).getText();
		System.out.println("Claim ID:"+ claimID);
		// --------------------------------------

		//------------ Verify that Claim Status should be Active -----------
		claimStatus=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"wid-id-0\"]/div/div/div/table/tbody/tr/td[2]/span"))).getText();
		Assert.assertEquals(claimStatus,"Active");
		// -----------------------------------------------------------

		// ************** Early Exploration_Submit Plan START *****************
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

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
		element.sendKeys(PIN);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();
		// ----------------------------
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='cata-collapse-click menu ng-scope']//span[text()='Early Exploration Activities']"))).click(); // click on Early Exploration Activities on Left
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Submit Plan/Permit for Early Exploration Activities')]"))).click(); // click on Submit plan/permit for early exploration activities
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='clientselect']")));
		element.sendKeys(clientNumber);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='disclaimerIndId']"))).click(); // Click on Disclaimer
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='createSubmissionBtnId']//span[contains(text(),'Create Submission')]"))).click(); // Click Create Submission button
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@id='tenureIdsTxtId']"))).sendKeys(claimID); // Enter claim/tenure ID
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click(); // Click on next

		// ---- Service is taking time to update entry. That's why this piece of code checks if there is any entry in DB for corresponding Claim ID, If yes it means service is updated and we can proceed----------------
		while(true)
		{
			Connection con1 = DriverManager.getConnection(
					"jdbc:oracle:thin:@rat3:1521:test",
					"PROXY_MAM", "PROXY_MAM");
			String queryToCheckEntry="select * from mam_spatial.mta_acquired_tenure_poly atp where atp.TENURE_NUMBER_ID ="+"'"+claimID+"'";
			Statement stmt1 = con1.createStatement();
			ResultSet rs1 =stmt1.executeQuery(queryToCheckEntry);
			Thread.sleep(5000);

			if(rs1.next()==true)
			{
				rs1.close();
				break;
			}
			rs1.close();
			con1.close();
		}
		// -------------------------------------------------------------
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click(); // Click on next
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Proceed')]"))).click(); // Click on Proceed button on Confirmation popup
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='permitActivityChkId0']"))).click(); // Check permit activities check box
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='projectNameInput']"))).sendKeys("Test Project"); // Enter project name
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='qualifiedSupervisorClientTxtId']"))).sendKeys(clientNumber);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//fieldset[@class='ng-binding']//input[@value='N']"))).click(); // Click on No radio button of Have you consulted with any Aboriginal communities for this project?
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='certificationInd' and @value='Y']"))).click(); // Click on first certification radio button
		Thread.sleep(5000);
		// --- Document Upload --------
		File file=new File("./dummy.pdf");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='uploadFileId']"))).sendKeys(file.getAbsolutePath());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='docTypeCode']"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='docTypeCode']//option[contains(text(),'Activity Details Report')]"))).click(); // Select Activity Details Report

		Thread.sleep(2000);
		File file1=new File("./dummy.pdf");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='uploadFileId']"))).sendKeys(file1.getAbsolutePath());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='docTypeCode_1' and @id='docTypeCode']"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='docTypeCode_1' and @id='docTypeCode']//option[contains(text(),'Project Scale Map')]"))).click(); // Select Project Scale Map

		Thread.sleep(2000);
		File file2=new File("./dummy.pdf");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='uploadFileId']"))).sendKeys(file2.getAbsolutePath());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='docTypeCode_2' and @id='docTypeCode']"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='docTypeCode_2' and @id='docTypeCode']//option[contains(text(),'Regional Scale Map')]"))).click(); // Select Regional Scale Map
		// ----------------------
		Thread.sleep(5000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button' and @data-dismiss='alert']"))).click(); // Close alert

		WebElement nextButton=driver.findElement(By.xpath("//button[@id='summarySubmissionBtnId']"));
		driverWait.until(ExpectedConditions.elementToBeClickable(nextButton));
		nextButton.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Confirm']")));
		element.click();
		// ----------- Capture Transaction Id -----------

		transactionId=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//strong[contains(text(),'Transaction ID:')]//parent::td/following-sibling::td[@class='ng-binding']"))).getText();
		System.out.print("Transaction Id:" +transactionId);
		Thread.sleep(4000);
		// --------------

		// *********************** Early Exploration_Submit Plan END ***********************

        // ***************** Group Task START *********************
		// --- Login as Internal-------
		String[] urlInfo1 = getLoginUrl();
		System.out.println("current url is: " + urlInfo1[2]);
		///  CRD
		driver.navigate().refresh();
		driver.navigate().to(urlInfo1[2]);

		CommonUtils.login();
		Thread.sleep(2000);

		// ------------------ (Group Task - 1)--------------
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Group Tasks')]"))).click(); // click on Group Task
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='basic-addon1']/following-sibling::input"))).sendKeys(transactionId); // Enter transaction ID
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']"))).click(); //click on Assign task to me
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='System Administrator']"))).click(); //click on System Administrator
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr[1]//td[@class='actions-cell no-wrap hidden-xs']//div//a[1]//i"))).click(); // click on Execute Task
		Thread.sleep(4000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='radio' and @value='Y']"))).click(); // Click on Yes for Does the submission meet initial validation requirements?

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='issueDateInTxtId']"))).click(); // Click on Issue Date textbox
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Today']"))).click(); // Click Today

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='expiryDateInTxt']"))).sendKeys("2025-12-16");// Enter Expiry Date textbox

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@name='comment']"))).sendKeys("Test comments"); // Enter comments
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click(); // Click on next
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Confirm']")));
		element.click();

		// ---- Validate Submission message----
		String groupTaskSuccessMessage1=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//confirmation-message[@confirmation-title='confirmationTitle']//div[@class='col-md-12']//span[2]"))).getText();
		Assert.assertEquals(groupTaskSuccessMessage1,"This submission has been successfully validated");


		// ------------------ (Group Task - 2)--------------
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Group Tasks')]"))).click(); // click on Group Task
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='basic-addon1']/following-sibling::input"))).sendKeys(transactionId); // Enter transaction ID
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']"))).click(); //click on Assign task to me
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='System Administrator']"))).click(); //click on System Administrator
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr[1]//td[@class='actions-cell no-wrap hidden-xs']//div//a[1]//i"))).click(); // click on Execute Task
		Thread.sleep(4000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='notificationTypeId0']"))).click(); // click opn Notification Type dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='notificationTypeId0']//option[text()='Email']"))).click(); // Select Email option in dropdown

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='circulationModeId0']"))).click(); // click on circulation mode dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='circulationModeId0']//option[text()='Email']"))).click(); // Select Email option in dropdown

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@id='commentTxtId']"))).sendKeys("Test comments"); // Enter comments
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click(); // Click on next
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Confirm']")));
		element.click();

		// ---- Validate Submission message----
		String thirdPartyVerification=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//confirmation-message[@confirmation-title='confirmationTitle']//div[@class='col-md-12']//span[2]"))).getText();
		Assert.assertEquals(thirdPartyVerification,"The 3rd party verifications have been completed successfully");

		// ------------------ (Group Task - 3)--------------
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Group Tasks')]"))).click(); // click on Group Task
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='basic-addon1']/following-sibling::input"))).sendKeys(transactionId); // Enter transaction ID
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']"))).click(); //click on Assign task to me
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='System Administrator']"))).click(); //click on System Administrator
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr[1]//td[@class='actions-cell no-wrap hidden-xs']//div//a[1]//i"))).click(); // click on Execute Task
		Thread.sleep(4000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Edit']"))).click(); //click on Edit
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@aria-labelledby='consultationStatusLblId']"))).click(); //click on Consultation Status dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@aria-labelledby='consultationStatusLblId']//option[text()='Pending']"))).click();
		Thread.sleep(5000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='saveBtnId']//span[text()='Confirm']")));
		element.click();
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='backBtnId']"))).click(); // Click on Back Button
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click(); // Click on next
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='bnConfirmBtnId']//span[text()='Confirm']")));
		element.click();
		// ---- Validate Submission message----
		String addAmendRemoveListItemsConfirmation=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//confirmation-message[@confirmation-title='confirmationTitle']//div[@class='col-md-12']//span[2]"))).getText();
		Assert.assertEquals(addAmendRemoveListItemsConfirmation,"Add, amend, remove list items and/or confirm list has been successfully completed");

// ------------------ (Group Task - 4)--------------
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Group Tasks')]"))).click(); // click on Group Task
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='basic-addon1']/following-sibling::input"))).sendKeys(transactionId); // Enter transaction ID
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']"))).click(); //click on Assign task to me
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='System Administrator']"))).click(); //click on System Administrator
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr[1]//td[@class='actions-cell no-wrap hidden-xs']//div//a[1]//i"))).click(); // click on Execute Task
		Thread.sleep(4000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Edit']"))).click(); //click on Edit
		Thread.sleep(3000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='saveBtnId']//span[text()='Confirm']")));
		element.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='backBtnId']"))).click(); // Click on Back Button
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click(); // Click on next
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='bnConfirmBtnId']//span[text()='Confirm']")));
		element.click();
		// ---- Validate Submission message----
		String prepareReferralPackageConfirmation=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//confirmation-message[@confirmation-title='confirmationTitle']//div[@class='col-md-12']//span[2]"))).getText();
		Assert.assertEquals(prepareReferralPackageConfirmation,"Prepare referral package and deliver it to list parties has been successfully completed");

// ------------------ (Group Task - 5)--------------
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Group Tasks')]"))).click(); // click on Group Task
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='basic-addon1']/following-sibling::input"))).sendKeys(transactionId); // Enter transaction ID
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']"))).click(); //click on Assign task to me
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='System Administrator']"))).click(); //click on System Administrator
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr[1]//td[@class='actions-cell no-wrap hidden-xs']//div//a[1]//i"))).click(); // click on Execute Task
		Thread.sleep(4000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox' and @aria-labelledby='reminderSendLblId']"))).click(); // Click Reminder Send checkbox
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click(); // Click on next
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='bnConfirmBtnId']//span[text()='Confirm']")));
		element.click();
// ---- Validate Submission message----
		String contactThirdPartiesWithPendingRepliesConfirmation=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//confirmation-message[@confirmation-title='confirmationTitle']//div[@class='col-md-12']//span[2]"))).getText();
		Assert.assertEquals(contactThirdPartiesWithPendingRepliesConfirmation,"Contact third-parties with pending replies has been successfully completed");

		// ------------------ (Group Task - 6)--------------
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Group Tasks')]"))).click(); // click on Group Task
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='basic-addon1']/following-sibling::input"))).sendKeys(transactionId); // Enter transaction ID
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']"))).click(); //click on Assign task to me
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='System Administrator']"))).click(); //click on System Administrator
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr[1]//td[@class='actions-cell no-wrap hidden-xs']//div//a[1]//i"))).click(); // click on Execute Task
		Thread.sleep(4000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='radio' and @value='Y']"))).click(); //Click Yes for Does the submission meet initial validation requirements?
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='issueDateInTxtId']"))).click(); // Click on Issue Date textbox
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Today']"))).click(); // Click Today

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='expiryDateInTxt']"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='expiryDateInTxt']"))).sendKeys("2025-12-16");// Enter Expiry
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@name='comment']"))).sendKeys("Test Comments"); // Comments
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]"))).click(); // Click on next
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='bnConfirmBtnId']//span[text()='Confirm']")));
		element.click();
		//--- Validate Submission message----
		String permitSubmissionConfirmation=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//confirmation-message[@confirmation-title='confirmationTitle']//div[@class='col-md-12']//span[2]"))).getText();
		Assert.assertEquals(permitSubmissionConfirmation,"Permit Submission has been successfully reviewed");

		// ------------------ (Group Task - 6)--------------
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Group Tasks')]"))).click(); // click on Group Task
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='basic-addon1']/following-sibling::input"))).sendKeys(transactionId); // Enter transaction ID
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']"))).click(); //click on Assign task to me
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='System Administrator']"))).click(); //click on System Administrator
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr[1]//td[@class='actions-cell no-wrap hidden-xs']//div//a[1]//i"))).click(); // click on Execute Task
		Thread.sleep(4000);

		File file3=new File("./dummy.pdf");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='uploadFileId']"))).sendKeys(file.getAbsolutePath());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='docTypeCode']"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='docTypeCode']//option[contains(text(),'Permit Document')]"))).click();
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='bnSaveBtnId']"))).click(); // click on Save button
		Thread.sleep(4000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='sendDocumentBtn']"))).click(); // click on Send Permit Document button
		Thread.sleep(15000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='bnGoNextBtnId']"))).click(); // Click on next
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='bnConfirmBtnId']//span[text()='Confirm']")));
		element.click();
		//--- Validate Submission message----
		String produceAndSendDocumentationConfirmation=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//confirmation-message[@confirmation-title='confirmationTitle']//div[@class='col-md-12']//span[2]"))).getText();
		Assert.assertEquals(produceAndSendDocumentationConfirmation,"Documentation has been produced and sent to Submitter");

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
