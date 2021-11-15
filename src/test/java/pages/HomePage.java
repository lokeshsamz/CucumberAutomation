package pages;

import org.openqa.selenium.By;

import Enums.Enums.TimeOutInSeconds;
import Hooks.Log;
import ObjectRepository.IElementRepo;
import testBase.BrowserUtil;

public class HomePage{
	
	private BrowserUtil browserUtils;
	private By locator;

	public HomePage(BrowserUtil browserUtils)
	{
		this.browserUtils = browserUtils;
	}
	
	public void LaunchHomePage(String url)
	{
		try
		{
			this.browserUtils.NavigateToUrl(url);
			Log.Info("I have navigated to the home page");		
		}
		catch (Exception e)
		{
			Log.Error("Failed to navigate to the home page");
		}
	}
		
	public void ClickMovieName(String movieName)
	{
		try
		{
			locator = IElementRepo.tab_HomePage_Movies;
			this.browserUtils.WaitForElement(locator, TimeOutInSeconds.TWENTY, TimeOutInSeconds.FIVE);
			By locatorToClick = By.xpath(IElementRepo.Txt_HomePage_MovieName.replace("M_MovieName", movieName));
			this.browserUtils.MouseHoverAndClickElement(locator, locatorToClick);
			Log.Info("I clicked the Movie name '" + movieName + "'");
		}
		catch (Exception e)
		{
			Log.Error("Failed to click the Movie name '" + movieName + "'");
		}
	}
	

}
