package Widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class WidgetTest {
    @Test
    public void C65990() throws InterruptedException {
        WebDriver wd;
        System.setProperty("webdriver.chrome.driver", "F:\\AutoTest\\chrome\\chromedriver.exe");
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        try {
            wd.get("https://widget.nsc-tech.ru/debug/test.html");
            Thread.sleep(5000);
            System.out.println("Браузер запущен я молодец");
            Thread.sleep(5000);
            System.out.println("Всё устал, выключаюсь");
            wd.quit();
        } catch (Exception e){
            System.out.println("Не могу запуустит браузер");
        }
    }
}
