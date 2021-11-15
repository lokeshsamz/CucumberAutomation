package ObjectRepository;

import org.openqa.selenium.By;

public interface IElementRepo {

	//Home Page
	By tab_HomePage_Movies = By.id("href_movies");
	String Txt_HomePage_MovieName = "//h5/following-sibling::ul/child::li/a[text()= 'M_MovieName']/parent::li/a";
	
	//Movie Page
	By tab_MoviePage_AvailableDates = By.xpath("//*[@id = 'ulShowDate']/li/a/span[@class = 'date']");
	By Txt_MoviePage_AvailableTheatres = By.xpath("//*[@id = 'divTheatreList']//div[@class='tn-entity-details']/h5");
	String Txt_MoviePage_AvailableTheatreTimingDetails = "//h5[contains(text(),'M_TheatreName')]/parent::div/following-sibling::div//a";
	By Dd_MoviePage_Amenities = By.id("ddlAmenities");
	By Dd_MoviePage_Experience = By.xpath("//select[@id = 'ddlExperience']");
	String Txt_MoviePage_TheatreTime = "//h5[contains(text(),'M_TheatreName')]/parent::div/following-sibling::div//a[text() = 'M_Time']";
	
	//Seat Selection page
	By Txt_SeatSelectionPage_AvailableSeatNames = By.xpath("//div[@class='tn-class-grid']//tr//td[contains(@class,'available')]");
	String Icon_SeatSelectionPage_AvailableSeat = "//div[@class='tn-class-grid']//tr//td[contains(@class,'available') and @data-seatname = 'M_SeatName']";
	By Btn_SeatSelectionPage_Continue = By.xpath("//span[text() = 'Continue']/parent::div");
	By Btn_SeatSelectionPage_Cancel = By.xpath("//span[text() = 'Cancel']/parent::div");
	
	
}
