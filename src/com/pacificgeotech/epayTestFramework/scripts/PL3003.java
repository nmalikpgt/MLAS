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

public class PL3003 {

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

		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();
		String clientNumber = lgn1001.getClientNumber();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Dashboard')]")))
				.getText();

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
				.click();
		Thread.sleep(2000);

		WebElement _element1 = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out")));
		_element1.click();

		new LGN1002().test();

		Thread.sleep(1000);

		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.linkText("Admin")));

		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Admin"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update Prospector Licence"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(lgn1001.getClientNumber());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();
		Thread.sleep(2000);

		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Licence not found')]")))
				.getText();

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

// old
/*
 * element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.linkText("Register Here"))); element.click();
 * Thread.sleep(10000); new Select( driver.findElement(By
 * .xpath("//div[@id='wid-id-1']/div/div/div[3]/fieldset/div/div/div/select")))
 * .selectByVisibleText("Mrs."); element = driverWait
 * .until(ExpectedConditions.presenceOfElementLocated(By
 * .cssSelector("option[value=\"3\"]"))); element.click();
 * 
 * element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.name("lastName"))); element.sendKeys("Jay");
 * 
 * element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.name("firstName"))); element.sendKeys("Smith");
 * 
 * element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.name("secondName"))); element.sendKeys("A");
 * 
 * element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.name("thirdName"))); element.sendKeys("B");
 * 
 * element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.name("prefName"))); element.sendKeys("Smith");
 * element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.name("declarationChkbox"))); element.click();
 * 
 * element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.name("addressLine1"))); element.sendKeys(
 * "79 Uralia");
 * 
 * element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.name("city"))); element.sendKeys("Toronto");
 * Thread.sleep(4000); new Select(driver.findElement(By.name("country")))
 * .selectByVisibleText("CANADA"); element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By .cssSelector("option[value=\"37\"]")));
 * element.click(); element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By .cssSelector("option[value=\"37\"]")));
 * element.click(); Thread.sleep(4000); new
 * Select(driver.findElement(By.name("province")))
 * .selectByVisibleText("ONTARIO"); element = driverWait
 * .until(ExpectedConditions.presenceOfElementLocated(By .cssSelector(
 * "select[name=\"province\"] > option[value=\"8\"]"))); element.click();
 * 
 * element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.name("postalCode"))); element.sendKeys("V5G 2H2"
 * ); element = driverWait.until(ExpectedConditions .presenceOfElementLocated(By
 * .xpath("(//input[@type='checkbox'])[2]"))); element.click();
 * Thread.sleep(4000); new
 * Select(driver.findElement(By.name("primary_phoneType")))
 * .selectByVisibleText("Home"); element = driverWait
 * .until(ExpectedConditions.presenceOfElementLocated(By .cssSelector(
 * "select[name=\"primary_phoneType\"] > option[value=\"0\"]")));
 * element.click();
 * 
 * element = driverWait .until(ExpectedConditions.presenceOfElementLocated(By
 * .xpath("(//input[@value=''])[18]"))); element.sendKeys("1");
 * 
 * element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.id("primPhone"))); element.sendKeys(
 * "(111) 111-1111"); // Thread.sleep(4000); new Select( driver.findElement(By
 * .xpath("//div[@id='wid-id-1']/div/div/div[15]/div/fieldset[2]/div/div/select"
 * ))) .selectByVisibleText("Mobile"); element = driverWait
 * .until(ExpectedConditions.presenceOfElementLocated(By
 * .xpath("(//option[@value='1'])[7]"))); element.click();
 * 
 * element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.name("secondary_countryCode")));
 * element.sendKeys("1");
 * 
 * element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.name("secondary_phoneNumber")));
 * element.sendKeys("(111) 111-1111");
 * 
 * element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.name("fax_countryCode")));
 * element.sendKeys("1");
 * 
 * element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.name("fax_phoneNumber"))); element.sendKeys(
 * "(111) 111-1111");
 * 
 * element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.id("emailAddress")));
 * element.sendKeys("gcontor@pacificgeotech.com"); element = driverWait
 * .until(ExpectedConditions.presenceOfElementLocated(By
 * .xpath("//button[@type='submit']"))); element.click();
 * 
 * driverWait .until(ExpectedConditions.presenceOfElementLocated(By
 * .linkText("Next"))).click();
 * 
 * Thread.sleep(2000); ((org.openqa.selenium.JavascriptExecutor) driver)
 * .executeScript("scroll(0, -250);"); Thread.sleep(2000);
 * clientNumber=driver.findElements(By.className("control-label-value")).get(1).
 * getText(); System.out.println("clientNumber" + clientNumber);
 * 
 * Thread.sleep(2000);
 * 
 * WebElement _element = driverWait.until(ExpectedConditions
 * .presenceOfElementLocated(By.name("sign-out")));
 * 
 * Actions actions = new Actions(driver);
 * 
 * actions.moveToElement(_element).click().perform(); Thread.sleep(2000); new
 * LGN1002().test();
 * 
 * Thread.sleep(2000);
 * driverWait.until(ExpectedConditions.presenceOfElementLocated(By
 * .cssSelector("em.fa.fa-plus-square-o")));
 * 
 * driver.findElements(By.cssSelector("em.fa.fa-plus-square-o")).get(3)
 * .click(); driverWait.until( ExpectedConditions.presenceOfElementLocated(By
 * .linkText("Client"))).click();
 * 
 * driverWait.until( ExpectedConditions.presenceOfElementLocated(By .linkText(
 * "Revoke or Suspend Prospector's Licence"))) .click(); driverWait.until(
 * ExpectedConditions.presenceOfElementLocated(By
 * .xpath("//input[@type='text']"))).clear(); driverWait.until(
 * ExpectedConditions.presenceOfElementLocated(By
 * .xpath("//input[@type='text']"))) .sendKeys(clientNumber); driverWait
 * .until(ExpectedConditions.presenceOfElementLocated(By
 * .linkText("Next"))).click(); Thread.sleep(2000); driverWait
 * .until(ExpectedConditions.presenceOfElementLocated(By
 * .linkText("Next"))).click(); driverWait
 * .until(ExpectedConditions.presenceOfElementLocated(By .xpath(
 * "//*[contains(text(), 'Licence Information not found!')]"))) .click();
 */
