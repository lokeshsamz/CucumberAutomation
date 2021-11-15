package pages;

import java.util.List;
import org.openqa.selenium.By;
import Enums.Enums.SelectBy;
import Enums.Enums.TimeOutInSeconds;
import ObjectRepository.IElementRepo;
import testBase.BrowserUtil;
import testBase.ExtentReportUtils;

public class MoviesPage{
	
	private BrowserUtil browserUtils;
	private ExtentReportUtils extentReportUtils;
	private By locator;
	
	public MoviesPage(BrowserUtil browserUtils, ExtentReportUtils extentReportUtils) 
	{
		this.browserUtils = browserUtils;
		this.extentReportUtils = extentReportUtils;
	}
	
	public List<String> GetAvailableDates()
	{
		List<String> availableDates = null;
		try 
		{
			locator = IElementRepo.tab_MoviePage_AvailableDates;
			this.browserUtils.WaitForElement(locator, TimeOutInSeconds.TWENTY);
			browserUtils.MoveToElement(locator);
			availableDates = browserUtils.GetTextFromElements(locator);
			extentReportUtils.NodePassed("I retrieve the Available Dates", false, String.join(", ", availableDates));
		}
		catch(Exception e)
		{
			extentReportUtils.NodeFailed("Failed to retrieve the Available Dates", e);
		}
		
		return availableDates;
	}

	public List<String> GetAvailableTheatres()
	{
		List<String> availableTheatres = null;
		try 
		{
			locator = IElementRepo.Txt_MoviePage_AvailableTheatres;
			this.browserUtils.WaitForElement(locator, TimeOutInSeconds.TWENTY);
			browserUtils.MoveToElement(locator);
			availableTheatres = browserUtils.GetTextFromElements(locator);
			extentReportUtils.NodePassed("I retrieve the Available Theatres", false, String.join(", ", availableTheatres));
		}
		catch(Exception e)
		{
			extentReportUtils.NodeFailed("Failed to retrieve the Available Theatres", e);
		}
		
		return availableTheatres;
	}
	
	public List<String> GetAvailableTheatreTimingDetails(String theatreName)
	{
		List<String> availableTheatreTimings = null;
		try 
		{
			locator = By.xpath(IElementRepo.Txt_MoviePage_AvailableTheatreTimingDetails.replace("M_TheatreName", theatreName));
			this.browserUtils.WaitForElement(locator, TimeOutInSeconds.TWENTY);
			this.browserUtils.MoveToElement(locator);
			availableTheatreTimings = browserUtils.GetTextFromElements(locator);
			extentReportUtils.NodePassed("I retrieve the Available timings of '" + theatreName + "'", true, String.join(", ", availableTheatreTimings));
		}
		catch(Exception e)
		{
			extentReportUtils.NodeFailed("Failed to retrieve the Available timings of '" + theatreName + "'", e);
		}
		
		return availableTheatreTimings;
		
	}
	
	public void SelectFromAmenities(String text, boolean moveToElement) 
	{
		try 
		{
			locator = IElementRepo.Dd_MoviePage_Amenities;
			this.browserUtils.WaitForElement(locator, TimeOutInSeconds.TWENTY, TimeOutInSeconds.FIVE);
			browserUtils.SelectFromDropdown(SelectBy.VISIBLETEXT, locator, text, moveToElement);
			extentReportUtils.NodePassed("I selected '" + text + "' from Amenities dropdown", true, text);
		} 
		catch (Exception e) 
		{
			extentReportUtils.NodeFailed("Failed to select " + text + "' from Amenities dropdown", e);
			e.printStackTrace();
		}
	}
	
	public void SelectFromExperience(String text, boolean moveToElement) 
	{
		try 
		{
			locator = IElementRepo.Dd_MoviePage_Experience;
			this.browserUtils.WaitForElement(locator, TimeOutInSeconds.TWENTY, TimeOutInSeconds.FIVE);
			this.browserUtils.SelectFromDropdown(SelectBy.VISIBLETEXT, locator, text, moveToElement);
			extentReportUtils.NodePassed("I selected '" + text + "' from Experience dropdown", true, text);
		} 
		catch (Exception e) 
		{
			extentReportUtils.NodeFailed("Failed to select " + text + "' from Experience dropdown", e);
			e.printStackTrace();
		}
	}
	
	public void ClickTheatreTime(String theatreName, String time)
	{
		try 
		{
			locator = By.xpath(IElementRepo.Txt_MoviePage_TheatreTime.replace("M_TheatreName", theatreName).replace("M_Time", time));
			this.browserUtils.WaitForElement(locator, TimeOutInSeconds.TWENTY, TimeOutInSeconds.FIVE);
			browserUtils.ClickElement(locator);
			extentReportUtils.NodePassed("I clicked " + time + " of '" + theatreName + "'", false, "Selected - " + time);
		}
		catch(Exception e)
		{
			extentReportUtils.NodeFailed("Failed to click " + time + " of '" + theatreName + "'", e);
		}
	}
}
