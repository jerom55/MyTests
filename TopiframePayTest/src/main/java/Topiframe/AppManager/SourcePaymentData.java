package Topiframe.AppManager;

import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class SourcePaymentData {
  public static Logger LOG = LoggerFactory.getLogger(SourcePaymentData.class);
  private WebDriver wd;

  public SourcePaymentData(WebDriver wd) {
    this.wd = wd;
  }

  public void setAmountPay(String amount) throws InterruptedException {
    //Вводим сумму платежа
    JavascriptExecutor js = (JavascriptExecutor) wd;
    js.executeScript("window.scrollBy(0,350)", "");
    WebElement summ = wd.findElement(By.xpath("//input[@name='amount']"));
    LOG.info("Set the payment amount");
    summ.sendKeys(Keys.CONTROL + "A");
    summ.sendKeys(Keys.DELETE);
    summ.sendKeys(amount);
    String amont = summ.getAttribute("defaultValue");
    assertThat(amount, equalTo(amont));
    LOG.info("TL_PRICE = " + amount);
  }

  public void fillPhoneNumberFromYouPay(String phone) throws InterruptedException {
    // Заполняем данные об источнике средств
    LOG.info("Filling the payment information");
    WebElement sourcePhone = wd.findElement(By.xpath("//input[@id='input-Phone']"));
    String value2 = sourcePhone.getAttribute("defaultValue");
    String value1 = sourceOfMoney(phone);
    if (Objects.equals(value1, value2)) {
      LOG.error("Payment form is empty");
      Assert.fail();
    } else {
      LOG.info("TL_MORE_INFO_SM = " + phone);
      amountCommission();
    }

  }

  public void fillPaymentDDK(String pan, String exp, String holder, String cvv) throws InterruptedException {
    // Вводим ДДК
    WebElement ass = wd.findElement(By.cssSelector(".Service_front__2mu1u"));
    String atr = ass.getAttribute("tagName");
    if (atr != null) {
      Assert.assertEquals(atr, "DIV");
      LOG.info("Filling the payment information");
      String panc1 = panCard(pan);
      assertThat(pan, equalTo(panc1));
      String date1 = expCard(exp);
      assertThat(exp, equalTo(date1));
      String hold1 = holderCard(holder);
      assertThat(holder, equalTo(hold1));
      String cvc1 = cvvCard(cvv);
      assertThat(cvv, equalTo(cvc1));
      LOG.info("TL_MORE_INFO_SM = " + pan);
      amountCommission();
    } else {
      LOG.info("Dont see payment form");
      wd.quit();
    }
  }

  public void fillPaymentDDKLetters(String pan, String exp, String holder, String cvv) throws InterruptedException {
    panCard(pan);
    expCard(exp);
    holderCard(holder);
    cvvCard(cvv);
  }

  private String cvvCard(String cvv) {
    WebElement cvc = wd.findElement(By.xpath("//input[@name='CVC']"));
    cvc.sendKeys(cvv);
    return cvc.getAttribute("defaultValue");
  }

  private String holderCard(String holder) {
    WebElement hold = wd.findElement(By.xpath("//input[@name='CardHolder']"));
    hold.sendKeys(holder);
    return hold.getAttribute("defaultValue");
  }

  private String expCard(String exp) {
    WebElement date = wd.findElement(By.xpath("//input[@name='ExpDate']"));
    date.sendKeys(exp);
    return date.getAttribute("defaultValue");
  }

  private String panCard(String pan) {
    WebElement panc = wd.findElement(By.xpath("//input[@name='Pan']"));
    panc.sendKeys(pan);
    return panc.getAttribute("defaultValue");
  }

  private String sourceOfMoney(String phone) {
    WebElement sourcePhone = wd.findElement(By.xpath("//input[@id='input-Phone']"));
    sourcePhone.sendKeys(Keys.CONTROL + "A");
    sourcePhone.sendKeys(Keys.DELETE);
    sourcePhone.sendKeys(phone);
    return sourcePhone.getAttribute("defaultValue");
  }

  private void amountCommission() throws InterruptedException {
    LOG.info("Get the cost of the service with a commission");
    Thread.sleep(1500);
    if (isElementPresent(By.cssSelector(".Service_inputsRow__1zBtT:nth-child(1) > .Form_value__cMLhf"))) {
      WebElement comm = wd.findElement(By.cssSelector(".Service_inputsRow__1zBtT:nth-child(1) > .Form_value__cMLhf"));
      String commission = comm.getAttribute("innerText");
      LOG.info("TL_COMMISSION = " + commission);
    } else {
      LOG.error("Commission block is not available");
      Assert.fail();
    }
    if (isElementPresent(By.cssSelector(".Service_inputsRow__1zBtT:nth-child(2) > .Form_value__cMLhf"))) {
      WebElement tlAmount = wd.findElement(By.cssSelector(".Service_inputsRow__1zBtT:nth-child(2) > .Form_value__cMLhf"));
      String amount = tlAmount.getAttribute("innerText");
      LOG.info("TL_AMOUNT = " + amount);
    } else {
      LOG.error("Commission block is not available");
      Assert.fail();
    }

  }

  public boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}
