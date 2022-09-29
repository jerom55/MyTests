package Topiframe.AppManager;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int actualRetry = 0;
    private static final int maxRetry = 2;

    public boolean retry (ITestResult result){
        if (actualRetry < maxRetry) {
            actualRetry ++;
            return true;
        } else {
            return false;
        }
    }

}
