package AppManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RefillablePhoneNumber {
   private WebDriver wd;

    public RefillablePhoneNumber(WebDriver wd) {
        this.wd = wd;
    }

    public void fillPhoneNumber(String targetPhone) throws InterruptedException {
        // Вводим номер телефона который пополняем
        WebElement phone = wd.findElement(By.xpath("//input[@type='tel']"));
        phone.sendKeys(targetPhone);
        Thread.sleep(1000);
    }
}
