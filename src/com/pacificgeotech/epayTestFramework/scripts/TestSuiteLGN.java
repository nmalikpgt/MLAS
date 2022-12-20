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
@Suite.SuiteClasses({ LGN1001.class, LGN1002.class, LGN1003.class, LGN1004.class, LGN1005.class, LGN1006.class,
		LGN1007.class, LGN1008.class, LGN1009.class, LGN2001.class,

})
public class TestSuiteLGN {

	@Test
	public void test() throws Exception {
		WebDriver driver = WebDriverManager.getDriver();
		WebDriverWait driverWait = WebDriverManager.getDriverWait();
		WebElement element = WebDriverManager.getElement();
		List<WebElement> elements = WebDriverManager.getElements();

	}
}
