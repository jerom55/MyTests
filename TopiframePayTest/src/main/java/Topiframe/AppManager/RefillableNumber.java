package Topiframe.AppManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RefillableNumber {
   private WebDriver wd;
    public static Logger LOG = LoggerFactory.getLogger(RefillableNumber.class);

    public RefillableNumber(WebDriver wd) {
        this.wd = wd;
    }

    public void fillPhoneNumber (String targetPhone) throws InterruptedException {
        // Вводим номер телефона который пополняем
      WebElement phone = wd.findElement(By.xpath("//input[@type='tel']"));
        phone.sendKeys(Keys.CONTROL+"A");
        phone.sendKeys(Keys.DELETE);
        phone.sendKeys(targetPhone);
        LOG.info("TL_MORE_INFO_ST = "+ targetPhone);
    }
    public void fillPhoneNumberAgregator (String targetPhone) throws InterruptedException {
        // Вводим номер телефона который пополняем
        WebElement phone = wd.findElement(By.xpath("//input[@type='tel']"));
        phone.sendKeys(Keys.CONTROL+"A");
        phone.sendKeys(Keys.DELETE);
        phone.sendKeys(targetPhone);
        LOG.info("TL_MORE_INFO_ST = "+ targetPhone);
        Thread.sleep(3000);
    }
    public void fillCardNumber(String targetCard){
        wd.findElement(By.xpath("//input[@id='input-cardnumber']")).sendKeys(targetCard);
        LOG.info("TL_MORE_INFO_ST = "+ targetCard);
    }
}
