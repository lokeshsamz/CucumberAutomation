package Hooks;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import testBase.WebBrowser;
import testBase.Webdriver;

public class WebHooks 
{
	WebBrowser browser = new WebBrowser();
	public static WebDriver driver = null;
	
	@Before
	public void LaunchBrowser() 
	{
		try 
		{
			Webdriver.getWebdriver().setDriver(browser.CreateBrowserInstance("chrome"));
			driver = Webdriver.getWebdriver().getDriver();
			System.out.println("Inside WebHooks");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@After
	public void CloseBrowser() 
	{
		Webdriver.getWebdriver().closeDriver();
	}
}
