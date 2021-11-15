package testBase;

import org.openqa.selenium.WebDriver;

public class Page 
{	
	public static WebDriver driver = null;

	public void SelectBrowser(WebBrowser browser, String browserName)
	{
		try 
		{
			Webdriver.getWebdriver().setDriver(browser.CreateBrowserInstance(browserName));
			driver = Webdriver.getWebdriver().getDriver();
			System.out.println("launched chrome");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void CloseBrowser()
	{
		try 
		{
			Webdriver.getWebdriver().closeDriver();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
