package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import testBase.ExtentReportUtils;

public class ReportHooks  
{
    
	public static ExtentReportUtils extentReportUtils = new ExtentReportUtils();
	
	@Before
	public void createTest()
	{
		extentReportUtils.AttachReport("New Test");
	}
	
	
	@After
	public void FlushReport()
	{
		ExtentReportUtils.closeReport();
	}

	
}

