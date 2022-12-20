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
@Suite.SuiteClasses({ F1001.class, F1002.class, F2001.class, F2002.class, F2005.class, F2006.class, F8001.class,
		F8002.class, F8003.class, F8004.class,

})
public class TestSuiteF {

	@Test
	public void test() throws Exception {
		WebDriver driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		List<WebElement> elements = WebDriverManager.getElements();

	}
}
