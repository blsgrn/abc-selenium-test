package com.balasegaran;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FirstSeleniumTest {

  WebDriver driver;

  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "chromedriver");

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-extensions");
    options.addArguments("--incognito");
    options.addArguments("--disable-gpu");
    options.addArguments("--no-sandbox");
    options.addArguments("--remote-allow-origins=*");

    driver = new ChromeDriver(options);
  }

  @Test
  public void testGoogleSearch() {
    driver.get("https://www.google.com");
    String title = driver.getTitle();
    assert title.equals("Google");
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

}
