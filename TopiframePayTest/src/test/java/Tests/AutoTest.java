package Tests;

import org.testng.annotations.Test;

public class AutoTest extends TestBase {

    @Test
    public void C65990() throws InterruptedException {
        //Оплата услуги с БК без 3ds
        getGoodUrl("https://topiframe.nsc-tech.ru/init-payment/9463");
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
    }

}

