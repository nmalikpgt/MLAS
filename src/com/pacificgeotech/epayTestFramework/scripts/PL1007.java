package com.pacificgeotech.epayTestFramework.scripts;

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

import com.pacificgeotech.epayTestFramework.core.CommonUtils;
import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class PL1007 {

	private String clientNumber;
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

		CommonUtils.login(); // known as LGN1002

		WebElement successMesage = (new WebDriverWait(driver, 50)).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[7]/a[2]/b")));
		driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[7]/a[2]/b"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register Client"))).click();

		// new code

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("clientTypeRdio"))).click();
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/div[2]/div/div[2]/a/span")))
				.click();

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
		Thread.sleep(1000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("province"))))
				.selectByVisibleText("ONTARIO");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name=\"province\"]
		// > option[value=\"8\"]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).sendKeys("B5G 5G5");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]"))).click();
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("primary_phoneType"))))
				.selectByVisibleText("Home");

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
		Thread.sleep(1000);

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("emailAddress")))
				.sendKeys("cvlasceanu@pacificgeotech.com");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("submitFormButton")));
		element.click();

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.elementToBeClickable(By.id("nextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Client Created Successfully')]")));

		WebElement TxtBoxContent = driver.findElement(By.cssSelector("[class='control-label-value ng-binding']"));
		String clientNum = TxtBoxContent.getText();
		System.out.println("Internal Client number: " + clientNum);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Update Client Profile")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(clientNum);
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("10003202
		// Hanks Tom")));
		// element.click();
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//section[@id='widget-grid']/div[2]/div/div[3]/a/span")));
		element.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='wid-id-1']/div/div/div[9]/div/fieldset/div/div/select"))))
						.selectByVisibleText("Yes");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"Y\"]")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.btn.btn-default")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[2]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("maapInfo.maapCompletedDate")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("maapInfo.maapCompletedDate")));
		element.sendKeys("2015 June 01");
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		Thread.sleep(1000);
		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		// element.click();
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/div[2]/div/div[3]/a/span")));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/a/span")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span")));
		element.click();

	}

}
