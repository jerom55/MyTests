package AppManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RefillableNumber {
   private WebDriver wd;

    public RefillableNumber(WebDriver wd) {
        this.wd = wd;
    }

    public void fillPhoneNumber(String targetPhone) throws InterruptedException {
        // Вводим номер телефона который пополняем
       wd.findElement(By.xpath("//input[@type='tel']")).sendKeys(targetPhone);
    }

    public void fillCardNumber(String targetCard){
        wd.findElement(By.xpath("//input[@id='input-cardnumber']")).sendKeys(targetCard);
    }
}
