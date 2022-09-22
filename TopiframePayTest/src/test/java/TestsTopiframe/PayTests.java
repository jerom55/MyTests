package TestsTopiframe;


import Topiframe.AppManager.Retry;
import Topiframe.TestBase.TestBase;
import org.testng.annotations.Test;

public class PayTests extends TestBase {

   @Test ( retryAnalyzer = Retry.class,
            description = "Оплата услуги с БК без 3ds")

    public void C6000() throws InterruptedException {
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        app.getRefillablePhoneNumber().fillPhoneNumber("9032582114");
        app.getNavigationHelper().chanceSourcePaymentOnBK();
        app.getSourcePaymentData().setAmountPay("555");
        app.getSourcePaymentData().fillPaymentDetails("2200000000000004", "1224", "SERGEI IVANOV", "123");
        app.getNavigationHelper().tickedCheckBox();
        app.getCapcha().getCaptchaCode();
        app.getNavigationHelper().clickPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }

    @Test ( retryAnalyzer = Retry.class,
            description = "Оплата услуги с БК c 3dsV1")

    public void C6001() throws InterruptedException {
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        app.getRefillablePhoneNumber().fillPhoneNumber("9032582115");
        app.getNavigationHelper().chanceSourcePaymentOnBK();
        app.getSourcePaymentData().setAmountPay("555");
        app.getSourcePaymentData().fillPaymentDetails("639002000000000003", "1224", "SERGEI IVANOV", "123");
        app.getNavigationHelper().tickedCheckBox();
        app.getCapcha().getCaptchaCode();
        app.getNavigationHelper().clickPayButton();
        app.getConfirmationPayment().confirmationPayCode();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
    @Test ( retryAnalyzer = Retry.class,
            description = "Оплата услуги с БК c 3dsV2")

    public void C6002()throws InterruptedException {
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        app.getRefillablePhoneNumber().fillPhoneNumber("9032582115");
        app.getNavigationHelper().chanceSourcePaymentOnBK();
        app.getSourcePaymentData().setAmountPay("555");
        app.getSourcePaymentData().fillPaymentDetails("5555555555555599", "1224", "SERGEI IVANOV", "123");
        app.getNavigationHelper().tickedCheckBox();
        app.getCapcha().getCaptchaCode();
        app.getNavigationHelper().clickPayButton();
        app.getNavigationHelper().insecurePage();
        app.getConfirmationPayment().confirmationPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
    @Test ( retryAnalyzer = Retry.class,
            description = "Оплата улуги МК")

    public void C6003()throws InterruptedException{
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        app.getRefillablePhoneNumber().fillPhoneNumber("9032582115");
        app.getNavigationHelper().chanceSourcePaymentOnMK();
        app.getSourcePaymentData().setAmountPay("555");
        app.getSourcePaymentData().fillPhoneNumberFromYouPay("9253703998");
        app.getNavigationHelper().tickedCheckBox();
        app.getCapcha().getCaptchaCode();
        app.getNavigationHelper().clickPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
    @Test ( retryAnalyzer = Retry.class,
            description = "Перевод на карту - VISA")

    public void C6004()throws InterruptedException{
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/8003");
        app.getRefillablePhoneNumber().fillCardNumber("4111111111111111");
        app.getSourcePaymentData().setAmountPay("555");
        app.getCapcha().getCaptchaCode();
        app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582114");
        app.getNavigationHelper().tickedCheckBoxAgregator();
        app.getNavigationHelper().clickPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
    @Test ( retryAnalyzer = Retry.class,
            description = "Перевод на карту - Maestro")

    public void C6005()throws InterruptedException{
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/8003");
        app.getRefillablePhoneNumber().fillCardNumber("639002000000000003");
        app.getSourcePaymentData().setAmountPay("555");
        app.getCapcha().getCaptchaCode();
        app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582114");
        app.getNavigationHelper().tickedCheckBoxAgregator();
        app.getNavigationHelper().clickPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
    @Test ( retryAnalyzer = Retry.class,
            description = "Перевод на карту - MasterCard")

    public void C6006()throws InterruptedException{
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/8003");
        app.getRefillablePhoneNumber().fillCardNumber("5100000000000008");
        app.getSourcePaymentData().setAmountPay("555");
        app.getCapcha().getCaptchaCode();
        app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582114");
        app.getNavigationHelper().tickedCheckBoxAgregator();
        app.getNavigationHelper().clickPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
    @Test ( retryAnalyzer = Retry.class,
            description = "Перевод на карту - MIR")

    public void C6007()throws InterruptedException{
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/8003");
        app.getRefillablePhoneNumber().fillCardNumber("2200000000000004");
        app.getSourcePaymentData().setAmountPay("555");
        app.getCapcha().getCaptchaCode();
        app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582114");
        app.getNavigationHelper().tickedCheckBoxAgregator();
        app.getNavigationHelper().clickPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
    @Test ( retryAnalyzer = Retry.class,
            description = "Оплата МК с аригатора - билайн")
    public void C6008()throws InterruptedException{
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9466");
        app.getRefillablePhoneNumber().fillPhoneNumberAgregator("9032582115");
        app.getSourcePaymentData().setAmountPay("555");
        app.getCapcha().getCaptchaCode();
        app.getSourcePaymentData().fillPhoneNumberFromYouPay("9009455201");
        app.getNavigationHelper().tickedCheckBoxAgregator();
        app.getNavigationHelper().clickPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
    @Test ( retryAnalyzer = Retry.class,
            description = "Оплата МК с аригатора - МТС")
    public void C6009()throws InterruptedException{
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9466");
        app.getRefillablePhoneNumber().fillPhoneNumberAgregator("9164253698");
        app.getSourcePaymentData().setAmountPay("555");
        app.getCapcha().getCaptchaCode();
        app.getSourcePaymentData().fillPhoneNumberFromYouPay("9162589632");
        app.getNavigationHelper().tickedCheckBoxAgregator();
        app.getNavigationHelper().clickPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
    @Test ( retryAnalyzer = Retry.class,
            description = "Оплата МК с аригатора - Мегафон")
    public void C6010()throws InterruptedException{
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9466");
        app.getRefillablePhoneNumber().fillPhoneNumberAgregator("9253789632");
        app.getSourcePaymentData().setAmountPay("555");
        app.getCapcha().getCaptchaCode();
        app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582114");
        app.getNavigationHelper().tickedCheckBoxAgregator();
        app.getNavigationHelper().clickPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
    @Test ( retryAnalyzer = Retry.class,
            description = "Оплата МК с аригатора - Теле2")
    public void C6011()throws InterruptedException{
        app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9466");
        app.getRefillablePhoneNumber().fillPhoneNumberAgregator("9253703998");
        app.getSourcePaymentData().setAmountPay("555");
        app.getCapcha().getCaptchaCode();
        app.getSourcePaymentData().fillPhoneNumberFromYouPay("9253789632");
        app.getNavigationHelper().tickedCheckBoxAgregator();
        app.getNavigationHelper().clickPayButton();
        app.getWaitingEndOfPayment().takeTransactionId();
    }
}

