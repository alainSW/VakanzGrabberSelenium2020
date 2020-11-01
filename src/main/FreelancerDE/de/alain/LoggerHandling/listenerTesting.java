package de.alain.LoggerHandling;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class listenerTesting implements ITestListener {

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

		/*
		 * if (arg0.getStatus() == ITestResult.FAILURE) {
		 * 
		 * // Screenshots betätigen String temp =
		 * Utility.getScreenshot(ExtentReportDemo.driver, arg0.getName());
		 * 
		 * try { ExtentReportDemo.logger.fail(arg0.getThrowable().getMessage(),
		 * MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		 * 
		 * ExtentReportDemo.logger.log(Status.FAIL, arg0.getThrowable());
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		// }
	}

	public void onTestSkipped(ITestResult arg0) {
		System.out.println("Test Skipped " + getMethodeName(arg0));
		Reporter.log("Test Skipped " + getMethodeName(arg0));

		/*
		 * if (arg0.getStatus() == ITestResult.SKIP) {
		 * 
		 * ExtentReportDemo.logger.log(Status.SKIP, "Test Skipped " +
		 * getMethodeName(arg0));
		 * 
		 * }
		 */

	}

	public void onTestStart(ITestResult arg0) {
		System.out.println("Starting test is " + getMethodeName(arg0));
		Reporter.log("Starting test is " + getMethodeName(arg0));

	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.out.println("Test passed " + getMethodeName(arg0));
		Reporter.log("Test passed " + getMethodeName(arg0));

		/*
		 * if (arg0.getStatus() == ITestResult.SUCCESS) {
		 * 
		 * // Screenshots betätigen String temp =
		 * Utility.getScreenshot(ExtentReportDemo.driver, arg0.getName()); //
		 * ExtentReportDemo.logger.pas);
		 * 
		 * ExtentReportDemo.logger.log(Status.PASS, "Assert Pass as condition is True");
		 * }
		 */
	}

	private static String getMethodeName(ITestResult arg0) {
		return arg0.getMethod().getConstructorOrMethod().getName();

	}

}
