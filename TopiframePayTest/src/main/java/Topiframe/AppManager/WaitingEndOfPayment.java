package Topiframe.AppManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class WaitingEndOfPayment {
    private WebDriver wd;
    public static Logger LOG = LoggerFactory.getLogger(WaitingEndOfPayment.class);
    public WaitingEndOfPayment(WebDriver wd) {
        this.wd = wd;
    }
    public String takeTransactionId() throws InterruptedException {
        //Thread.sleep(3000);
        WebElement idTransaction = (new WebDriverWait(wd, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".Form_value__cMLhf"))));
        String transId = idTransaction.getAttribute("innerText");
        LOG.info("TL_ID = " + transId);
       // System.out.println(transId);
        return transId;
    }
}
