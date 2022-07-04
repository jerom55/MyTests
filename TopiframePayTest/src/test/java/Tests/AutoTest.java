package Tests;

import org.testng.annotations.Test;

public class AutoTest extends TestBase {

    @Test
    public void C65990() throws InterruptedException {
        //Оплата услуги с БК без 3ds
        getGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        String window = wd.getWindowHandle();
        fillPhoneNumber("9032582114");
        chanceSourcePayment();
        fillPaymentDetails("2200000000000004", "1224", "SERGEI IVANOV", "123");
        setAmountPay("500");
        tickedCheckBox();
        String text = getCapchaCode();
        wd.switchTo().window(window);
        inputCapchCode(text);
        clickPayButton();
        waitingEndPayment();
    }

    @Test
    public void C65991() throws InterruptedException {
        //Оплата услуги с БК c 3dsV1
        getGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        String window = wd.getWindowHandle();
        fillPhoneNumber("9032582115");
        chanceSourcePayment();
        fillPaymentDetails("639002000000000003", "1224", "SERGEI IVANOV", "123");
        setAmountPay("555");
        tickedCheckBox();
        String text = getCapchaCode();
        wd.switchTo().window(window);
        inputCapchCode(text);
        clickPayButton();
        confirmationPay();
        waitingEndPayment();
    }
    @Test
    public void C65992()throws InterruptedException {
        getGoodsUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
        
    }
}

