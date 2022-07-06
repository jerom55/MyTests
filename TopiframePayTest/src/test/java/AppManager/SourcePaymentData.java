package AppManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SourcePaymentData {
    private WebDriver wd;

    public SourcePaymentData(WebDriver wd) {
        this.wd = wd;
    }

    public void setAmountPay(String amount) throws InterruptedException {
        //Вводим сумму платежа
        WebElement summ = wd.findElement(By.xpath("//input[@name='amount']"));
        summ.sendKeys(amount);

    }

    public void fillPaymentDetails(String pan, String exp, String holder, String cvv) {
        // Вводим ДДК
        WebElement number = wd.findElement(By.xpath("//input[@name='Pan']"));
        number.sendKeys(pan);
        WebElement date = wd.findElement(By.xpath("//input[@name='ExpDate']"));
        date.sendKeys(exp);
        WebElement name = wd.findElement(By.xpath("//input[@name='CardHolder']"));
        name.sendKeys(holder);
        WebElement code = wd.findElement(By.xpath("//input[@name='CVC']"));
        code.sendKeys(cvv);
    }
}
