package Topiframe.TestBase;

import Topiframe.AppManager.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;


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
        //LOG.info("Start test " + m.getName());
        String description = "";
        Test testAnnotation = m.getAnnotation(Test.class);
        if (testAnnotation != null) {
            description = testAnnotation.description();
        }
        LOG.info("Start test: " + m.getName() + ". " + description);
    }
    @AfterMethod (alwaysRun = true)
    public void stopTest (Method m){
        String description = "";
        Test testAnnotation = m.getAnnotation(Test.class);
        if (testAnnotation != null) {
            description = testAnnotation.description();
        }
        LOG.info("Stop test " + m.getName() + ". " + description);
    }
}
