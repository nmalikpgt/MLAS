package com.pacificgeotech.epayTestFramework.scripts;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;
import com.pacificgeotech.epayTestFramework.dbConnection.DbConnection;

//@RunWith(ConcurrentJunitRunner.class)
//@Concurrent(threads = 2)

public class CellAquisition50 {private String clientNumber;
private String transactionId;
private String claimId;
private String pin;
private String pid;
private WebDriver driver;

//@After
//public void tearDown() {
//	driver.getCurrentUrl();
//}
//
//@AfterClass
//public static void afterClass() {
//	WebDriverManager.instance = null;
//}

@Test
public void test() throws Exception {
	driver = WebDriverManager.getDriver();
	WebDriverWait driverWait = WebDriverManager.getDriverWait();
	WebElement element = WebDriverManager.getElement();
	WebDriverManager.getElements();
	
	PL1001 pl1001 = new PL1001();
	pl1001.test();
	setClientNumber(pl1001.getClientNumber());

	setPin(pl1001.getPin());
	setPid(pl1001.getPid());
	Thread.sleep(1500);

	Thread.sleep(4000);

	//String cellKeys = "43C10J177, 43C10J240, 43C10I201, 43C10I143, 43C10I164, 43C10I205, 43C10I145, 43C10I146, 43C10J197, 43C10J219, 43C10J180, 43C10I181, 43C10I203, 43C10I185, 43C10I165, 43C10I202, 43C10I225, 43C10I206, 43C10J157, 43C10J238, 43C10J198, 43C10J178, 43C10J158, 43C10J159, 43C10J220, 43C10J200, 43C10J160, 43C10I141, 43C10I222, 43C10I183, 43C10I204, 43C10J218, 43C10I163, 43C10I226, 43C10J237, 43C10J239, 43C10I142, 43C10I144, 43C10J217, 43C10I221, 43C10I186, 43C10J199, 43C10J179, 43C10I161, 43C10I182, 43C10I162, 43C10I223, 43C10I224, 43C10I184, 43C10I166";
	String cellKeys = "43B03C239,43B03C238,43B03C219,43B03C218";
	
	updateCellgridTable(cellKeys);
	updateCellLockTable(cellKeys);

	driverWait.until(
			ExpectedConditions.presenceOfElementLocated(By.linkText("Claim Acquisition")))
			.click();

	driverWait.until(
			ExpectedConditions.presenceOfElementLocated(By.linkText("Register a Mining Claim")))
			.click();

//	Thread.sleep(1000);
//	new Select(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("regMethodId"))))
//			.selectByVisibleText("Input or Generate Cell IDs");

	Thread.sleep(2000);
	new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
			"//*[contains(text(), 'The registration of a mining claim or the acquisition of any right or interest in a mining claim by any person does not confer upon that person')]")));

	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

	Thread.sleep(1000);
	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("declarationChkbox"))).click();

	Thread.sleep(1000);
	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nextBtnId"))).click();

	Thread.sleep(1000);
	element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectedGeoMapIds")));
	element = driver.findElement(By.id("selectedGeoMapIds"));
	
	element.sendKeys(cellKeys);
	
	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
	Thread.sleep(1500);
	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
	new WebDriverWait(driver, 50)
	.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoIdendityButtonId")));
	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoIdendityButtonId"))).click();
