package com.pacificgeotech.epayTestFramework.scripts;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
import com.pacificgeotech.epayTestFramework.dbConnection.DbConnection;

import junit.framework.Assert;

public class CLM1B003 {
	
	
	private String clientNumber;
	private String transactionId;
	private String claimId;
	private String pid;
	private String pin;
	private String buyerClientNumber;
	
	private WebDriver driver;

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;		
	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		// Create new client as Buyer
		CLM1D001 clm1d001 = new CLM1D001();
		clm1d001.test();
		setClientNumber(clm1d001.getClientNumber());
		// sign out
				driverWait
						.until(ExpectedConditions
								.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
						.click();
				Thread.sleep(2000);

				WebElement _element1 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out")));
				_element1.click();
				MP5003 mp5003 = new MP5003();
		
		String[] loginURL = LGN1001.getLoginUrl();
		driver.get(loginURL[0]); // for local Test
		setPid(clm1d001.getPid());
		setPin(clm1d001.getPin());
		LGN1001 lgn1001 = new LGN1001();
		System.out.println("Pid and Pin:  " + clm1d001.getPid() + "; " + clm1d001.getPin());

		if ("localTest".equals(loginURL[1])) {

			driver.navigate().refresh();
			driver.navigate().to(loginURL[0]);

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
			element.sendKeys(clm1d001.getPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(clm1d001.getPin());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

		} else {

			lgn1001.getLoginInfoExternalRemote(loginURL[1], loginURL[0]);
		}

		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Claim Management")));
		element.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Complete Transfer of Mining Claim(s)"))).click();
		element =
				driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput")));
		element.clear();
		 element =
		 driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput")));
		 element.sendKeys(clm1d001.getClientNumber());

		// click Next
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/div/section/div/form/article[2]/div/div/div/fieldset/div[2]/div/ol/li/a")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/div/section/div/form/article[2]/div/div/div/fieldset/div[8]/div[2]/label[1]/input")));
		element.click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();
		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();

		// shopping cart
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId")));
		element.click();
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("onlinePaymentSltId")));
		element.click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='showSelectedPaymentId']/span")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("visa")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("submit")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardOwner")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardOwner")));
		element.sendKeys("test");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardNumber")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardNumber")));
		element.sendKeys("4030000010001234");
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnExpYear"))))
				.selectByVisibleText("2020");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardCvd")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardCvd")));
		element.sendKeys("123");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("submitButton")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("cancel")));
		element.click();
		new WebDriverWait(driver, 50).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nextToCfmPgIndexBtnId']/span")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='nextToCfmPgIndexBtnId']/span")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid")));
		element.click();
		new WebDriverWait(driver, 50)
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Transfer of the mining claim(s) has been completed successfully')]")));

		// search if claim is active
		/*
		 * ((org.openqa.selenium.JavascriptExecutor)
		 * driver).executeScript("window.scrollTo(0, 0)");
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By
		 * .xpath(
		 * "//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[2]/a[2]"))
		 * ) .click();
		 * 
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.
		 * linkText("Search Tenures"))).click();
		 * 
		 * Thread.sleep(2000); element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.
		 * xpath("//input[@placeholder='Tenure Number']"))); Thread.sleep(2000);
		 * driver.findElement(By.xpath("//input[@placeholder='Tenure Number']"))
		 * .sendKeys(this.claimId);
		 * 
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By
		 * .xpath("/html/body/div[2]/div[3]/div/div[2]/div/div[2]/div/div/a")))
		 * .click();
		 * 
		 * ((org.openqa.selenium.JavascriptExecutor)
		 * driver).executeScript("window.scrollTo(0, 2300)");
		 * 
		 * String actualString =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By
		 * .xpath(
		 * "html/body/div[2]/div[3]/div/div[3]/div/div/div[2]/table/tbody/tr/td[3]"
		 * ))).getText();
		 * Assert.assertTrue(actualString.contains("Active Pending Transfer"));
		 */
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

	public String getBuyerClientNumber() {
		return buyerClientNumber;
	}

	public void setBuyerClientNumber(String buyerClientNumber) {
		this.buyerClientNumber = buyerClientNumber;
	}

	// Ideally this should be a common method. Not moving now
	/*
	 * public String decryptMlasOneKeyString(String encryptedString) { String
	 * encryptionKey = "ENC(Ovn5lB2TEgrjtU955iRw+TzPoW9qHDurAyCGb9i/ezU=)";
	 * String encryptionSalt =
	 * "ENC(s3t9LqSSNSCnVfdDPAO0j7xLOPQSpIN5e4+CRG2yXlkVtKeHDhY0707ZBXh7W8z9gkauvdyDIyDjoL2J+TYD4w==)";
	 * try { String decryptedString = CipherAES.decrypt(encryptedString,
	 * encryptionKey, encryptionSalt); System.out.println("decryptedString: " +
	 * decryptedString); return decryptedString; } catch (PidCryptoException e){
	 * throw new SystemException( "Problem occurred during decryption process: "
	 * + getStackTraceAsString(e)); }
	 * 
	 * }
	 * 
	 * private String getStackTraceAsString(final Throwable t) { StringWriter sw
	 * = new StringWriter(); PrintWriter pw = new PrintWriter(sw);
	 * t.printStackTrace(pw); return sw.toString(); }
	 */
}
