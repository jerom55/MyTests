package AppManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApplicationManager {
    WebDriver wd;
    private WaitingEndOfPayment waitingEndOfPayment;
    private RefillablePhoneNumber refillablePhoneNumber;
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
        refillablePhoneNumber = new RefillablePhoneNumber(wd);
        waitingEndOfPayment = new WaitingEndOfPayment(wd);
    }
    public void stopTest() {
        wd.quit();
    }
    public ConfirmationPayment getConfirmationPayment() {
        return confirmationPayment;
    }
    public AppManager.Capcha getCapcha() {
        return capcha;
    }
    public SourcePaymentData getSourcePaymentData() {
        return sourcePaymentData;
    }
    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
    public RefillablePhoneNumber getRefillablePhoneNumber() {
        return refillablePhoneNumber;
    }
    public WaitingEndOfPayment getWaitingEndOfPayment() {
        return waitingEndOfPayment;
    }
}
