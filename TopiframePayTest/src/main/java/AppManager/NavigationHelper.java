package AppManager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

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
       // JavascriptExecutor js = (JavascriptExecutor) wd;
        // js.executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(2000);
        //wd.findElement(By.id("checkbox__offer")).click();
        WebElement box = wd.findElement(By.xpath("//input[@id='checkbox__offer']"));
        Actions actions = new Actions(wd);
        actions.moveToElement(box).clickAndHold().release().build().perform();
    }

    public void chanceSourcePaymentOnBK() throws InterruptedException {
        // Выбираем метод оплаты БК
        //wd.findElement(By.cssSelector(".Tabs_tab___fA6r:nth-child(2)")).click();By.xpath("//span[text()= 'С банковской карты']"
        WebElement paycard = wd.findElement(By.xpath("//span[text()= 'С банковской карты']"));
        if (isElementPresent(By.xpath("//span[text()= 'С банковской карты']"))){
            paycard.click();
        } else {
            LOG.error("Can't find element " + paycard);
            Assert.fail();
        }
    }
    public void chanceSourcePaymentOnMK(){
        wd.findElement(By.xpath("//span[text()= 'Со счёта мобильного телефона']")).click();
    }
    public void goGoodsUrl(String url) throws InterruptedException {
        wd.get(url);
    }

    public void insecurePage (){
        //Переход на небезопасную страницу
        WebElement confirm = (new WebDriverWait(wd, Duration.ofSeconds(40))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='details-button']"))));
       confirm.click();
        WebElement confirm2 = (new WebDriverWait(wd, Duration.ofSeconds(40))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='proceed-link']"))));
       confirm2.click();
    }
}
