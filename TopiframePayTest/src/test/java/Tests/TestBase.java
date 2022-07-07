package Tests;

import AppManager.ApplicationManager;
import org.testng.annotations.*;

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
