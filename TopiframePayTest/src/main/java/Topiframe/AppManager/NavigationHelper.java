package Topiframe.AppManager;

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
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(20));
        WebElement button = wd.findElement(By.xpath("//button[@type='submit']"));
        button.click();
        LOG.info("Press the payment button");
        try {
            wait.until(ExpectedConditions.stalenessOf(button));
        } catch (Exception e){
            LOG.error("Pay button is visible");
            Assert.fail();
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
        WebElement box = wd.findElement(By.xpath("//input[@id='checkbox__offer']"));
        Actions actions = new Actions(wd);
        actions.moveToElement(box).clickAndHold().release().build().perform();
        LOG.info("Set the checkbox");
    }
    public void tickedCheckBoxAgregator() throws InterruptedException {
        // Check-box ставим галочку
        Thread.sleep(2000);
        WebElement box = wd.findElement(By.xpath("//input[@id='checkbox__offer']"));
        Actions actions = new Actions(wd);
        actions.moveToElement(box).clickAndHold().release().build().perform();
        Thread.sleep(3000);
        LOG.info("Set the checkbox");
    }

    public void chanceSourcePaymentOnBK() throws InterruptedException {
        // Выбираем метод оплаты БК
        if (isElementPresent(By.xpath("//span[text()= 'С банковской карты']"))) {
            WebElement payCard = wd.findElement(By.xpath("//span[text()= 'С банковской карты']"));
            payCard.click();
        } else {
            LOG.error("Can't find element Pay from BK");
            Assert.fail();
        }
    }

    public void chanceSourcePaymentOnMK() {
        // Выбираем метод оплаты МК
        wd.findElement(By.xpath("//span[text()= 'Со счёта мобильного телефона']")).click();
    }

    public void goGoodsUrl(String url) throws InterruptedException {
        // ВВодим адрес страницы
        wd.get(url);
    }

    public void insecurePage() {
        //Переход на небезопасную страницу
        String safety = "Safety page is not available";
        try {
            WebElement confirm = (new WebDriverWait(wd, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='details-button']"))));
            confirm.click();
            WebElement confirm2 = (new WebDriverWait(wd, Duration.ofSeconds(1))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='proceed-link']"))));
            confirm2.click();
        } catch (Exception e) {
            LOG.info(safety);
        }
    }
    public void chequeEmail(String email){
        LOG.info("Set the email to receive cheque = "+ email);
        wd.findElement(By.xpath("//input[@name='cheques.email']")).sendKeys(email);
    }
}
