package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.LoginPage;
import base.DriverFactory;
import utils.ConfigReader;
import utils.RetryAnalyzer;

import static org.testng.Assert.*;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
public void setup() {
    DriverFactory.initDriver();
    driver = DriverFactory.getDriver();
    driver.manage().window().maximize();

    loginPage = new LoginPage(driver);
    loginPage.open(ConfigReader.get("url"));
}

    @Test(retryAnalyzer = RetryAnalyzer.class)
public void validLoginTest() {
        loginPage.login("tomsmith", "SuperSecretPassword!");
        assertTrue(driver.getCurrentUrl().contains("secure"));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
public void invalidLoginTest() {
        loginPage.login("wrong", "wrong");
        assertTrue(loginPage.getErrorMessage().contains("invalid"));
    }

    @AfterMethod
public void tearDown() {
    DriverFactory.quitDriver();
}
}