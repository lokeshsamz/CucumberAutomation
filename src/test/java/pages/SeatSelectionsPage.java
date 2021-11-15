package pages;

import java.util.List;

import org.openqa.selenium.By;

import Enums.Enums.TimeOutInSeconds;
import ObjectRepository.IElementRepo;
import testBase.BrowserUtil;
import testBase.ExtentReportUtils;

public class SeatSelectionsPage extends ExtentReportUtils{

	private BrowserUtil browserUtils;
	private By locator;
	private ExtentReportUtils extentReportUtils;
	
	public SeatSelectionsPage(BrowserUtil browserUtils, ExtentReportUtils extentReportUtils)
	{
		this.browserUtils = browserUtils;
		this.extentReportUtils = extentReportUtils;
	}

	public List<String> GetAvailableSeats()
	{
		List<String> availableSeats = null;
		try 
		{
			locator = IElementRepo.Txt_SeatSelectionPage_AvailableSeatNames;
			this.browserUtils.WaitForElement(locator, TimeOutInSeconds.TWENTY, TimeOutInSeconds.FIVE);
			availableSeats = browserUtils.GetAttributeValueFromElements(locator, "data-seatname");
			extentReportUtils.NodePassed("I retrieve the Available Seats", true, String.join(", ", availableSeats));
		}
		catch(Exception e)
		{
			extentReportUtils.NodeFailed("Failed to retrieve the Available Seats", e);
		}
		
		return availableSeats;
	}
	
	public void ClickSeats(List<String> seatNames)
	{
		try 
		{
			seatNames.forEach(seat -> 
			{
				locator = By.xpath(IElementRepo.Icon_SeatSelectionPage_AvailableSeat.replace("M_SeatName", seat.trim()));
				this.browserUtils.WaitForElement(locator, TimeOutInSeconds.TWENTY, TimeOutInSeconds.FIVE);
				this.browserUtils.MoveToElement(locator);
				this.browserUtils.ClickElement(locator);
			});
			extentReportUtils.NodePassed("I click on '" + String.join(",", seatNames) + "' seat(s)", true, String.join(",", seatNames));
		}
		catch(Exception e)
		{
			extentReportUtils.NodeFailed("Failed to click given seat(s)", e);
		}	
	}
	
	public void ClickContinueButton()
	{
		try 
		{
			locator = IElementRepo.Btn_SeatSelectionPage_Continue;
			this.browserUtils.WaitForElement(locator, TimeOutInSeconds.TWENTY, TimeOutInSeconds.FIVE);
			browserUtils.ClickElement(locator);
			extentReportUtils.NodePassed("I click on continue button", false, "clicked continue button");
		}
		catch(Exception e)
		{
			extentReportUtils.NodeFailed("Failed to click the continue button", e);
		}	
	}

}
