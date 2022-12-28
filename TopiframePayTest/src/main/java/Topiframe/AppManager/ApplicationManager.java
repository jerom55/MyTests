package Topiframe.AppManager;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static Topiframe.AppManager.NavigationHelper.screenshot;

public class ApplicationManager {
  private final Retry retry = new Retry();
  public static Logger LOG = LoggerFactory.getLogger(ApplicationManager.class);
  private NavigationOnWebsite navigationOnWebsite;
  WebDriver wd;
  private WaitingEndOfPayment waitingEndOfPayment;
  private RefillableNumber refillablePhoneNumber;
  private NavigationHelper navigationHelper;
  private SourcePaymentData sourcePaymentData;
  private Capcha capcha;
  private ConfirmationPayment confirmationPayment;

  public void startTest() throws Exception {
    System.setProperty("webdriver.chrome.driver", "F:\\AutoTest\\chrome\\chromedriver.exe");
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    confirmationPayment = new ConfirmationPayment(wd);
    capcha = new Capcha(wd);
    sourcePaymentData = new SourcePaymentData(wd);
    navigationHelper = new NavigationHelper(wd);
    refillablePhoneNumber = new RefillableNumber(wd);
    waitingEndOfPayment = new WaitingEndOfPayment(wd);
    navigationOnWebsite = new NavigationOnWebsite(wd);
    cashClean();
  }

  public void stopTest() {
    wd.quit();
  }

  public ConfirmationPayment getConfirmationPayment() {
    return confirmationPayment;
  }

  public Capcha getCapcha() {
    return capcha;
  }

  public SourcePaymentData getSourcePaymentData() {
    return sourcePaymentData;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public RefillableNumber getRefillableNumber() {
    return refillablePhoneNumber;
  }

  public WaitingEndOfPayment getWaitingEndOfPayment() {
    return waitingEndOfPayment;
  }

  public Retry getRetry() {
    return retry;
  }

  public NavigationOnWebsite getNavigationOnWebsite() {
    return navigationOnWebsite;
  }



  public void cashClean () throws Exception {
    try {
      wd.get("https://dev-panel.nsc-tech.ru/#/index/hosts");
      JavascriptExecutor js = (JavascriptExecutor) wd;
      js.executeScript("window.scrollBy(0,350)", "");
      wd.findElement(By.xpath("//span[@href='http://qa-02-topiframe02.qa.ruru.ru']")).click();
      wd.findElement(By.cssSelector(".table-block_open .btn")).click();
      wd.findElement(By.cssSelector("div:nth-child(3) > button")).click();
      try {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(30));
        WebElement status = wd.findElement(By.cssSelector(".log-viewer__log-area"));
        wait.until(ExpectedConditions.attributeContains(status, "innerText", "OK"));
        LOG.info("Кэш витрины Topiframe сброшен");
      } catch (Exception e) {
        LOG.error("Кэш не сброшен");
        screenshot();
      }

    } catch (Exception e) {
      LOG.error("DEV-панель, не доступна");
      screenshot();
    }
  }
}
