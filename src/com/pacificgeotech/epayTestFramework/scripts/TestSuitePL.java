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
@Suite.SuiteClasses({ PL1001.class, PL1002.class, PL1003.class, PL1004.class, PL1005.class, PL2001.class, PL2002.class,
		PL2003.class, PL2004.class, PL2005.class, PL3001.class, PL3002.class, PL3003.class, PL3004.class, PL3005.class,
		PL4001.class, PL4002.class, PL4003.class, PL4004.class, PL4005.class, PL4006.class, PL7001.class, PL7002.class,
		PL7003.class

})
public class TestSuitePL {

	@Test
	public void test() throws Exception {
		WebDriver driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		List<WebElement> elements = WebDriverManager.getElements();

	}
}
