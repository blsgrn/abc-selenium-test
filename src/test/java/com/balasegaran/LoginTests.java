package com.balasegaran;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests {
  private WebDriver driver;

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

    driver.get("http://localhost:3000/");

  }

  @Test
  public void testLogin() {
    // Navigate to login page
    WebElement loginButton = driver.findElement(By.linkText("Login"));
    loginButton.click();

    // Fill out login form
    WebElement usernameField = driver.findElement(By.name("username"));
    WebElement passwordField = driver.findElement(By.name("password"));
    WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

    usernameField.sendKeys("emma_smith");
    passwordField.sendKeys("EmmaS#2023");
    submitButton.click();

    // Verify login
    WebElement logoutButton = driver.findElement(By.linkText("Logout"));
    assertTrue(logoutButton.isDisplayed(), "Logout button should be displayed after login");
  }

  @AfterEach
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}