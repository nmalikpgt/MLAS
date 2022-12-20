package com.pacificgeotech.epayTestFramework.scripts;

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

public class AWRRC001 {

	private WebDriver driver;
	private String ClaimNo;
	private String pid;
	private String pin;
	private String clientNumber;
	private String SecondClaimNo;
	private String ThirdClaimNo;

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
		AWR11001 awr11001 = new AWR11001();
		awr11001.test();
		setClaimNo(awr11001.getClaimNo());
		setPin(awr11001.getPin());
		setPid(awr11001.getPid());
		setClientNumber(awr11001.getClientNumber());
		setSecondClaimNo(awr11001.getSecondClaimNo());
		setThirdClaimNo(awr11001.getThirdClaimNo());


		LGN1002 lgn1002 = new LGN1002();
		lgn1002.test();
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(6000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(6000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(6000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(awr11001.getClaimNo());

		Thread.sleep(6000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		// assign to all
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Geoscience Assessor")));
		element.click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(6000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(awr11001.getClaimNo());

		Thread.sleep(6000);

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();
		Thread.sleep(1000);
		 new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("generateDocTypeSelectionId")))).selectByVisibleText("Approval Letter");

		 
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("reviewOutcomeRequiresInspectionIndInputId"))); 
		 element.click();
		 
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("reviewOutcomeRequiresNoticeIndInputId"))); 
		 element.click();
		 
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[5]"))); 
		 element.click();

		 
		 
		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("reviewOutcomeInspectionResultsId"))); 
		 element.sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis");
		 
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		 element.click();
		 Thread.sleep(1500);
		    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		 element.click();
		 Thread.sleep(1500);
//		 element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button']"))); 
//		 element.click();

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

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

}
