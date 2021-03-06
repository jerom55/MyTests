package AppManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RefillablePhoneNumber {
   private WebDriver wd;

    public RefillablePhoneNumber(WebDriver wd) {
        this.wd = wd;
    }

    public void fillPhoneNumber(String targetPhone) throws InterruptedException {
        // Вводим номер телефона который пополняем
       wd.findElement(By.xpath("//input[@type='tel']")).sendKeys(targetPhone);
    }
}
