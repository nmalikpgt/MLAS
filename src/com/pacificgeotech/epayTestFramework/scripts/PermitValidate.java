package com.pacificgeotech.epayTestFramework.scripts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

public class PermitValidate {

	
	  @After public void tearDown() { driver.getCurrentUrl(); }
	  
	  @AfterClass public static void afterClass() { WebDriverManager.instance =
	  null; }
	 

	private WebDriver driver;
	private String PermitId;

	@Test
	public void test() throws Exception {
		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		Permit permit = new Permit();
		permit.test();
		setPermitId(getPermitId());

		new LGN1002().test();

		// group task
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(
				By.linkText("Group Tasks")));

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Group Tasks")));
		element.click();

		Thread.sleep(6000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='checkbox']"))).click();

		Thread.sleep(6000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(permit.getPermitId());

		Thread.sleep(4000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenu1")));
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("System Administrator")));
		element.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).clear();

		Thread.sleep(4000);
		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Reference Number']")));
		driver.findElement(By.xpath("//input[@placeholder='Reference Number']")).sendKeys(permit.getPermitId());

		Thread.sleep(4000);
		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div[2]/div[3]/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/div/a[1]")))
				.click();
		Thread.sleep(4000);
		// ((org.openqa.selenium.JavascriptExecutor)
		// driver).executeScript("window.scrollTo(0, 3300)");
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("regionSlctId")))).selectByVisibleText("North-east");
		Thread.sleep(1000);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.name("submissionMeetInitRequirementInd")));
		element.click();

		//today
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 0);
		Date today = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String issueDate = dateFormat.format(today);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("issueDateInTxtId")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("issueDateInTxtId")));
		element.sendKeys(issueDate);
			
		//tomorrow
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_YEAR, +1);
		Date today1 = calendar1.getTime();
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		String expDate = dateFormat1.format(today1);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("expiryDateInTxt")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("expiryDateInTxt")));
		element.sendKeys(expDate);
				
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("comment")));
		element.clear();
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("comment")));
		element.sendKeys(
				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. N");
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();
		Thread.sleep(1500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(
				By.id("bnGoNextBtnId")));
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId")));
		element.click();

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(text(), 'This submission has been successfully validated')]")));

	}

	public String getPermitId() {
		return PermitId;
	}

	public void setPermitId(String permitId) {
		PermitId = permitId;
	}
}
