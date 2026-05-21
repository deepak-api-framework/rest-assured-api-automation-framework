package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExtentReporterNG;

public class Listeners implements ITestListener {

    ExtentReports extent = ExtentReporterNG.getReportObject();

    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

    	ExtentTest extentTest =
    	        extent.createTest(result.getMethod().getMethodName());

    	test.set(extentTest);

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

    }
}