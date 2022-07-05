package Tests;

import AppManager.ApplicationMeneger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    protected final ApplicationMeneger app = new ApplicationMeneger();

    @BeforeMethod
    public void setUp() {
        app.startTest();
    }
    @AfterMethod
    public void tearDown() {
        app.stopTest();
    }


}
