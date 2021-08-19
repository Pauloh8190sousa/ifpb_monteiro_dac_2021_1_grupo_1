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
public class RegisterBookTest {
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
  public void registerbook() {
    try {
      driver.get("http://localhost:8080//createBook");
      Thread.sleep(500);
      driver.manage().window().setSize(new Dimension(1382, 744));
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
      driver.findElement(By.id("navbarBook")).click();
      Thread.sleep(500);
      driver.findElement(By.linkText("Cadastrar")).click();
      Thread.sleep(500);
      driver.findElement(By.id("title")).click();
      driver.findElement(By.id("title")).sendKeys("Harry Potter");
      Thread.sleep(500);
      driver.findElement(By.id("authors")).click();
      Thread.sleep(500);
      driver.findElement(By.id("publishingCompanies")).click();
      Thread.sleep(500);
      driver.findElement(By.id("categories")).click();
      Thread.sleep(500);
      driver.findElement(By.id("price")).click();
      driver.findElement(By.id("price")).sendKeys("33");
      Thread.sleep(500);
      driver.findElement(By.id("description")).click();
      driver.findElement(By.id("description")).sendKeys("Um mundo de mistério e magia");
      Thread.sleep(500);
      driver.findElement(By.id("nbOfPages")).click();
      driver.findElement(By.id("nbOfPages")).sendKeys("357");
      Thread.sleep(500);
      driver.findElement(By.id("isbn")).click();
      driver.findElement(By.id("isbn")).sendKeys("9267207350377");
      Thread.sleep(500);
      driver.findElement(By.id("stock")).click();
      driver.findElement(By.id("stock")).sendKeys("50");
      Thread.sleep(500);
      driver.findElement(By.id("illustration")).click();
      driver.findElement(By.id("illustration")).sendKeys("true");
      Thread.sleep(500);
      driver.findElement(By.id("publicationDate")).click();
      driver.findElement(By.id("publicationDate")).sendKeys("1998-07-14");
//      Thread.sleep(500);
//      driver.findElement(By.id("bookForm")).click();
//      driver.findElement(By.id("imageLink")).click();
//      driver.findElement(By.id("imageLink")).sendKeys("C:\\fakepath\\fundo.jpg");
      Thread.sleep(1000);
      driver.findElement(By.cssSelector(".btn-primary")).click();
    } catch (InterruptedException error) {
    error.printStackTrace();
    }

  }
}
