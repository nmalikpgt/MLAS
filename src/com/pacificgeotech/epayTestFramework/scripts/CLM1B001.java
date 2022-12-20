package com.pacificgeotech.epayTestFramework.scripts;

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

public class CLM1B001 {

	private String clientNumber;
	private String transactionId;
	private String claimId;
	private String pid;
	private String pin;
	private String buyerClientNumber;
	private String buyerPid;
	private String buyerPin;
	
	private WebDriver driver;
	
	@After
	public void tearDown(){
		driver.getCurrentUrl();
	}	
	
	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;		
	}

	public String getBuyerPid() {
		return buyerPid;
	}

	public void setBuyerPid(String buyerPid) {
		this.buyerPid = buyerPid;
	}

	public String getBuyerPin() {
		return buyerPin;
	}

	public void setBuyerPin(String buyerPin) {
		this.buyerPin = buyerPin;
	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		// Initiate Transfer
		CLM1A001 clm1a001 = new CLM1A001();
		clm1a001.test();

		String[] loginURL = LGN1001.getLoginUrl();
		driver.get(loginURL[0]); // for local Test
		setPid(clm1a001.getBuyerPid());
		setPin(clm1a001.getBuyerPin());
		LGN1001 lgn1001 = new LGN1001();
		System.out.println("Pid and Pin:  " + clm1a001.getBuyerPid() + "; " + clm1a001.getBuyerPin());

		if ("localTest".equals(loginURL[1])) {

			driver.navigate().refresh();
			driver.navigate().to(loginURL[0]);

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
			element.sendKeys(clm1a001.getBuyerPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(clm1a001.getBuyerPin());
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

		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput")));
		// element.sendKeys(this.buyerClientNumber);

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
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnConfirmBtnId"))).click();

		// shopping cart
		Thread.sleep(1000);

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
