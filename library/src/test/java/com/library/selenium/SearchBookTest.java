package com.library.selenium;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class SearchBookTest {

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
  public void searchBook() {
    try {
      driver.get("http://localhost:8080/Home");
      Thread.sleep(500);
      driver.manage().window().setSize(new Dimension(1050, 708));
      Thread.sleep(500);
      driver.findElement(By.linkText("Listagem de Livros")).click();
      Thread.sleep(500);
      driver.findElement(By.id("title")).click();
      driver.findElement(By.id("title")).sendKeys("Harry Potter");
      Thread.sleep(500);
      driver.findElement(By.cssSelector(".btn-primary")).click();
      driver.findElement(By.id("username")).click();
      driver.findElement(By.id("username")).sendKeys("Inathan");
      Thread.sleep(500);
      driver.findElement(By.id("password")).sendKeys("123");
      Thread.sleep(500);
      driver.findElement(By.cssSelector(".btn")).click();
      driver.findElement(By.linkText("Listagem de Livros")).click();
      Thread.sleep(500);
      driver.findElement(By.id("title")).click();
      driver.findElement(By.id("title")).sendKeys("Harry Potter");
      Thread.sleep(500);
      driver.findElement(By.cssSelector(".btn-primary")).click();
      Thread.sleep(500);
      driver.findElement(By.id("title")).click();
      driver.findElement(By.id("title")).sendKeys("fogo");
      Thread.sleep(500);
      driver.findElement(By.cssSelector(".btn-primary")).click();
      driver.findElement(By.cssSelector("body")).click();
      Thread.sleep(500);
      driver.findElement(By.id("title")).click();
      driver.findElement(By.id("title")).sendKeys("Harry");
      Thread.sleep(500);
      driver.findElement(By.cssSelector(".btn-primary")).click();
      Thread.sleep(1000);
      driver.findElement(By.cssSelector("html")).click();
    } catch (InterruptedException error) {
      error.printStackTrace();
    }
  }
}
