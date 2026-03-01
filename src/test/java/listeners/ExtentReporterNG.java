package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReporterNG implements ITestListener {

    private static ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        String reportPath = System.getProperty("user.dir") + "/target/extent-reports/extent-report.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail("Test Failed: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Leave other methods empty
    public void onTestSkipped(ITestResult result) {}
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    public void onTestFailedWithTimeout(ITestResult result) {}
}