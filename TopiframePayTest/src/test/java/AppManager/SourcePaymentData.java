package AppManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class SourcePaymentData {
    private WebDriver wd;

    public SourcePaymentData(WebDriver wd) {
        this.wd = wd;
    }

    public void setAmountPay(String amount) throws InterruptedException {
        //Вводим сумму платежа
        WebElement summ = wd.findElement(By.xpath("//input[@name='amount']"));
        summ.sendKeys(Keys.CONTROL+"A");
        summ.sendKeys(Keys.DELETE);
        summ.sendKeys(amount);
    }

    public void fillPaymentDetails(String pan, String exp, String holder, String cvv) {
        // Вводим ДДК
        WebElement ass = wd.findElement(By.cssSelector(".Service_front__2mu1u"));
        String atr = ass.getAttribute("tagName");
       if (atr != null){
           Assert.assertEquals(atr,"DIV");
       } else {
           wd.quit();
       }
        wd.findElement(By.xpath("//input[@name='Pan']")).sendKeys(pan);
        wd.findElement(By.xpath("//input[@name='ExpDate']")).sendKeys(exp);
        wd.findElement(By.xpath("//input[@name='CardHolder']")).sendKeys(holder);
        wd.findElement(By.xpath("//input[@name='CVC']")).sendKeys(cvv);
    }
}
