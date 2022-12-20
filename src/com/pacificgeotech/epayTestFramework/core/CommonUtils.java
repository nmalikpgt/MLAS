package com.pacificgeotech.epayTestFramework.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author scheong
 * 
 *         This utility class stores common methods that are useful for multiple
 *         scripts. Examples include logging in, going through the shopping
 *         cart, taking screenshots, etc.
 * 
 */
public class CommonUtils {

	//private static Logger log = Logger.getLogger(CommonUtils.class);

	public static void login() throws Exception {

		WebDriver driver = WebDriverManager.getDriver();
		WebElement element = WebDriverManager.getElement();

		String username = null;
		String password = null;

		if (Config.ENVIROMENT.equals(Constants.LOCAL_HOST)) {
			driver.get("http://localhost:20280/mlas/login.jsf");
			driver.navigate().to("http://http://localhost:20280/mlas/login.jsf");
			driver.navigate().refresh();

			username = "admin";
			password = "welcome";

		} else if (Config.ENVIROMENT.equals(Constants.LOCAL_DEV)) {
			driver.get("http://cheetah-vm1:30080/epermit/module/core/module/login/ui/jsf/login.jsf");

		//	username = "admin";
		//	password = "admin";

		} else if (Config.ENVIROMENT.equals(Constants.LOCAL_TEST)) {
			driver.get("http://rat1:20281/mlas/login");
			//driver.navigate().to("http://rat1:20281/mlas/login");

		/*	driver.get("https://intra.dev.mlas.mndm.gov.on.ca/mlas/login");
			driver.navigate().to("https://intra.dev.mlas.mndm.gov.on.ca/mlas/login");*/

			driver.navigate().refresh();

			username = "admin";
			password = "@MLAS4you";
		} else if (Config.ENVIROMENT.equals(Constants.STAGE1)) {
			driver.get("http://rat1:10281/mlas/login");

			username = "admin";
			password = "welcome";

		} else if (Config.ENVIROMENT.equals(Constants.STAGE2)) {
		driver.get("http://rat1:20281/mlas/login");

		username = "admin";
		password = "welcome";

	}
		Thread.sleep(1000);
		element = driver.findElement(By.id("username"));
		element.sendKeys(username);
		Thread.sleep(1000);
		element = driver.findElement(By.id("password"));
		element.sendKeys(password);
		Thread.sleep(1000);
		element = driver.findElement(By.id("loginSubmit"));
		element.click();

		//CommonUtils.takeScreenshot();
	}

