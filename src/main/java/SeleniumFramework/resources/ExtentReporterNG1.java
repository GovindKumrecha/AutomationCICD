package SeleniumFramework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG1 {

	public static ExtentReports getReportObject() {

		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter =  new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Test ReportG1");
		reporter.config().setDocumentTitle("TEST REPORT");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester12", "Govind Kumrecha");
		extent.createTest(path);
		return extent;
	}
}
