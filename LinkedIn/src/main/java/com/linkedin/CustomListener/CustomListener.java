package com.linkedin.CustomListener;

import com.linkedin.base.LinkedInBase;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener extends LinkedInBase implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        takeScreenShot("pass");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenShot("fail");
    }

    @Override
    public void onFinish(ITestContext context) {
        takeScreenShot("finish");
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        takeScreenShot("skipped");
    }
}
