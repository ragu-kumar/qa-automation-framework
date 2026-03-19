package tests;

import org.testng.annotations.Test;
import base.BaseTest;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void validLoginTest() {
        loginPage.login("tomsmith", "SuperSecretPassword!");
        assertTrue(driver.getCurrentUrl().contains("secure"));
    }

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void invalidLoginTest() {
        loginPage.login("wrong", "wrong");

        String error = loginPage.getErrorMessage();
        System.out.println("ERROR MESSAGE: " + error);

        assertTrue(error.contains("invalid"));
    }
}