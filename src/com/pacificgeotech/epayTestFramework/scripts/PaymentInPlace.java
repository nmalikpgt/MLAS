package com.pacificgeotech.epayTestFramework.scripts;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class PaymentInPlace {

	private String clientNumber;
	private String claimId;
	private String pin;
	private String pid;
	private WebDriver driver;

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

		MP1001 mp1001 = new MP1001();
		mp1001.test();

		setClientNumber(mp1001.getClientNumber());
		setPin(mp1001.getPin());
		setPid(mp1001.getPid());
		setClaimId(mp1001.getClaimId());

		LGN1002 lgn1002 = new LGN1002();
		lgn1002.test();

		// Amend claim details
		
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Admin")));
		element.click();
	
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Claim Database Amendment")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimId")));
		element.clear();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimId")));
		element.sendKeys(mp1001.getClaimId());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1000);
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("claimStatus"))))
				.selectByVisibleText("Active");

		Calendar calendar3 = Calendar.getInstance();
		calendar3.add(Calendar.DAY_OF_YEAR, -1095);
		Date today3 = calendar3.getTime();
		DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
		String threeYearsAgo = dateFormat3.format(today3);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("registrationDate")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("registrationDate")));
		element.sendKeys(threeYearsAgo);

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 364);
		Date today = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String yearInfronMinusOneDay = dateFormat.format(today);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("anniversaryDate")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("anniversaryDate")));
		element.sendKeys(yearInfronMinusOneDay);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("totalWork")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("totalWork")));
		element.sendKeys("1600");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'None of these changes will apply a wording code to the claim abstract. If you made a change that requires a public abstract entry, please go to Add Abstract Entry and provide the necessary code. None of these changes will trigger the geoproessing queue. If you made a change that requires a spatial update, please run the Mapping Operation to update the spatial views.')]")));

		//Payment in place 
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Work Reporting")));
		element.click();
	
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Payment in Place")));
		element.click();
		
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect"))); 
		 element.sendKeys(mp1001.getClientNumber());
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimsNumbersCsv"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimsNumbersCsv"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimsNumbersCsv"))); 
		 element.sendKeys(mp1001.getClaimId());
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimPaymentAmountAtIdx0"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("claimPaymentAmountAtIdx0"))); 
		 element.sendKeys("1600");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		 element.click();
		 Thread.sleep(1500);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("otcPaymentSltId"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='showSelectedPaymentId']/span"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))); 
		 element.sendKeys("JOHN ARCHIBALD");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("102825 JOHN ARCHIBALD"))); 
		 element.click();

		    new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeInput_0_id")))).selectByVisibleText("Cash");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentAmoutInput_0_id"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentAmoutInput_0_id"))); 
		 element.sendKeys("1600");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeNumber_0_id"))); 
		 element.click();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeNumber_0_id"))); 
		 element.clear();
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeNumber_0_id"))); 
		 element.sendKeys("1");
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))); 
		 element.click();
		 Thread.sleep(1000);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='nextToConfirmLnkId']/span")));
		 element.click();
		 
		 Thread.sleep(1000);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextToCfmPgIndexId")));
		 element.click();
		 
		 Thread.sleep(1000);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid"))); 
		 element.click();
		
		 new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"//*[contains(text(), 'Payment In Place - Payment Confirmation')]")));

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

}
