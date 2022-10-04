package Topiframe.AppManager;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class Capcha {
    private WebDriver wd;
    public static Logger LOG = LoggerFactory.getLogger(Capcha.class);


    public Capcha(WebDriver wd) {
        this.wd = wd;
    }

    public void getCaptchaCode() throws InterruptedException {
        // Получаем код капчи
        String window = wd.getWindowHandle();
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("window.scrollBy(0,350)", "");
        WebElement pich = wd.findElement(By.cssSelector(".Service_captcha-img__2xct9"));
        String par = pich.getAttribute("currentSrc");
        wd.switchTo().newWindow(WindowType.TAB);
        wd.navigate().to(par + "&get_code=1");
        WebElement code = wd.findElement(By.tagName("body"));
        String text = code.getAttribute("innerText");
        String rCode = "null";
        Thread.sleep(1000);
        if (!Objects.equals(text, rCode)){
            LOG.info("Input capcha code = " + text);
            wd.close();
            Thread.sleep(1000);
            wd.switchTo().window(window);
            WebElement capcha = wd.findElement(By.xpath("//input[@name='captcha']"));
            capcha.sendKeys(text);
            //Thread.sleep(3000);
            String eq = capcha.getAttribute("defaultValue");
            assertThat(text, equalTo(eq));
        } else {
            LOG.error("Capcha code = null");
            wd.close();
            LOG.info("Get a new captcha code");
            Thread.sleep(2000);
            wd.switchTo().window(window);
            wd.findElement(By.xpath("//button[text()= 'Обновить картинку']")).click();
            Thread.sleep(1000);
            WebElement pich2 = wd.findElement(By.cssSelector(".Service_captcha-img__2xct9"));
            String par2 = pich2.getAttribute("currentSrc");
            wd.switchTo().newWindow(WindowType.TAB);
            wd.navigate().to(par2 + "&get_code=1");
            WebElement code2 = wd.findElement(By.tagName("body"));
            String text2 = code2.getAttribute("innerText");
            LOG.info("Input new capcha code = " + text2);
            wd.close();
            Thread.sleep(2000);
            wd.switchTo().window(window);
            WebElement capcha = wd.findElement(By.xpath("//input[@name='captcha']"));
            capcha.sendKeys(text2);
            Thread.sleep(3000);
            String eq = capcha.getAttribute("defaultValue");
            assertThat(text2, equalTo(eq));
        }
    }
}
