package testBase;

import org.openqa.selenium.WebDriver;

public class Webdriver 
{
	private Webdriver(){}
	
	public static Webdriver getWebdriver() {
		return webdriver;
	}

	private static Webdriver webdriver = new Webdriver();
	
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public WebDriver getDriver()
	{
		return driver.get();
	}
	
	public void setDriver(WebDriver webdriver)
	{
		driver.set(webdriver);
	}
	
	public void closeDriver()
	{
		driver.get().close();
		driver.remove();
	}
}