//	new WebDriverWait(driver, 50)
//			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='MCMC']")));
//
//	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='MCMC']"))).click();

	new WebDriverWait(driver, 50)
			.until(ExpectedConditions.presenceOfElementLocated(By
					.id("owneridInput_0")))
			.sendKeys(this.clientNumber);

	element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name(
			"percentage_0")));
	element.sendKeys("100");
	Thread.sleep(1000);
	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
	// To Summary page
	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoSummaryButtonId"))).click();
	// To payment
	Thread.sleep(1000);
	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");
	Thread.sleep(1500);
	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Confirm"))).click();
	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("shoppingCartNextId"))).click();

	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='onlinePaymentSltId']")))
			.click();
	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2300)");

	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showSelectedPaymentId"))).click();

	makePayment(element, driverWait, driver);

	element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("showdetailsbtnid")));
	element.click();

	Thread.sleep(2000);
	new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
			"//*[contains(text(), 'Claims have been registered successfully. You have 60 days to notify any surface rights owners which the PRO identifies as being required for any or all of your claims. If you fail to notify any required surface rights owners, your claims will automatically cancel on the 61st day.')]")));

	transactionId = driver
			.findElement(
					By.xpath("//*[@id='widget-grid']/div[1]/confirmation-message/div/div/table/tbody/tr[2]/td[3]"))
			.getText();

	System.out.println("Printing claim ids...");
	
	Thread.sleep(1000);
	
	int claimCount = Arrays.asList(cellKeys).size();
	
	System.out.println("Acquired " + claimCount + " claims");
	
	for (int i = 0; i < claimCount; i++) {
		System.out.println("Claim ID[" + (i + 1) + "]: " + driver.findElement(By.id("claim_id_" + i)).getText());
	}
	Thread.sleep(1000);
	

	// search if claim is active pending
	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
	driverWait.until(ExpectedConditions.presenceOfElementLocated(
			By.xpath("//aside[@id='left-panel']/navigation-widget/div/nav/ul/li[2]/a[2]"))).click();

	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Search Claims"))).click();

	Thread.sleep(2000);	
	driverWait
			.until(ExpectedConditions
					.presenceOfElementLocated(By.cssSelector("li.usr-block > a.dropdown-toggle > span.caret")))
			.click();

	Thread.sleep(2000);
	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("sign-out"))).click();

}

public void updateCellgridTable(String cellKeysCsv) throws ClassNotFoundException, SQLException {
	Connection con = DbConnection.getConnection();
	con.setAutoCommit(false);
	Statement stmt = con.createStatement();
	//String sql = ("UPDATE MAM_SPATIAL.MTA_NTS_GRID_CELL SET CELL_STATUS_CODE = 'A', CELL_REASON_CODE = 'N', CELL_REOPENING_DATE = CELL_REOPENING_DATE  - 1000  WHERE CELL_KEY_ID IN ('43C10J177', '43C10J240', '43C10I201', '43C10I143', '43C10I164', '43C10I205', '43C10I145', '43C10I146', '43C10J197', '43C10J219', '43C10J180', '43C10I181', '43C10I203', '43C10I185', '43C10I165', '43C10I202', '43C10I225', '43C10I206', '43C10J157', '43C10J238', '43C10J198', '43C10J178', '43C10J158', '43C10J159', '43C10J220', '43C10J200', '43C10J160', '43C10I141', '43C10I222', '43C10I183', '43C10I204', '43C10J218', '43C10I163', '43C10I226', '43C10J237', '43C10J239', '43C10I142', '43C10I144', '43C10J217', '43C10I221', '43C10I186', '43C10J199', '43C10J179', '43C10I161', '43C10I182', '43C10I162', '43C10I223', '43C10I224', '43C10I184', '43C10I166')");
	
	String wrappedCellKeys = wrapInApostrophes(cellKeysCsv);
	
	String sql = ("UPDATE MAM_SPATIAL.MTA_NTS_GRID_CELL SET CELL_STATUS_CODE = 'A', CELL_REASON_CODE = 'N', CELL_REOPENING_DATE = CELL_REOPENING_DATE  - 1000  WHERE CELL_KEY_ID IN (" + wrappedCellKeys + ")");
	stmt.executeUpdate(sql);
	con.commit();
	con.close();
}


private List<String> csvToListTrimSpaces(final String srcStr) {
	if (srcStr == null || srcStr.isEmpty()) {
		return null;
	}
	String[] tmpArr = csvToArray(srcStr);
	for (int i = 0; i < tmpArr.length; i++) {
		tmpArr[i] = tmpArr[i] == null ? null : tmpArr[i].trim();
	}
	return Arrays.asList(tmpArr);
}

