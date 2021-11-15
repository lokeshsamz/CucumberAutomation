package testBase;

import com.aventstack.extentreports.ExtentTest;

public class ReportSettings 
{
	private ReportSettings() {}
	
	private static ReportSettings reportSettings = new ReportSettings();
	
	public static ReportSettings getInstance()
	{
		return reportSettings;
	}
	
	ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	
	public ExtentTest getTest()
	{
		return test.get();
	}
	
	public void setTest(ExtentTest testcase)
	{
		test.set(testcase);
	}
}
