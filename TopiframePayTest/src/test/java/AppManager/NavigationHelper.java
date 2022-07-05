package AppManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class NavigationHelper {
    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void clickPayButton() throws InterruptedException {
        // Жмём кнопу оплаты
        WebElement pay = wd.findElement(By.xpath("//button[@type='submit']"));
        pay.click();
        Thread.sleep(10000);
    }

    public void tickedCheckBox() throws InterruptedException {
        // Check-box ставим галочку
        WebElement box = wd.findElement(By.xpath("//input[@type='checkbox']"));
        Actions actions = new Actions(wd);
        actions.moveToElement(box).clickAndHold().release().build().perform();
        Thread.sleep(2000);
    }

    public void chanceSourcePayment() throws InterruptedException {
        // Выбираем метод оплаты БК
        WebElement bk = wd.findElement(By.xpath("//*[text()='С банковской карты']"));
        bk.click();
        Thread.sleep(3000);
    }

    public void getGoodsUrl(String url) throws InterruptedException {
        wd.get(url);
        Thread.sleep(5000);
    }
}
