package Topiframe.AppManager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;

public class ApplicationManager {
    private final Retry retry = new Retry();
    WebDriver wd;

    private WaitingEndOfPayment waitingEndOfPayment;
    private RefillableNumber refillablePhoneNumber;
    private  NavigationHelper navigationHelper ;
    private  SourcePaymentData sourcePaymentData ;
    private Capcha capcha;
    private ConfirmationPayment confirmationPayment ;

    public void startTest() {
        System.setProperty("webdriver.chrome.driver", "F:\\AutoTest\\chrome\\chromedriver.exe");
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        confirmationPayment = new ConfirmationPayment(wd);
        capcha = new Capcha(wd);
        sourcePaymentData = new SourcePaymentData(wd);
        navigationHelper = new NavigationHelper(wd);
        refillablePhoneNumber = new RefillableNumber(wd);
        waitingEndOfPayment = new WaitingEndOfPayment(wd);
    }
    public void stopTest() {
        wd.quit();
    }
    public ConfirmationPayment getConfirmationPayment() {
        return confirmationPayment;
    }
    public Capcha getCapcha() {
        return capcha;
    }
    public SourcePaymentData getSourcePaymentData() {
        return sourcePaymentData;
    }
    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
    public RefillableNumber getRefillablePhoneNumber() {
        return refillablePhoneNumber;
    }
    public WaitingEndOfPayment getWaitingEndOfPayment() {
        return waitingEndOfPayment;
    }

    public Retry getRetry() {
        return retry;
    }
}
