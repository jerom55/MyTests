package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AutoTest {
    private WebDriver wd;

    @BeforeMethod
    public void setUp()  {
        System.setProperty("webdriver.chrome.driver", "F:\\AutoTest\\chrome\\chromedriver.exe");
        wd = new ChromeDriver();

    }

    @Test
    public void C65990() throws InterruptedException {
        //Оплата услуги с БК без 3ds
        getGoodUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        String window = wd.getWindowHandle();
        fillPhoneNumber("9032582114");
        chanceSourcePayment();
        fillPaymentDetails(new ddk("2200000000000004", "1224", "SERGEI IVANOV", "123"));
        setAmountPay("500");
        tickedCheckBox();
        String text = getCapchaCode();
        wd.switchTo().window(window);
        inputCapchCode(text);
        clickPayButton();
    }

    private void clickPayButton() throws InterruptedException {
        // Жмём кнопу оплаты
        WebElement pay = wd.findElement(By.xpath("//button[@type='submit']"));
        pay.click();
        Thread.sleep(10000);
    }

    private void inputCapchCode(String text) throws InterruptedException {
        // Вставляем код капчи полученный ранее
        Thread.sleep(2000);
        WebElement capcha = wd.findElement(By.xpath("//input[@name='captcha']"));
        capcha.sendKeys(text);
        Thread.sleep(3000);
    }

    private String getCapchaCode() throws InterruptedException {
        // Получаем код капчи
        WebElement pich = wd.findElement(By.cssSelector(".Service_captcha-img__2xct9"));
        String par = pich.getAttribute("currentSrc");
        wd.switchTo().newWindow(WindowType.TAB);
        wd.navigate().to(par + "&get_code=1");
        Thread.sleep(2000);
        WebElement code = wd.findElement(By.tagName("body"));
        String text = code.getAttribute("innerText");
        System.out.println(text);
        wd.close();
        return text;
    }

    private void tickedCheckBox() throws InterruptedException {
        // Check-box ставим галочку
        WebElement box = wd.findElement(By.xpath("//input[@type='checkbox']"));
        Actions actions = new Actions(wd);
        actions.moveToElement(box).clickAndHold().release().build().perform();
        Thread.sleep(2000);
    }

    private void setAmountPay(String summ) throws InterruptedException {
        //Вводим сумму платежа
        WebElement amount = wd.findElement(By.xpath("//input[@name='amount']"));
        amount.sendKeys(summ);
        Thread.sleep(2000);
    }

    private void fillPaymentDetails(ddk ddk) {
        // Вводим ДДК
        WebElement number = wd.findElement(By.xpath("//input[@name='Pan']"));
        number.sendKeys(ddk.getPan());
        WebElement date = wd.findElement(By.xpath("//input[@name='ExpDate']"));
        date.sendKeys(ddk.getExp());
        WebElement name = wd.findElement(By.xpath("//input[@name='CardHolder']"));
        name.sendKeys(ddk.getHolder());
        WebElement code = wd.findElement(By.xpath("//input[@name='CVC']"));
        code.sendKeys(ddk.getCvv());
    }

    private void chanceSourcePayment() throws InterruptedException {
        // Выбираем метод оплаты БК
        WebElement bk = wd.findElement(By.xpath("//*[text()='С банковской карты']"));
        bk.click();
        Thread.sleep(3000);
    }

    private void fillPhoneNumber(String targetPhone) throws InterruptedException {
        // Вводим номер телефона который пополняем
        WebElement phone = wd.findElement(By.xpath("//input[@type='tel']"));
        phone.sendKeys(targetPhone);
        Thread.sleep(1000);
    }

    private void getGoodUrl(String url) throws InterruptedException {
        wd.get(url);
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() { wd.quit();
    }
}

