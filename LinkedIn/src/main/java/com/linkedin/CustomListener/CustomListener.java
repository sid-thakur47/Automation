/**********************************************************
 * @Purpose: To take Screenshots of test result
 * @Author: Siddhesh Thakur
 * @Date: 26/06/2020
 **********************************************************/

package com.linkedin.CustomListener;

import com.linkedin.base.LinkedInBase;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener extends LinkedInBase implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        takeScreenShot("TestPass");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenShot("TestFail");
    }

    @Override
    public void onFinish(ITestContext context) {
        takeScreenShot("TestFinish");
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        takeScreenShot("TestSkipped");
    }
}
