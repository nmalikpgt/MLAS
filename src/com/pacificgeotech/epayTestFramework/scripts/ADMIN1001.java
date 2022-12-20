package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;


import com.pacificgeotech.epayTestFramework.core.CommonUtils;
import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class ADMIN1001 {

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

         driver = WebDriverManager.initDriver();
		// --- driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
	    WebElement element = WebDriverManager.getElement();
		WebDriverManager.getElements();

		// login
		CommonUtils.login();

		// driver.get("http://cheetah-vm2:10281/mlas/login#/mlasDashboard.jsf");http:

	//	element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("appFrmId:loginImgId")));
		//element.click();

		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"left-panel\"]/navigation-widget/div/nav/ul/li[16]/a[2]")));
		element.click();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Add to Bulletin Board")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")));
		element.sendKeys("100000");
		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("10000004 William Love")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='text'])[2]")));
		element.sendKeys("mr");
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.id("noticeinputid")));
		element.sendKeys(
				"ewuferiuherugheriugherugierhbiugerbugibervuierbviuerbvuierbuierbviuerbvueribvuerbvuerbviuerbveurbveruvbuerivberuivbeurivbuervbuervberuvberuvberuvberuvberuvberuvberuvbervubervuerbvuerbvuervbuervburvburvurbruverbvuebrvubervuebruvuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.id("nextBtnId")));
		element.click();
		Thread.sleep(4000);
		element = driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.id("abbLnkId")));
		element.click();

	}

}
