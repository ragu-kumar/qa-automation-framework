package utils;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        ExtentManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = ExtentManager.getInstance().createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail("Test Failed");
        test.fail(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }
}