package com.pacificgeotech.epayTestFramework.scripts;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class LGN1007 {
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
		WebDriverManager.getElements();

		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().to("http://rat1:20281/mlas/externalLogin");
		driver.navigate().refresh();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register Here"))).click();
		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName"))).sendKeys("Tom");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName"))).sendKeys("Hanks");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("declarationChkbox"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("addressLine1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("addressLine1"))).sendKeys("85 Trotol");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("city"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("city"))).sendKeys("Toronto");
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("country"))))
				.selectByVisibleText("CANADA");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"37\"]"))).click();
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("province"))))
				.selectByVisibleText("ONTARIO");
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("select[name=\"province\"] > option[value=\"8\"]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).sendKeys("B5G 5G5");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")))
				.click();
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneType"))))
				.selectByVisibleText("Home");
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("select[name=\"primary_phoneType\"] > option[value=\"0\"]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@value=''])[18]"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@value=''])[18]")))
				.sendKeys("1");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("primPhone"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("primPhone"))).sendKeys("(111) 111-1111");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("emailAddress"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("emailAddress")))
				.sendKeys("gcontor@pacificgeotech.com");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();
		Thread.sleep(2000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next"))).click();
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Client Created Successfully')]")));

		String clientNumber = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("col-md-9")))
				.getText();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("scroll(0, -250);");

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
				.click();
		Thread.sleep(2000);

		WebElement _element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out")));

		Actions actions = new Actions(driver);

		actions.moveToElement(_element).click().perform();
		Thread.sleep(2000);
		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().refresh();
		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().to("http://rat1:20281/mlas/externalLogin");
		getLatestPid();
		System.out.println(pin);
		System.out.println(pid);
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid"))).sendKeys(getPid());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit"))).click();
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin"))).sendKeys(getPin());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit"))).click();

		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//aside[@id='left-panel']/nav/ul/li[3]/a/b/em")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register Organization"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgName"))).sendKeys("EMPRESS");
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgType"))))
				.selectByVisibleText("Partnership");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"1\"]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1")))
				.sendKeys("89 TOTEM STREET");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddressCity"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddressCity")))
				.sendKeys("Toronto");
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingCountry"))))
				.selectByVisibleText("CANADA");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"37\"]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("province"))).click();
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("province"))))
				.selectByVisibleText("ONTARIO");
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("select[name=\"province\"] > option[value=\"8\"]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).sendKeys("V5G 5H5");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")))
				.click();
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneType"))))
				.selectByVisibleText("Home");
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("select[name=\"primary_phoneType\"] > option[value=\"1\"]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_prefix"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_prefix"))).sendKeys("1");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneNumber"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneNumber")))
				.sendKeys("(111) 111-1111");
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("phone2Type"))))
				.selectByVisibleText("Mobile");
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("select[name=\"phone2Type\"] > option[value=\"2\"]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondary_countryCode"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondary_countryCode"))).sendKeys("1");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondary_phoneNumber"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondary_phoneNumber")))
				.sendKeys("(111) 111-1111");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fax_countryCode"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fax_countryCode"))).sendKeys("1");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fax_phoneNumber"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fax_phoneNumber")))
				.sendKeys("(111) 111-1111");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")))
				.sendKeys("gcontor@pacificgeotech.com");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();
		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next"))).click();

		Thread.sleep(2000);

		WebElement _element1 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out")));

		Actions actions1 = new Actions(driver);

		actions1.moveToElement(_element1).click().perform();

		WebDriver driver1 = new ChromeDriver();
		driver1.get("http://rat1:20281/mlas/externalLogin");
		// do some admin stuff

		// driver.get("http://rat1:20281/mlas/externalLogin");

		driver1.get("http://rat1:20281/mlas/externalLogin");
		driver1.navigate().to("http://rat1:20281/mlas/externalLogin");
		driver1.navigate().refresh();
		System.out.println(pin);
		System.out.println(pid);
		// Switch to new window opened
		for (String winHandle : driver1.getWindowHandles()) {
			driver1.switchTo().window(winHandle);

			getLatestPid();
			Thread.sleep(4000);
			driver1.findElement(By.id("pid")).clear();
			driver1.findElement(By.id("pid")).sendKeys(pid);
			Thread.sleep(4000);
			driver1.findElement(By.id("loginSubmit")).click();
			Thread.sleep(2000);
			Thread.sleep(4000);
			driver1.findElement(By.id("pin")).clear();
			driver1.findElement(By.id("pin")).sendKeys(pin);
			Thread.sleep(4000);
			driver1.findElement(By.id("loginSubmit")).click();
			driver1.close();
		}

	}

	private WebDriver driver() {
		// TODO Auto-generated method stub
		return null;
	}

	private By getMyDriver() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object open_browser(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getLatestPid()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@orca3:1521:test", "PROXY_MAM",
				"PROXY_MAM");

		try {
			java.sql.Statement st = con.createStatement();
			String sql = ("select HASHED_ONE_KEY_PID,HASHED_ONE_KEY_PIN from MAM.MTA_SEC_USER where ACCESS_LOGIN_ID in (select max(ACCESS_LOGIN_ID) from MAM.MTA_SEC_USER)");

			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				pid = rs.getString("HASHED_ONE_KEY_PID");
				pin = rs.getString("HASHED_ONE_KEY_PIN");
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
}