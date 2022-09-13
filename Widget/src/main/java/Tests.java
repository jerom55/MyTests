import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class Tests {

    @Test
    public void C65990() throws InterruptedException {
        WebDriver wd;
        System.setProperty("webdriver.chrome.driver", "F:\\AutoTest\\chrome\\chromedriver.exe");
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wd.get("https://widget.nsc-tech.ru/debug/test.html");
        Thread.sleep(10000);
        wd.quit();
    }

}
