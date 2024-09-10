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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTests {
  private WebDriver driver;
  private WebDriverWait wait;

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
    wait = new WebDriverWait(driver, Duration.ofSeconds(20));// wait 20 minutes

    driver.get("http://localhost:3000/sign-up");
  }

  @Test
  public void testRegistration() {
    // Fill out registration form
    WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
    WebElement passwordField = driver.findElement(By.name("password"));
    WebElement repeatPasswordField = driver.findElement(By.name("repeatPassword"));
    WebElement nameField = driver.findElement(By.name("name"));
    WebElement emailField = driver.findElement(By.name("email"));
    WebElement contactNumberField = driver.findElement(By.name("contactNumber"));
    WebElement subscribeCheckbox = driver.findElement(By.name("subscribe"));
    WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

    // Enter registration details
    usernameField.sendKeys("new_user");
    passwordField.sendKeys("New@password123");
    repeatPasswordField.sendKeys("New@password123"); // Repeat password should match
    nameField.sendKeys("New User");
    emailField.sendKeys("new.user@example.com");
    contactNumberField.sendKeys("1234567890");
    subscribeCheckbox.click(); // Check subscribe checkbox
    submitButton.click();

    // Verify registration success
    WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login")));
    assertTrue(loginButton.isDisplayed(), "Login button should be displayed after registration");
  }

  @AfterEach
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
