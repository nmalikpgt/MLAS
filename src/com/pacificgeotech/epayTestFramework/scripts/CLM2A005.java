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

public class CLM2A005 {

	

	private static final Object Webelement = null;
	private String clientNumber;
	private String pin;
	private String pid;  
	//private static final String ENCRYPTION_KEY = "MLASDev2k16!";
	//private static final String ENCRYPTION_SALT = "thisSaltIsMaxCharactersForASalt!";
	
	private WebDriver driver;
	
	@After
	public void tearDown(){
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
		
	new LGN1001().test();
	
	  
    Thread.sleep(2000);
    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By
			.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[8]/a[2]/span")));
    element.click();
    
    driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Submit Notice of Claim Abandonment"))).click();
    
    //partial abandonment
    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By
			.xpath("/html/body/div[2]/div[3]/div/section/div/form/article[2]/div/div/div[1]/fieldset/div/div[2]/label[2]/input")));
    element.click();
    
	((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();
	
	Thread.sleep(1000);
	String bodyText = driver.findElement(By.tagName("body")).getText();
	org.junit.Assert.assertTrue(bodyText.contains("Claim ID is required"));
		
	}
	
}
