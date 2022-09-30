package Widget;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class WidgetTest {
    private WebDriver wd;


    public void C65990() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "F:\\AutoTest\\chrome\\chromedriver.exe");
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        try {
            wd.get("https://widget.nsc-tech.ru/debug/test.html");
            Thread.sleep(5000);
            System.out.println("Браузер запущен я молодец");
        } catch (Exception e){
            System.out.println("Не могу запуустит браузер");
        }
        wd.findElement(By.cssSelector("tr:nth-child(2) .selectBtn")).click();
        wd.findElement(By.id("url_new")).click();

        WebElement pan = wd.findElement(By.xpath("//input[@id='pan']"));
        pan.click();
        Thread.sleep(5000);
        pan.sendKeys("4111111111111111");
        //wd.findElement(By.xpath("//input[@id='pan']")).sendKeys("4111111111111111");
        wd.findElement(By.id("exp")).sendKeys("12/24");
        wd.findElement(By.id("cvc")).sendKeys("123");
        wd.findElement(By.id("__email_for_bill")).sendKeys("s.ivanov@ruru.ru");
        wd.findElement(By.cssSelector(".button > span")).click();
        Thread.sleep(10000);
    }
}
