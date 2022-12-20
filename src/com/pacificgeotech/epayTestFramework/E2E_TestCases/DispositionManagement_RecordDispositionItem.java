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
import java.util.Random;

import static com.pacificgeotech.epayTestFramework.scripts.LGN1001.getLoginUrl;
import static com.pacificgeotech.epayTestFramework.scripts.LGN1001.user;

//@RunWith(ConcurrentJunitRunner.class)
//@Concurrent(threads = 3)

public class DispositionManagement_RecordDispositionItem {
	public static String clientNumber;
	public static String pid;
	public static String pin;
	public static WebDriver driver;

	public static String shoppingCartValue;

	public static String transactionId;

	public static String claimStatus;
	public static String claimID;
	public static String eventId_RecordDisposition;

	@Test
	public void DispositionManagement_RecordDispositionItem() throws Exception {
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

		// ************** Disposition Management_Record Disposition Item START *****************

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='cata-collapse-click menu ng-scope']/span[text()='Disposition Management']"))).click(); // click on Disposition Management on left panel
		Thread.sleep(4000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Record Disposition Item')]"))).click(); // click on Record Disposition item

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='effectiveDateInput']"))).click(); // Click on Tax/Rent Effective Date
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Today']"))).click(); // Click on Today button

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='dispositionType']"))).click(); // click on Disposition Type dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='dispositionType']//option[text()='Lease']"))).click(); // select lease from Disposition Type dropdown

		// ---- Here we need to type random numbers in Mining Right ID field ---
		Random random = new Random();
		int num = random.nextInt(100000);
		String miningRightId = String.format("%05d", num);
		System.out.println("Mining Right ID: LEA-"+miningRightId);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='miningRightId']"))).sendKeys("LEA-"+miningRightId); // Enter Mining Right ID
        // ---------------
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='legalRight']"))).click(); // click on Legal Rights dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='legalRight']//option[text()='Mining Rights only']"))).click(); // click Mining Rights in Legal Rights dropdown

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='townshipAreaName0']"))).sendKeys("Crab"); // Township Area Name
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@ng-repeat='matchingTownship in matchingTownships']"))).click(); // Click populated option in Township Area Name

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='tenureTownshipType0']"))).click(); // click on Township Type dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='tenureTownshipType0']//option[text()='Organized']"))).click(); // click on Organized from Type dropdown

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='territorialDistrict']"))).click(); // click on Territorial District dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='territorialDistrict']//option[text()='DISTRICT OF ALGOMA']"))).click(); // click on DISTRICT OF ALGOMA from District dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='taxRentEffectiveDate']"))).click(); // Click on Tax/Rent Effective Date
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Today']"))).click(); // Click on Today button

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='leaseTermYear']"))).click(); // click on Territorial District dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='leaseTermYear']//option[text()='10']"))).click(); // click on 10 in Territorial District dropdown

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='landAreaHa']"))).sendKeys("10"); // Enter Land Area Ha:
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='pin']"))).sendKeys("10"); // Enter pin
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='shortLegalDescr']"))).sendKeys("Short Legal Description:"); // Enter Short Legal Description
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='longLegalDescr']"))).sendKeys("Long Legal Description"); // Long Legal Description

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='miningDivision']"))).click(); // click on Territorial District dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='miningDivision']//option[text()='Red Lake']"))).click(); //

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='landRegistryOffice']"))).click(); // click on Land Registry Office dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='landRegistryOffice']//option[text()='ELGIN']"))).click(); //

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='liability']"))).click(); // click on Liability dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='liability']//option[text()='Mining Purposes']"))).click(); //


		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='addOwners']"))).click(); // Click on + icon under Recorded Disposition Ownership
        Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='dispositionOwnershipClient0']"))).sendKeys(clientNumber); // Ownership
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='dispositionOwnershipPercentage0']"))).sendKeys("100"); // Owned Percentage
		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='bnGoNextBtnId']/span"))).click(); // Click on Next button
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='bnGoNextBtnId']/span"))).click(); // Click on Next button on Summary page

		// ----- Capture Event Id -----
		eventId_RecordDisposition=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='eventInfoUserFormHtmlId']//div[@class='table-responsive']/table/tbody/tr/td[3]"))).getText();
		System.out.println("Event ID for Record Disposition Item:" + eventId_RecordDisposition);
		// -------------------------------------
		// ---- Validate Successful message----
		String dispositionSuccessMessage=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='btn btn-default btn-circle']//parent::div[@class='col-md-12']/span"))).getText();
		Assert.assertEquals(dispositionSuccessMessage,"Disposition was recorded successfully");
		// ************** Disposition Management_Record Disposition Item END *****************

		// ************** Create Account and Sub Account START ***********
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Create Account and Sub-Account')]"))).click(); // click on Create Account and Sub-Account

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='billingPartyId']"))).clear();
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='billingPartyId']"))).sendKeys(clientNumber); // Enter client number in Billing Party field

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='selectTenureType']"))).click(); // Click on Tenure Type dropdown
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='selectTenureType']//option[text()='Lease']"))).click(); // Click on Lease in Tenure Type dropdown

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='selectDistrict']"))).click(); // click on District dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='selectDistrict']//option[text()='DISTRICT OF ALGOMA']"))).click(); // click on DISTRICT OF ALGOMA from District dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@id='miningLandNumbesCsv']"))).sendKeys("LEA-"+miningRightId); // Enter Mining ID

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='bnGoNextBtnId']")));
		element.click(); // click on Next button
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Confirm']")));
		element.click(); // click on confirm
		// ************** Create Account and Sub Account END ***********

		// ************* Claim Management_Record Application For Lease START *******
		Thread.sleep(3000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" //a[@class='cata-collapse-click menu ng-scope']//span[text()='Claim Management']"))).click(); // click on Claim Management
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Record Application for Lease')]"))).click(); // Click on Record Application for Lease

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='clientselect']"))).sendKeys(clientNumber);// Enter Submitter For
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='effectiveDateInput']"))).click(); // Click on Effective Date
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Today']"))).click(); // Click on Today button

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='leaseTypeAtIdx0']"))).click(); // click on Lease Type dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='leaseTypeAtIdx0']//option[text()='Mining Rights only']"))).click(); // click on Mining Rights only under Lease Type dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='singleClaimLeaseAtIdx0']"))).click(); // click on Single Claim Lease radiobutton

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@id='claimIdsCsvAtIdx0']"))).sendKeys(claimID); // Enter Mining ID
		Thread.sleep(5000);

		// ---driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox' and @id='leaseApplicationFeePaid']"))).click(); // Click Lease Application Fee Paid checkbox
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='assessmentWorkSatisfied']"))).click(); // Click Total Rental Amount for First Year Paid checkbox
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='totalRentalAmountForFirstYearPaid']"))).sendKeys("2000");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='nextBtnId']//span[text()='Next']")));
		element.click(); // click on Next button
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='nextBtnId']//span[text()='Next']"))).click(); // click Next


        // ---- Validate Successful message----
		String recordApplicationForLeaseSuccessMessage=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='btn btn-default btn-circle']//parent::div[@class='col-md-12']/span"))).getText();
		Assert.assertEquals(recordApplicationForLeaseSuccessMessage,"Application for lease was recorded successfully");

		//-------Capture Transaction ID to use in Group Task -------
		String transactionIdRecordAppLease=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@ng-if='eventInfo.transactionId']/td[3]"))).getText();
		System.out.println("Transaction ID for Record Application for Lease: "+transactionIdRecordAppLease);

		// ***************** Group Task - 1 START ***************************
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='#/mlas_group_tasks']/span[text()='Group Tasks']"))).click(); // click on Group Task
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='basic-addon1']/following-sibling::input"))).sendKeys(transactionIdRecordAppLease); // Enter tenure ID capture on Record Application Lease
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@data-ng-repeat='task in tasks track by task.id'][1]/td[9]/div/span/button"))).click(); //click on Assign task to me
		Thread.sleep(4000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']//parent::span//ul//li/a[contains(text(),'System Administrator')]"))).click(); // Click on System Administrator in dropdown
		Thread.sleep(8000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr[1]//td[@class='actions-cell no-wrap hidden-xs']//div//a[1]//i"))).click(); // click on Execute Task

		Thread.sleep(4000);
		// ---- Here we need to type random numbers in Mining Right ID field ---
		Random random1 = new Random();
		int num1 = random1.nextInt(100000);
		String miningRightId1 = String.format("%05d", num1);
		//System.out.println("Mining Right ID: LEA-"+miningRightId);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='miningRightId']"))).sendKeys("LEA-"+miningRightId1); // Mining Right ID

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='townshipAreaName0']"))).sendKeys("Crab"); // Township Area Name
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@ng-repeat='matchingTownship in matchingTownships']"))).click(); // Click populated option in Township Area Name
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='tenureTownshipType0']"))).click(); // click on Township Type dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='tenureTownshipType0']//option[text()='Organized']"))).click(); // click on Organized from Type dropdown

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='territorialDistrict']"))).click(); // click on Territorial District dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='territorialDistrict']//option[text()='DISTRICT OF ALGOMA']"))).click(); // click on DISTRICT OF ALGOMA from District dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='taxRentEffectiveDate']"))).click(); // Click on Tax/Rent Effective Date
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Today']"))).click(); // Click on Today button

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='landAreaHa']"))).sendKeys("10"); // Enter Land Area Ha:
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='pin']"))).sendKeys("10"); // Enter pin
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='shortLegalDescr']"))).sendKeys("Short Legal Description:"); // Enter Short Legal Description
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='longLegalDescr']"))).sendKeys("Long Legal Description"); // Long Legal Description

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='miningDivision']"))).click(); // click on Territorial District dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='miningDivision']//option[text()='Red Lake']"))).click(); //
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='landRegistryOffice']"))).click(); // click on Land Registry Office dropdown

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='landRegistryOffice']//option[text()='ELGIN']"))).click(); //
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='liability']"))).click(); // click on Liability dropdown
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='liability']//option[text()='Mining Purposes']"))).click(); //

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='addOwners']"))).click(); // Click on + icon under Recorded Disposition Ownership
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='leaseOwnershipClient0']"))).sendKeys(clientNumber); // Ownership
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='leaseOwnershipPercentage0']"))).sendKeys("100"); // Owned Percentage
		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='bnGoNextBtnId']/span"))).click(); // Click on Next button
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Confirm']")));
		element.click(); // click on confirm

       // ---- Validate Record Lease----
		String groupTaskSuccessMessage1=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='btn btn-default btn-circle']//parent::div[@class='col-md-12']/span"))).getText();
		Assert.assertEquals(groupTaskSuccessMessage1,"Lease was recorded successfully.");

		// ------------------ (Group Task - 2)--------------
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='#/mlas_group_tasks']/span[text()='Group Tasks']"))).click(); // click on Group Task
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='basic-addon1']/following-sibling::input"))).sendKeys(transactionIdRecordAppLease); // Enter tenure ID capture on Record Application Lease
		Thread.sleep(5000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@data-ng-repeat='task in tasks track by task.id'][1]/td[9]/div/span/button"))).click(); //click on Assign task to me
		Thread.sleep(4000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']//parent::span//ul//li/a[contains(text(),'System Administrator')]"))).click(); // Click on System Administrator in dropdown
		Thread.sleep(8000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr[1]//td[@class='actions-cell no-wrap hidden-xs']//div//a[1]//i"))).click(); // click on Execute Task

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='effectiveDateInput']"))).click(); // Click on Tax/Rent Effective Date
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Today']"))).click(); // Click on Today button

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='tempLeaseGeometryExistsOnActiveTenuresLyr']"))).click(); // click checkbox: Lease has its temporary geometry on operational Active Tenures layer and any issue found was already addressed with the help of co-responsible parties
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='leaseCreatedInEditingEnv']"))).click(); // click checkbox:Lease geometry was created on Tenures layer of Editing Environment
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='scheduledMappingOperationToCopyFromEditingEnv']"))).click(); // click checkbox: Scheduled an immediate mapping operation to 'Copy from Editing Environment' Tenures layer the geometry created for current lease
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='verifiedLeaseGeometryCreatedOnActiveTenuresLyr']"))).click(); // click Checkbox : Verified the existence of lease geometry on the operational Active Tenures layer
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='bnGoNextBtnId']")));
		element.click(); // click on Next button
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Confirm']")));
		element.click(); // click on confirm

        // ---- Validate Record Lease----
		String updateLeaseSpatialRecordSuccessMessage=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='btn btn-default btn-circle']//parent::div[@class='col-md-12']/span"))).getText();
		Assert.assertEquals(updateLeaseSpatialRecordSuccessMessage,"Lease Spatial Record was updated successfully.");
		Thread.sleep(4000);
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
