package SeleniumFramework.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SeleniumFramework.resources.ExtentReporterNG1;

public class Listeners extends BaseTest implements ITestListener{
 
	WebDriver driver;
	ExtentReports extent = ExtentReporterNG1.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
		public void onTestStart(ITestResult result) {
			// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		}
 
 @Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Passed");
	}
 
 @Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
	//Need to call below method to get the reason of failed method mandatory	
	 extentTest.get().fail(result.getThrowable());
	 
	 //Screenshot, attach to report
	 try {
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 String filePath = null;
	 try {
		filePath = getScreenshot1(result.getMethod().getMethodName(),driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	 
	 
	}
 
 
 @Override
public void onFinish(ITestContext context) {
	// TODO Auto-generated method stub
   extent.flush();
}
// @Override
//	public void onTestSkipped(ITestResult result) {
//		// TODO Auto-generated method stub
//		ITestListener.super.onTestSkipped(result);
//	}
 
}
