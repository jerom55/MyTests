package Topiframe.AppManager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;

import static Topiframe.AppManager.NavigationHelper.*;

public class WaitingEndOfPayment {
    private WebDriver wd;
    public static Logger LOG = LoggerFactory.getLogger(WaitingEndOfPayment.class);

    public WaitingEndOfPayment(WebDriver wd) {
        this.wd = wd;
    }

    public void takeTransactionId() throws Exception {
        //Получаем номер транзакции
        LOG.info("Ожидаем создания транзакции");
        try {
            WebElement idTransaction = (new WebDriverWait(wd, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".Form_value__cMLhf"))));
            String transactionID = idTransaction.getAttribute("innerText");
            try {
                WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(60));
                WebElement status = wd.findElement(By.xpath("//div[@class='StatusPage_baddger-text__3h_Ls']"));
                wait.until(ExpectedConditions.attributeContains(status, "innerText", "Оплата успешно проведена."));
                String success = status.getAttribute("innerText");
                LOG.info(success);
                if (transactionID != null){
                    LOG.info("TL_ID = " + transactionID);
                } else {
                    LOG.error("Транззакция не создана");
                    screenshot();
                    Assert.fail();
                }
            } catch (Exception e) {
                WebElement status = wd.findElement(By.xpath("//div[@class='StatusPage_baddger-text__3h_Ls']"));
                String status1 = status.getAttribute("innerText");
                LOG.error(status1);
                LOG.info("TL_ID = " + transactionID);
                screenshot();
                Assert.fail();
            }
        } catch (Exception e){
            LOG.error("Транзакция не создана");
            screenshot();
            Assert.fail();
        }
    }
}
