package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import utils.ConfigReader;
import org.testng.ITestResult;
import utils.ScreenshotUtil;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        loginPage.open(ConfigReader.get("url"));
    }

    @AfterMethod
public void tearDown(ITestResult result) {
    if (ITestResult.FAILURE == result.getStatus()) {
        ScreenshotUtil.captureScreenshot(driver, result.getName());
    }
    DriverFactory.quitDriver();
}
}