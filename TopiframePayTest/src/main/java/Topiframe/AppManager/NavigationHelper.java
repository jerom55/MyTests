package Topiframe.AppManager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;

public class NavigationHelper {

  private WebDriver wd;
  public static Logger LOG = LoggerFactory.getLogger(NavigationHelper.class);

  public NavigationHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void clickPayButton() throws InterruptedException {
    //Жмём кнопу оплаты
    WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(20));
    WebElement button = wd.findElement(By.xpath("//button[@type='submit']"));
    try {
      wait.until(ExpectedConditions.elementToBeClickable(button));
      button.click();
      LOG.info("Press the payment button");
    } catch (Exception e) {
      LOG.error("Pay button is not active");
      Assert.fail();
    }
    try {
      wait.until(ExpectedConditions.stalenessOf(button));
    } catch (Exception e) {
      LOG.error("Pay button is visible");
      Assert.fail();
    }
  }

  public void tickedCheckBoxClickPayButton() throws InterruptedException {
    // Check-box ставим галочку
    commission();
    Thread.sleep(2000);
    WebElement box = wd.findElement(By.xpath("//input[@id='checkbox__offer']"));
    Actions actions = new Actions(wd);
    actions.moveToElement(box).clickAndHold().release().build().perform();
    LOG.info("Set the checkbox");
    clickPayButton();
  }

  public void goGoodsUrl(String url) throws InterruptedException {
    // ВВодим адрес страницы
    wd.get(url);
  }

  public void insecurePage() {
    //Переход на небезопасную страницу
    String safety = "Safety page is not available";
    try {
      WebElement confirm = (new WebDriverWait(wd, Duration.ofSeconds(10))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='details-button']"))));
      confirm.click();
      WebElement confirm2 = (new WebDriverWait(wd, Duration.ofSeconds(1))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='proceed-link']"))));
      confirm2.click();
    } catch (Exception e) {
      LOG.info(safety);
    }
  }

  public void chequeEmail(String email) {
    LOG.info("Set the email to receive cheque = " + email);
    wd.findElement(By.xpath("//input[@name='cheques.email']")).sendKeys(email);
  }
  private void commission() {
    LOG.info("Get the cost of the service with a commission");
    WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
    try {
      wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".Service_inputsRow__1zBtT:nth-child(1) > .Form_value__cMLhf")));
      WebElement comm = wd.findElement(By.cssSelector(".Service_inputsRow__1zBtT:nth-child(1) > .Form_value__cMLhf"));
      String commission = comm.getAttribute("innerText");
      LOG.info("TL_COMMISSION = " + commission);
      WebElement tlAmount = wd.findElement(By.cssSelector(".Service_inputsRow__1zBtT:nth-child(2) > .Form_value__cMLhf"));
      String amount = tlAmount.getAttribute("innerText");
      LOG.info("TL_AMOUNT = " + amount);
    }catch (Exception e) {
      LOG.error("Commission block is not available");
      Assert.fail();
    }
  }

}


