package com.balasegaran;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    wait = new WebDriverWait(driver, Duration.ofSeconds(20));// wait 20 seconds

    driver.get("http://localhost:3000/sign-up");
  }

  @Test
  public void testRegistration() throws InterruptedException {
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
    repeatPasswordField.sendKeys("New@password123");
    nameField.sendKeys("New User");
    emailField.sendKeys("new.user@example.com");
    contactNumberField.sendKeys("1234567890");
    subscribeCheckbox.click();
    submitButton.click();

    // Handle alert
    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    String alertText = alert.getText();
    assertEquals("Registration successful!", alertText, "Alert text should confirm successful registration");

    alert.accept();

    // Wait for 10 seconds
    Thread.sleep(10000); // 10000 milliseconds = 10 seconds

    // Check that the URL has changed to the sign-in page
    String currentUrl = driver.getCurrentUrl();
    assertEquals("http://localhost:3000/sign-in", currentUrl,
        "URL should be changed to the sign-in page after registration");

    // Verify that the login button is visible on the sign-in page
    WebElement loginButton = wait
        .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
    assertEquals("Login", loginButton.getText(), "Login button should be visible on the sign-in page");
  }
}
