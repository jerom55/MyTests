package Topiframe.AppManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ConfirmationPayment {
    public WebDriver wd;
    public static Logger LOG = LoggerFactory.getLogger(ConfirmationPayment.class);

    public ConfirmationPayment(WebDriver wd) {
        this.wd = wd;
    }

    public void confirmationPayCode() {
        String message = "The confirmation page is not available";
        try {
            WebElement confirm = (new WebDriverWait(wd, Duration.ofSeconds(30))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='password']"))));
            confirm.sendKeys("12345678");
        } catch (Exception e) {
            LOG.error(message);
        }
    }
    public void confirmationPayButton() {
        String message = "The confirmation page is not available";
    try {
        WebElement confirmBut = (new WebDriverWait(wd, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='btn-half btn-success']"))));
        confirmBut.click();
    }
       catch (Exception e){
           LOG.error(message);
       }
    }
}
