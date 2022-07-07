package AppManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

public class Capcha {
    private WebDriver wd;

    public Capcha(WebDriver wd) {
        this.wd = wd;
    }

    public void inputCapchCode(String text) throws InterruptedException {
        // Вставляем код капчи полученный ранее
        WebElement capcha = wd.findElement(By.xpath("//input[@name='captcha']"));
        capcha.sendKeys(text);
        Thread.sleep(3000);
    }

    public String takeCapchaCode() throws InterruptedException {
        // Получаем код капчи
        WebElement pich = wd.findElement(By.cssSelector(".Service_captcha-img__2xct9"));
        String par = pich.getAttribute("currentSrc");
        wd.switchTo().newWindow(WindowType.TAB);
        wd.navigate().to(par + "&get_code=1");
        WebElement code = wd.findElement(By.tagName("body"));
        String text = code.getAttribute("innerText");
        System.out.println(text);
        wd.close();
        return text;
    }
}
