package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class CM7006 {

	private String OrgclientNum;
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
		WebDriverManager.getElements();

		java.util.List<WebElement> elements = WebDriverManager.getElements();

		// login

		CM1B008 cm1b008 = new CM1B008();
		cm1b008.test();
		setOrgclientNum(cm1b008.getOrgclientNum());
		// logout
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/a/span")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/span")));
		element.click();
		// setClientNum(cm1b008.getClientNum());
		LGN1001 lgn1001 = new LGN1001();
		lgn1001.test();
		setClientNumber(lgn1001.getClientNumber());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[2]/a/span")));
		element.click();
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.pull-left.ng-binding")));
		element.click();

		LGN1002 lgn1002 = new LGN1002();
		lgn1002.test();
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[7]/a[2]/b/em")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[7]/a[2]/b/em")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manage Agents")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientselect")));
		element.sendKeys(cm1b008.getOrgclientNum());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchClientId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchClientId")));
		element.sendKeys(lgn1001.getClientNumber());
		Thread.sleep(500);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='search_client_list']")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("addAgentBtnId")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("assessmentWorkManagerId")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("checkedAll")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
		element.click();
		Thread.sleep(1500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'Agent status and permissions have been updated')]")));

	}

	public String getOrgclientNum() {
		return OrgclientNum;
	}

	public void setOrgclientNum(String orgclientNum) {
		OrgclientNum = orgclientNum;
	}

}
