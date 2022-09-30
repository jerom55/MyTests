package Topiframe.AppManager;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class RefillableNumber {
  private WebDriver wd;
  public static Logger LOG = LoggerFactory.getLogger(RefillableNumber.class);

  public RefillableNumber(WebDriver wd) {
    this.wd = wd;
  }

  public void fillPhoneLetters(String targetLetters) throws InterruptedException {
    String phone1 = targetPhoneBeforeFill();
    LOG.info("Copy the text of the 'Recipient's phone number field' = " + phone1);
    LOG.info("Enter the letters in the field 'Recipient's phone number field:' = " + targetLetters);
    String phone2 = targetPhone(targetLetters);
    LOG.info("Copy the text of the 'Recipient's phone number field' after entering letters = " + phone2);
    Thread.sleep(2000);
    if (Objects.equals(phone2, phone1)) {
      LOG.info("This field is not available for entering letters, please enter numbers");
    } else {
      LOG.error("Letters entered in the field");
    }
  }

  public void fillPhoneNumber(String targetPhone) throws InterruptedException {
    // Вводим номер телефона который пополняем
    String phone1 = targetPhoneBeforeFill();
    String phone2 = targetPhone(targetPhone);
    if (!Objects.equals(phone1, phone2)) {
      LOG.info("TL_MORE_INFO_ST = " + targetPhone);
    } else {
      LOG.error("The target phone number field is empty");
      Assert.fail();
    }
  }

  public void fillPhoneNumberAgregator(String targetPhone) throws InterruptedException {
    // Вводим номер телефона который пополняем
    String phone1 = targetPhoneBeforeFill();
    String phone2 = targetPhone(targetPhone);
    if (!Objects.equals(phone1, phone2)) {
      LOG.info("TL_MORE_INFO_ST = " + targetPhone);
      Thread.sleep(3000);
    } else {
      LOG.error("The target phone number field is empty");
      Assert.fail();
    }
  }

  private String targetPhone(String targetPhone) {
    WebElement phone = wd.findElement(By.xpath("//input[@type='tel']"));
    phone.sendKeys(Keys.CONTROL + "A");
    phone.sendKeys(Keys.DELETE);
    phone.sendKeys(targetPhone);
    LOG.info("Fill phone number = " + targetPhone);
    return phone.getAttribute("defaultValue");
  }

  public void fillCardNumber(String targetCard) {
    WebElement card = wd.findElement(By.xpath("//input[@id='input-cardnumber']"));
    card.sendKeys(targetCard);
    String card1 = card.getAttribute("defaultValue");
    if (card1 != null) {
      assertThat(targetCard, equalTo(card1));
      LOG.info("TL_MORE_INFO_ST = " + targetCard);
    }
  }

  private String targetPhoneBeforeFill() {
    WebElement phone = wd.findElement(By.xpath("//input[@type='tel']"));
    return phone.getAttribute("defaultValue");
  }
}