private String[] csvToArray(final String srcStr) {
	if (srcStr == null) {
		return null;
	}

	return srcStr.split(",");

}

private String toCsvString(final List<String> stringList,
						   final String wrapWith) {
	
	if ((stringList == null) || (stringList.size() == 0)) {
		return null;
	}
	StringBuffer sb = new StringBuffer(wrapWith
			+ String.valueOf(stringList.get(0)) + wrapWith);
	for (int i = 1; i < stringList.size(); i++) {
		sb.append(", ");
		sb.append(wrapWith + String.valueOf(stringList.get(i)) + wrapWith);
	}
	
	return sb.toString();
}

private String wrapInApostrophes(String csvString) {
	List<String> list = csvToListTrimSpaces(csvString);
	return toCsvString(list, "'");
}

public void updateCellLockTable(String cellKeysCsv) throws ClassNotFoundException, SQLException {
	Connection con = DbConnection.getConnection();
	con.setAutoCommit(false);
	Statement stmt = con.createStatement();
	
	String wrappedCellKeys = wrapInApostrophes(cellKeysCsv);

	//String sql = ("DELETE FROM MAM.MTA_GRID_LOCK_STATUS WHERE CELL_KEY_ID IN ('43C10J177', '43C10J240', '43C10I201', '43C10I143', '43C10I164', '43C10I205', '43C10I145', '43C10I146', '43C10J197', '43C10J219', '43C10J180', '43C10I181', '43C10I203', '43C10I185', '43C10I165', '43C10I202', '43C10I225', '43C10I206', '43C10J157', '43C10J238', '43C10J198', '43C10J178', '43C10J158', '43C10J159', '43C10J220', '43C10J200', '43C10J160', '43C10I141', '43C10I222', '43C10I183', '43C10I204', '43C10J218', '43C10I163', '43C10I226', '43C10J237', '43C10J239', '43C10I142', '43C10I144', '43C10J217', '43C10I221', '43C10I186', '43C10J199', '43C10J179', '43C10I161', '43C10I182', '43C10I162', '43C10I223', '43C10I224', '43C10I184', '43C10I166')");
	String sql = ("DELETE FROM MAM.MTA_GRID_LOCK_STATUS WHERE CELL_KEY_ID IN (" + wrappedCellKeys + ")");
	stmt.executeQuery(sql);
	con.commit();
	con.close();
}

public void makePayment(WebElement element, WebDriverWait driverWait, WebDriver driver) {
	element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("visa")));
	element.click();
	element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("submit")));
	element.click();

	element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardOwner")));
	element.clear();
	element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardOwner")));
	element.sendKeys("test");
	element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardNumber")));
	element.clear();
	element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardNumber")));
	element.sendKeys("4030000010001234");
	new Select(element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnExpYear"))))
			.selectByVisibleText("2020");
	element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardCvd")));
	element.clear();
	element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("trnCardCvd")));
	element.sendKeys("123");
	element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("submitButton")));
	element.click();

	element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("cancel")));
	element.click();
	new WebDriverWait(driver, 50).until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nextToCfmPgIndexBtnId']/span")));
	element = driverWait
			.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='nextToCfmPgIndexBtnId']/span")));
	element.click();

}

public String getClientNumber() {
	return clientNumber;
}

public void setClientNumber(String clientNumber) {
	this.clientNumber = clientNumber;
}

public String getClaimId() {
	return this.claimId;
}

public void setClaimId(String claimId) {
	this.claimId = claimId;
}

public String getPin() {
	return pin;
}

public void setPin(String pin) {
	this.pin = pin;
}

public String getPid() {
	return pid;
}

public void setPid(String pid) {
	this.pid = pid;
}

@Test
public void test1() throws Exception {
	test();
}

@Test
public void test2() throws Exception {
	test();
}

@Test
public void test3() throws Exception {
	test();
}
/*
@Test
public void test4() throws Exception {
	test();
}

@Test
public void test5() throws Exception {
	test();*/


}
