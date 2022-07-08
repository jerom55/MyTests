package AppManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitingEndOfPayment {
    private WebDriver wd;
    public WaitingEndOfPayment(WebDriver wd) {
        this.wd = wd;
    }
    public String takeTransactionId() throws InterruptedException {
        Thread.sleep(3000);
        WebElement idTransaction = (new WebDriverWait(wd, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".Form_value__cMLhf"))));
        String transId = idTransaction.getAttribute("innerText");
        System.out.println(transId);
        return transId;
    }
}
