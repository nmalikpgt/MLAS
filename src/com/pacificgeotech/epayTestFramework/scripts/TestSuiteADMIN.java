package com.pacificgeotech.epayTestFramework.scripts;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pacificgeotech.epayTestFramework.core.WebDriverManager;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ADMIN1001.class, ADMIN1002.class, ADMIN1003.class, ADMIN1004.class, ADMIN2001.class,
		ADMIN2002.class, ADMIN2003.class, ADMIN2004.class, ADMIN4001.class, ADMIN4002.class, ADMIN4003.class,
		ADMIN4004.class, ADMIN4005.class, ADMIN4006.class, ADMIN5001.class, ADMIN5002.class, ADMIN5004.class,
		ADMIN5005.class, ADMIN7001.class, ADMIN7002.class, ADMIN7003.class, ADMIN7004.class, ADMIN7005.class,
		ADMIN7006.class, ADMIN7007.class, ADMIN7008.class, ADMIN7009.class, ADMIN7010.class })
public class TestSuiteADMIN {

	@Test
	public void test() throws Exception {
		WebDriver driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		List<WebElement> elements = WebDriverManager.getElements();
	}

}
