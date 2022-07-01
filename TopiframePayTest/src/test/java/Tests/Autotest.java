package Tests;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Autotest {
    private WebDriver wd;

    @BeforeMethod
    public void setUp()  {
        System.setProperty("webdriver.chrome.driver", "C:\\soft\\chrome\\chromedriver.exe");
        wd = new ChromeDriver();

    }

    @Test
    public void C65990() throws InterruptedException {
        String window = wd.getWindowHandle();
        wd.get("https://topiframe.nsc-tech.ru/init-payment/9463");
        Thread.sleep(5000);
        // Вводим номер телефона который пополняем
        WebElement phone = wd.findElement(By.xpath("//input[@type='tel']"));
        phone.sendKeys("9032582114");
        Thread.sleep(1000);
        // Выбираем метод оплаты БК
        WebElement bk = wd.findElement(By.xpath("//*[text()='С банковской карты']"));
        bk.click();
        Thread.sleep(3000);
        // Вводим данные карты
        WebElement pan = wd.findElement(By.xpath("//input[@name='Pan']"));
        pan.sendKeys("2200000000000004");
        WebElement exp = wd.findElement(By.xpath("//input[@name='ExpDate']"));
        exp.sendKeys("1224");
        WebElement name = wd.findElement(By.xpath("//input[@name='CardHolder']"));
        name.sendKeys("SEGREI IVANOV");
        WebElement cvv = wd.findElement(By.xpath("//input[@name='CVC']"));
        cvv.sendKeys("123");
        //Вводим сумму платежа
        WebElement amount = wd.findElement(By.xpath("//input[@name='amount']"));
        amount.sendKeys("500");
        Thread.sleep(2000);
        // Check-box
        WebElement box = wd.findElement(By.xpath("//input[@type='checkbox']"));
        Actions actions = new Actions(wd);
        actions.moveToElement(box).clickAndHold().release().build().perform();
        Thread.sleep(2000);
        // Код капчи
        WebElement pich = wd.findElement(By.cssSelector(".Service_captcha-img__2xct9"));
        String par = pich.getAttribute("currentSrc");
        wd.switchTo().newWindow(WindowType.TAB);
        wd.navigate().to(par + "&get_code=1");
        Thread.sleep(2000);
        WebElement code = wd.findElement(By.tagName("body"));
        String text = code.getAttribute("innerText");
        System.out.println(text);
        wd.close();
        wd.switchTo().window(window);
        // Вставляем код капчи
        Thread.sleep(2000);
        WebElement capcha = wd.findElement(By.xpath("//input[@name='captcha']"));
        capcha.sendKeys(text);
        Thread.sleep(3000);
        // оплата
        WebElement pay = wd.findElement(By.xpath("//button[@type='submit']"));
        pay.click();
        Thread.sleep(10000);
    }

    @AfterMethod
    public void tearDown() { wd.quit();
    }
}

