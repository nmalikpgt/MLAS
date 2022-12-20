package com.pacificgeotech.epayTestFramework.scripts;

import java.io.File;
import java.net.URISyntaxException;

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

import junit.framework.Assert;

public class CLM1A007 {

	private String clientNumber;
	private String pid;
	private String pin;
	private String claimId;
	private WebDriver driver;
	private String OrgclientNum; 

	@After
	public void tearDown() {
		driver.getCurrentUrl();
	}

	@AfterClass
	public static void afterClass() {
		WebDriverManager.instance = null;
	}

	public String getOrgclientNum() {
		return OrgclientNum;
	}

	public void setOrgclientNum(String orgclientNum) {
		OrgclientNum = orgclientNum;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test() throws Exception {

		driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();

		MP5003 mp5003 = new MP5003();
		mp5003.test();
		setClientNumber(mp5003.getClientNumber());
		setPid(mp5003.getPid());
		setPin(mp5003.getPin());
		setClaimId(mp5003.getClaimId());
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Client Management")));
		element.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register Client"))).click();
		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//input[@name='clientTypeRdio' and @value='org']")))
				.click();

		// driverWait.until(
		// ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/div[2]/div/div[3]/a/span")))
		// .click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Next"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")))
				.sendKeys(getClientNumber());
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Next')]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgName"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgName"))).sendKeys("EMPRESS");

		// element =
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"37\"]")));
		// element.click();

		Thread.sleep(1000);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddress1")))
				.sendKeys("89 TOTEM STREET");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddressCity"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingAddressCity")))
				.sendKeys("Toronto");
		Thread.sleep(5000);
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingCountry"))))
				.selectByVisibleText("CANADA");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"37\"]"))).click();
		// Thread.sleep(10000);
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mailingCountry"))).click();
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"string:CA\"]"))).click();
		Thread.sleep(10000);
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("province"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"string:ON\"]")))
				.click();

		// new
		// Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("province")))).selectByVisibleText("ONTARIO");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name=\"province\"]
		// > option[value=\"8\"]"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("postalCode"))).sendKeys("B5H 8H5");

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("primaryPhoneTypeSelect"))))
		.selectByVisibleText("Home");
new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.name("primary_phoneNumber")));

element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
		"[class='form-control input-sm ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required ng-valid-phone-vdr ng-valid-maxlength']")));
element.clear();
element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
		"[class='form-control input-sm ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required ng-valid-phone-vdr ng-valid-maxlength']")));
element.sendKeys("(111) 111-1111");
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

//		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("uploadFileId")));
//		element.clear();
//		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("uploadFileId")));
//		element.sendKeys("C:\\Users\\cvlasceanu\\Desktop\\Claim History Report (106090) (1).pdf");

		// Upload
		
		  Object file = null; try { file = new
		  File(CM2002.class.getClassLoader().getResource("Flaring.pdf").toURI()
		  ); } catch (URISyntaxException e) { e.printStackTrace(); }
		  Assert.assertTrue(((File) file).exists()); WebElement browseButton =
		  driver.findElement(By.name("fileInput"));
		  browseButton.sendKeys(((File) file).getAbsolutePath());
		 
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.name("orgType"))).click();
		Thread.sleep(500);

		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("orgType"))))
				.selectByVisibleText("Incorporated Company");
		// driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"1\"]"))).click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpNumber"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpNumber"))).sendKeys("122122");
		new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.name("incorpPlace")));
		new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("incorpPlace"))))
				.selectByVisibleText("CANADA");

		new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("docTypeCode"))))
				.selectByVisibleText("Certificate of Status");
		element = driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option.ng-binding.ng-scope")));
		element.click();

		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		Thread.sleep(1000);
		element = driverWait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn-label  ng-binding']")));
		element.click();
		Thread.sleep(1000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn-label  ng-binding']")));
		element = driverWait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn-label  ng-binding']")));
		element.click();

		Thread.sleep(10000);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		/*new WebDriverWait(driver, 50).until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[class='btn btn-labeled bg-color-greenDark txt-color-white ng-binding']")));
		element = driverWait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[class='btn btn-labeled bg-color-greenDark txt-color-white ng-binding']")));
		element.click();*/
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Client Created Successfully.Please see your email for further information')]")));

		new WebDriverWait(driver, 50).until(ExpectedConditions
				.presenceOfElementLocated(By.id("organizationNumberId")));
		// Collect org number
		/*WebElement TxtBoxContent = driver.findElement(By.id("organizationNumberId"));
		String OrgclientNum = TxtBoxContent.getText();
		System.out.println("Organization Client number: " + OrgclientNum);*/
		
		
		String OrgclientNum;
		OrgclientNum = driver.findElement(By.id("organizationNumberId")).getText();
		setOrgclientNum(OrgclientNum);
		System.out.println("Organization Client number: " + getOrgclientNum());

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/a/span")));
		element.click();
		Thread.sleep(1000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//header[@id='header']/cata-header/div/div[2]/ul[2]/li[3]/ul/li[3]/a/div")));
		element.click();
		Thread.sleep(1000);

		String[] loginURL = LGN1001.getLoginUrl();
		driver.get(loginURL[0]); // for local Test
		LGN1001 lgn1001 = new LGN1001();

		if ("localTest".equals(loginURL[1])) {

			driver.navigate().refresh();
			driver.navigate().to(loginURL[0]);

			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pid")));
			element.sendKeys(mp5003.getPid());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

			Thread.sleep(1500);
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("pin")));
			element.sendKeys(mp5003.getPin());
			element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginSubmit")));
			element.click();

		} else {

			lgn1001.getLoginInfoExternalRemote(loginURL[1], loginURL[0]);
		}

		Thread.sleep(2000);
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.linkText("Claim Management")));
		element.click();

		driverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Initiate Transfer of Mining Claim(s)")))
				.click();

		Thread.sleep(2000);

		// seller
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput")));
		element.clear();
		element.sendKeys(getClientNumber());

		element = driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='search_client_list col-md-12']")));
		element.click();

		// buyer
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/section/div/form/section[1]/div/div/div/fieldset/div[2]/div[1]/div[1]/div/input")));
		element.clear();
		element.sendKeys(OrgclientNum);

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/div[2]/div[3]/section/div/form/section[1]/div/div/div/fieldset/div[2]/div[1]/div[1]/div/div[2]/div")));
		element.click();

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
		element.sendKeys(getClaimId());

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();

		Thread.sleep(1500);

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("percentageInputId_0")));
		element.clear();
		element.sendKeys("100");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnGoNextBtnId"))).click();
		Thread.sleep(1500);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnConfirmBtnId"))).click();

	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
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

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

}
