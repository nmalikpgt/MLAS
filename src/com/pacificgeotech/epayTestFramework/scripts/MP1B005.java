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

public class MP1B005 {

	private WebDriver driver;

	/*@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}*/

	@Test
	public void test() throws Exception {

		PL2001 pl2001 = new PL2001();
		pl2001.test();

		registerAsNonIncorporated(pl2001);
		registerMiningClaimInternal(pl2001);

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
		MP1001 mp1001 = new MP1001();
		mp1001.updateCellLockTable();
		mp1001.updateCellgridTable();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[9]/a[2]"))).click();
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

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("owneridInput_0"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("owneridInput_0")))
				.sendKeys(pl2001.getClientNumber());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("percentage_0"))).clear();
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("percentage_0"))).sendKeys("100");

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='wid-id-0']/div/div/div/table/tbody/tr[3]/td[3]/span/i"))).click();

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
//		driverWait
//				.until(ExpectedConditions
//						.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/a/span")))
//				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm"))).click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId"))).click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
//		driverWait
//				.until(ExpectedConditions
//						.presenceOfElementLocated(By.xpath("//form[@id='paymentTypeFrmId']/div/fieldset/div/label")))
//				.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("otcPaymentSltId"))).click();

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showSelectedPaymentId"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))).sendKeys("DARYL SMITH");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("412908 DARYL SMITH"))).click();

		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentTypeInput_0_id"))))
				.selectByVisibleText("Cash");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("paymentAmount_0"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentAmoutInput_0_id"))).sendKeys("102");

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
	}

	private void registerAsNonIncorporated(PL2001 pl2001) throws InterruptedException {
		System.out.println("Executing registerAsNonIncorporated method...");

		WebDriver driver = WebDriverManager.getDriver();
		WebElement element = WebDriverManager.getElement();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebDriverManager.getElements();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[7]/a[2]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register Client"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientTypeRdioOrg"))).click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/div[2]/div/div[3]/a/span")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")))
				.sendKeys(pl2001.getClientNumber());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

		Thread.sleep(1000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgName"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgName"))).sendKeys("Systems Ltd.");

		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgType"))))
				.selectByVisibleText("Other");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"string:02\"]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpNumber"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpNumber"))).sendKeys("122122");

		Thread.sleep(1000);
		new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.name("incorpPlace")));
		
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpPlace")))).selectByVisibleText("CANADA");


		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1")))
				.sendKeys("85 Lupus Street");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddressCity"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddressCity")))
				.sendKeys("Toronto");

		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingCountry"))))
				.selectByVisibleText("CANADA");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"37\"]"))).click();
		Thread.sleep(10000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("province"))))
				.selectByVisibleText("ONTARIO");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name=\"province\"]
		// > option[value=\"8\"]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).sendKeys("B5H 8H5");

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneType"))))
				.selectByVisibleText("Home");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneNumber"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneNumber")))
				.sendKeys("(111) 111-1111");
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("phone2Type"))))
				.selectByVisibleText("Mobile");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondary_phoneNumber"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondary_phoneNumber")))
				.sendKeys("(111) 111-1111");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fax_phoneNumber"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fax_phoneNumber")))
				.sendKeys("(111) 111-1111");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")))
				.sendKeys("gcontor@pacificgeotech.com");

		// more info
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox']")));
		element.click();

		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("orgAckSelect"))))
				.selectByVisibleText("I agree");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='true']")));
		element.click();

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
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option.ng-binding.ng-scope")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
				.selectByVisibleText("Certificate of Status");
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		Thread.sleep(1000);
		element = driverWait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn-label  ng-binding']")));
		element.click();

		Thread.sleep(8000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		element = driverWait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn-label  ng-binding']")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Client Created Successfully')]")));

	}

}
