package com.pacificgeotech.epayTestFramework.core;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

/**
 * @author scheong
 * 
 *         SantaClaus: a combination of a singleton and a factory. There's only
 *         one and he gives presents!
 * 
 *         Use a singleton to manage access to global objects. Reusing the same
 *         WebElement will minimize garbage collector usage.
 */
public  final class WebDriverManager {

	public static WebDriverManager instance = null;

	// web driver objects
	// --- private static WebDriver driver = null;

	private static WebDriver driver = null;

	private static WebDriverWait driverWait = null;
	private static WebElement element = null;
	private static List<WebElement> elements = null;
	private static Select select = null;
	//private static WebDriver driver1 = null;

	/**
	 * private ctor - initialize everything
	 */
	private WebDriverManager() {
		driver = initDriver();
		driverWait = (new WebDriverWait(driver, Config.TIMEOUT));
		element = null;
		elements = null;
		select = null;
	}

	public static WebDriverManager getInstance() {
		if (instance == null)
		{
			instance = new WebDriverManager();

		}
		return instance;
	}

	public static WebDriver initDriver() {

		if (Config.SELECTED_DRIVER.equals(Constants.CHROME_DRIVER)) {

			File file = new File("lib/chromedriver.exe");
			//File file = new File("E:/schinna/MLAS/lib/chromedriverOLD.exe");
			System.setProperty("webdriver.chrome.driver",
					file.getAbsolutePath());
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();

			options.addArguments("test-type");
			//--- options.addArguments("--start-maximized");
			//---options.addArguments("--disable-extensions");
			options.addArguments("disable-infobars");
			//---options.addArguments("--ignore-certificate-errors");
			options.addArguments("'--start-maximized", "--window-size=1920,1080");
			options.addArguments("--incognito");

			capabilities.setCapability("chrome.binary", file.getAbsolutePath());
			// ---capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "normal"); // ---
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
			// ---driver = new ChromeDriver(capabilities);
			driver = new ChromeDriver(capabilities);

			/*String chromePath="E:\\schinna\\MLAS\\lib\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver",chromePath);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("chrome", true);
			driver=new ChromeDriver(capabilities);
*/


			// ---driver=new ChromeDriver();

		} else if (Config.SELECTED_DRIVER.equals(Constants.GHOST_DRIVER)) {

			File file = new File("lib/phantomjs.exe");

			Capabilities caps = new DesiredCapabilities();

			((DesiredCapabilities) caps).setJavascriptEnabled(true);

			((DesiredCapabilities) caps).setCapability("takesScreenshot", true);

			((DesiredCapabilities) caps).setCapability(

			PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,

			file.getAbsolutePath()

			);

			driver = new PhantomJSDriver(caps);

		} else if (Config.SELECTED_DRIVER.equals(Constants.FIREFOX_DRIVER)) {


			ProfilesIni profile = new ProfilesIni();

			FirefoxProfile myprofile = profile.getProfile("profileToolsQA");
			System.setProperty("webdriver.gecko.driver","E:\\geckodriver.exe");
			driver = new FirefoxDriver(myprofile);

		}

		return driver;

	}

	public static WebDriver getDriver() {
		return getInstance().driver;
	}

	public static WebDriverWait getDriverWait() {
		return getInstance().driverWait;
	}

	public static WebElement getElement() {
		return getInstance().element;
	}

	public static List<WebElement> getElements() {
		return getInstance().elements;
	}

	public static Select getSelect() {
		return getInstance().select;
	}

}
