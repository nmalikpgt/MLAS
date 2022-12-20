package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Properties;

import com.pacificgeotech.epayTestFramework.core.Config;
import com.pacificgeotech.epayTestFramework.core.Constants;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;
import com.pacificgeotech.epayTestFramework.dbConnection.DbConnection;

public class LGN1001 {

	public static File fileUrl = new File(LGN1001.class.getProtectionDomain().getCodeSource().getLocation().getPath())
			.getParentFile();
	public static String clientNumber;
	public static String pin;
	public static String pid;
	public static String user;

	public WebDriver driver;

	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	/*@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}*/

	/*public static String[] getLoginUrl() {
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
			String[] urls = {url, loginType, internalLogin};
			return urls;
		} catch (IOException e) {
			System.out.println("IOException failed reason: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}*/
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

			if(Config.ENVIROMENT==Constants.LOCAL_TEST)
			{
				url = integrationProperties.getProperty("integration.login.url");
				loginType = integrationProperties.getProperty("integration.login.type");
				internalLogin = integrationProperties.getProperty("integration.login.internal");
				String[] urls = {url, loginType, internalLogin};
				return urls;
			}
			else if(Config.ENVIROMENT==Constants.LOCAL_DEV)
			{
				url = integrationProperties.getProperty("dev.integration.login.url");
				loginType = integrationProperties.getProperty("dev.integration.login.type");
				internalLogin = integrationProperties.getProperty("dev.integration.login.internal");
				String[] urls = {url, loginType, internalLogin};
				return urls;
			}
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
		if ("remote".equals(urlInfo[1])) {
			driver.navigate().refresh();
			driver.navigate().to(urlInfo[0]);

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign up now!")));
			element.click();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("oneKeyId")));
			element.click();

			user = "pgtsTest" + Calendar.getInstance().getTimeInMillis();
			setUser(user);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("oneKeyId")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("oneKeyId")));
			element.sendKeys(user);

			System.out.println(user);

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password1")));
			element.click();
			Thread.sleep(1000);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password1")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password1")));
			element.sendKeys("Pgts_test1234");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password2")));
			element.click();
			Thread.sleep(1000);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password2")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password2")));
			element.sendKeys("Pgts_test1234");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("termAndCondition")));
			element.click();
			new Select(
					element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("securityQuestion1"))))
							.selectByVisibleText("What was the first and last name of my first boyfriend/girlfriend?");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("securityAnswer1")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("securityAnswer1")));
			element.sendKeys("abc");
			new Select(
					element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("securityQuestion2"))))
							.selectByVisibleText("What was the name of the first school I attended?");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("securityAnswer2")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("securityAnswer2")));
			element.sendKeys("abc");
			new Select(
					element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("securityQuestion3"))))
							.selectByVisibleText("What is my child's middle name?");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("securityAnswer2")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("securityAnswer2")));
			element.sendKeys("1234");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("securityAnswer3")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("securityAnswer3")));
			element.sendKeys("test");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("recoveryEmail")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("recoveryEmail")));
			element.sendKeys("cvlasceanu@pacificgeotech.com");
			Thread.sleep(1000);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("submitForm")));
			element.click();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("Continue")));
			element.click();
			element = driverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector("#pa_type_ > #MLASDEVENROL > a")));
			element.click();
			element = driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register as MNDM client")));
			element.click();

		} else {
			// local test login
			driver.navigate().refresh();
			driver.navigate().to(urlInfo[0]);
			driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register Here"))).click();

		}
		Thread.sleep(1000);
		// common code for remote & localTest
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.name("lastName")));
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName"))).sendKeys("Test");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("honorificSelectId"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='honorificSelectId']//option[contains(text(),'Mr.')]"))).click();


		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName"))).sendKeys("Automatic");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("addressLine1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("addressLine1"))).sendKeys("85 Trotol");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("city"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("city"))).sendKeys("Toronto");

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("country"))))
				.selectByVisibleText("CANADA");


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
		Thread.sleep(1000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")))
				.sendKeys("test@pacificgeotech.com");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("conEmail")))
				.sendKeys("test@pacificgeotech.com");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("submitFormButton")));
		element.click();

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.elementToBeClickable(By.id("nextButtonId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Client Created Successfully. Please see your email for further information.')]")));
		Thread.sleep(1000);
		String clientNumber = driver.findElement(By.className("col-md-7")).getText();
		setClientNumber(clientNumber);

		System.out.println("New created client number is: " + clientNumber);

		/*element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[2]/a/span")));
		element.click();
		Thread.sleep(2000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.pull-left.ng-binding")));
		element.click();*/
		
//		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/a/span"))); 
//		 element.click();
		 
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='header-label hidden-xs ng-binding ng-scope']")));
		 element.click();
		 Thread.sleep(1000);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.clearfix")));
		 element.click();

		if ("remote".equals(urlInfo[1])) {
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ldap_user")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ldap_user")));
			element.sendKeys(user);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ldap_password")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ldap_password")));
			element.sendKeys("Pgts_test1234");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("Login")));
			element.click();
			element = driverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector("#pa_type_ > #MLASDEVENROL > a")));
			element.click();
			
			// client number
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("wacinput")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("wacinput")));
			element.sendKeys(clientNumber);

			driverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='send' and @value='Submit']")))
					.click();

			// Getting PIN & PID
			String[] pidPin = getPidPin();
System.out.println(pidPin);
			// submit external
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("answer0")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("answer0")));
			element.sendKeys(decryptMlasOneKeyString(pidPin[1]));

			driverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='send' and @value='Submit']")))
					.click();
			driverWait
			.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//input[@name='accept' and @value='I agree']")))
			.click();

			driverWait.until(ExpectedConditions
					.presenceOfElementLocated(By.linkText("Mining Lands Administration System (DEV)"))).click();
			// authenticate in external
			/**/


		} else {

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

		}

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Dashboard')]")))
				.getText();
	}

	public String[] getPidPin() throws SQLException {
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

	public void switchLocalRemote(String loginType, String url, String pid, String pin) throws InterruptedException {

		WebDriver driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		if ("remote".equals(loginType)) {
			driver.get(url); // for remote
			// login remote
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ldap_user")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ldap_user")));
			element.sendKeys(getUser());
			System.out.println("user: " + getUser());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ldap_password")));
			element.clear();
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ldap_password")));
			element.sendKeys("Pgts_test1234");
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("Login")));
			element.click();
			driverWait.until(ExpectedConditions
					.presenceOfElementLocated(By.linkText("Mining Lands Administration System (DEV)"))).click();
		} else {
			System.out.println(pin);
			driver.navigate().refresh();
			driver.navigate().to(url);
			Thread.sleep(2000);

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
			element.sendKeys(pid);

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(pin);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();
		}

	}

	public void getLoginInfoExternalRemote(String loginType, String url)
			throws InterruptedException {

		WebDriver driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		// authenticate in external
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ldap_user")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ldap_user")));
		element.sendKeys(user);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ldap_password")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ldap_password")));
		element.sendKeys("Pgts_test1234");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("Login")));
		element.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Mining Lands Administration System (DEV)")))
				.click();

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

		//setPid(PID);
		//setPin(PIN);

	}

	// -------Getter Setter-------------

	public String getPid() {
		return pid;
	}

	private void setPid(String pid) {
		this.pid = pid;
	}

	public String getPin() {
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

}