	public static void loginExternal() throws Exception {

		WebDriver driver = WebDriverManager.getDriver();
		WebElement element = WebDriverManager.getElement();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		driver.get("http://rat1:10281/mlas/externalLogin");
		driver.navigate().to("http://rat1:10281/mlas/externalLogin");
		driver.navigate().refresh();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("pid")))
				.clear();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("pid")))
				.sendKeys("kdN2EFNMKIhh");
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By
						.id("loginSubmit"))).click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("pin")))
				.clear();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("pin")))
				.sendKeys("whdUO");
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit"))).click();
	}

	/*
	public static void login(String username, String password) throws Exception {

		WebDriver driver = WebDriverManager.getDriver();
		WebElement element = WebDriverManager.getElement();

		if (Config.ENVIROMENT.equals(Constants.LOCAL_HOST)) {
			driver.get("http://localhost:8080/epermit/module/core/module/login/ui/jsf/login.jsf");

		} else if (Config.ENVIROMENT.equals(Constants.LOCAL_DEV)) {
			driver.get("http://cheetah-vm1:30080/epermit/module/core/module/login/ui/jsf/login.jsf");

		} else if (Config.ENVIROMENT.equals(Constants.LOCAL_TEST)) {
			driver.get("http://cheetah-vm2:10281/mlas/login#/mlasDashboard.jsf");

		} else if (Config.ENVIROMENT.equals(Constants.LOCAL_ROLLBACK)) {
			driver.get("http://cheetah-vm1:29080/epermit/module/test/ui/jsf/startTest.jsf");

		} else if (Config.ENVIROMENT.equals(Constants.CAPRICORN)) {
			driver.get("http://capricorn:58081/epermit/module/core/module/login/ui/jsf/login.jsf");

		} else if (Config.ENVIROMENT.equals(Constants.OGCDEV)) {
			driver.get("http://10.1.53.190:8080/epermit/module/core/module/login/ui/jsf/login.jsf");

		} else if (Config.ENVIROMENT.equals(Constants.OGCDEV1)) {
			driver.get("http://matar-damse:8080/epermit/module/core/module/login/ui/jsf/login.jsf");

		} else if (Config.ENVIROMENT.equals(Constants.VULTURE)) {
			driver.get("http://vulture:3080/epermit/module/core/module/login/ui/jsf/login.jsf");

		} else if (Config.ENVIROMENT.equals(Constants.OGCTEST)) {
			driver.get("http://matar-tamse:8080/epermit/module/core/module/login/ui/jsf/login.jsf");

		} else if (Config.ENVIROMENT.equals(Constants.OGCUAT)) {
			driver.get("http://matar-uamse:8080/epermit/module/core/module/login/ui/jsf/login.jsf");

		} else if (Config.ENVIROMENT.equals(Constants.STAGE1)) {
			driver.get("http://rat1:10281/mlas/login");

		} else if (Config.ENVIROMENT.equals(Constants.CRDUAT)) {
			driver.get("http://matar-uamse:8080/crd/login");

		} else if (Config.ENVIROMENT.equals(Constants.ePayUAT)) {
			driver.get("http://matar-uamse.bcogc.local:8181/epay/module/core/module/login/ui/jsf/login.jsf");

		}

		element = driver.findElement(By.id("username"));
		element.sendKeys(username);

		element = driver.findElement(By.id("password"));
		element.sendKeys(password);

		element = driver.findElement(By.id("loginSubmit"));
		element.click();

	}*/

	public static void getThroughShoppingCart() throws Exception {

		WebDriver driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		Select select = WebDriverManager.getSelect();

		// get amount
		Thread.sleep(Config.SLEEP_TIME_FULL_REFRESH);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By
						.id("appFrmId:shoppingCrtItmsDtaTblId:shoppingCrtTtlOutTxtId")));
		String totalCost = element.getText().substring(1,
				element.getText().length());

		// click Next (show shopping cart page)
		CommonUtils.takeScreenshot();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By
						.id("appFrmId:next"))).click();

		// click "New Receipt" radio
		Thread.sleep(Config.SLEEP_TIME_FULL_REFRESH);
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.id("appFrmId:newRcptIndRdoId:0")));
		element.click();

		// click Next (select receipt page)
		CommonUtils.takeScreenshot();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By
						.id("appFrmId:downNavNextCmdBtn"))).click();

		// select payer
		Thread.sleep(Config.SLEEP_TIME_PART_REFRESH);
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.id("appFrmId:payerInTxtId")));
		new Actions(driver).moveToElement(element).sendKeys(Config.CLIENT_ID)
				.perform();
		Thread.sleep(Config.SLEEP_TIME_PART_REFRESH);
		new Actions(driver).moveToElement(element).sendKeys(Keys.ESCAPE)
				.perform();
		Thread.sleep(Config.SLEEP_TIME_PART_REFRESH);

		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By
						.id("appFrmId:pickPayerAddrCmdBtnId"))).click();
		Thread.sleep(Config.SLEEP_TIME_PART_REFRESH);
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By
						.id("appFrmId:addrListDtaTblId:0:slctAddrCmdBtnId")))
				.click();
		Thread.sleep(Config.SLEEP_TIME_PART_REFRESH);

		// payment type drop down - cash
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By
						.id("appFrmId:paymentDetailsDtaTblId:0:pymntTypeMnuId")));
		select = new Select(element);
		select.selectByVisibleText("Cash");

		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By
						.id("appFrmId:paymentDetailsDtaTblId:0:subamtInTxtId")));
		element.clear();
		element.sendKeys(totalCost);

		// click Next (Input Payment Details page)
		CommonUtils.takeScreenshot();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By
						.id("appFrmId:downNavNextCmdBtn"))).click();

		// click Next (payment summary page)
		CommonUtils.takeScreenshot();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By
						.id("appFrmId:downNavNextCmdBtn"))).click();

		// click Next (payment confirmation page)
		CommonUtils.takeScreenshot();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By
						.id("appFrmId:downNavNextCmdBtn"))).click();

		// show conf page
		CommonUtils.takeScreenshot();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By
						.id("appFrmId:eventsDtaTblId:0:showConfPage"))).click();
		CommonUtils.takeScreenshot();
	}

	public static void goToMainMenu() throws Exception {

		WebDriverWait driverWait = WebDriverManager.getDriverWait();

		CommonUtils.takeScreenshot();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By
						.id("appFrmId:goMainMenuCmdLnkId"))).click();
		CommonUtils.takeScreenshot();
	}

	public static void takeScreenshot() throws Exception {

		WebDriver driver = WebDriverManager.getDriver();

		// sleep process to give the page time to load
		Thread.sleep(Config.SLEEP_TIME_FULL_REFRESH);

		String url = driver.getCurrentUrl();
		int lastSlash = url.lastIndexOf("/");
		String page = url.substring(lastSlash + 1, url.length());

		Date date = new Date();
		String timestamp = new Timestamp(date.getTime()).toString().replace(
				":", ".");

		String filename = timestamp + " " + page + ".png";
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("screenshots\\"
				+ Config.TEST_SCENARIO + "\\" + filename));

	//	log.info("Processed: " + filename);
	}

	public static List executeQuery(String query) throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = null;
		if (Config.ENVIROMENT.equals(Constants.LOCAL_HOST)) {
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@WILDDOG:1521:dev", "EPM", "EPM");
		} else if (Config.ENVIROMENT.equals(Constants.LOCAL_DEV)) {
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@WILDDOG:1521:dev", "EPM", "EPM");
		} else if (Config.ENVIROMENT.equals(Constants.LOCAL_TEST)) {
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@WILDDOG:1521:test", "EPM", "EPM");
		}

		Statement queryer = con.createStatement();
		ResultSet rs = queryer.executeQuery(query);

		List rows = new ArrayList();

		try {
			while (rs.next()) {
				rows.add(rs.getString(1));
			}
		} catch (SQLException e) {
			return null; // just execute update (not expecting any results back)
		}

		con.close();
		return rows;
	}

	public static String getAvailableClsrNumber() throws Exception {

		int min = 1000;
		int max = 80000;
		Random random = new Random();
		int clsrNumber = random.nextInt(max - min) + min;

		return new Integer(clsrNumber).toString();
	}

	public static BigDecimal getArea(String tenureNumber) throws Exception {

		StringBuilder sb = new StringBuilder();
		sb.append(" select a.area_ha ");
		sb.append(" from mta_tenure a ");
		sb.append(" where a.STAKED_CLAIM_ID = '" + tenureNumber + "'");
		return new BigDecimal(CommonUtils.executeQuery(sb.toString()).get(0)
				.toString());
	}
}
