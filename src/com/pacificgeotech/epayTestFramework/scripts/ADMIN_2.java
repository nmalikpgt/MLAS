package com.pacificgeotech.epayTestFramework.scripts;

import com.pacificgeotech.epayTestFramework.core.CommonUtils;
import com.pacificgeotech.epayTestFramework.core.WebDriverManager;
import com.pacificgeotech.epayTestFramework.dbConnection.DbConnection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;

public class ADMIN_2 {
	private static File fileUrl = new File(LGN1001.class.getProtectionDomain().getCodeSource().getLocation().getPath())
			.getParentFile();
	private String clientNumber;
	private String pin;
	private String pid;
	public static String user;

	private WebDriver driver;

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}

	public static String[] getLoginUrl() {
		System.out.println("Reading url from a integration.properties file");
		String url = null;
		String loginType = null;
		String internalLogin = null;
		Properties integrationProperties = new Properties();
		FileInputStream file;
		try {
			file = new FileInputStream(fileUrl.getAbsolutePath() + "\\Config\\integration.properties");
			integrationProperties.load(file);
			file.close();

			url = integrationProperties.getProperty("integration.login.url");
			loginType = integrationProperties.getProperty("integration.login.type");
			internalLogin = integrationProperties.getProperty("integration.login.internal");
			String[] urls = { url, loginType, internalLogin };
			return urls;
		} catch (IOException e) {
			System.out.println("IOException failed reason: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		String[] urlInfo = getLoginUrl();
		System.out.println("current url is: " + urlInfo[0]);

		// For remote login
		String user = null;


			// local test login
			driver.navigate().refresh();
			driver.navigate().to(urlInfo[0]);
			driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register Here"))).click();

		// common code for remote & localTest
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.name("lastName")));
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName"))).sendKeys("Test");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName"))).sendKeys("Automatic");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("addressLine1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("addressLine1"))).sendKeys("85 Trotol");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("city"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("city"))).sendKeys("Toronto");
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("country"))))
				.selectByVisibleText("CANADA");

		Thread.sleep(2000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("province"))))
				.selectByVisibleText("ONTARIO");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).sendKeys("B5G 5G5");

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneType"))))
				.selectByVisibleText("Home");
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("primary_phoneType")));
//		Thread.sleep(1000);
//		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("primary_phoneType"))).clear();
//		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("primary_phoneType"))).sendKeys("(111) 111-1111");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"[class='form-control input-sm ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required ng-valid-phone-vdr ng-valid-maxlength']")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"[class='form-control input-sm ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required ng-valid-phone-vdr ng-valid-maxlength']")));
		element.sendKeys("(111) 111-1111");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox']"))).click();

		new Select(driver.findElement(By.id("declarationSelect"))).selectByVisibleText("I agree");
		driver.findElement(By.xpath("//option[@value='true']")).click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='true']")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='true']")));
		element.click();
		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")))
				.sendKeys("test@pacificgeotech.com");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("submitFormButton")));
		element.click();

		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.elementToBeClickable(By.id("nextButtonId")));
		element.click();
