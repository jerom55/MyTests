package Topiframe.AppManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.Objects;

import static Topiframe.AppManager.NavigationHelper.screenshot;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NavigationOnWebsite {
  private WebDriver wd;
  public static Logger LOG = LoggerFactory.getLogger(NavigationOnWebsite.class);
  public NavigationOnWebsite(WebDriver wd) {
    this.wd = wd;
  }

  public void searchingLine (CharSequence service) throws Exception {
    searchingService(service);
    WebElement dev = wd.findElement(By.xpath("//div[@class='ListIcons_list-item-inner__1G5Cs']"));
    String atr = dev.getAttribute("innerText");
    LOG.info("Нажимаем на услугу = "+ atr);
    assertThat(atr, equalTo(service));
    dev.click();
    String atr1 = geTitleOfService();
    LOG.info("Сравниваем элементы '"+atr+"' и '"+atr1+"'");
    if (Objects.equals(atr, atr1)){
      LOG.info(atr1 + " = " + atr);
      // Переходим на главную страницу
      wd.findElement(By.cssSelector(".Breadcrumbs_link__2IY3m")).click();
    } else {
      LOG.error(atr + " не совпадает " + atr1);
      screenshot();
      Assert.fail();
    }
  }

  private String geTitleOfService() throws Exception {
    WebElement tests = null;
    try {
      tests = wd.findElement(By.cssSelector(".Heading_heading__njkQq.Heading_h2__3abFA.Service_heading__2CMBi"));
      return tests.getAttribute("innerText");
    } catch (Exception e) {
      LOG.error("Серис не доступен");
      screenshot();
      Assert.fail();
    }
    return tests.getAttribute("innerText");
  }

  public void navigateFromCatalogue () throws Exception {
    WebElement mobile = wd.findElement(By.xpath("//span[@title='Мобильная связь']"));
    String text = mobile.getAttribute("innerText");
    LOG.info("Нажимаем элемент '"+ text+"'");
    mobile.click();
    WebElement back = wd.findElement(By.xpath("//a[@itemprop='item']"));
    String atr = back.getAttribute("innerText");
    back.click();
    LOG.info("Нажимаем элемент '"+atr+"'");
    WebElement mobile2 = wd.findElement(By.xpath("//span[@title='Мобильная связь']"));
    String text2 = mobile2.getAttribute("innerText");
    LOG.info("Получаем название услуги '"+text2+ "'");
    try {
      assertThat(text, equalTo(text2));
      LOG.info(text + " = " + text2);
    } catch (Exception e) {
      LOG.error(text + " не совпадает " + text2);
      screenshot();
      Assert.fail();
    }
  }

  public void trySearchHiddenService (CharSequence service, String url) throws Exception {
    searchingService(service);
    try {
      WebElement services = (new WebDriverWait(wd, Duration.ofSeconds(5)))
          .until(ExpectedConditions.presenceOfElementLocated((By.xpath("//div[@class='ListIcons_list-item-inner__1G5Cs']"))));
      LOG.error("Услуга видима");
      screenshot();
      Assert.fail();
    } catch (Exception e) {
      LOG.info("Сервис доступен только по прямой ссылке");
      LOG.info("Переходим по ссылке на услугу '" + service+"'");
      wd.get(url);
      String atr = geTitleOfService();
      LOG.info("Сравниваем элементы '" + service + "' и '" + atr+"'");
      try {
        assertThat(service, equalTo(atr));
        LOG.info(service + " = " + atr);
      } catch (Exception r) {
        LOG.error("Услуга не доступна");
        screenshot();
        Assert.fail();
      }
    }
  }
  private void searchingService (CharSequence service) {
    WebElement line = wd.findElement(By.xpath("//input[@type='text']"));
    LOG.info("Ищем услугу = "+ service);
    line.sendKeys(Keys.CONTROL+"A");
    line.sendKeys(Keys.DELETE);
    line.sendKeys(service);
    line.sendKeys(Keys.ENTER);
  }
}
