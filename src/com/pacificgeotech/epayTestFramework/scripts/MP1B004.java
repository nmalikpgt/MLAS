package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;
import java.sql.SQLException;

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

public class MP1B004 {

	private String clientNumber;
	private String orgNum;
	private String claimId;
	private WebDriver driver;

/*	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}*/

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}

	@Test
	public void test() throws Exception {
		PL2001 pl2001 = new PL2001();
		pl2001.test();
		registerMiningClaimInternal(pl2001);
		setClientNumber(pl2001.getClientNumber());

	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public String getOrgNum() {
		return orgNum;
	}

	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public void registerMiningClaimInternal(PL2001 pl2001)
			throws ClassNotFoundException, SQLException, InterruptedException {

		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		/**
		 * 
		 * Register Mining Claim - Internal User
		 * 
		 **/

		// register organization

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register Client")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientTypeRdioOrg")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(pl2001.getClientNumber());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("orgNameId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("orgNameId")));
		element.sendKeys("test LTD");
		Thread.sleep(5000);
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgType")));
		// element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgType"))))
				.selectByVisibleText("Incorporated Company");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpNumber")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpNumber")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpNumber")));
		element.sendKeys("121212");
		Thread.sleep(5000);
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpPlace"))))
				.selectByVisibleText("CANADA");
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1")));
		element.sendKeys("str 1");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("mailingCity")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("mailingCity")));
		element.sendKeys("ontario");
		Thread.sleep(5000);
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingCountry"))))
				.selectByVisibleText("CANADA");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("select[name=\"mailingCountry\"] > option[value=\"string:CA\"]")));
		Thread.sleep(5000);
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("province"))))
				.selectByVisibleText("ONTARIO");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("mailingPostalCode")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("mailingPostalCode")));
		element.sendKeys("V8X 1G7");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("primaryPhoneTypeSelect")));
		element.click();
		new Select(
				element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primaryPhoneTypeSelect"))))
						.selectByVisibleText("Home");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("select[name=\"primaryPhoneTypeSelect\"] > option[value=\"string:01\"]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@ng-model=\"formData.primaryPhone.phoneNumber\"]")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@ng-model=\"formData.primaryPhone.phoneNumber\"]")));
		element.sendKeys("(111) 111-1111");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		element.sendKeys("cvlasceanu@pacificgeotech.com");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox']")));
		element.click();
		Thread.sleep(1500);
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("orgAckSelect"))))
				.selectByVisibleText("I agree");
		Thread.sleep(1500);
		// Upload
		Object file = null;
		try {
			file = new File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(((File) file).exists());
		WebElement browseButton = driver.findElement(By.name("fileInput"));
		browseButton.sendKeys(((File) file).getAbsolutePath());
		Thread.sleep(2000);

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option.ng-binding.ng-scope")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
				.selectByVisibleText("Certificate of Status");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		Thread.sleep(2500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm")));
		element.click();
		Thread.sleep(2500);
		
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.id("organizationNumberId")));
		WebElement TxtBoxContent1 = driver.findElement(By.id("organizationNumberId"));
		String orgNum = TxtBoxContent1.getText();
		System.out.println("Org number: " + orgNum);
		setOrgNum(orgNum);

		MP1001 mp1001 = new MP1001();
		mp1001.updateCellLockTable();
		mp1001.updateCellgridTable();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Claim Acquisition"))).click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Register Mining Claim")))
				.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")))
				.sendKeys(pl2001.getClientNumber());
		// .sendKeys(cm1A003.getClientNumber());

		Thread.sleep(1000);
	
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("declarationChkbox"))).click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds"))).clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element = driver.findElement(By.id("selectedGeoMapIds"));
		element.sendKeys("43B03C239,43B03C238,43B03C219,43B03C218");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoIdendityButtonId"))).click();

		/*
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "owneridInput_0"))).clear();
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "owneridInput_0"))) .sendKeys(cm1A003.getClientNumber());
		 * 
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name(
		 * "percentage_0"))).clear();
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name(
		 * "percentage_0"))).sendKeys("100");
		 */

		/*
		 * element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath
		 * ("//div[@id='wid-id-0']/div/div/div/table/tbody/tr[3]/td[3]/span/i"))
		 * ); JavascriptExecutor executor = (JavascriptExecutor)driver;
		 * executor.executeScript("arguments[0].click();", element);
		 */

		Thread.sleep(2000);

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='wid-id-0']/div/div/div/table/tbody/tr[3]/td[3]/span/i"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("owneridInput_0"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("owneridInput_0")))
				.sendKeys(pl2001.getClientNumber());

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("addOwvnerButtonId"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("owneridInput_1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("owneridInput_1")))
				.sendKeys(String.valueOf(orgNum));

		Thread.sleep(2000);
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Distribute ownership percentage equally")))
				.click();

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();

		Thread.sleep(1000);

		// To payment
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/a/span")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId"))).click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("otcPaymentSltId"))).click();

//		driverWait
//				.until(ExpectedConditions
//						.presenceOfElementLocated(By.xpath("//form[@id='paymentTypeFrmId']/div/fieldset/div/label")))
//				.click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showSelectedPaymentId"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))).sendKeys("DARYL NEUSTATER");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("175221 DARYL NEUSTATER"))).click();

		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeInput_0_id"))))
				.selectByVisibleText("Cash");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("paymentAmount_0"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentAmoutInput_0_id"))).sendKeys("200");

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeNumber_0_id"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeNumber_0_id"))).sendKeys("1234");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='nextToConfirmLnkId']/span")))
				.click();

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='nextToCfmPgIndexId']/span")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid"))).click();
		Thread.sleep(1500);
		String claimId;
		claimId = driver.findElement(By.xpath("//*[@id='wid-id-0']/div/div/table/tbody/tr/td[2]")).getText();

		setClaimId(claimId);
		System.out.println("Claim ID: " + getClaimId());
	}
}
