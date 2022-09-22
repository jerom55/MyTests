package Topiframe.AppManager;

import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


public class SourcePaymentData {
    private WebDriver wd;
    public static Logger LOG = LoggerFactory.getLogger(SourcePaymentData.class);

    public SourcePaymentData(WebDriver wd) {
        this.wd = wd;
    }

    public void setAmountPay(String amount) throws InterruptedException {
        //Вводим сумму платежа
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("window.scrollBy(0,350)", "");
        WebElement summ = wd.findElement(By.xpath("//input[@name='amount']"));
        summ.sendKeys(Keys.CONTROL+"A");
        summ.sendKeys(Keys.DELETE);
        summ.sendKeys(amount);
        LOG.info("TL_PRICE = "+ amount);
    }

    public void fillPaymentDetails(String pan, String exp, String holder, String cvv) throws InterruptedException {
        // Вводим ДДК
        WebElement ass = wd.findElement(By.cssSelector(".Service_front__2mu1u"));
        String atr = ass.getAttribute("tagName");
       if (atr != null){
           Assert.assertEquals(atr,"DIV");
           wd.findElement(By.xpath("//input[@name='Pan']")).sendKeys(pan);
           wd.findElement(By.xpath("//input[@name='ExpDate']")).sendKeys(exp);
           wd.findElement(By.xpath("//input[@name='CardHolder']")).sendKeys(holder);
           wd.findElement(By.xpath("//input[@name='CVC']")).sendKeys(cvv);
           LOG.info("TL_MORE_INFO_SM = "+ pan);
           amountCommission();
       } else {
           LOG.info("Dont see payment form");
           wd.quit();
       }
    }
    public void fillPhoneNumberFromYouPay (String phone) throws InterruptedException {
       WebElement sourcePhone =  wd.findElement(By.xpath("//input[@id='input-Phone']"));
        sourcePhone.sendKeys(Keys.CONTROL+"A");
        sourcePhone.sendKeys(Keys.DELETE);
        sourcePhone.sendKeys(phone);
        LOG.info("TL_MORE_INFO_SM = "+ phone);
        amountCommission();
    }

    private void amountCommission() throws InterruptedException {
        Thread.sleep(1500);
        WebElement comm = wd.findElement(By.cssSelector(".Service_inputsRow__1zBtT:nth-child(1) > .Form_value__cMLhf"));
        String commission = comm.getAttribute("innerText");
        WebElement tlAmount = wd.findElement(By.cssSelector(".Service_inputsRow__1zBtT:nth-child(2) > .Form_value__cMLhf"));
        String amount = tlAmount.getAttribute("innerText");
        LOG.info("TL_COMMISSION = "+ commission);
        LOG.info("TL_AMOUNT = "+ amount);
    }
}
