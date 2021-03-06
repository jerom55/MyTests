package AppManager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class NavigationHelper {

    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void clickPayButton() throws InterruptedException {
        //Жмём кнопу оплаты
        wd.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);
        if (isElementPresent(By.xpath("//button[@type='submit']"))) {
            Assert.fail();
            wd.quit();
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void tickedCheckBox() throws InterruptedException {
        // Check-box ставим галочку
        Thread.sleep(2000);
        WebElement box = wd.findElement(By.xpath("//input[@type='checkbox']"));
        Actions actions = new Actions(wd);
        actions.moveToElement(box).clickAndHold().release().build().perform();
    }

    public void chanceSourcePayment() throws InterruptedException {
        // Выбираем метод оплаты БК
        wd.findElement(By.cssSelector(".Tabs_tab___fA6r:nth-child(3) > .Service_break-word__kCdwU")).click();
    }

    public void goGoodsUrl(String url) throws InterruptedException {
        wd.get(url);
    }
}
