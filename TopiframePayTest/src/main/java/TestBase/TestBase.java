package TestBase;

import AppManager.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;


public class TestBase {
    public static Logger LOG = LoggerFactory.getLogger(TestBase.class);
    protected final ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setUp() {
        app.startTest();
    }
    @AfterSuite (alwaysRun = true)
    public void tearDown() {
        app.stopTest();
    }

    @BeforeMethod
    public void startTest (Method m){
        LOG.info("Start test " + m.getName());
    }
    @AfterMethod (alwaysRun = true)
    public void stopTest (Method m){
        LOG.info("Stop test " + m.getName());
    }
}
