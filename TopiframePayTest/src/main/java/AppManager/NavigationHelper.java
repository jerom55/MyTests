package AppManager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.lang.reflect.Method;
import java.time.Duration;

public class NavigationHelper {

    private WebDriver wd;
    public static Logger LOG = LoggerFactory.getLogger(NavigationHelper.class);

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void clickPayButton() throws InterruptedException {
        //Жмём кнопу оплаты
        wd.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);
        if (isElementPresent(By.xpath("//button[@type='submit']"))) {
            LOG.error("clickPayButton - Pay button is visible");
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
        //wd.findElement(By.cssSelector(".Tabs_tab___fA6r:nth-child(2)")).click();By.xpath("//span[text()= 'С банковской карты']"
        WebElement paycard = wd.findElement(By.cssSelector(".Tabs_tab___fA6r:nth-child(2)"));
        if (isElementPresent(By.cssSelector(".Tabs_tab___fA6r:nth-child(2)"))){
            paycard.click();
        } else {
            LOG.error("Can't find element " + paycard);
            Assert.fail();
        }
    }
    public void goGoodsUrl(String url) throws InterruptedException {
        wd.get(url);
    }
}