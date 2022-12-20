package com.pacificgeotech.epayTestFramework.scripts;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

public class MP1009 {

	private String pin;

	private String pid;

	private String clientNumber;
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
		WebDriverManager.getElements();
		// register external user

		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().refresh();
		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().to("http://rat1:20281/mlas/externalLogin");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register Here"))).click();

		// old code
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName"))).sendKeys("Tom");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName"))).sendKeys("Hanks");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("declarationChkbox"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("addressLine1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("addressLine1"))).sendKeys("85 Trotol");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("city"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("city"))).sendKeys("Toronto");
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("country"))))
				.selectByVisibleText("CANADA");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"37\"]"))).click();
		Thread.sleep(4000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("province"))))
				.selectByVisibleText("ONTARIO");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name=\"province\"]
		// > option[value=\"8\"]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).sendKeys("B5G 5G5");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]"))).click();
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneType"))))
				.selectByVisibleText("Home");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name=\"primary_phoneType\"]
		// > option[value=\"0\"]"))).click();
		/*
		 * driverWait.until( ExpectedConditions.presenceOfElementLocated(By
		 * .xpath("(//input[@value=''])[18]"))).clear(); driverWait.until(
		 * ExpectedConditions.presenceOfElementLocated(By
		 * .xpath("(//input[@value=''])[18]"))).sendKeys("1");
		 */

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("primPhone"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("primPhone"))).sendKeys("(111) 111-1111");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("emailAddress"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox']"))).click();

		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();

		new Select(driver.findElement(By.id("declarationSelect"))).selectByVisibleText("I agree");
		driver.findElement(By.xpath("//option[@value='true']")).click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='true']")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='true']")));
		element.click();
		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("emailAddress")))
				.sendKeys("cvlasceanu@pacificgeotech.com");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("submitFormButton")));
		element.click();

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.elementToBeClickable(By.id("nextBtnId")));
		element.click();

		new WebDriverWait(driver, 500).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Client Created Successfully')]")));
		WebElement TxtBoxContent = driver.findElement(By.cssSelector("[class='control-label-value ng-binding']"));
		String ExtclientNum = TxtBoxContent.getText();
		System.out.println("Client number: " + ExtclientNum);
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
				.click();
		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.pull-left.ng-binding")))
				.click();

		Thread.sleep(4000);

		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().refresh();
		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().to("http://rat1:20281/mlas/externalLogin");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register Here"))).click();

		// old code
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName"))).sendKeys("Tom");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName"))).sendKeys("Hanks");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("declarationChkbox"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("addressLine1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("addressLine1"))).sendKeys("85 Trotol");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("city"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("city"))).sendKeys("Toronto");
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("country"))))
				.selectByVisibleText("CANADA");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"37\"]"))).click();
		Thread.sleep(4000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("province"))))
				.selectByVisibleText("ONTARIO");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name=\"province\"]
		// > option[value=\"8\"]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).sendKeys("B5G 5G5");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]"))).click();
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneType"))))
				.selectByVisibleText("Home");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name=\"primary_phoneType\"]
		// > option[value=\"0\"]"))).click();
		/*
		 * driverWait.until( ExpectedConditions.presenceOfElementLocated(By
		 * .xpath("(//input[@value=''])[18]"))).clear(); driverWait.until(
		 * ExpectedConditions.presenceOfElementLocated(By
		 * .xpath("(//input[@value=''])[18]"))).sendKeys("1");
		 */

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("primPhone"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("primPhone"))).sendKeys("(111) 111-1111");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("emailAddress"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox']"))).click();

		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();

		new Select(driver.findElement(By.id("declarationSelect"))).selectByVisibleText("I agree");
		driver.findElement(By.xpath("//option[@value='true']")).click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='true']")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='true']")));
		element.click();
		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("emailAddress")))
				.sendKeys("cvlasceanu@pacificgeotech.com");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("submitFormButton")));
		element.click();

		Thread.sleep(2000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.elementToBeClickable(By.id("nextBtnId")));
		element.click();

		new WebDriverWait(driver, 500).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Client Created Successfully')]")));
		WebElement TxtBoxContent1 = driver.findElement(By.cssSelector("[class='control-label-value ng-binding']"));
		String ExtclientNum1 = TxtBoxContent1.getText();
		System.out.println("Client number 2: " + ExtclientNum1);
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
				.click();
		Thread.sleep(2000);

		WebElement _element2 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out")));

		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().refresh();
		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().to("http://rat1:20281/mlas/externalLogin");
		String pid1 = null;
		String pin1 = null;
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		java.sql.Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@rat3:1521:test", "PROXY_MAM",
				"PROXY_MAM");
		try {
			java.sql.Statement st = con1.createStatement();
			String sql = ("select ENCRYPTED_ONE_KEY_PID,ENCRYPTED_ONE_KEY_PIN from MAM.MTA_SEC_USER where ACCESS_LOGIN_ID in (select max(ACCESS_LOGIN_ID) from MAM.MTA_SEC_USER)");
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				pid1 = rs.getString("ENCRYPTED_ONE_KEY_PID");
				pin1 = rs.getString("ENCRYPTED_ONE_KEY_PIN");
			}

		} finally {
			con1.close();
		}
		if (pin1 == null) {
			throw new IllegalAccessError("Application number is empty");
		}
		if (pid1 == null) {
			throw new IllegalAccessError("Application number is empty");
		}
		String pinStr1 = String.valueOf(pin1);
		System.out.println("pin: " + pinStr1);
		String pidStr1 = String.valueOf(pid1);
		System.out.println("pid: " + pidStr1);
		System.out.println("decrypted: " + decryptMlasOneKeyString(pin1));
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
		// element.sendKeys(pid1);
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		// element.click();
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
		element.sendKeys(decryptMlasOneKeyString(pin1));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Dashboard')]")))
				.getText();

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
				.click();
		Thread.sleep(2000);

		WebElement _element1 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out")));

		Thread.sleep(4000);
		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().refresh();
		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().to("http://rat1:20281/mlas/externalLogin");
		String pid2 = null;
		String pin2 = null;
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		java.sql.Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@rat3:1521:test", "PROXY_MAM",
				"PROXY_MAM");
		try {
			java.sql.Statement st2 = con2.createStatement();
			String sql2 = ("select ENCRYPTED_ONE_KEY_PID,ENCRYPTED_ONE_KEY_PIN from MAM.MTA_SEC_USER where ACCESS_LOGIN_ID in (select max(ACCESS_LOGIN_ID) from MAM.MTA_SEC_USER)");
			ResultSet rs = st2.executeQuery(sql2);
			if (rs.next()) {
				pid2 = rs.getString("ENCRYPTED_ONE_KEY_PID");
				pin2 = rs.getString("ENCRYPTED_ONE_KEY_PIN");
			}

		} finally {
			con2.close();
		}
		if (pin2 == null) {
			throw new IllegalAccessError("Application number is empty");
		}
		if (pid2 == null) {
			throw new IllegalAccessError("Application number is empty");
		}
		String pinStr2 = String.valueOf(pin2);
		System.out.println("pin: " + pinStr2);
		String pidStr2 = String.valueOf(pid2);
		System.out.println("pid: " + pidStr2);
		System.out.println("decrypted 2: " + decryptMlasOneKeyString(pin2));
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
		// element.sendKeys(pid1);
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		// element.click();
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
		element.sendKeys(decryptMlasOneKeyString(pin2));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Dashboard')]")))
				.getText();

	}

	private void waitForNumberofWindowsToEqual(int i) {
		// TODO Auto-generated method stub

	}

	public String decryptMlasOneKeyString(String encryptedString) {
		String encryptionKey = "ENC(Ovn5lB2TEgrjtU955iRw+TzPoW9qHDurAyCGb9i/ezU=)";
		String encryptionSalt = "ENC(s3t9LqSSNSCnVfdDPAO0j7xLOPQSpIN5e4+CRG2yXlkVtKeHDhY0707ZBXh7W8z9gkauvdyDIyDjoL2J+TYD4w==)";

		// String encryptionKey = "MLASDev2k16!";
		// String encryptionSalt = "thisSaltIsMaxCharactersForASalt!";

		try {
			String decryptedString = CipherAES.decrypt(encryptedString, encryptionKey, encryptionSalt);
			System.out.println("decryptedString: " + decryptedString);
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

	private String getLatestPid()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@rat3:1521:test", "PROXY_MAM",
				"PROXY_MAM");

		try {
			java.sql.Statement st = con.createStatement();
			String sql = ("select ENCRYPTED_ONE_KEY_PID,ENCRYPTED_ONE_KEY_PIN from MAM.MTA_SEC_USER where ACCESS_LOGIN_ID in (select max(ACCESS_LOGIN_ID) from MAM.MTA_SEC_USER)");

			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				pid = rs.getString("ENCRYPTED_ONE_KEY_PID");
				pin = rs.getString("ENCRYPTED_ONE_KEY_PIN");
			}

		} finally {
			con.close();
		}
		if ((pid == null) || (pin == null)) {
			throw new IllegalAccessError("data empty");
		}

		return null;

	}

	private String getPid() {
		return pid;
	}

	private void setPid(String pid) {
		this.pid = pid;
	}

	private String getPin() {
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

}
