package com.library.selenium;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;
public class RegisterAuthorTest {

  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "src/test/java/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void registerAuthor() {
    try {
      driver.get("http://localhost:8080/Home");
      Thread.sleep(500);
      driver.manage().window().setSize(new Dimension(1382, 744));
      Thread.sleep(500);
      driver.findElement(By.linkText("Opções de Gerenciamento")).click();
      driver.findElement(By.id("username")).click();
      driver.findElement(By.id("username")).sendKeys("Inathan");
      Thread.sleep(500);
      driver.findElement(By.id("password")).sendKeys("123");
      Thread.sleep(500);
      driver.findElement(By.cssSelector(".btn")).click();
      Thread.sleep(500);
      driver.findElement(By.linkText("Opções de Gerenciamento")).click();
      Thread.sleep(500);
      driver.findElement(By.id("navbarAuthor")).click();
      Thread.sleep(500);
      driver.findElement(By.linkText("Cadastrar")).click();
      Thread.sleep(500);
      driver.findElement(By.id("name")).click();
      driver.findElement(By.id("name")).sendKeys("Joane Rowling");
      Thread.sleep(500);
      driver.findElement(By.id("bibliographicReference")).click();
      driver.findElement(By.id("bibliographicReference")).sendKeys("J.K Rowling");
      Thread.sleep(1000);
      driver.findElement(By.cssSelector(".btn-primary")).click();
    } catch (InterruptedException error) {
    error.printStackTrace();
    }
  }
}
