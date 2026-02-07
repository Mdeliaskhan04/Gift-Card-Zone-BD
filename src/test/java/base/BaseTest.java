package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected ExtentReports extents;
    protected ExtentTest test;

    @BeforeMethod
     public void setUp(Method method) {
        // Reporting
        playwright = Playwright.create();
        browser = playwright.firefox().launch(new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        page = browser.newPage();
    }

     @AfterMethod
     public void tearDown(Method method) {
         extents.flush();
         if (browser != null) browser.close();
         if (playwright != null) playwright.close();
     }
}
