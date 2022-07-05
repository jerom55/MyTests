package AppManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmationPayment {
    public WebDriver wd;

    public ConfirmationPayment(WebDriver wd) {
        this.wd = wd;
    }

    public void confirmationPayCode() {

        WebElement confirm = (new WebDriverWait(wd, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='password']"))));
        confirm.sendKeys("12345678");
    }

    public void confirmationPayButton() {

        WebElement confirmBut = (new WebDriverWait(wd, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='btn-half btn-success']"))));
        confirmBut.click();
    }
}
