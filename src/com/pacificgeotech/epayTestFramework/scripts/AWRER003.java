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

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class AWRER003 {
	
	private WebDriver driver;
	private String ClaimNo;
	private String pid;
	private String pin;
	private String clientNumber;
	private String SecondClaimNo;
	private String ThirdClaimNo;
	

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

		AWRRC003 awrrc003 = new AWRRC003();
		awrrc003.test();

		setClaimNo(awrrc003.getClaimNo());
		setClientNumber(awrrc003.getClientNumber());
		setPin(awrrc003.getPin());
		setPid(awrrc003.getPid());
		setSecondClaimNo(awrrc003.getSecondClaimNo());
		setThirdClaimNo(awrrc003.getThirdClaimNo());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(6000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();
		Thread.sleep(1000);
		
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[2]"))); 
		 element.click();
		 Thread.sleep(1000);
		 new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.linkText("Evaluate Recommender Outcome for report of work submission")));
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Evaluate Recommender Outcome for report of work submission"))); 
		 element.click();
		 
//		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='multiselect-parent btn-group dropdown-multiselect']"))))
//		.selectByVisibleText("Evaluate Recommender Outcome for report of work submission");
		
		Thread.sleep(6000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		element.clear();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(awrrc003.getClaimNo());

		Thread.sleep(6000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		// assign to all
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("SR Manager Mining Lands")));
		element.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(awrrc003.getClaimNo());

		Thread.sleep(4000);

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();
		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("agreementSelectId")))).selectByVisibleText("Yes");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(2500);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1000);
		
		new WebDriverWait(driver, 50)
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Task Evaluate Recommender Outcome has been completed successfully.')]")));
		
		
		
		
	}

	public String getThirdClaimNo() {
		return ThirdClaimNo;
	}

	public void setThirdClaimNo(String thirdClaimNo) {
		ThirdClaimNo = thirdClaimNo;
	}

	public String getSecondClaimNo() {
		return SecondClaimNo;
	}

	public void setSecondClaimNo(String secondClaimNo) {
		SecondClaimNo = secondClaimNo;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getClaimNo() {
		return ClaimNo;
	}

	public void setClaimNo(String claimNo) {
		ClaimNo = claimNo;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

}
