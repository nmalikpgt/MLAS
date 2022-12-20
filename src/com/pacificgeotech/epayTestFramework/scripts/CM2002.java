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

public class CM2002 {

	private WebDriver driver;
	private String clientNumber;

	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	@Test
	public void test() throws Exception {

		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		java.util.List<WebElement> elements = WebDriverManager.getElements();
		
		CM2001 cm2001 = new CM2001();
		cm2001.test();
		
		setClientNumber(cm2001.getClientNumber());

		LGN1002 lgn1002 = new LGN1002();
		lgn1002.test();
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Client Management"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register Client"))).click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@name='clientTypeRdio' and @value='org']")))
				.click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/div[2]/div/div[3]/a/span")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(cm2001.getClientNumber());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgName"))).sendKeys("Systems Ltd.");
		Thread.sleep(10000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgType"))))
				.selectByVisibleText("Incorporated Company");

		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"3\"]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpNumber"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpNumber"))).sendKeys("122122");
		Thread.sleep(10000);
		new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.name("incorpPlace")));
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpPlace"))))
				.selectByVisibleText("CANADA");

		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"37\"]")));
		// element.click();
		/*
		 * driverWait.until( ExpectedConditions.presenceOfElementLocated(By
		 * .name("incorpPlace"))).clear(); driverWait.until(
		 * ExpectedConditions.presenceOfElementLocated(By
		 * .name("incorpPlace"))).sendKeys("Toronto");
		 */
		Thread.sleep(3000);
		WebElement _element = WebDriverManager.getDriver().findElement(By.name("fileInput"));
		Actions actions = new Actions(WebDriverManager.getDriver());

		actions.moveToElement(_element).click().perform();

		/*
		 * driverWait .until(ExpectedConditions.presenceOfElementLocated(By
		 * .name("fileInput"))) .sendKeys(
		 * "C:\\Users\\cvlasceanu.PACIFICGEOTECH\\Desktop\\Claim History Report (106090) (1).pdf"
		 * );
		 */
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1")))
				.sendKeys("85 Lupus Street");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddressCity"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddressCity")))
				.sendKeys("Toronto");
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

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primaryPhoneTypeSelect"))))
				.selectByVisibleText("Home");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name=\"primary_phoneType\"]
		// > option[value=\"1\"]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneNumber"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneNumber")))
				.sendKeys("(111) 111-1111");
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondaryPhoneTypeSelect"))))
				.selectByVisibleText("Mobile");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name=\"phone2Type\"]
		// > option[value=\"2\"]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondary_phoneNumber"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("secondary_phoneNumber")))
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

		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
				.selectByVisibleText("Certificate of Status");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option.ng-binding.ng-scope")));
		element.click();
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("docTypeCode_1")));
		// element.click();

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

		// WebElement TxtBoxContent1 =
		// driver.findElement(By.cssSelector("[class='col-md-7']"));
		// String orgNum = TxtBoxContent1.getText();
		// System.out.println("Org number: " + orgNum);

	}
}
