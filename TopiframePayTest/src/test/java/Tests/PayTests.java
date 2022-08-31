package Tests;


import AppManager.Retry;
import TestBase.TestBase;
import org.testng.annotations.Test;

public class PayTests extends TestBase {

    @Test ( retryAnalyzer = Retry.class,
            description = "Оплата услуги с БК без 3ds")

    public void C65990() throws InterruptedException {
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        String window = app.getConfirmationPayment().wd.getWindowHandle();
        app.getRefillablePhoneNumber().fillPhoneNumber("9032582114");
        app.getNavigationHelper().chanceSourcePaymentOnBK();
        app.getSourcePaymentData().fillPaymentDetails("2200000000000004", "1224", "SERGEI IVANOV", "123");
        app.getSourcePaymentData().setAmountPay("555");
        app.getNavigationHelper().tickedCheckBox();
        String text = app.getCapcha().takeCapchaCode();
        app.getConfirmationPayment().wd.switchTo().window(window);
        app.getCapcha().inputCapchCode(text);
        app.getNavigationHelper().clickPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }

    @Test ( retryAnalyzer = Retry.class,
            description = "Оплата услуги с БК c 3dsV1")

    public void C65991() throws InterruptedException {
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        String window = app.getConfirmationPayment().wd.getWindowHandle();
        app.getRefillablePhoneNumber().fillPhoneNumber("9032582115");
        app.getNavigationHelper().chanceSourcePaymentOnBK();
        app.getSourcePaymentData().fillPaymentDetails("639002000000000003", "1224", "SERGEI IVANOV", "123");
        app.getSourcePaymentData().setAmountPay("555");
        app.getNavigationHelper().tickedCheckBox();
        String text = app.getCapcha().takeCapchaCode();
        app.getConfirmationPayment().wd.switchTo().window(window);
        app.getCapcha().inputCapchCode(text);
        app.getNavigationHelper().clickPayButton();
        app.getConfirmationPayment().confirmationPayCode();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
    @Test ( retryAnalyzer = Retry.class,
            description = "Оплата услуги с БК c 3dsV2")

    public void C65992()throws InterruptedException {
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        String window = app.getConfirmationPayment().wd.getWindowHandle();
        app.getRefillablePhoneNumber().fillPhoneNumber("9032582115");
        app.getNavigationHelper().chanceSourcePaymentOnBK();
        app.getSourcePaymentData().fillPaymentDetails("5555555555555599", "1224", "SERGEI IVANOV", "123");
        app.getSourcePaymentData().setAmountPay("555");
        app.getNavigationHelper().tickedCheckBox();
        String text = app.getCapcha().takeCapchaCode();
        app.getConfirmationPayment().wd.switchTo().window(window);
        app.getCapcha().inputCapchCode(text);
        app.getNavigationHelper().clickPayButton();
        app.getNavigationHelper().insecurePage();
        app.getConfirmationPayment().confirmationPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
    @Test ( retryAnalyzer = Retry.class,
            description = "Payment MK - TELE2")

    public void C65993()throws InterruptedException{
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        String window = app.getConfirmationPayment().wd.getWindowHandle();
        app.getRefillablePhoneNumber().fillPhoneNumber("9032582115");
        app.getNavigationHelper().chanceSourcePaymentOnMK();
        app.getSourcePaymentData().fillPhoneNumberFromYouPay("9253703998");
        app.getSourcePaymentData().setAmountPay("555");
        app.getNavigationHelper().tickedCheckBox();
        String text = app.getCapcha().takeCapchaCode();
        app.getConfirmationPayment().wd.switchTo().window(window);
        app.getCapcha().inputCapchCode(text);
        app.getNavigationHelper().clickPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
    @Test ( retryAnalyzer = Retry.class,
            description = "Transfer to card - VISA")

    public void C65994()throws InterruptedException{
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/8003");
        String window = app.getConfirmationPayment().wd.getWindowHandle();
        app.getRefillablePhoneNumber().fillCardNumber("4111111111111111");
        app.getSourcePaymentData().setAmountPay("555");
        String text = app.getCapcha().takeCapchaCode();
        app.getConfirmationPayment().wd.switchTo().window(window);
        app.getCapcha().inputCapchCode(text);
        app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582114");
        app.getNavigationHelper().tickedCheckBox();
        Thread.sleep(3000);
        app.getNavigationHelper().clickPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
    @Test ( retryAnalyzer = Retry.class,
            description = "Transfer to card - Maestro")

    public void C65995()throws InterruptedException{
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/8003");
        String window = app.getConfirmationPayment().wd.getWindowHandle();
        app.getRefillablePhoneNumber().fillCardNumber("639002000000000003");
        app.getSourcePaymentData().setAmountPay("555");
        String text = app.getCapcha().takeCapchaCode();
        app.getConfirmationPayment().wd.switchTo().window(window);
        app.getCapcha().inputCapchCode(text);
        app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582114");
        app.getNavigationHelper().tickedCheckBox();
        Thread.sleep(3000);
        app.getNavigationHelper().clickPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
    @Test ( retryAnalyzer = Retry.class,
            description = "Transfer to card - MasterCard")

    public void C65996()throws InterruptedException{
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/8003");
        String window = app.getConfirmationPayment().wd.getWindowHandle();
        app.getRefillablePhoneNumber().fillCardNumber("5100000000000008");
        app.getSourcePaymentData().setAmountPay("555");
        String text = app.getCapcha().takeCapchaCode();
        app.getConfirmationPayment().wd.switchTo().window(window);
        app.getCapcha().inputCapchCode(text);
        app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582114");
        app.getNavigationHelper().tickedCheckBox();
        Thread.sleep(3000);
        app.getNavigationHelper().clickPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
    @Test ( retryAnalyzer = Retry.class,
            description = "Transfer to card - MIR")

    public void C65997()throws InterruptedException{
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/8003");
        String window = app.getConfirmationPayment().wd.getWindowHandle();
        app.getRefillablePhoneNumber().fillCardNumber("2200000000000004");
        app.getSourcePaymentData().setAmountPay("555");
        String text = app.getCapcha().takeCapchaCode();
        app.getConfirmationPayment().wd.switchTo().window(window);
        app.getCapcha().inputCapchCode(text);
        app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582114");
        app.getNavigationHelper().tickedCheckBox();
        Thread.sleep(3000);
        app.getNavigationHelper().clickPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
}

