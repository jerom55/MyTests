package Topiframe.AppManager;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Objects;

import static Topiframe.AppManager.NavigationHelper.screenshot;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class RefillableNumber {
  private WebDriver wd;
  public static Logger LOG = LoggerFactory.getLogger(RefillableNumber.class);

  public RefillableNumber(WebDriver wd) {
    this.wd = wd;
  }

  public void fillPhoneLetters(String targetLetters) throws Exception {
    String phone1 = targetPhoneBeforeFill();
    LOG.info("Копируем тест поля 'Номер телефона получателя' = " + phone1);
    LOG.info("Заполняем поле 'Номер телефона получателя' буквами = " + targetLetters);
    String phone2 = targetPhone(targetLetters);
    LOG.info("Копируем тест поля 'Номер телефона получателя' после ввода букв = " + phone2);
    Thread.sleep(2000);
    if (Objects.equals(phone2, phone1)) {
      LOG.info("Поле не доступно для ввода букв, используйте цифры");
    } else {
      LOG.error("Ошибка валидации - буквы в поле для цифр");
      screenshot();
    }
  }

  public void fillPhoneNumber(String targetPhone) throws Exception {
    // Вводим номер телефона который пополняем
    String phone1 = targetPhoneBeforeFill();
    String phone2 = targetPhone(targetPhone);
    if (!Objects.equals(phone1, phone2)) {
      LOG.info("TL_MORE_INFO_ST = " + targetPhone);
    } else {
      LOG.error("Поле ввода пополняемого номера пустое");
      screenshot();
      Assert.fail();
    }
  }

  public void fillPhoneNumberAgregator(String targetPhone) throws Exception {
    // Вводим номер телефона который пополняем
    String phone1 = targetPhoneBeforeFill();
    String phone2 = targetPhone(targetPhone);
    if (!Objects.equals(phone1, phone2)) {
      LOG.info("TL_MORE_INFO_ST = " + targetPhone);
      Thread.sleep(3000);
    } else {
      LOG.error("Поле ввода пополняемого номера пустое");
      screenshot();
      Assert.fail();
    }
  }

  private String targetPhone(String targetPhone) {
    WebElement phone = wd.findElement(By.xpath("//input[@type='tel']"));
    phone.sendKeys(Keys.CONTROL + "A");
    phone.sendKeys(Keys.DELETE);
    phone.sendKeys(targetPhone);
    LOG.info("Вводим номер телефона, который хотим пополнить = " + targetPhone);
    return phone.getAttribute("defaultValue");
  }

  public void fillCardNumber(String targetCard) {
    WebElement card = wd.findElement(By.xpath("//input[@id='input-cardnumber']"));
    card.sendKeys(targetCard);
    String card1 = card.getAttribute("defaultValue");
    LOG.info("Вводим номер карты, которую хотим пополнить = "+ targetCard);
    if (card1 != null) {
      assertThat(targetCard, equalTo(card1));
      LOG.info("TL_MORE_INFO_ST = " + targetCard);
    }
  }

  private String targetPhoneBeforeFill() {
    WebElement phone = wd.findElement(By.xpath("//input[@type='tel']"));
    phone.sendKeys(Keys.CONTROL + "A");
    phone.sendKeys(Keys.DELETE);
    return phone.getAttribute("defaultValue");
  }
}