/*
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Client Created Successfully. Please see your email for further information.')]")));
		Thread.sleep(1000);*/
		String clientNumber = driver.findElement(By.className("col-md-7")).getText();
		setClientNumber(clientNumber);

		System.out.println("New created client number is: " + clientNumber);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='header-label hidden-xs ng-binding ng-scope']")));
		element.click();
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.clearfix")));
		element.click();
			driver.navigate().refresh();
			driver.navigate().to(urlInfo[0]);

			String[] pidPin = getPidPin();

			String PID = decryptMlasOneKeyString(pidPin[0]);
			String PIN = decryptMlasOneKeyString(pidPin[1]);

			System.out.println("Pid: " + PID);
			System.out.println("Pin: " + PIN);

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
			element.sendKeys(PID);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();
			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(PIN);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();
			setPid(PID);
			setPin(PIN);

		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update my MAAP (Mining Act Awareness Program)")));
		element.click();

		Thread.sleep(2000);
		// Alert alert = driver.switchTo().alert();

		// wait till two windows are not opened

		waitForNumberofWindowsToEqual(2);// this method is for wait

		Set handles = driver.getWindowHandles();

		String firstWinHandle = driver.getWindowHandle();
		handles.remove(firstWinHandle);

		String winHandle = (String) handles.iterator().next();

		if (winHandle != firstWinHandle) {

			// To retrieve the handle of second window, extracting the handle
			// which does not match to first window handle

			String secondWinHandle = winHandle; // Storing handle of second
			// window handle

			// Switch control to new window

			driver.switchTo().window(secondWinHandle);
			driver.manage().window().maximize();
			((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
			Thread.sleep(1000);
			WebElement element1 = driver.findElement(By.id("purpose-prospector"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element1);
			((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next")));
			element.click();
			driver.switchTo().window(firstWinHandle);
		}

		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Prospector Licensing")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Apply for Prospector's Licence")));
		element.click();

		/*
		 * ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
		 * "window.scrollTo(0, 2300)"); element =
		 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath
		 * ("//section[@id='widget-grid']/div[2]/back-next/div/div[3]/button")))
		 * ; Actions actions = new Actions(driver);
		 * actions.moveToElement(element).click().perform();
		 */
		Thread.sleep(1500);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/button")))
				.click();

		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId")));
		element.click();
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("onlinePaymentSltId")));
		element.click();


		///  CRD


		CommonUtils.login();
		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Prospector Licensing")));
		element.click();


		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Apply for Prospector's Licence")));
		element.click();


		Thread.sleep(1500);


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder=\"Enter Submitter Id\"]")));
		element.sendKeys(clientNumber);
		Thread.sleep(1500);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();


		JavascriptExecutor js1 = ((JavascriptExecutor) driver);
		js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions
						.presenceOfElementLocated(By.id("confirmLnkId")))
				.click();




		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId")));
		element.click();
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("otcPaymentSltId")));
		element.click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='showSelectedPaymentId']/span")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder=\"Input part of Client ID or Name\"]")));
		element.sendKeys(clientNumber);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"payorAutoSelectDivId\"]/div[1]/div/div[2]/div/a")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeInput_0_id")));
		element.click();
		Thread.sleep(1000);


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"paymentTypeInput_0_id\"]/option[2]")));
		element.click();
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentAmoutInput_0_id")));
		element.sendKeys("40");


		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeNumber_0_id")));
		element.sendKeys("40");
		Thread.sleep(1500);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(1000);


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextToConfirmLnkId")));
		element.click();
		Thread.sleep(1000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextToCfmPgIndexId")));
		element.click();
		Thread.sleep(1000);





	}

	private String[] getPidPin() throws SQLException {
		String pid = null;
		String pin = null;

		Connection con = DbConnection.getConnection();
		try {
			Statement st = con.createStatement();
			String sql = ("select ENCRYPTED_ONE_KEY_PID,ENCRYPTED_ONE_KEY_PIN from MAM.MTA_SEC_USER where ACCESS_LOGIN_ID in (select max(ACCESS_LOGIN_ID) from MAM.MTA_SEC_USER)");
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				pid = rs.getString("ENCRYPTED_ONE_KEY_PID");
				pin = rs.getString("ENCRYPTED_ONE_KEY_PIN");
			}

		} finally {
			con.close();
		}
		if (pin == null) {
			throw new IllegalAccessError("Application number is empty");
		}
		if (pid == null) {
			throw new IllegalAccessError("Application number is empty");
		}

		String[] pidPin = { pid, pin };

		return pidPin;

	}

	public String decryptMlasOneKeyString(String encryptedString) {
		// String encryptionKey
		// ="ENC(Ovn5lB2TEgrjtU955iRw+TzPoW9qHDurAyCGb9i/ezU=)";
		// String encryptionSalt
		// ="ENC(s3t9LqSSNSCnVfdDPAO0j7xLOPQSpIN5e4+CRG2yXlkVtKeHDhY0707ZBXh7W8z9gkauvdyDIyDjoL2J+TYD4w==)";

		String encryptionKey = "MLASDev2k16!";
		String encryptionSalt = "thisSaltIsMaxCharactersForASalt!";

		try {
			String decryptedString = CipherAES.decrypt(encryptedString, encryptionKey, encryptionSalt);
			return decryptedString;
		} catch (PidCryptoException e) {

			throw new SystemException("Problem occurred during decryption process: " + getStackTraceAsString(e));
		}
	}

	private String getStackTraceAsString(final Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString();
	}




	public void getLoginInfoExternalLocalTest(String loginType, String url, String pid, String pin)
			throws InterruptedException, SQLException {

		WebDriver driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		System.out.println("PREVIOUS  PID: " + pid + "-- PIN: " + pin);

		driver.navigate().refresh();
		driver.navigate().to(url);

		//String[] pidPin = getPidPin();

		//String PID = decryptMlasOneKeyString(pidPin[0]);
		//String PIN = decryptMlasOneKeyString(pidPin[1]);

		//System.out.println("Pid: " + PID);
		//System.out.println("Pin: " + PIN);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
		element.sendKeys(pid);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
		element.sendKeys(pin);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();
		System.out.print("go to next step");

		}



	// -------Getter Setter-------------

	String getPid() {
		return pid;
	}

	private void setPid(String pid) {
		this.pid = pid;
	}

	String getPin() {
		return pin;
	}

	private void setPin(String pin) {
		this.pin = pin;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;

	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	private void waitForNumberofWindowsToEqual(int i) {
		// TODO Auto-generated method stub

	}









}
