package StepDefinitions;

import java.util.ArrayList;
import java.util.List;
import Hooks.Log;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.MoviesPage;
import pages.SeatSelectionsPage;
import testBase.BrowserUtil;
import testBase.ExtentReportUtils;


public class TicketBookingSteps
{	
	private BrowserUtil browserUtils = new BrowserUtil();
	private HomePage homePage;
	private ExtentReportUtils extentReportUtils;
	private MoviesPage moviePage;
	private SeatSelectionsPage seatSelectionsPage;
	
	public TicketBookingSteps() 
	{
		extentReportUtils = new ExtentReportUtils();
		homePage = new HomePage(browserUtils);
		moviePage = new MoviesPage(browserUtils, extentReportUtils);
		seatSelectionsPage = new SeatSelectionsPage(browserUtils, extentReportUtils);
	}

	@Given("I login to TicketsNew website")
	public void i_login_to_tickets_new_website() throws InterruptedException 
	{
		this.browserUtils.MaximizeScreen();
		Thread.sleep(2000);
		this.homePage.LaunchHomePage("https://www.ticketnew.com/online-advance-booking/Movies/C/Chennai");
		Thread.sleep(3000);
	}

	@Then("To verify the page proceeded to selected movie")
	public void to_verify_the_page_proceeded_to_selected_movie() 
	{
		Log.Info("Navigated to selected movie");
	}

	@When("I retrieve the available threatre names")
	public void i_retrieve_the_available_threatre_names() 
	{
		List<String> theatres = new ArrayList<String>();
		Log.Info("Available Theatres : ");
		theatres = moviePage.GetAvailableTheatres();
		theatres.removeIf(emp -> emp.isEmpty() || emp.isBlank());
		theatres.forEach(x -> Log.Info(x));
	}

	@When("I go to {string} tab and select the movie name {string}")
	public void i_go_to_tab_and_select_the_movie_name(String tab, String movieName) throws InterruptedException 
	{
		this.homePage.ClickMovieName(movieName);
		Thread.sleep(3000);
	}

	@When("I select the amenities as {string}")
	public void i_select_the(String amenities) throws InterruptedException 
	{
		moviePage.SelectFromAmenities(amenities, false);
		Thread.sleep(3000);
	}

	@When("I select the movie date as {string}")
	public void i_select_the_movie_date_as(String date) 
	{
		Log.Info("Available dates : ");
		moviePage.GetAvailableDates().forEach(x -> System.out.println(x));
	}

}
