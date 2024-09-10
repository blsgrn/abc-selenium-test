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

public class RegistrationTests {
  private WebDriver driver;

  @BeforeEach
  public void setUp() {
    // Set path to ChromeDriver executable
    System.setProperty("webdriver.chrome.driver", "chromedriver");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-extensions");
    options.addArguments("--incognito");
    options.addArguments("--disable-gpu");
    options.addArguments("--no-sandbox");
    options.addArguments("--remote-allow-origins=*");

    driver = new ChromeDriver(options);

    driver.get("http://localhost:3000/reservation");
  }

  @Test
  public void testRegistration() {
    // Navigate to sign-up page
    WebElement signUpButton = driver.findElement(By.linkText("Sign up"));
    signUpButton.click();

    // Fill out registration form
    WebElement usernameField = driver.findElement(By.name("username"));
    WebElement passwordField = driver.findElement(By.name("password"));
    WebElement roleSelect = driver.findElement(By.name("role"));
    WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

    usernameField.sendKeys("new_user");
    passwordField.sendKeys("New@password123");
    roleSelect.sendKeys("Customer");
    submitButton.click();

    // Verify registration success
    WebElement loginButton = driver.findElement(By.linkText("Login"));
    assertTrue(loginButton.isDisplayed(), "Login button should be displayed after registration");
  }

  @AfterEach
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
