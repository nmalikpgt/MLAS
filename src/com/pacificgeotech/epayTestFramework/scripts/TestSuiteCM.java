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
@Suite.SuiteClasses({ CM10001.class, CM10002.class, CM10003.class, CM10A001.class, CM10A002.class, CM10B001.class,
		CM10B002.class, CM11001.class, CM11002.class, CM1A001.class, CM1A002.class, CM1A003.class, CM1A004.class,
		CM1B001.class, CM1B002.class, CM1B003.class, CM1B004.class, CM1B005.class, CM2001.class, CM2002.class,
		CM2003.class, CM2004.class, CM2005.class, CM3001.class, CM3002.class, CM3003.class, CM3004.class, CM3005.class,
		CM3006.class, CM5001.class, CM5002.class, CM5003.class, CM5004.class, CM7001.class, CM7002.class, CM7003.class,
		CM7004.class, CM7005.class,

})
public class TestSuiteCM {

	@Test
	public void test() throws Exception {
		WebDriver driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		List<WebElement> elements = WebDriverManager.getElements();

	}
}
