package Tests;

import org.testng.annotations.Test;

public class AutoTest extends TestBase {

    @Test
    public void C65990() throws InterruptedException {
        //Оплата услуги с БК без 3ds
        app.getGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        String window = app.wd.getWindowHandle();
        app.fillPhoneNumber("9032582114");
        app.chanceSourcePayment();
        app.fillPaymentDetails("2200000000000004", "1224", "SERGEI IVANOV", "123");
        app.setAmountPay("555");
        app.tickedCheckBox();
        String text = app.getCapchaCode();
        app.wd.switchTo().window(window);
        app.inputCapchCode(text);
        app.clickPayButton();
        app.waitingEndPayment();
    }

    @Test
    public void C65991() throws InterruptedException {
        //Оплата услуги с БК c 3dsV1
        app.getGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        String window = app.wd.getWindowHandle();
        app.fillPhoneNumber("9032582115");
        app.chanceSourcePayment();
        app.fillPaymentDetails("639002000000000003", "1224", "SERGEI IVANOV", "123");
        app.setAmountPay("555");
        app.tickedCheckBox();
        String text = app.getCapchaCode();
        app.wd.switchTo().window(window);
        app.inputCapchCode(text);
        app.clickPayButton();
        app.confirmationPayCode();
        app.waitingEndPayment();
    }
    @Test
    public void C65992()throws InterruptedException {
        //Оплата услуги с БК c 3dsV2
        app.getGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        String window = app.wd.getWindowHandle();
        app.fillPhoneNumber("9032582115");
        app.chanceSourcePayment();
        app.fillPaymentDetails("5555555555555599", "1224", "SERGEI IVANOV", "123");
        app.setAmountPay("555");
        app.tickedCheckBox();
        String text = app.getCapchaCode();
        app.wd.switchTo().window(window);
        app.inputCapchCode(text);
        app.clickPayButton();
        app.confirmationPayButton();
        app.waitingEndPayment();
    }
}

