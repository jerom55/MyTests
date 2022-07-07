package Tests;

import AppManager.ApplicationManager;
import org.testng.annotations.*;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setUp() {
        app.startTest();
    }
    @AfterSuite
    public void tearDown() {
        app.stopTest();
    }

}
