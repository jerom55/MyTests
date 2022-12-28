package Topiframe.AppManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NavigationHelper {

  private static WebDriver wd;
  public static Logger LOG = LoggerFactory.getLogger(NavigationHelper.class);
  public NavigationHelper(WebDriver wd) {
    NavigationHelper.wd = wd;
  }

  public void clickPayButton() throws Exception {
    //Жмём кнопу оплаты
    WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(40));
    WebElement button = wd.findElement(By.xpath("//button[@type='submit']"));
    try {
      wait.until(ExpectedConditions.elementToBeClickable(button));
      button.click();
      LOG.info("Нажимаем кнопку оплаты");
    } catch (Exception e) {
      LOG.error("Кнопка оплаты не активна");
      Assert.fail();
    }
    try {
      wait.until(ExpectedConditions.stalenessOf(button));
    } catch (Exception e) {
      LOG.error("Кнопка оплаты видима");
      screenshot();
      Assert.fail();
    }
  }

  public void tickedCheckBoxClickPayButton() throws Exception {
    // Check-box ставим галочку
    commission();
    Thread.sleep(2000);
    WebElement box = wd.findElement(By.xpath("//input[@id='checkbox__offer']"));
    Actions actions = new Actions(wd);
    actions.moveToElement(box).clickAndHold().release().build().perform();
    LOG.info("Ставим галочку чек-бокса");
    clickPayButton();
  }

  public void goGoodsUrl(String url) throws InterruptedException {
    // ВВодим адрес страницы
    wd.get(url);
  }

  public void insecurePage() {
    //Переход на небезопасную страницу
    String safety = "Страница безопасности не доступна";
    try {
      WebElement confirm = (new WebDriverWait(wd, Duration.ofSeconds(30))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='details-button']"))));
      confirm.click();
      WebElement confirm2 = (new WebDriverWait(wd, Duration.ofSeconds(1))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='proceed-link']"))));
      confirm2.click();
      WebElement confirm3 = (new WebDriverWait(wd, Duration.ofSeconds(5))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@testid='redirect-url']"))));
      confirm3.click();
    } catch (Exception e) {
      LOG.info(safety);
    }
  }

  public void chequeEmail(String email) {
    try {
      wd.findElement(By.xpath("//input[@name='cheques.email']")).sendKeys(email);
      LOG.info("Указываем e-mail для электронного чека = " + email);
    } catch (Exception e) {
      LOG.info("Электронный чек не требуется");
    }
  }
  public boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
  private void commission() throws Exception {
    if (isElementPresent(By.cssSelector(".SmartInput_textFail__37qzX"))){
      LOG.error("Введённая сумма не входит в допустимый диапазон");
      screenshot();
      Assert.fail();
    }
    LOG.info("Получаем стоимость услуги с комиссией");
    WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
    try {
      wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".Service_inputsRow__1zBtT:nth-child(1) > .Form_value__cMLhf")));
      WebElement comm = wd.findElement(By.cssSelector(".Service_inputsRow__1zBtT:nth-child(1) > .Form_value__cMLhf"));
      String commission = comm.getAttribute("innerText");
      LOG.info("TL_COMMISSION = " + commission);
      WebElement tlAmount = wd.findElement(By.cssSelector(".Service_inputsRow__1zBtT:nth-child(2) > .Form_value__cMLhf"));
      String amount = tlAmount.getAttribute("innerText");
      LOG.info("TL_AMOUNT = " + amount);
    }catch (Exception e) {
      LOG.error("Блок комиссий не доступен");
      //Делаем скрин
      screenshot();
      Assert.fail();
    }
  }
  public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
    //Convert web driver object to TakeScreenshot
    TakesScreenshot scrShot =((TakesScreenshot)webdriver);
    //Call getScreenshotAs method to create image file
    File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
    //Move image file to new destination
    File DestFile=new File(fileWithPath);
    //Copy file at destination
    FileUtils.copyFile(SrcFile, DestFile);
  }
  public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM_HH:mm:ss");

  public static String getRandomFileName() {
    return dateTimeFormatter.format(LocalDateTime.now()).replaceAll("[-: ]", "");
  }
  public static void screenshot() throws Exception {
    takeSnapShot(wd, "F:\\GitHub\\MyTests\\TopiframePayTest\\" +
        "Screenshots\\"+getRandomFileName()+".png") ;
  }
}


