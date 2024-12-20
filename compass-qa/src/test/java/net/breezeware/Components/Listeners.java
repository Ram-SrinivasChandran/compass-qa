package net.breezeware.Components;

import java.io.IOException;

import net.breezeware.resources.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class Listeners extends BaseTest implements ITestListener {
    ExtentReports extentReports= ExtentReporterNG.getReportObject();

    ExtentTest test;

    ThreadLocal<ExtentTest> threadLocal=new ThreadLocal();
    @Override
    public void onTestStart(ITestResult result) {
        test =  extentReports.createTest(result.getMethod().getMethodName());
        threadLocal.set(test);
    }
    @Override
    public void onTestFailure(ITestResult result) {
//        test.log(Status.FAIL,"Test Failed");
//        test.log(Status.FAIL,"Test Failed");
        threadLocal.get().fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        }catch (Exception e){
            e.printStackTrace();
        }
        String filePath=null;
        try {
            filePath=  getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
        test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result){
        test.log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
//        ITestListener.super.onFinish(context);
    }



}
