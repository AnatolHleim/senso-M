package controller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.ITestContext;

public class BaseTest {
    protected WebDriver driver;
    protected Logger log;

    @Parameters({"browser", "chromeProfile"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, ITestContext crx, @Optional String parameter) {
        String testName = crx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);
        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
        if (parameter != null) {
            driver = factory.createChromeWithProfile(parameter);
        } else {
            driver = factory.createDriver();
        }
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Close driver");
        driver.quit();
    }

}
