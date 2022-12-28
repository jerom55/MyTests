package Topiframe.AppManager;

import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Objects;

import static Topiframe.AppManager.NavigationHelper.screenshot;
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
  public void setOverAmountPay(String amount) throws Exception {
    JavascriptExecutor js = (JavascriptExecutor) wd;
    js.executeScript("window.scrollBy(0,350)", "");
    setPrice(amount);
    wd.findElement(By.cssSelector(".Service_wrapper__1PQ18")).click();
    Thread.sleep(1000);
    if (isElementPresent(By.cssSelector(".SmartInput_textFail__37qzX"))){
      LOG.info("Сумма не входит в диапозон доступных по данной услуге");
    }else {
      LOG.error("Ошибка валидации");
      screenshot();
      Assert.fail();
    }
  }


  public void fillPhoneNumberFromYouPay(String phone) throws Exception {
    // Заполняем данные об источнике средств
    wd.findElement(By.xpath("//span[text()= 'Со счёта мобильного телефона']")).click();
    LOG.info("Заполняем платёжную информацию = "+ phone);
    String value2 = getAtributBeforeFillPhone();
    String value1 = fillSourceOfMoney(phone);
    if (Objects.equals(value1, value2)) {
      LOG.error("Payment form is empty");
      screenshot();
      Assert.fail();
    } else {
      LOG.info("TL_MORE_INFO_SM = " + phone);
    }
  }
  public void fillSomPhoneLetters (String phone) throws Exception {
    // Заполняем данные об источнике средств
    wd.findElement(By.xpath("//span[text()= 'Со счёта мобильного телефона']")).click();
    LOG.info("Заполняем платёжную информацию = "+ phone);
    String value2 = getAtributBeforeFillPhone();
    String value1 = fillSourceOfMoney(phone);
    if (!Objects.equals(phone, value2)) {
      LOG.info("Нельзя ввести значение '"+ phone + "' в поле для чисел");
    } else {
      LOG.error("Ошибка валидации");
      screenshot();
      Assert.fail();
    }
  }
  public void fillPaymentDDK(String pan, String exp, String holder, String cvv) throws Exception {
    // Выбираем метод оплаты БК
    try {
      WebElement payCard = wd.findElement(By.xpath("//span[text()= 'С банковской карты']"));
      payCard.click();
    } catch (Exception e){
      LOG.error("Нет элемента 'Оплата с банковской карты'");
      screenshot();
      Assert.fail();
    }
    // Вводим ДДК
    WebElement ass = wd.findElement(By.cssSelector(".Service_front__2mu1u"));
    String atr = ass.getAttribute("tagName");
    if (atr != null) {
      Assert.assertEquals(atr, "DIV");
      LOG.info("Заполняем платёжную информацию");
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
      LOG.info("Нет формы оплаты");
      screenshot();
      wd.quit();
    }
  }

  public void fillPaymentDDKLetters(String pan, String exp, String holder, String cvv) throws Exception {
    // Выбираем метод оплаты БК
    try {
      WebElement payCard = wd.findElement(By.xpath("//span[text()= 'С банковской карты']"));
      payCard.click();
    } catch (Exception e){
      LOG.error("Нет элемента платёж с банковской карты");
      screenshot();
      Assert.fail();
    }
    LOG.info("Пытаемся ввести буквы в поле PAN = "+ pan);
    String pan1 = panCard(pan);
    if (Objects.equals(pan, pan1)){
      LOG.error("Ошибка валидации");
      screenshot();
      Assert.fail();
    } else {
      LOG.info("Поле недоступно для букв, используйте цифры");
    }
    LOG.info("Пытаемся ввести буквы в поле EXP = "+ exp);
    String exp1 = expCard(exp);
    if (Objects.equals(exp, exp1)){
      LOG.error("Ошибка валидации");
      screenshot();
      Assert.fail();
    } else {
      LOG.info("Поле недоступно для букв, используйте цифры");
    }
    LOG.info("Пытаемся ввести буквы в поле HOLDER = "+ holder);
    String holder1 = holderCard(holder);
    if (Objects.equals(holder, holder1)){
      LOG.error("Ошибка валидации");
      screenshot();
      Assert.fail();
    } else {
      LOG.info("Поле недоступно для букв, используйте цифры");
    }
    LOG.info("Пытаемся ввести буквы в поле CVV = "+ cvv);
    String cvv1 = cvvCard(cvv);
    if (Objects.equals(cvv, cvv1)){
      LOG.error("Ошибка валидации");
      screenshot();
      Assert.fail();
    } else {
      LOG.info("Поле недоступно для букв, используйте цифры");
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
    LOG.info("Указываем сумму платежа = "+ amount);
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
