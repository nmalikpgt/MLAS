package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

import junit.framework.Assert;

public class MP1007 {

	private static final Object Webelement = null;
	// private String clientNumber;
	private String pin;
	private String pid;
	private static String clientNumber;
	private String transactionId;
	private static String claimId;
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

		java.util.List<WebElement> elements = WebDriverManager.getElements();

		// new LGN1001().test();
		// new CM1B001().test();

		new PL1001().test();
		this.setClientNumber(new PL1001().getClientNumber());

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[5]/a[2]/b/em"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register Organization"))).click();

		// register org

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgName"))).sendKeys("EMPRESS");

		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"37\"]")));
		// element.click();

		Thread.sleep(500);
		WebElement _element = WebDriverManager.getDriver().findElement(By.name("fileInput"));
		Actions actions = new Actions(WebDriverManager.getDriver());

		actions.moveToElement(_element).click().perform();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("fileInput")))
				.sendKeys("C:\\Users\\cvlasceanu.PACIFICGEOTECH\\Desktop\\Claim History Report (106090) (1).pdf");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1")))
				.sendKeys("89 TOTEM STREET");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddressCity"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddressCity")))
				.sendKeys("Toronto");
		Thread.sleep(10000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingCountry"))))
				.selectByVisibleText("CANADA");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"37\"]"))).click();
		// Thread.sleep(10000);
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingCountry"))).click();
		// Thread.sleep(2000);
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"string:CA\"]"))).click();
		Thread.sleep(10000);
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("province"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"string:ON\"]")))
				.click();

		// new
		// Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("province")))).selectByVisibleText("ONTARIO");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name=\"province\"]
		// > option[value=\"8\"]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).sendKeys("B5H 8H5");

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneType"))))
				.selectByVisibleText("Home");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name=\"primary_phoneType\"]
		// > option[value=\"1\"]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneNumber"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneNumber")))
				.sendKeys("(111) 111-1111");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")))
				.sendKeys("gcontor@pacificgeotech.com");

		// more info
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox']")));
		element.click();
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

		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.name("orgType"))).click();
		Thread.sleep(500);

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgType"))))
				.selectByVisibleText("Incorporated Company");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"1\"]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpNumber"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpNumber"))).sendKeys("122122");
		new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.name("incorpPlace")));
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpPlace"))))
				.selectByVisibleText("CANADA");

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		Thread.sleep(1000);
		element = driverWait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn-label  ng-binding']")));
		element.click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn-label  ng-binding']")));
		element = driverWait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn-label  ng-binding']")));
		element.click();

		Thread.sleep(10000);

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'Client Created Successfully.Please see your email for further information')]")));

		// Collect org number

		WebElement TxtBoxContent1 = driver.findElement(By.id("organizationNumberId"));
		String orgclientNum = TxtBoxContent1.getText();
		System.out.println("Client number: " + orgclientNum);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[7]/a[2]/b/em"))).click();

		// Thread.sleep(1000);
		// updateCellLockTable();
		// Thread.sleep(4000);
		// updateCellgridTable();
		// Thread.sleep(1000);
		// upadteStatus(orgclientNum);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[7]/a[2]"))).click();

		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.linkText("Register a Mining Claim - External Client")))
				.click();

		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("regMethodId"))))
				.selectByVisibleText("Input or Generate Cell IDs");

		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[contains(text(), 'The registration of a mining claim or the acquisition of any right or interest in a mining claim by any person does not confer upon that person')]")));

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("declarationChkbox"))).click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element = driver.findElement(By.id("selectedGeoMapIds"));
		element.sendKeys("43B0123");

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoIdendityButtonId"))).click();

		driver.getPageSource()
				.contains("The IDs: 43B0123 provided are not in the correct Cell ID format of [42F07E027]");

	}

	private void setClientNumber(String clientNumber2) {
		// TODO Auto-generated method stub

	}

}
