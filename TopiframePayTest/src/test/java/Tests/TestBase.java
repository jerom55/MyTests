package Tests;

import AppManager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeMethod
    public void setUp() {
        app.startTest();
    }
    @AfterMethod
    public void tearDown() {
        app.stopTest();
    }


}
