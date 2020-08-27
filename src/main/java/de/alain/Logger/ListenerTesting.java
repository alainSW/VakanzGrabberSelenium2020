package de.alain.Logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ListenerTesting implements ITestListener {

	public void onFinish(ITestContext arg0) {
		System.out.println("All test finished");
		Reporter.log("All test finished");

	}

	public void onStart(ITestContext arg0) {
		System.out.println("All test Execution started");
		Reporter.log("All test Execution started");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		System.out.println("Test is pass absed  on percentage " + getMethodeName(arg0));
		Reporter.log("Test is pass absed  on percentage " + getMethodeName(arg0));
	}

	public void onTestFailure(ITestResult arg0) {
		System.out.println("Test failed " + getMethodeName(arg0));
		Reporter.log("Test failed " + getMethodeName(arg0));

	}

	public void onTestSkipped(ITestResult arg0) {
		System.out.println("Test Skipped " + getMethodeName(arg0));
		Reporter.log("Test Skipped " + getMethodeName(arg0));

	}

	public void onTestStart(ITestResult arg0) {
		System.out.println("Starting test is " + getMethodeName(arg0));
		Reporter.log("Starting test is " + getMethodeName(arg0));

	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.out.println("Test passed " + getMethodeName(arg0));
		Reporter.log("Test passed " + getMethodeName(arg0));
	}

	private static String getMethodeName(ITestResult arg0) {
		return arg0.getMethod().getConstructorOrMethod().getName();

	}
}
