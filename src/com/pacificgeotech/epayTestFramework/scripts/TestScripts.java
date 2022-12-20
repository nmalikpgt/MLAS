package com.pacificgeotech.epayTestFramework.scripts;

import com.pacificgeotech.epayTestFramework.core.CommonUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class TestScripts {

    public WebDriver driver;

    @Test
    public void Test1()
    {
        String chromePath="E://schinna//MLAS//lib//chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",chromePath);
        ChromeOptions options = new ChromeOptions();
        driver=new ChromeDriver(options);
        driver.get("www.google.com");
       /* try {
            CommonUtils.login();
        }
        catch(Exception e)
        {

        }*/
    }
}
