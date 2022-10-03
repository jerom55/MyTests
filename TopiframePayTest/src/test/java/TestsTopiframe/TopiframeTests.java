package TestsTopiframe;


import Topiframe.AppManager.Retry;
import Topiframe.TestBase.TestBase;
import org.testng.annotations.Test;

public class TopiframeTests extends TestBase {

  @Test(retryAnalyzer = Retry.class,
        description = "Payment for services from BC without 3ds")

  public void C6000() throws InterruptedException {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
    app.getRefillableNumber().fillPhoneNumber("9032582114");
    app.getNavigationHelper().chanceSourcePaymentOnBK();
    app.getSourcePaymentData().setAmountPay("555");
    app.getSourcePaymentData().fillPaymentDDK("2200 0000 0000 0004", "12/24", "SERGEI IVANOV", "123");
    app.getSourcePaymentData().amountCommission();
    app.getNavigationHelper().chequeEmail("s.ivanov@ruru.ru");
    app.getNavigationHelper().tickedCheckBox();
    app.getCapcha().getCaptchaCode();
    app.getNavigationHelper().clickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Payment for services from BC c 3dsV1")

  public void C6001() throws InterruptedException {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
    app.getRefillableNumber().fillPhoneNumber("9032582115");
    app.getNavigationHelper().chanceSourcePaymentOnBK();
    app.getSourcePaymentData().setAmountPay("555");
    app.getSourcePaymentData().fillPaymentDDK("6390 0200 0000 0000 03", "12/24", "SERGEI IVANOV", "123");
    app.getSourcePaymentData().amountCommission();
    app.getNavigationHelper().chequeEmail("s.ivanov@ruru.ru");
    app.getNavigationHelper().tickedCheckBox();
    app.getCapcha().getCaptchaCode();
    app.getNavigationHelper().clickPayButton();
    app.getConfirmationPayment().confirmationPayCode();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Payment for services from BC c 3dsV2")

  public void C6002() throws InterruptedException {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
    app.getRefillableNumber().fillPhoneNumber("9032582116");
    app.getNavigationHelper().chanceSourcePaymentOnBK();
    app.getSourcePaymentData().setAmountPay("555");
    app.getSourcePaymentData().fillPaymentDDK("5555 5555 5555 5599", "12/24", "SERGEI IVANOV", "123");
    app.getSourcePaymentData().amountCommission();
    app.getNavigationHelper().chequeEmail("s.ivanov@ruru.ru");
    app.getNavigationHelper().tickedCheckBox();
    app.getCapcha().getCaptchaCode();
    app.getNavigationHelper().clickPayButton();
    app.getNavigationHelper().insecurePage();
    app.getConfirmationPayment().confirmationPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Payment for services MK")

  public void C6003() throws InterruptedException {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
    app.getRefillableNumber().fillPhoneNumber("9032582117");
    app.getNavigationHelper().chanceSourcePaymentOnMK();
    app.getSourcePaymentData().setAmountPay("555");
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582114");
    app.getSourcePaymentData().amountCommission();
    app.getNavigationHelper().chequeEmail("s.ivanov@ruru.ru");
    app.getNavigationHelper().tickedCheckBox();
    app.getCapcha().getCaptchaCode();
    app.getNavigationHelper().clickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Transfer to card - VISA")

  public void C6004() throws InterruptedException {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/8003");
    app.getRefillableNumber().fillCardNumber("4111 1111 1111 1111");
    app.getSourcePaymentData().setAmountPay("555");
    app.getCapcha().getCaptchaCode();
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582114");
    app.getSourcePaymentData().amountCommission();
    app.getNavigationHelper().tickedCheckBoxAgregator();
    app.getNavigationHelper().clickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Transfer to card - Maestro")

  public void C6005() throws InterruptedException {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/8003");
    app.getRefillableNumber().fillCardNumber("6390 0200 0000 0000 03");
    app.getSourcePaymentData().setAmountPay("555");
    app.getCapcha().getCaptchaCode();
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582115");
    app.getSourcePaymentData().amountCommission();
    app.getNavigationHelper().tickedCheckBoxAgregator();
    app.getNavigationHelper().clickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Transfer to card - MasterCard")

  public void C6006() throws InterruptedException {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/8003");
    app.getRefillableNumber().fillCardNumber("5100 0000 0000 0008");
    app.getSourcePaymentData().setAmountPay("555");
    app.getCapcha().getCaptchaCode();
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582116");
    app.getSourcePaymentData().amountCommission();
    app.getNavigationHelper().tickedCheckBoxAgregator();
    app.getNavigationHelper().clickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Transfer to card - MIR")

  public void C6007() throws InterruptedException {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/8003");
    app.getRefillableNumber().fillCardNumber("2200 0000 0000 0004");
    app.getSourcePaymentData().setAmountPay("555");
    app.getCapcha().getCaptchaCode();
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582117");
    app.getSourcePaymentData().amountCommission();
    app.getNavigationHelper().tickedCheckBoxAgregator();
    app.getNavigationHelper().clickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Payment for MK from agrigator - beeline")

  public void C6008() throws InterruptedException {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9466");
    app.getRefillableNumber().fillPhoneNumberAgregator("9032582115");
    app.getSourcePaymentData().setAmountPay("555");
    app.getCapcha().getCaptchaCode();
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9009455201");
    app.getSourcePaymentData().amountCommission();
    app.getNavigationHelper().tickedCheckBoxAgregator();
    app.getNavigationHelper().clickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Payment for MK from agrigator - MTS")

  public void C6009() throws InterruptedException {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9466");
    app.getRefillableNumber().fillPhoneNumberAgregator("9164253698");
    app.getSourcePaymentData().setAmountPay("555");
    app.getCapcha().getCaptchaCode();
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9162589632");
    app.getSourcePaymentData().amountCommission();
    app.getNavigationHelper().tickedCheckBoxAgregator();
    app.getNavigationHelper().clickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Payment for MK from agrigator - Megafon")

  public void C6010() throws InterruptedException {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9466");
    app.getRefillableNumber().fillPhoneNumberAgregator("9253789632");
    app.getSourcePaymentData().setAmountPay("555");
    app.getCapcha().getCaptchaCode();
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9032582114");
    app.getSourcePaymentData().amountCommission();
    app.getNavigationHelper().tickedCheckBoxAgregator();
    app.getNavigationHelper().clickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }

  @Test(retryAnalyzer = Retry.class,
        description = "Payment for MK from agrigator - Tele2")

  public void C6011() throws InterruptedException {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9466");
    app.getRefillableNumber().fillPhoneNumberAgregator("9253703998");
    app.getSourcePaymentData().setAmountPay("555");
    app.getCapcha().getCaptchaCode();
    app.getSourcePaymentData().fillPhoneNumberFromYouPay("9253789632");
    app.getSourcePaymentData().amountCommission();
    app.getNavigationHelper().tickedCheckBoxAgregator();
    app.getNavigationHelper().clickPayButton();
    app.getWaitingEndOfPayment().takeTransactionId();
  }
  @Test(description = "Validation a refillable phone number field")

  public void C5000() throws InterruptedException {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
    app.getRefillableNumber().fillPhoneLetters("TEST");
    app.getRefillableNumber().fillPhoneNumber("9032582114");
  }

  @Test(description = "Validation a source of money field BK ")

  public void C5001() throws InterruptedException {
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
    app.getNavigationHelper().chanceSourcePaymentOnBK();
    app.getSourcePaymentData().fillPaymentDDKLetters("panTest", "expTest", "12345", "CVV");
    app.getSourcePaymentData().fillPaymentDDK("5555 5555 5555 5599", "12/24", "SERGEI IVANOV", "123");
  }
  @Test(description = "Validation the field price ")

  public void C5002() throws InterruptedException{
    app.getNavigationHelper().goGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
    app.getSourcePaymentData().setOverAmountPay("20000");
    app.getSourcePaymentData().setAmountPay("555");
  }
}

