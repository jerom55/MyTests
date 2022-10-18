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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NavigationOnWebsite {
  private WebDriver wd;
  public static Logger LOG = LoggerFactory.getLogger(NavigationOnWebsite.class);
  public NavigationOnWebsite(WebDriver wd) {
    this.wd = wd;
  }

  public void searchingLine (CharSequence service) {
    searchingService(service);
    WebElement dev = wd.findElement(By.xpath("//div[@class='ListIcons_list-item-inner__1G5Cs']"));
    String atr = dev.getAttribute("innerText");
    LOG.info("Click on service = "+ atr);
    assertThat(atr, equalTo(service));
    dev.click();
    String atr1 = geTitleOfService();
    LOG.info("Compare elements '"+atr+"' and '"+atr1+"'");
    assertThat(atr, equalTo(atr1));
    wd.findElement(By.cssSelector(".Breadcrumbs_link__2IY3m")).click();
  }

  private String geTitleOfService() {
    WebElement tests = null;
    try {
      tests = wd.findElement(By.cssSelector(".Heading_heading__njkQq.Heading_h2__3abFA.Service_heading__2CMBi"));
      return tests.getAttribute("innerText");
    } catch (Exception e) {
      LOG.error("Service is not available");
      Assert.fail();
    }
    return tests.getAttribute("innerText");
  }

  public void navigateFromCatalogue () {
    WebElement mobile = wd.findElement(By.xpath("//span[@title='Мобильная связь']"));
    String text = mobile.getAttribute("innerText");
    LOG.info("Click element '"+ text+"'");
    mobile.click();
    WebElement back = wd.findElement(By.xpath("//a[@itemprop='item']"));
    String atr = back.getAttribute("innerText");
    back.click();
    LOG.info("Click element '"+atr+"'");
    WebElement mobile2 = wd.findElement(By.xpath("//span[@title='Мобильная связь']"));
    String text2 = mobile2.getAttribute("innerText");
    LOG.info("Get attribute '"+text2+ "'");
    try {
      assertThat(text, equalTo(text2));
      LOG.info(text + " = " + text2);
    } catch (Exception e) {
      LOG.error(text + " no equalTo " + text2);
      Assert.fail();
    }
  }

  public void trySearchHiddenService (CharSequence service, String url) throws InterruptedException {
    searchingService(service);
    try {
      WebElement services = (new WebDriverWait(wd, Duration.ofSeconds(5)))
          .until(ExpectedConditions.presenceOfElementLocated((By.xpath("//div[@class='ListIcons_list-item-inner__1G5Cs']"))));
      LOG.error("Service is visible");
      Assert.fail();
    } catch (Exception e) {
      LOG.info("Service hidden use direct link");
      LOG.info("Go to service '" + service+"'");
      wd.get(url);
      String atr = geTitleOfService();
      LOG.info("Compare elements '" + service + "' and '" + atr+"'");
      try {
        assertThat(service, equalTo(atr));
        LOG.info(service + " = " + atr);
      } catch (Exception r) {
        LOG.error("Service is not available");
        Assert.fail();
      }
    }
  }
  private void searchingService (CharSequence service) {
    WebElement line = wd.findElement(By.xpath("//input[@type='search']"));
    LOG.info("Searching a service = "+ service);
    line.sendKeys(Keys.CONTROL+"A");
    line.sendKeys(Keys.DELETE);
    line.sendKeys(service);
    line.sendKeys(Keys.ENTER);
  }
}
