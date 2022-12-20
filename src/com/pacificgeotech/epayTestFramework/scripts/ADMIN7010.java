package com.pacificgeotech.epayTestFramework.scripts;

import java.sql.DriverManager;
import java.sql.ResultSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.CommonUtils;
import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class ADMIN7010 {

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
		java.util.List<WebElement> elements = WebDriverManager.getElements();

		// login
		 CommonUtils.login();

		//driver.get("http://rat1:20281/mlas/externalLogin");
	//	driver.navigate().to("http://rat1:20281/mlas/externalLogin");
	//	driver.navigate().refresh();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"left-panel\"]/navigation-widget/div/nav/ul/li[7]/a[2]")));
		element.click();
		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"left-panel\"]/navigation-widget/div/nav/ul/li[7]/ul/li[2]/a")));
		element.click();
		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientTypeRdioInd")));
		element.click();
		Thread.sleep(2000);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[2]/div/div[2]/a")));
		element.click();
		Thread.sleep(2000);


		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register Here")));
		element.click();
		Thread.sleep(4000);
		new Select(driver.findElement(By.xpath("//div[@id='wid-id-1']/div/div/div[3]/fieldset/div/div/div/select")))
				.selectByVisibleText("Mrs.");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"3\"]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName")));
		element.sendKeys("Jay");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
		element.sendKeys("Smith");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondName")));
		element.sendKeys("A");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("thirdName")));
		element.sendKeys("B");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("prefName")));
		element.sendKeys("Smith");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("declarationChkbox")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("addressLine1")));
		element.sendKeys("79 Uralia");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("city")));
		element.sendKeys("Toronto");
		Thread.sleep(4000);
		new Select(driver.findElement(By.name("country"))).selectByVisibleText("CANADA");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"37\"]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"37\"]")));
		element.click();
		Thread.sleep(4000);
		new Select(driver.findElement(By.name("province"))).selectByVisibleText("ONTARIO");
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("select[name=\"province\"] > option[value=\"8\"]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode")));
		element.sendKeys("V5G 2H2");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")));
		element.click();
		Thread.sleep(4000);
		new Select(driver.findElement(By.name("primary_phoneType"))).selectByVisibleText("Home");
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("select[name=\"primary_phoneType\"] > option[value=\"0\"]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@value=''])[18]")));
		element.sendKeys("1");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("primPhone")));
		element.sendKeys("(111) 111-1111");
		// Thread.sleep(4000);
		new Select(driver.findElement(By.xpath("//div[@id='wid-id-1']/div/div/div[15]/div/fieldset[2]/div/div/select")))
				.selectByVisibleText("Mobile");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='1'])[7]")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondary_countryCode")));
		element.sendKeys("1");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondary_phoneNumber")));
		element.sendKeys("(111) 111-1111");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fax_countryCode")));
		element.sendKeys("1");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fax_phoneNumber")));
		element.sendKeys("(111) 111-1111");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("emailAddress")));
		element.sendKeys("gcontor@pacificgeotech.com");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next"))).click();
		Thread.sleep(4000);
		String clientNumber = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("col-md-9")))
				.getText();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("scroll(0, -250);");
		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.dropdown-toggle > i[name=\"user\"]")));
		element.click();

		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[3]/ul[2]/li[5]/ul/li[3]/a/span")));
		element.click();

		// login
		driver.get("http://cheetah-vm2:10281/mlas/login");
		driver.navigate().to("http://cheetah-vm2:10281/mlas/login");
		driver.navigate().refresh();
		CommonUtils.login();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("appFrmId:loginImgId")));
		element.click();

		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//aside[@id='left-panel']/nav/ul/li[3]/a/b/em")))
				.click();

		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("appFrmId:loginImgId")));
		// element.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update Client Profile"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys("10000078");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1"))).sendKeys("12 oak st");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")))
				.sendKeys("gcontor@pacificgeotech.com");
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-1']/div/div/div[11]/div[2]/fieldset/div/div/select"))))
						.selectByVisibleText("Yes");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"Y\"]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.btn.btn-default"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Today')]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']"))).click();

		Thread.sleep(2000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Client Profile updated successfully and client notified of changes.')]")));

		String clientNumber1 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("col-md-9")))
				.getText();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("scroll(0, -250);");
		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.dropdown-toggle > i[name=\"user\"]")));
		element.click();

		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[3]/ul[2]/li[5]/ul/li[3]/a/span")));
		element.click();

		Thread.sleep(2000);
		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().refresh();
		driver.get("http://rat1:20281/mlas/externalLogin");
		driver.navigate().to("http://rat1:20281/mlas/externalLogin");

		String pid = null;
		String pin = null;
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
		if (pin == null) {
			throw new IllegalAccessError("Application number is empty");
		}
		if (pid == null) {
			throw new IllegalAccessError("Application number is empty");
		}
		String pinStr = String.valueOf(pin);
		System.out.println("Submitted application number: " + pinStr);
		String pidStr = String.valueOf(pid);
		System.out.println("Submitted application number: " + pidStr);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
		element.sendKeys(pid);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
		element.sendKeys(pin);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
		element.click();

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.menu-item-parent.ng-binding")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Apply for Prospector's Licence")));
		element.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), '2015')]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Jay')]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Smith')]")))
				.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), '79 Uralia')]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Toronto')]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'ONTARIO')]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'CANADA')]")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'V5G 2H2')]")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), '1-(111) 111-1111')]")))
				.click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(), 'gcontor@pacificgeotech.com')]")))
				.click();

	}

}
