package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.CommonUtils;
import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class CM1A004 {
	
	private boolean acceptNextAlert = true;	
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
		
		
		//login
		 //driver.get("http://rat1:10281/mlas/login");
	     //   driver.navigate().to("http://rat1:10281/mlas/login");
	       // 	driver.navigate().refresh();
	        	CommonUtils.login();
    
 
        
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//aside[@id='left-panel']/nav/ul/li[3]/a/b/em"))).click();

		
		Boolean successMesage23 = (new WebDriverWait(driver, 150))
				  .until(ExpectedConditions.invisibilityOfElementLocated(By.linkText("Register Individual")));
		
	
	}

}
