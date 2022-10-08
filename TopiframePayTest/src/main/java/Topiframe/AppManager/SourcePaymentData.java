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
    String amont = setPrice(amount);
    assertThat(amount, equalTo(amont));
    LOG.info("TL_PRICE = " + amount);
  }
  public void setOverAmountPay(String amount) throws InterruptedException{
    JavascriptExecutor js = (JavascriptExecutor) wd;
    js.executeScript("window.scrollBy(0,350)", "");
    setPrice(amount);
    wd.findElement(By.cssSelector(".Service_wrapper__1PQ18")).click();
    Thread.sleep(1000);
    if (isElementPresent(By.cssSelector(".SmartInput_textFail__37qzX"))){
      LOG.info("Too much amount, please enter a smaller amount");
    }else {
      LOG.error("Invalid validation");
      Assert.fail();
    }
  }


  public void fillPhoneNumberFromYouPay(String phone) throws InterruptedException {
    // Заполняем данные об источнике средств
    wd.findElement(By.xpath("//span[text()= 'Со счёта мобильного телефона']")).click();
    LOG.info("Filling the payment information = "+ phone);
    String value2 = getAtributBeforeFillPhone();
    String value1 = fillSourceOfMoney(phone);
    if (Objects.equals(value1, value2)) {
      LOG.error("Payment form is empty");
      Assert.fail();
    } else {
      LOG.info("TL_MORE_INFO_SM = " + phone);
    }
  }
  public void fillSomPhoneLetters (String phone) throws InterruptedException {
    // Заполняем данные об источнике средств
    wd.findElement(By.xpath("//span[text()= 'Со счёта мобильного телефона']")).click();
    LOG.info("Filling the payment information = "+ phone);
    String value2 = getAtributBeforeFillPhone();
    String value1 = fillSourceOfMoney(phone);
    if (!Objects.equals(phone, value2)) {
      LOG.info("Can't enter value  '"+ phone + "'  in the field, please entre the numbers");
    } else {
      LOG.error("Validation error");
      Assert.fail();
    }
  }
  public void fillPaymentDDK(String pan, String exp, String holder, String cvv) throws InterruptedException {
    // Выбираем метод оплаты БК
    try {
      WebElement payCard = wd.findElement(By.xpath("//span[text()= 'С банковской карты']"));
      payCard.click();
    } catch (Exception e){
      LOG.error("Can't find element Pay from BK");
      Assert.fail();
    }
    // Вводим ДДК
    WebElement ass = wd.findElement(By.cssSelector(".Service_front__2mu1u"));
    String atr = ass.getAttribute("tagName");
    if (atr != null) {
      Assert.assertEquals(atr, "DIV");
      LOG.info("Filling the correct payment information");
      String panc1 = panCard(pan);
      assertThat(pan, equalTo(panc1));
      String date1 = expCard(exp);
      assertThat(exp, equalTo(date1));
      String hold1 = holderCard(holder);
      assertThat(holder, equalTo(hold1));
      String cvc1 = cvvCard(cvv);
      assertThat(cvv, equalTo(cvc1));
      LOG.info("TL_MORE_INFO_SM = " + pan);
    } else {
      LOG.info("Dont see payment form");
      wd.quit();
    }
  }

  public void fillPaymentDDKLetters(String pan, String exp, String holder, String cvv) throws InterruptedException {
    // Выбираем метод оплаты БК
    try {
      WebElement payCard = wd.findElement(By.xpath("//span[text()= 'С банковской карты']"));
      payCard.click();
    } catch (Exception e){
      LOG.error("Can't find element Pay from BK");
      Assert.fail();
    }
    LOG.info("Try input letters in PAN field = "+ pan);
    String pan1 = panCard(pan);
    if (Objects.equals(pan, pan1)){
      LOG.error("Invalid validation");
    } else {
      LOG.info("Letters not available, please use numbers");
    }
    LOG.info("Try input letters in EXP field = "+ exp);
    String exp1 = expCard(exp);
    if (Objects.equals(exp, exp1)){
      LOG.error("Invalid validation");
    } else {
      LOG.info("Letters not available, please use numbers");
    }
    LOG.info("Try input numbers in HOLDER field = "+ holder);
    String holder1 = holderCard(holder);
    if (Objects.equals(holder, holder1)){
      LOG.error("Invalid validation");
    } else {
      LOG.info("Numbers not available, please use letters");
    }
    LOG.info("Try input letters in CVV field = "+ cvv);
    String cvv1 = cvvCard(cvv);
    if (Objects.equals(cvv, cvv1)){
      LOG.error("Invalid validation");
    } else {
      LOG.info("Letters not available, please use numbers");
    }
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

  private String fillSourceOfMoney(String phone) {
    WebElement sourcePhone = wd.findElement(By.xpath("//input[@id='input-Phone']"));
    sourcePhone.sendKeys(Keys.CONTROL + "A");
    sourcePhone.sendKeys(Keys.DELETE);
    sourcePhone.sendKeys(phone);
    return sourcePhone.getAttribute("defaultValue");
  }

  private String setPrice (String amount) {
    WebElement summ = wd.findElement(By.xpath("//input[@name='amount']"));
    LOG.info("Set the payment amount = "+ amount);
    summ.sendKeys(Keys.CONTROL + "A");
    summ.sendKeys(Keys.DELETE);
    summ.sendKeys(amount);
    return summ.getAttribute("defaultValue");
  }

  private String getAtributBeforeFillPhone() {
    WebElement sourcePhone = wd.findElement(By.xpath("//input[@id='input-Phone']"));
    sourcePhone.sendKeys(Keys.CONTROL + "A");
    sourcePhone.sendKeys(Keys.DELETE);
    return sourcePhone.getAttribute("defaultValue");
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
