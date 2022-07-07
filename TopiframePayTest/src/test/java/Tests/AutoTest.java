package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AutoTest extends TestBase {

    @Test
    public void C65990() throws InterruptedException {
        //Оплата услуги с БК без 3ds
        app.getNavigationHelper().getGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        String window = app.getConfirmationPayment().wd.getWindowHandle();
        app.getRefillablePhoneNumber().fillPhoneNumber("9032582114");
        app.getNavigationHelper().chanceSourcePayment();
        app.getSourcePaymentData().fillPaymentDetails("2200000000000004", "1224", "SERGEI IVANOV", "123");
        app.getSourcePaymentData().setAmountPay("555");
        app.getNavigationHelper().tickedCheckBox();
        String text = app.getCapcha().getCapchaCode();
        app.getConfirmationPayment().wd.switchTo().window(window);
        app.getCapcha().inputCapchCode(text);
        app.getNavigationHelper().clickPayButton();
        app.waitingEndOfPayment();
    }

    @Test
    public void C65991() throws InterruptedException {
        //Оплата услуги с БК c 3dsV1
        app.getNavigationHelper().getGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        String window = app.getConfirmationPayment().wd.getWindowHandle();
        app.getRefillablePhoneNumber().fillPhoneNumber("9032582115");
        app.getNavigationHelper().chanceSourcePayment();
        app.getSourcePaymentData().fillPaymentDetails("639002000000000003", "1224", "SERGEI IVANOV", "123");
        app.getSourcePaymentData().setAmountPay("555");
        app.getNavigationHelper().tickedCheckBox();
        String text = app.getCapcha().getCapchaCode();
        app.getConfirmationPayment().wd.switchTo().window(window);
        app.getCapcha().inputCapchCode(text);
        app.getNavigationHelper().clickPayButton();
        app.getConfirmationPayment().confirmationPayCode();
        app.waitingEndOfPayment();
    }
    @Test
    public void C65992()throws InterruptedException {
        //Оплата услуги с БК c 3dsV2
        app.getNavigationHelper().getGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        String window = app.getConfirmationPayment().wd.getWindowHandle();
        app.getRefillablePhoneNumber().fillPhoneNumber("9032582115");
        app.getNavigationHelper().chanceSourcePayment();
        app.getSourcePaymentData().fillPaymentDetails("5555555555555599", "1224", "SERGEI IVANOV", "123");
        app.getSourcePaymentData().setAmountPay("555");
        app.getNavigationHelper().tickedCheckBox();
        String text = app.getCapcha().getCapchaCode();
        app.getConfirmationPayment().wd.switchTo().window(window);
        app.getCapcha().inputCapchCode(text);
        app.getNavigationHelper().clickPayButton();
        app.getConfirmationPayment().confirmationPayButton();
        app.waitingEndOfPayment();
    }
}

