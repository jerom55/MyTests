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

import static Topiframe.AppManager.NavigationHelper.screenshot;

public class ConfirmationPayment {
  public WebDriver wd;
  public static Logger LOG = LoggerFactory.getLogger(ConfirmationPayment.class);

  String message = "Страница подтверждения не доступна";

  public ConfirmationPayment(WebDriver wd) {
    this.wd = wd;
  }

  public void confirmationPayCode() throws Exception {
    try {
      WebElement confirm = (new WebDriverWait(wd, Duration.ofSeconds(30))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='password']"))));
      confirm.sendKeys("12345678");
      LOG.info("Вводим код подтверждения");
    } catch (Exception e) {
      LOG.error(message);
      screenshot();
      Assert.fail();
    }
  }

  public void confirmationPayButton() throws Exception {
    try {
      LOG.info("Нажимаем кнопку подтверждения");
      WebElement confirmBut = (new WebDriverWait(wd, Duration.ofSeconds(30))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='btn-half btn-success']"))));
      confirmBut.click();
    } catch (Exception e) {
      LOG.error(message);
      screenshot();
      Assert.fail();
    }
  }
}
