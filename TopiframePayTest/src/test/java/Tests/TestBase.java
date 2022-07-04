package Tests;

import Application.ApplicationMeneger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase extends ApplicationMeneger {
    public WebDriver wd;

    @BeforeMethod
    public void setUp() {
        startTest();

    }

    public void startTest() {
        System.setProperty("webdriver.chrome.driver", "F:\\AutoTest\\chrome\\chromedriver.exe");
        wd = new ChromeDriver();
    }

    public void clickPayButton() throws InterruptedException {
        // Жмём кнопу оплаты
        WebElement pay = wd.findElement(By.xpath("//button[@type='submit']"));
        pay.click();
        Thread.sleep(10000);
    }

    public void inputCapchCode(String text) throws InterruptedException {
        // Вставляем код капчи полученный ранее
        Thread.sleep(2000);
        WebElement capcha = wd.findElement(By.xpath("//input[@name='captcha']"));
        capcha.sendKeys(text);
        Thread.sleep(3000);
    }

    public String getCapchaCode() throws InterruptedException {
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

    public void tickedCheckBox() throws InterruptedException {
        // Check-box ставим галочку
        WebElement box = wd.findElement(By.xpath("//input[@type='checkbox']"));
        Actions actions = new Actions(wd);
        actions.moveToElement(box).clickAndHold().release().build().perform();
        Thread.sleep(2000);
    }

    public void setAmountPay(String summ) throws InterruptedException {
        //Вводим сумму платежа
        WebElement amount = wd.findElement(By.xpath("//input[@name='amount']"));
        amount.sendKeys(summ);
        Thread.sleep(2000);
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

    public void chanceSourcePayment() throws InterruptedException {
        // Выбираем метод оплаты БК
        WebElement bk = wd.findElement(By.xpath("//*[text()='С банковской карты']"));
        bk.click();
        Thread.sleep(3000);
    }

    public void fillPhoneNumber(String targetPhone) throws InterruptedException {
        // Вводим номер телефона который пополняем
        WebElement phone = wd.findElement(By.xpath("//input[@type='tel']"));
        phone.sendKeys(targetPhone);
        Thread.sleep(1000);
    }

    public void getGoodsUrl(String url) throws InterruptedException {
        wd.get(url);
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() {
        stopTest();
    }

    public void stopTest() {
        wd.quit();
    }

    public void confirmationPay() {

        WebElement confirm = (new WebDriverWait(wd, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='password']"))));
        confirm.sendKeys("12345678");
    }

    public void waitingEndPayment() throws InterruptedException {
        Thread.sleep(10000);
        WebElement confirmButton = wd.findElement(By.xpath("//div[@class='StatusPage_baddger-text__3h_Ls']"));
        String succes = confirmButton.getAttribute("innerText");
        System.out.println(succes);
        Assert.assertEquals(succes, "Оплата успешно проведена.", "Test Fail");
    }

}
