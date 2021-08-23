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

public class EditAuthorTest {
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
  public void editAuthor() {
    try {
      driver.get("http://localhost:8080/Home");
      Thread.sleep(500);
      driver.manage().window().setSize(new Dimension(1050, 708));
      Thread.sleep(500);
      driver.findElement(By.linkText("Opções de Gerenciamento")).click();
      Thread.sleep(500);
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
      driver.findElement(By.linkText("Listar")).click();
      Thread.sleep(500);
      driver.findElement(By.cssSelector("tr:nth-child(1) .btn-primary > .fa")).click();
      Thread.sleep(500);
      driver.findElement(By.id("name")).click();
      driver.findElement(By.cssSelector("body")).click();
      driver.findElement(By.id("name")).sendKeys("Clarice Lispector");
      Thread.sleep(500);
      driver.findElement(By.id("bibliographicReference")).click();
      driver.findElement(By.cssSelector("#authorsForm")).click();
      Thread.sleep(500);
      driver.findElement(By.id("bibliographicReference")).click();
      driver.findElement(By.id("bibliographicReference")).sendKeys("L. Clarice");
      Thread.sleep(500);
      driver.findElement(By.cssSelector("html")).click();
      driver.findElement(By.cssSelector(".btn-primary")).click();
      Thread.sleep(500);
      driver.findElement(By.id("navbarAuthor")).click();
      driver.findElement(By.linkText("Listar")).click();
      Thread.sleep(1000);
    } catch (InterruptedException error) {
      error.printStackTrace();
    }
  }
}
