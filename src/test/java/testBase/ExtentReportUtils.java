package testBase;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReportUtils{

	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
	static Object timestamp = sdf1.format(new Timestamp(System.currentTimeMillis()));
	private static ExtentReports extent;
	private ExtentTest test;
	private static String projectPath = System.getProperty("user.dir");
	static File reportDirectory = new File( projectPath + "/Reports/TestReport/" + timestamp );

	//Create New Directory and attach report.
	public void AttachReport(String testcase)
	{
		System.out.println(reportDirectory);
		if (!reportDirectory.exists()){
			reportDirectory.mkdirs();
		}
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(reportDirectory + "/index.html");
		spark.config().setReportName("Sample Extent Report");
		extent.attachReporter(spark);		
		 ReportSettings.getInstance().setTest(extent.createTest(testcase));
	}
	
	//Create New Test Node to the report.
	public ExtentTest CreateTestNode(String stepName)
	{	
		return ReportSettings.getInstance().getTest().createNode(stepName);		
	}

	//Add Screenshot to the created node.
	public ExtentTest AddScreenshotToNode(boolean takeScreenshot, String stepName)
	{
		ExtentTest node = CreateTestNode(stepName);
		if (takeScreenshot)
		{
			String image = BrowserUtil.TakeScreenshot();
			node.addScreenCaptureFromPath(image);
		}

		return node;
	}

	public void NodePassed(String stepName)
	{
		AddScreenshotToNode(true, stepName).pass(MarkupHelper.createLabel(stepName, ExtentColor.BLUE));
	}

	public void NodePassed(String stepName, boolean attachScreenshot)
	{
		AddScreenshotToNode(attachScreenshot, stepName).pass(MarkupHelper.createLabel(stepName, ExtentColor.BLUE));
	}

	public void NodePassed(String stepName, boolean attachScreenshot, String logs)
	{
		if(logs.isEmpty()) 
		{
			NodePassed(stepName,attachScreenshot);
		}
		else
		{
			AddScreenshotToNode(attachScreenshot, stepName).pass(MarkupHelper.createLabel(logs, ExtentColor.BLUE));
		}
	}

	public void NodeFailed(String stepName, Throwable e)
	{
		AddScreenshotToNode(true, stepName).fail(e);
	}


	public static void closeReport()
	{
		extent.flush();
	}

	public static String getMethodName(final int depth)
	{
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		System.out.println("Method Name: " + ste.toString());
		return ste[ste.length - 1 - depth].getMethodName();
	}
}
