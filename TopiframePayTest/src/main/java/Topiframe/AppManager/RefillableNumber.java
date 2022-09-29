package Topiframe.AppManager;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class RefillableNumber {
   private WebDriver wd;
    public static Logger LOG = LoggerFactory.getLogger(RefillableNumber.class);

    public RefillableNumber(WebDriver wd) {
        this.wd = wd;
    }

    public void fillPhoneNumber (String targetPhone) throws InterruptedException {
        // Вводим номер телефона который пополняем
        WebElement phone = wd.findElement(By.xpath("//input[@type='tel']"));
        String phone1 = phone.getAttribute("defaultValue");
        String phone2 = targetPhone(targetPhone);
        if (!Objects.equals(phone1, phone2)){
            LOG.info("TL_MORE_INFO_ST = "+ targetPhone);
        } else {
            LOG.error("There is no phone number on which we put money");
            Assert.fail();
        }
    }

    public void fillPhoneNumberAgregator (String targetPhone) throws InterruptedException {
        // Вводим номер телефона который пополняем
        WebElement phone = wd.findElement(By.xpath("//input[@type='tel']"));
        String phone1 = phone.getAttribute("defaultValue");
        String phone2 = targetPhone(targetPhone);
        if (!Objects.equals(phone1, phone2)){
            LOG.info("TL_MORE_INFO_ST = "+ targetPhone);
            Thread.sleep(3000);
        } else {
            LOG.error("There is no phone number on which we put money");
            Assert.fail();
        }
    }
    private String targetPhone(String targetPhone) {
        WebElement phone = wd.findElement(By.xpath("//input[@type='tel']"));
        phone.sendKeys(Keys.CONTROL+"A");
        phone.sendKeys(Keys.DELETE);
        phone.sendKeys(targetPhone);
        return phone.getAttribute("defaultValue");
    }
    public void fillCardNumber(String targetCard){
       WebElement card = wd.findElement(By.xpath("//input[@id='input-cardnumber']"));
       card.sendKeys(targetCard);
       String card1 = card.getAttribute("defaultValue");
       if (card1 != null){
           assertThat(targetCard, equalTo(card1));
           LOG.info("TL_MORE_INFO_ST = "+ targetCard);
       }

    }
}
