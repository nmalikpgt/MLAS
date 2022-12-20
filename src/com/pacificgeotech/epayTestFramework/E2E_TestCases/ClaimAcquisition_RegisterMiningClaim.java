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
import java.sql.* ;
import java.util.ArrayList;
import java.util.List;

import static com.pacificgeotech.epayTestFramework.scripts.LGN1001.getLoginUrl;
import static com.pacificgeotech.epayTestFramework.scripts.LGN1001.user;

//@RunWith(ConcurrentJunitRunner.class)
//@Concurrent(threads = 3)

public class ClaimAcquisition_RegisterMiningClaim {
	private String clientNumber;
	private String pid;
	private String pin;
	private WebDriver driver;

	private String shoppingCartValue;

	private String transactionId;

	@Test
	public void UpdateClientAndApplyProspectorsLicence() throws Exception {
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
//		while(rs.next())
//		{
//			list.add(rs.getString("CELL_KEY_ID"));
//		}
//		String cell1=list.get(1);
//		System.out.println(cell1);
		String cell1 = null;
		if (rs.next())
			cell1 = rs.getString("CELL_KEY_ID");
		System.out.print("Claim Id:"+cell1);

//		String queryToCheckIfCellIsUnlock="select * from MAM.MTA_GRID_LOCK_STATUS where cell_key_id='"+cell1+"'";
//		String queryToUnlockCell="DELETE FROM MAM.MTA_GRID_LOCK_STATUS WHERE cell_key_id='"+cell1+"'";
//
//		ResultSet rs1 =stmt.executeQuery(queryToCheckIfCellIsUnlock);
//		if(rs1.next())
//		{
//			stmt.executeUpdate(queryToUnlockCell);
//
//		}

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

        // ----------- Capture Event Id -----------

		transactionId=driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//strong[contains(text(),'Transaction ID:')]//parent::td/following-sibling::td[@class='ng-binding']"))).getText();
        System.out.print("Transaction Id:" +transactionId);
		Thread.sleep(4000);
		//------------------------------------------------------------------------------------------

	// ---	driver.quit();

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
