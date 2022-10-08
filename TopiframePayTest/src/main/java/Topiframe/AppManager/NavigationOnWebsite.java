package Topiframe.AppManager;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class NavigationOnWebsite {
  private WebDriver wd;
  public static Logger LOG = LoggerFactory.getLogger(NavigationOnWebsite.class);
  public NavigationOnWebsite(WebDriver wd) {
    this.wd = wd;
  }

  public void searchingLine (CharSequence service) {
    WebElement line = wd.findElement(By.xpath("//input[@type='search']"));
    LOG.info("Searching a service = "+ service);
    line.sendKeys(Keys.CONTROL+"A");
    line.sendKeys(Keys.DELETE);
    line.sendKeys(service);
    line.sendKeys(Keys.ENTER);
    WebElement dev = wd.findElement(By.xpath("//div[@class='ListIcons_list-item-inner__1G5Cs']"));
    String atr = dev.getAttribute("innerText");
    LOG.info("Click on service = "+ atr);
    dev.click();
    WebElement tests = wd.findElement(By.cssSelector(".Heading_heading__njkQq.Heading_h2__3abFA.Service_heading__2CMBi"));
    String atr1 = tests.getAttribute("innerText");
    LOG.info("Compare elements "+atr+" and ");
    assertThat(atr, equalTo(atr1));

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
    }
  }
}
