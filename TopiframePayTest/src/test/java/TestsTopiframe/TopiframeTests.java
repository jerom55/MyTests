package TestsTopiframe;


import Topiframe.AppManager.Retry;
import Topiframe.TestBase.TestBase;
import org.testng.annotations.Test;


public class TopiframeTests extends TestBase {

  @Test(retryAnalyzer = Retry.class,
        description = "Оплата услуги банковской картой без 3ds")

  public void C6000() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
    app.getRefillableNumber().fillPhoneNumber("9032582114");
    app.getSourcePaymentData().setAmountPay("555");
    app.getSourcePaymentData().fillPaymentDDK("2200 0000 0000 0004", "12/24", "SERGEI IVANOV", "123");
    app.getNavigationHelper().chequeEmail("s.ivanov@ruru.ru");
    app.getCapcha().getCaptchaCode();
    app.getNavigationHelper().tickedCheckBoxClickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Оплата услуги банковской картой  с 3dsV1")

  public void C6001() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
    app.getRefillableNumber().fillPhoneNumber("9032582115");
    app.getSourcePaymentData().setAmountPay("555");
    app.getSourcePaymentData().fillPaymentDDK("6390 0200 0000 0000 03", "12/24", "SERGEI IVANOV", "123");
    app.getNavigationHelper().chequeEmail("s.ivanov@ruru.ru");
    app.getCapcha().getCaptchaCode();
    app.getNavigationHelper().tickedCheckBoxClickPayButton();
    app.getConfirmationPayment().confirmationPayCode();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Оплата услуги банковской картой с 3dsV2")

  public void C6002() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
    app.getRefillableNumber().fillPhoneNumber("9032582116");
    app.getSourcePaymentData().setAmountPay("555");
    app.getSourcePaymentData().fillPaymentDDK("5555 5555 5555 5599", "12/24", "SERGEI IVANOV", "123");
    app.getNavigationHelper().chequeEmail("s.ivanov@ruru.ru");
    app.getCapcha().getCaptchaCode();
    app.getNavigationHelper().tickedCheckBoxClickPayButton();
    app.getNavigationHelper().insecurePage();
    app.getConfirmationPayment().confirmationPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Оплата услуги с мобильной комерции")

  public void C6003() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
    app.getRefillableNumber().fillPhoneNumber("9032582117");
    app.getSourcePaymentData().setAmountPay("555");
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582114");
    app.getNavigationHelper().chequeEmail("s.ivanov@ruru.ru");
    app.getCapcha().getCaptchaCode();
    app.getNavigationHelper().tickedCheckBoxClickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Перевод на карту - VISA")

  public void C6004() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/8003");
    app.getRefillableNumber().fillCardNumber("4111 1111 1111 1111");
    app.getSourcePaymentData().setAmountPay("555");
    app.getCapcha().getCaptchaCode();
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582114");
    app.getNavigationHelper().tickedCheckBoxClickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Перевод на карту - Maestro")

  public void C6005() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/8003");
    app.getRefillableNumber().fillCardNumber("6390 0200 0000 0000 03");
    app.getSourcePaymentData().setAmountPay("555");
    app.getCapcha().getCaptchaCode();
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582115");
    app.getNavigationHelper().tickedCheckBoxClickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Перевод на карту - MasterCard")

  public void C6006() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/8003");
    app.getRefillableNumber().fillCardNumber("5555 5555 5555 5599");
    app.getSourcePaymentData().setAmountPay("555");
    app.getCapcha().getCaptchaCode();
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9167509053");
    app.getNavigationHelper().tickedCheckBoxClickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Перевод на карту - MIR")

  public void C6007() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/8003");
    app.getRefillableNumber().fillCardNumber("2200 0000 0000 0004");
    app.getSourcePaymentData().setAmountPay("555");
    app.getCapcha().getCaptchaCode();
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582117");
    app.getNavigationHelper().tickedCheckBoxClickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Платёж по МК с агригатора - beeline")

  public void C6008() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9466");
    app.getRefillableNumber().fillPhoneNumberAgregator("9032582115");
    app.getSourcePaymentData().setAmountPay("555");
    app.getCapcha().getCaptchaCode();
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9009455201");
    app.getNavigationHelper().tickedCheckBoxClickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Платёж по МК с агригатора - MTS")

  public void C6009() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9466");
    app.getRefillableNumber().fillPhoneNumberAgregator("9164253698");
    app.getSourcePaymentData().setAmountPay("555");
    app.getCapcha().getCaptchaCode();
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9162589632");
    app.getNavigationHelper().tickedCheckBoxClickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Платёж по МК с агригатора - Megafon")

  public void C6010() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9466");
    app.getRefillableNumber().fillPhoneNumberAgregator("9253789632");
    app.getSourcePaymentData().setAmountPay("555");
    app.getCapcha().getCaptchaCode();
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582114");
    app.getNavigationHelper().tickedCheckBoxClickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Платёж по МК с агригатора - Tele2")

  public void C6011() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9466");
    app.getRefillableNumber().fillPhoneNumberAgregator("9253703998");
    app.getSourcePaymentData().setAmountPay("555");
    app.getCapcha().getCaptchaCode();
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9253789632");
    app.getNavigationHelper().tickedCheckBoxClickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }
  @Test(description = "Валидация поля номер телефона")

  public void C5000() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
    app.getRefillableNumber().fillPhoneLetters("TEST");
    app.getRefillableNumber().fillPhoneNumber("9032582114");
  }

  @Test(description = "Валидация поля источника средств - БК")

  public void C5001() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
    app.getSourcePaymentData().fillPaymentDDKLetters("panTest", "expTest", "12345", "CVV");
    app.getSourcePaymentData().fillPaymentDDK("5555 5555 5555 5599", "12/24", "SERGEI IVANOV", "123");
  }
  @Test(description = "Валидация поля стоимость услуги")

  public void C5002() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
    app.getSourcePaymentData().setOverAmountPay("20000");
    app.getSourcePaymentData().setAmountPay("555");
  }
  @Test(description = "Валидация поля источника средств - МК")

  public void C5003() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
    app.getSourcePaymentData().fillSomPhoneLetters("TEST");
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582114");
  }
  @Test(description = "Проверка работы поисковой строки" )

  public void C5004() throws Exception {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru");
    app.getNavigationOnWebsite().navigateFromCatalogue();
    app.getNavigationOnWebsite().searchingLine("AutoTests");
    app.getNavigationOnWebsite().trySearchHiddenService("TELE2 TEST", "https://topiframe.nsc-tech.ru/init-payment/9465");
  }
}

