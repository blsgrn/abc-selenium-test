package com.balasegaran;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTests {
  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeEach
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "chromedriver");

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-extensions");
    options.addArguments("--incognito");
    options.addArguments("--disable-gpu");
    options.addArguments("--no-sandbox");
    options.addArguments("--remote-allow-origins=*");

    driver = new ChromeDriver(options);
    wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait

    driver.get("http://localhost:3000/");
  }

  @Test
  public void testLogin() {
    // Wait for and click the login button
    WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-accent")));
    loginButton.click();

    // Wait for and fill out the login form
    WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
    WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
    WebElement submitButton = wait
        .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));

    usernameField.sendKeys("emma_smith"); // test username
    passwordField.sendKeys("EmmaS#2023"); // test password
    submitButton.click();

    // Verify navigation by checking the URL
    String expectedUrl = "http://localhost:3000/staff";
    wait.until(ExpectedConditions.urlToBe(expectedUrl));
  }

  @AfterEach
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
